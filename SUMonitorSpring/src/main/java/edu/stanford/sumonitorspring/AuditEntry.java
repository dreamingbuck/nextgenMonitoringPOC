/**
 * 
 */
package edu.stanford.sumonitorspring;

import org.apache.log4j.Logger;

/**
 * @author michael
 * 
 */
public class AuditEntry {
	private String when;
	private String who;
	private String what;

	private final Logger logger = Logger.getLogger(new Throwable()
			.getStackTrace()[0].getClassName());

	public AuditEntry() {
	}

	public AuditEntry(String[] auditEntry) {
		this.when = auditEntry[0];
		this.who = auditEntry[1];
		this.what = auditEntry[2];
	}

	/**
	 * @param who
	 * @param when
	 * @param what
	 */
	public AuditEntry(String when, String who, String what) {
		logger.debug("AuditEntry constructor entered... when=" + when
				+ ", who=" + who + ", what=" + what);
		this.who = who;
		this.when = when;
		this.what = what;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return when + ", " + who + ", " + what + "<br>";
	}

	/**
	 * @return the who
	 */
	public String getWho() {
		return who;
	}

	/**
	 * @param who
	 *            the who to set
	 */
	public void setWho(String who) {
		this.who = who;
	}

	/**
	 * @return the when
	 */
	public String getWhen() {
		return when;
	}

	/**
	 * @param when
	 *            the when to set
	 */
	public void setWhen(String when) {
		this.when = when;
	}

	/**
	 * @return the what
	 */
	public String getWhat() {
		return what;
	}

	/**
	 * @param what
	 *            the what to set
	 */
	public void setWhat(String what) {
		this.what = what;
	}

}
