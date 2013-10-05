package main.form.validator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.form.RegistrationForm;

public class RegistrationFormValidator {

	private static final String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$";
	private static final String ADDRESS_PATTERN = "^[A-Z0-9a-z ]+$";
	
	public static List<FormError> validate(RegistrationForm form) {
		List<FormError> errors = new ArrayList<FormError>();
		if (form.getUsername().isEmpty()) {
			errors.add(new FormError("username", form.getUsername(), "Username field is empty"));
		} else {
			//check the username doesnt exist
		}
		
		if (form.getCreditCard().isEmpty()) {
			errors.add(new FormError("creditCard", form.getCreditCard(), "Credit card field is empty"));
		}
				
				
				
		
		
		if (form.getPassword().isEmpty()) {
			errors.add(new FormError("password", form.getPassword(), "Password field is empty"));
		} else {
			 // Check if passwords are equal.
			if (!form.getPassword().equals(form.getPasswordConfirm())) {
				errors.add(new FormError("passwordConfirm", form.getPasswordConfirm(), "Confirm password must match password"));
			}
		}
		if (form.getEmail().isEmpty()) {
			errors.add(new FormError("email", form.getEmail(), "Email field is empty"));
		} else {
			Pattern emailPattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
			Matcher emailMatcher = emailPattern.matcher(form.getEmail());
			// Check if valid email sequence is given
			if (!emailMatcher.matches()) {
				errors.add(new FormError("email", form.getEmail(), "Invalid email address"));
			}
		}
		
		int year = Calendar.getInstance().get(Calendar.YEAR);
		// Cannot be born in future.
		if (form.getDob() != null) {
			if (year < form.getDob()) {
				errors.add(new FormError("dob", form.getDob().toString(), "Date of birth <" + form.getDob() + "> cannot exceed current year"));
			}
		}
		Pattern addressPattern = Pattern.compile(ADDRESS_PATTERN);
		Matcher addressMatcher = addressPattern.matcher(form.getAddress());
		// Check address does not have malicious characters.
		if (!addressMatcher.matches()) {
			errors.add(new FormError("address", form.getAddress(), "Address <" + form.getAddress() + "> has invalid characters. All characters must be alphanumeric"));
		}
		
		return errors;
	}


}
