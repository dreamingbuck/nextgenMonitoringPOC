package edu.stanford.sumonitorspring;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Alert implements java.io.Serializable {

	// private final Logger logger = Logger.getLogger(this.getClass());
	// private static Logger logger = Logger.getLogger(Alert.class);
	// to avoid hardcoding what class I am in static method
	/*
	 * private static final Logger logger = Logger.getLogger(new Throwable()
	 * .getStackTrace()[0].getClassName());
	 */
	private final static SimpleDateFormat TIMESTAMPFORMAT = new SimpleDateFormat(
			"MM/dd/yyyy HH:mm:ss");
	private final static Pattern PATTERN = Pattern.compile("(^[^\\n]*)");
	private String name;
	private String event;
	private String state;
	private Integer severity;
	private String owner;
	private Integer count;
	private String source;
	private String category;
	private String text;
	private Calendar timestamp;
	private Boolean clearOnAck;
	private List<AuditEntry> auditLog = new LinkedList<AuditEntry>();

	// need a public no arg constructor since Spring requires beans!
	public Alert() {
	}

	public Alert(String name, String event, String state, Integer severity,
			String owner, Integer count, String source, String category,
			String text, Calendar timestamp, Boolean clearOnAck) {
		this.name = name;
		this.event = event;
		this.state = state;
		this.severity = severity;
		this.owner = owner;
		this.count = count;
		this.source = source;
		this.category = category;
		this.text = text;
		this.timestamp = timestamp;
		this.clearOnAck = clearOnAck;
	}

	@Override
	public String toString() {
		return "Alert [" + "name=" + name + ", " + "event=" + event + ", "
				+ "state=" + state + ", " + "owner=" + owner + ", "
				+ "severity=" + severity + ", " + "count=" + count + ", "
				+ "source=" + source + ", " + "category=" + category + ", "
				+ "eventtext=" + text + ", " + "timestamp=" + timestamp + ", "
				+ "clearOnAck=" + clearOnAck + ", " + "auditLog=" + "<"
				+ auditLog + ">" + "]";
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the event
	 */
	public String getEvent() {
		return event;
	}

	/**
	 * @param event
	 *            the event to set
	 */
	public void setEvent(String event) {
		this.event = event;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the severity
	 */
	public Integer getSeverity() {
		return severity;
	}

	/**
	 * @param severity
	 *            the severity to set
	 */
	public void setSeverity(Integer severity) {
		this.severity = severity;
	}

	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * @param owner
	 *            the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source
	 *            the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the eventtext
	 */
	public String getEventtext() {
		return text;
	}

	/**
	 * @param eventtext
	 *            the eventtext to set
	 */
	public void setEventtext(String eventtext) {
		this.text = eventtext;
	}

	/**
	 * @return the clearOnAck
	 */
	public Boolean getClearOnAck() {
		return clearOnAck;
	}

	/**
	 * @param clearOnAck
	 *            the clearOnAck to set
	 */
	public void setClearOnAck(Boolean clearOnAck) {
		this.clearOnAck = clearOnAck;
	}

	/**
	 * @return the headline
	 */
	public String getHeadline() {
		Matcher m = PATTERN.matcher(text);
		if (m.matches()) {
			return m.group(0);
		} else {
			return text;
		}
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the id (FIXME: for now just use the event)
	 */
	public String getId() {
		return event;
	}

	/**
	 * @param auditLog
	 *            the auditLog to set
	 */
	public void setAuditLog(List<AuditEntry> auditLog) {
		this.auditLog = auditLog;
	}

	/**
	 * @return the auditLog
	 */
	public List<AuditEntry> getAuditLog() {
		return auditLog;
	}

	/**
	 * @param timestamp
	 *            the timestamp to set
	 */
	public void setTimestamp(Calendar timestamp) {
		this.timestamp = timestamp;
		System.err.println("setTimestamp=" + timestamp);
	}

	/**
	 * @return the timestamp
	 */
	public Calendar getTimestamp() {
		// return timestamp.setTime(TIMESTAMPFORMAT.parse(timestamp)));
		// return "" + timestamp.getTime();
		System.err.println("getTimestamp=" + timestamp);
		return timestamp;
	}

	public String getPrintableTimestamp() {
		return TIMESTAMPFORMAT.format(timestamp.getTime());
	}

}