package edu.stanford.sumonitorspring;

import java.util.Date;

import org.springframework.mail.SimpleMailMessage;

public class MessageDetails {

	/**
	 * id of the message
	 */
	private long id;

	/**
	 * template that created the message
	 */
	private String template;

	/**
	 * kerb principal and host that created message
	 */
	private String creator;

	/**
	 * Creation time
	 */
	private Date at;

	/**
	 * contents of the actual message
	 */
	private SimpleMailMessage message;

	private String rawMessageText;

	public String getRawMessageText() {
		return this.rawMessageText;
	}

	public void setRawMessageText(String rawMessageText) {
		this.rawMessageText = rawMessageText;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTemplate() {
		return this.template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public SimpleMailMessage getMessage() {
		return this.message;
	}

	public void setMessage(SimpleMailMessage message) {
		this.message = message;
	}

	public Date getAt() {
		return this.at;
	}

	public void setAt(Date at) {
		this.at = at;
	}

}
