package edu.stanford.notify;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.eyrie.remctl.client.RemctlClient;
import org.eyrie.remctl.client.RemctlResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;

/**
 * 
 * This test shows how to use mockito to stub out the behavior of a service so you can test your interaction with that
 * service.
 * 
 * <p>
 * More details on Mockito can be found on the <a
 * href="http://docs.mockito.googlecode.com/hg/latest/org/mockito/Mockito.html"> website</a>
 * </p>
 * 
 */
// @RunWith says "run this test with Mockito and do some magic with those annotations"
@RunWith(value = MockitoJUnitRunner.class)
public class NotifyQaManagerTest {

    /**
     * @Mock gives us a mock version of this class. We can then stub the behavior
     */
    @Mock
    RemctlClient remctlClient;

    /**
     * Class under test. The @InjectMocks creates an instance of this class and resolves any dependencies using other
     * classes defined with @Mock
     */
    @InjectMocks
    NotifyQaManager manager;

    /**
     * Test listing messages
     * 
     * @throws IOException
     *             throw if trouble loading the text file
     */
    @Test
    public void testList() throws IOException {
        /**
         * Setup the test
         */
        ClassPathResource resource = new ClassPathResource("list.txt");
        String stdOut = IOUtils.toString(resource.getInputStream());

        RemctlResponse remctlResponse = new RemctlResponse(stdOut, "", 0);
        // stub the behavior
        when(this.remctlClient.execute("notify-qa", "list")).thenReturn(remctlResponse);

        /**
         * run the test
         */
        List<MessageInfo> results = this.manager.listMessages();

        /**
         * assert the behavior
         */
        assertEquals(6, results.size());
        // assert contents on the 4th info
        MessageInfo info = results.get(3);
        assertEquals(75673, info.getId());
        SimpleDateFormat dateFormat = new SimpleDateFormat(NotifyQaManager.LIST_DATE_FORMAT);
        assertEquals("2013-06-16 17:30:07", dateFormat.format(info.getDate()));
        assertEquals("accounts/account-invitation/invite-sponsored", info.getTemplate());

    }

    /**
     * Test loading a message that doesn't exist. We expect a 'could not find' error to be turned into a
     * {@link ResourceNotFoundException}.
     * 
     * @throws IOException
     *             Thrown if unable to find resource file
     */
    @Test(expected = ResourceNotFoundException.class)
    public void testLoadNonExistantMessage() throws IOException {

        /**
         * Setup the test: a non existent record has a remctl code of 255 .
         */
        RemctlResponse remctlResponse = new RemctlResponse("", "notifications-debug: could not find record 2", 255);
        // stub the behavior
        when(this.remctlClient.executeAllowAnyStatus("notify-qa", "details", "2")).thenReturn(remctlResponse);

        /**
         * run the test
         */
        this.manager.loadMessage(2);

        /**
         * assert: see the expected value in the @Test. it ensures the exception is returned
         */

    }

    /**
     * Test loading a message
     * 
     * @throws IOException
     *             Thrown if unable to find resource file
     */
    @Test
    public void testLoadMessage() throws IOException {
        /**
         * Setup the test
         */
        ClassPathResource resource = new ClassPathResource("details-75669.txt");
        String stdOut = IOUtils.toString(resource.getInputStream());

        RemctlResponse remctlResponse = new RemctlResponse(stdOut, "", 0);
        // stub the behavior
        when(this.remctlClient.executeAllowAnyStatus("notify-qa", "details", "75669")).thenReturn(remctlResponse);

        /**
         * run the test
         */
        MessageDetails details = this.manager.loadMessage(75669);

        /**
         * assert the behavior
         */
        assertEquals(75669, details.getId());
        assertEquals("accounts/account-invitation/invite-sponsored", details.getTemplate());
        assertEquals("service/sponsorship-acct-webapps-lassen@ at acct-webapps-lassen.Stanford.EDU",
                details.getCreator());
        SimpleDateFormat dateFormat = new SimpleDateFormat(NotifyQaManager.DETAILS_DATE_FORMAT);
        assertEquals("2013-06-05T13:28:57", dateFormat.format(details.getAt()));

        assertTrue(details.getRawMessageText().startsWith("To: Yuetest"));
        assertTrue(details.getRawMessageText().endsWith("Cameron Gilb"));

        SimpleMailMessage message = details.getMessage();
        assertEquals("Yuetest Vernet <yuelu@stanford.edu>", message.getTo()[0]);
        assertEquals("Cameron Gilb <yuelu@stanford.edu>", message.getFrom());
        assertEquals("test:Stanford University SUNet ID creation", message.getSubject());
        assertTrue(message.getText().startsWith("Yuetest Vernet:"));
        assertTrue(message.getText().endsWith("Cameron Gilb"));

    }

    /**
     * Test search with no results
     * 
     * @throws IOException
     *             Thrown if unable to find resource file
     */
    @Test
    public void testSearchNoResults() throws IOException {
        /**
         * Setup the test
         */
        ClassPathResource resource = new ClassPathResource("search-no-results.txt");
        String stdOut = IOUtils.toString(resource.getInputStream());

        RemctlResponse remctlResponse = new RemctlResponse(stdOut, "", 0);
        // stub the behavior
        when(this.remctlClient.execute("notify-qa", "search", "turkey legs")).thenReturn(remctlResponse);

        /**
         * run the test
         */
        List<MessageInfo> results = this.manager.searchMessages("turkey legs");

        /**
         * assert the behavior
         */
        assertEquals("No results expected", 0, results.size());

    }

    /**
     * Test listing messages
     * 
     * @throws IOException
     *             throw if trouble loading the text file
     */
    @Test
    public void testSearchResults() throws IOException {
        /**
         * Setup the test
         */
        ClassPathResource resource = new ClassPathResource("search-results.txt");
        String stdOut = IOUtils.toString(resource.getInputStream());

        RemctlResponse remctlResponse = new RemctlResponse(stdOut, "", 0);
        // stub the behavior
        when(this.remctlClient.execute("notify-qa", "search", "oink")).thenReturn(remctlResponse);

        /**
         * run the test
         */
        List<MessageInfo> results = this.manager.searchMessages("oink");

        /**
         * assert the behavior
         */
        assertEquals(7, results.size());
        // assert contents on the 1st info
        MessageInfo info = results.get(0);
        assertEquals(67, info.getId());
        SimpleDateFormat dateFormat = new SimpleDateFormat(NotifyQaManager.LIST_DATE_FORMAT);
        assertEquals("2011-05-20 13:10:50", dateFormat.format(info.getDate()));
        assertEquals("super-template", info.getTemplate());

    }

}
