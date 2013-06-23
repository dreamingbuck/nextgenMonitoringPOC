package edu.stanford.notify;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eyrie.remctl.client.RemctlClient;
import org.eyrie.remctl.client.RemctlResponse;
import org.eyrie.remctl.client.RemctlStatusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

/**
 * Responsible for interacting with the notify-qa system and parsing the results
 * 
 * @author pradtke
 * 
 */
// Component tells spring to make an instance of this class. An instance is called a bean.
@Component
public class NotifyQaManager {

    /** Our logging facility */
    private static final Logger logger = LoggerFactory.getLogger(NotifyQaManager.class);

    /** Date format used in the list and search commands. 2013-06-05 13:28:55 */
    protected static final String LIST_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /** Date format used in the list and search commands. 2013-06-05T13:28:57 */
    protected static final String DETAILS_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    /** Delimiter used to seperate message form metadata in the details command */
    private static final String DELIMITER = "================================\n?";

    /** What headers looks like in a message and the metadata */
    private static final Pattern HEADER_PATTERN = Pattern.compile("^(.*):\\s+(.*)");

    /**
     * Autowried tells spring to resolved this dependency. Spring will look for another bean (object instance) of the
     * same type
     */
    @Autowired
    RemctlClient notifyRemctlClient;

    /**
     * Search for messages with the given content
     * 
     * @param searchTerm
     *            The term to search for
     * 
     * @return Some info on matching messages
     */
    public List<MessageInfo> searchMessages(String searchTerm) {

        RemctlResponse response = this.notifyRemctlClient.execute("notify-qa", "search", searchTerm);
        // FIXME: wrtie tests
        return this.parseList(response.getStdOut());
    }

    /**
     * List the messages from the last two days
     * 
     * @return Some info on the messages
     */
    public List<MessageInfo> listMessages() {
        RemctlResponse response = this.notifyRemctlClient.execute("notify-qa", "list");

        return this.parseList(response.getStdOut());
    }

    /**
     * Parese the stdout from the list or search command
     * 
     * @param stdout
     *            the stdout
     * @return The parsed results
     */
    private List<MessageInfo> parseList(String stdout) {
        logger.debug("Parsing results");
        List<MessageInfo> results = new ArrayList<MessageInfo>();

        if (stdout.startsWith("No matching records")) {
            return results;
        }

        String[] lines = stdout.split("\n");
        for (String line : lines) {
            String[] columns = line.split("\\|");
            MessageInfo info = new MessageInfo();
            info.setId(Long.parseLong(columns[0].trim()));
            info.setDate(this.toDate(LIST_DATE_FORMAT, columns[1].trim()));
            info.setTemplate(columns[2].trim());

            results.add(info);
        }

        return results;
    }

    /**
     * Performs date parsing and converts checked exceptions to unchecked.
     * 
     * @param format
     *            The date format
     * @param input
     *            The input string to parse
     * @return The parsed date
     * @throws RuntimeException
     *             if there is a parsing error
     */
    private Date toDate(String format, String input) {
        // remember that SimpleDateFormat isn't thread safe so it can't be a field in the object.
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.parse(input);
        } catch (ParseException e) {
            String msg = "Unable to parse date " + input + " with format " + format;
            logger.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }

    /**
     * Load the given message from notify-qa
     * 
     * @param id
     *            The message id
     * @return The message details
     * @throws ResourceNotFoundException
     *             if there is no message for the id
     */
    public MessageDetails loadMessage(long id) {

        MessageDetails details = new MessageDetails();
        RemctlResponse response = this.notifyRemctlClient.executeAllowAnyStatus("notify-qa", "details",
                String.valueOf(id));
        // check for errors
        if (!response.getStatus().equals(0)) {
            if (response.getStdErr().contains("could not find record")) {
                throw new ResourceNotFoundException(response.getStdErr());
            } else {
                throw new RemctlStatusException(response);
            }
        }
        String[] components = response.getStdOut().split(DELIMITER);

        /*
         * Load headers
         */
        String[] headers = components[0].split("\\n");
        for (String header : headers) {
            Matcher matcher = HEADER_PATTERN.matcher(header);
            if (!matcher.matches()) {
                continue;
            }
            String key = matcher.group(1);
            String value = matcher.group(2).trim();
            if (key.equalsIgnoreCase("id")) {
                details.setId(Long.parseLong(value));
            } else if (key.equalsIgnoreCase("message")) {
                details.setTemplate(value);
            } else if (key.equalsIgnoreCase("at")) {
                details.setAt(this.toDate(DETAILS_DATE_FORMAT, value));
            } else if (key.equalsIgnoreCase("from")) {
                details.setCreator(value);
            }
        }

        details.setRawMessageText(components[1].trim());
        /*
         * Load Message
         */
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        details.setMessage(mailMessage);
        // A blank line seperates message headers from body - so we look for two returns
        String[] msgComponents = components[1].split("\\n\\n", 2);
        mailMessage.setText(msgComponents[1].trim());
        headers = msgComponents[0].split("\\n");
        for (String header : headers) {
            Matcher matcher = HEADER_PATTERN.matcher(header);
            if (!matcher.matches()) {
                continue;
            }
            String key = matcher.group(1);
            String value = matcher.group(2);
            // TODO: add support for bcc, cc, etc
            if (key.equalsIgnoreCase("to")) {
                // TODO: add support for multiple to addreses
                mailMessage.setTo(value);
            } else if (key.equalsIgnoreCase("from")) {
                mailMessage.setFrom(value);
            } else if (key.equalsIgnoreCase("subject")) {
                mailMessage.setSubject(value);
            }
        }

        return details;

    }
}
