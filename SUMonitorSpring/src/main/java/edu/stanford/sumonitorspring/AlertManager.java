package edu.stanford.sumonitorspring;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import edu.stanford.sumonitorspring.Alert;

//Component tells spring to make an instance of this class. An instance is called a bean.
@Component
public class AlertManager implements java.io.Serializable {

	// private final Logger logger = Logger.getLogger(this.getClass());
	// private static Logger logger = Logger.getLogger(Alert.class);
	// to avoid hardcoding what class I am in static method

	private final Logger logger = Logger.getLogger(new Throwable()
			.getStackTrace()[0].getClassName());
	private static List<Alert> instances = new ArrayList<Alert>();
	private static final String jsonFile = "/home/michael/src/sumonitor-spring/SUMonitorSpring/src/main/webapp/WEB-INF/test/alert.json";

	// need a public no arg constructor since Spring requires beans!
	public AlertManager() {
		logger.debug("no arg constructor entered...");
	}

	public static void main(String[] args) {
		AlertManager a = new AlertManager();
		System.out.println(a.getInstances());
	}

	/**
	 * @return the instances
	 */
	// public static ArrayList getInstances() {
	// logger.debug("getInstances from Alert class..." + instances);
	// return instances;
	// }
	// FIXME: dummy wrappers
	public List<Alert> listAlerts() {
		return getInstances();
	}

	public List<Alert> searchAlerts() {
		return getInstances();
	}

	public Alert getAlert(String id) {
		logger.debug("getAlert instances:" + instances.size());
		logger.debug("instances");
		for (Alert a : instances) {
			logger.debug("getAlert:  looping" + a);
			if (id.equals(a.getEvent())) {
				logger.debug("getAlert:  id='" + id + " a.getEvent()='"
						+ a.getEvent());
				logger.debug("getAlert found id:" + id);
				return a;
			}
		}
		logger.debug("getAlert:  error no id:" + id);
		return null;
	}

	public List<Alert> getInstances() {
		logger.debug("getInstances entered...");
		try {
			instances.clear();
			// Gson gson = new GsonBuilder().create();

			/*
			 * JsonStreamParser parser = new JsonStreamParser(new FileReader(
			 * jsonFile));
			 */

			/*
			 * ObjectMapper mapper = new ObjectMapper(); ObjectReader reader =
			 * mapper.reader(SomeEntity.class); InputStream is = new
			 * FileInputStream(new File(filePath)); for (SomeEntity entity :
			 * reader.readValues(is)) { // do something with the object }
			 */
			// ObjectMapper jsonMapper = new ObjectMapper();
			/*
			 * ObjectMapper mapper = new ObjectMapper(); ObjectReader reader =
			 * mapper.reader(Alert.class); InputStream is = new
			 * FileInputStream(new File(jsonFile)); for (Object o :
			 * reader.readValues(is)) { // do something with the object
			 * logger.debug("got JSON instance..." + a); instances.add(a); }
			 */
			/**/

			final InputStream in = new FileInputStream(jsonFile);
			try {
				for (Iterator<Alert> it = new ObjectMapper().readValues(
						new JsonFactory().createJsonParser(in), Alert.class); it
						.hasNext();) {
					// System.out.println(it.next());
					Alert a = it.next();
					instances.add(a);
					// instances.add(it.next());
					logger.debug("got JSON instance..." + a);
					// logger.debug("got JSON instance..." + it.next());
				}
			} finally {
				in.close();
			}

			// Iterator<User> it = mapper.readValues(json, User.class);
			// Iterator<Alert> it = mapper.readValues(is, Alert.class);
			/*
			 * while (parser.hasNext()) {
			 * logger.debug("getting JSON instance..."); //Alert a =
			 * gson.fromJson(parser.next(), Alert.class); Alert a =
			 * jsonMapper.readValues(new FileReader(jsonFile), Alert.class);
			 * 
			 * logger.debug("got JSON instance..." + a);
			 * 
			 * // FIXME temp for testing AuditLog auditLog = new AuditLog();
			 * String who = "michael"; String what = "comment..."; String when =
			 * "when"; AuditEntry auditEntry = new AuditEntry(when, who, what);
			 * auditLog.addAuditLog(auditEntry); a.setAuditLog(auditLog);
			 * instances.add(a); logger.debug("getting instance: " + a); }
			 */
			/*
			 * ListIterator<Alert> listIterator = instances.listIterator();
			 * while (listIterator.hasNext()) {
			 * System.out.println(listIterator.next()); }
			 */
			// Reading from file
			// BufferedReader br = new BufferedReader(new FileReader(
			// "test/alert.json"));
			// Create Gson object
			// Gson gson = new Gson();

			// convert the json string back to java Object
			// Alert obj = gson.fromJson(br, Alert.class);

			// System.out.println(obj);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return instances;
	}
}