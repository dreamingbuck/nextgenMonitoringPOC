package edu.stanford.notify;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Sample form to send a template
 * 
 * @author pradtke
 * 
 */
public class TemplateForm {

    /**
     * Service to send to
     */
    @NotBlank(message = "Service is required.")
    private String service;

    /**
     * Template to use
     */
    private String template;

    /**
     * Email address
     */
    @Email(message = "Invalid email address.")
    @NotBlank(message = "A contact email address is required.")
    private String to;

    /**
     * Variables to send
     */
    private String variables;


    public String getService() {
        return this.service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getTemplate() {
        return this.template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getTo() {
        return this.to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getVariables() {
        return this.variables;
    }

    public void setVariables(String variables) {
        this.variables = variables;
    }

}
