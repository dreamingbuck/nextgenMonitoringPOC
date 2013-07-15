package edu.stanford.sumonitorspring;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

public class GsonSerializerTest {
	private final Logger logger = Logger.getLogger(new Throwable()
			.getStackTrace()[0].getClassName());
	String name = "Name";
	String event = "Event";
	// AuditLog auditLog = new AuditLog();
	// private final AuditEntry auditEntry = new AuditEntry("when", "who",
	// "what");
	// AuditLog auditLog = new AuditLog();
	String who = "michael";
	String what = "comment...";
	String when = "when";

	public static void main(String[] args) {

		GsonSerializerTest o = new GsonSerializerTest();
		Gson gson = new Gson();
		System.out.println("in main...");
		String json = gson.toJson(o);
		System.out.println(json);
		// logger.debug("json=" + json);
	}

	public GsonSerializerTest() {

		// AuditEntry auditEntry = new AuditEntry(when, who, what);
		// auditLog.addAuditLog(auditEntry);
		// Gson gson = new Gson();
		// String json = gson.toJson(object);
		// System.out.println(json);
		// logger.debug("json=" + json);
	}

}
