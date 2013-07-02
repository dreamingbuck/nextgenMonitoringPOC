package edu.stanford.sumonitorspring;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
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
		for (Alert a : instances) {
			if (id == a.getEvent()) {
				return a;
			}
			return null;
		}
		return a;
	}

	public List<Alert> getInstances() {
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