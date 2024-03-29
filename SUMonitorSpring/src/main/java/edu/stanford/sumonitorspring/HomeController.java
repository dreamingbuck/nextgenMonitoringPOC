package edu.stanford.sumonitorspring;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * In a simple webapp we have a single controller to handle all requests
 * 
 * @author pradtke
 * 
 */
@Controller
public class HomeController {

	/** Our logging facility */
	private static final Logger logger = LoggerFactory
			.getLogger(new Throwable().getStackTrace()[0].getClassName());

	/**
	 * Manages our interaction with notify-qa
	 */
	@Autowired
	AlertManager manager;

	/**
	 * Map the root url here. If an optional query parameter is given, then do a
	 * search, otherwise just list the last two days of messages
	 * 
	 * @param model
	 *            The model to send to the view
	 * @param searchTerm
	 *            Optional seach term
	 * @return The name of the view to use in displaying
	 */
	@RequestMapping("/")
	public String loadHomePage(Model model,
			@RequestParam(value = "q", required = false) String searchTerm) {
		logger.info("*********************Entering loadHomePage..........................");
		// List<MessageInfo> results = null;
		List<Alert> results = null;
		if (StringUtils.isBlank(searchTerm)) {
			logger.info("Listing results");
			// results = this.alert.listMessages();
			results = this.manager.listAlerts();
			this.manager.setInstancesJackson(); // FIXME: temp for testing
		} /*
		 * else { logger.info("Searching for {}", searchTerm); results =
		 * this.alert.searchMessages(searchTerm); }
		 */
		logger.info("Found {} results", results.size());
		model.addAttribute("results", results);
		return "home";
	}

	/**
	 * Load details on a messaged
	 * 
	 * @param id
	 *            The id of the message to load
	 * @param model
	 *            The model to send to the view
	 * @return The name of the view to use in displaying the details
	 */
	@RequestMapping("/details/{id}")
	public String loadDetails(@PathVariable("id") String id, Model model) {
		logger.info("Loading alert {}", id);
		// Alert alert = this.loadDetails(id);
		Alert alert = this.manager.getAlert(id);
		// this will add our object and give it the name 'messageDetails'
		model.addAttribute(alert);
		// the name of the .jsp to view
		return "details";
	}

	@RequestMapping("/event/{id}")
	public String performAction(@PathVariable("id") String id,
			@ModelAttribute ActionForm form) {

		logger.info("id {} form {}", id, form);

		return "home";
	}

	/**
	 * Load details on a messaged
	 * 
	 * @param id
	 *            The id of the message to load
	 * @return The name of the view to use in displaying the details
	 */
	/*
	 * @RequestMapping(value = "/details/{id}", method = RequestMethod.GET,
	 * produces = { "application/json" })
	 * 
	 * @ResponseStatus(HttpStatus.OK)
	 * 
	 * @ResponseBody public MessageDetails loadDetails(@PathVariable("id") long/
	 * id) { logger.info("Loading message {}", id); return
	 * this.manager.loadMessage(id); }
	 */
	/**
	 * Prepopulate the form for sending a template
	 * 
	 * @param model
	 *            The model to use for creating the view
	 * @return the view name
	 */
	@RequestMapping(value = "/send", method = RequestMethod.GET)
	public String loadSendPage(Model model) {
		model.addAttribute(new TemplateForm());
		return "test-template";
	}

	/**
	 * Accept the form POST and make sure the submission is valid
	 * 
	 * @param form
	 *            The submitted form
	 * @param result
	 *            The result of validation
	 * @return the view name to display
	 */
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public String doSendPage(@ModelAttribute @Valid TemplateForm form,
			BindingResult result) {

		// perform additional validation
		if (StringUtils.isNotBlank(form.getVariables())) {
			String[] keyValPairs = form.getVariables().split(",");
			for (String keyVal : keyValPairs) {
				String[] split = keyVal.split("=");
				if (split.length != 2) {
					result.rejectValue("variables", "invalid", keyVal
							+ " is not of the form key=value");
					break;
				}
			}
		}

		// reject if there were errors
		if (result.hasErrors()) {
			return "test-template";
		}

		// if this was a real service, we would do something
		return "redirect:/";
	}

}
