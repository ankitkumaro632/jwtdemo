package com.jwt.employee.model;

import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * This Class is to represent object containing the service result message
 * details.
 * 
 * @author Vishwas Raghav.
 *
 */
public class ServiceMessage {

	/**
	 * Variable to store message code, corresponding message description will be
	 * picked from messages.properties.
	 */
	private String msgcode;
	/**
	 * Variable to store message description for a particular message code.
	 */
	private String msgdescription;
	/**
	 * Variable to store the message arguments.
	 */
	private Object[] arguments;
	/**
	 * Variable to store the name of the field, whose value has generated the error.
	 */
	private String field;
	/**
	 * Variable to store the name of the model object, whose field has generated the
	 * error.
	 */
	private String objectname;
	/**
	 * Variable to store the rejected value.
	 */
	private Object rejectedvalue;
	/**
	 * Variable to store field binding failure.
	 */
	private boolean bindingfailure;

	// ------------------------------------------------------------------------------------------------------------------
	/**
	 * default Constructor
	 */
	public ServiceMessage() {

	}

	// ------------------------------------------------------------------------------------------------------------------
	/**
	 * Constructor of the class.
	 * 

	 */
	public ServiceMessage(MessageSource messageSource, String msgcode, Object... arguments) {
		this.msgcode = msgcode;
		this.arguments = arguments;
		this.msgdescription = messageSource.getMessage(msgcode, arguments, Locale.US);

	}

	// ------------------------------------------------------------------------------------------------------------------
	/**
	 * Constructor of the class.
	 * 

	 */
	public ServiceMessage(String msgdesc) {
		this.msgdescription = msgdesc;

	}

	// ------------------------------------------------------------------------------------------------------------------
	/**
	 * Constructor of the class.
	 * 
	 * @param msgcode
	 * @param arguments
	 * @param field
	 * @param objectname
	 * @param rejectedvalue
	 * @param bindingfailure
	 */
	public ServiceMessage(MessageSource messageSource, String msgcode, Object[] arguments, String field,
                          String objectname, Object rejectedvalue, boolean bindingfailure) {

		this.msgcode = msgcode;
		this.msgdescription = messageSource.getMessage(msgcode, arguments, Locale.US);
		this.arguments = arguments;
		this.field = field;
		this.objectname = objectname;
		this.rejectedvalue = rejectedvalue;
		this.bindingfailure = bindingfailure;

	}

	// ------------------------------------------------------------------------------------------------------------------
	public String getMsgcode() {
		return msgcode;
	}

	public String getMsgdescription() {
		return msgdescription;
	}

	public Object[] getArguments() {
		return arguments;
	}

	public String getField() {
		return field;
	}

	public String getObjectname() {
		return objectname;
	}

	public Object getRejectedvalue() {
		return rejectedvalue;
	}

	public boolean isBindingfailure() {
		return bindingfailure;
	}
	// ------------------------------------------------------------------------------------------------------------------

}
