/**
 * 
 */
package edu.stanford.sumonitorspring;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;

/**
 * @author michael
 * 
 */
public class AuditEntry {
	private final static SimpleDateFormat TIMESTAMPFORMAT = new SimpleDateFormat(
			"MM/dd/yyyy HH:mm:ss");
	private Calendar when;
	private String who;
	private String what;

	private final Logger logger = Logger.getLogger(new Throwable()
			.getStackTrace()[0].getClassName());

	public AuditEntry() {
	}

	/**
	 * @param who
	 * @param when
	 * @param what
	 */
	public AuditEntry(Calendar when, String who, String what) {
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

	/**
	 * @return the when
	 */
	public Calendar getWhen() {
		return when;
	}

	/**
	 * @param when
	 *            the when to set
	 */
	public void setWhen(Calendar when) {
		this.when = when;
	}

	public String getPrintableWhen() {
		return TIMESTAMPFORMAT.format(when.getTime());
	}
}
