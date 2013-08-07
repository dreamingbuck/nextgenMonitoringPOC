package edu.stanford.sumonitorspring;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonStreamParser;

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
		return getInstancesJackson(); // FIXME: hmmm GSON doesn't work
										// anymore...
	}

	public List<Alert> getInstancesJackson() {
		logger.debug("getInstances entered...");
		try {
			instances.clear();

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

		} catch (IOException e) {
			e.printStackTrace();
		}
		return instances;
	}

	public void setInstancesJackson() {
		logger.debug("setInstances entered...");
		ListIterator<Alert> listIterator = instances.listIterator();
		ObjectMapper mapper = new ObjectMapper();

		try {
			final OutputStream out = new FileOutputStream(jsonFile + ".out"); // FIXME:
																				// temp
																				// .out
																				// suffix
																				// for
																				// testing
			try {
				while (listIterator.hasNext()) {
					Alert a = listIterator.next();

					mapper.writeValue(out, a);
				}
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return;
	}

	public List<Alert> getInstancesGSON() {
		logger.debug("getInstances entered...");
		try {
			instances.clear();
			Gson gson = new GsonBuilder().create();

			JsonStreamParser parser = new JsonStreamParser(new FileReader(
					jsonFile));
			while (parser.hasNext()) {
				Alert o = gson.fromJson(parser.next(), Alert.class);
				instances.add(o);
				logger.debug("getting instance: System.out.println(o)");
			}
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