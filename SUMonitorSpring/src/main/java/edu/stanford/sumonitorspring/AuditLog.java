/**
 * 
 */
package edu.stanford.sumonitorspring;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * @author michael
 * 
 */
public class AuditLog {

	private final Logger logger = Logger.getLogger(new Throwable()
			.getStackTrace()[0].getClassName());

	private List<AuditEntry> auditLog = new ArrayList<AuditEntry>();

	/**
	 * @return the auditLog
	 */
	public List<AuditEntry> getAuditLog() {
		return auditLog;
	}

	/**
	 * @param auditLog
	 *            the auditLog to set
	 */
	public void setAuditLog(List<AuditEntry> auditLog) {
		this.auditLog = auditLog;
	}

	public void addAuditLog(AuditEntry auditEntry) {
		logger.debug("addAuditEntry entered... AuditEntry=" + auditEntry);
		this.auditLog.add(auditEntry);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "" + auditLog + ""; // FIXME: cast it!
	}

}
