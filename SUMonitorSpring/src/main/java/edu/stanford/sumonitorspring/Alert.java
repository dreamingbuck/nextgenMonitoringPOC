package edu.stanford.sumonitorspring;

import java.util.ArrayList;
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
	private String timestamp;
	private Boolean clearOnAck;
	private AuditLog auditLog;

	// private List<String[]> auditLogTest = new ArrayList<String[]>();
	private List<List<String>> auditLogTest = new ArrayList<List<String>>();

	private List<AuditEntry> auditLogTest2 = new ArrayList<AuditEntry>();

	private AuditEntry auditEntry;

	// need a public no arg constructor since Spring requires beans!
	public Alert() {
	}

	public Alert(String name, String event, String state, Integer severity,
			String owner, Integer count, String source, String category,
			String text, String timestamp, Boolean clearOnAck) {
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
				+ "clearOnAck=" + clearOnAck + ", " + "auditLog=" + auditLog
				+ ", " + "auditLogTest=<" + auditLogTest + ">" + ", "
				+ "auditEntry=<" + auditEntry + ">" + ", " + "auditLogTest2=<"
				+ auditLogTest2 + ">" + "]";
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
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp
	 *            the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the timestamp
	 */
	public String getDate() {
		return timestamp;
	}

	/**
	 * @param timestamp
	 *            the timestamp to set
	 */
	public void setDate(String timestamp) {
		this.timestamp = timestamp;
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
	 * @return the auditLog
	 */
	public AuditLog getAuditLog() {
		return auditLog;
	}

	/**
	 * @param auditLog
	 *            the auditLog to set
	 */
	public void setAuditLog(AuditLog auditLog) {
		this.auditLog = auditLog;
	}

	/**
	 * @return the auditLogTest
	 */
	/*
	 * public List<String[]> getAuditLogTest() { return auditLogTest; }
	 */

	/**
	 * @return the auditLogTest2
	 */
	public List<AuditEntry> getAuditLogTest2() {
		return auditLogTest2;
	}

	/**
	 * @param auditLogTest
	 *            the auditLogTest to set
	 */
	/*
	 * public void setAuditLogTest(List<String> auditLogTest) {
	 * this.auditLogTest = auditLogTest; }
	 */

	/**
	 * @param auditLogTest2
	 *            the auditLogTest2 to set
	 */
	public void setAuditLogTest2(List<AuditEntry> auditLogTest2) {
		this.auditLogTest2 = auditLogTest2;
	}

	/**
	 * @return the auditLogTest
	 */
	public List<List<String>> getAuditLogTest() {
		return auditLogTest;
	}

	/**
	 * @param auditLogTest
	 *            the auditLogTest to set
	 */
	public void setAuditLogTest(List<List<String>> auditLogTest) {
		this.auditLogTest = auditLogTest;
	}

	/**
	 * @return the auditEntry
	 */
	public AuditEntry getAuditEntry() {
		return auditEntry;
	}

	/**
	 * @param auditEntry
	 *            the auditEntry to set
	 */
	public void setAuditEntry(AuditEntry auditEntry) {
		this.auditEntry = auditEntry;
	}
}