package edu.stanford.notify;

import java.util.Date;

/**
 * Message information displayed when listing or searching
 * 
 * @author pradtke
 * 
 */
public class MessageInfo {

    /** Message id */
    private long id;

    /** Message creation time */
    private Date date;

    /** Template name */
    private String template;


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTemplate() {
        return this.template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

}
