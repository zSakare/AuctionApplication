package main.form.validator;

import java.io.Serializable;

public class FormError implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3333054686701501293L;
	private String fieldName;
	private String value;
	private String error;
	
	public FormError(String fieldName, String value, String error) {
		this.setFieldName(fieldName);
		this.setValue(value);
		this.setError(error);
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
}
