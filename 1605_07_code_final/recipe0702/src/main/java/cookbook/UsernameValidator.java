package cookbook;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

public class UsernameValidator implements IValidator<String> {
	public void validate(IValidatable<String> validatable) {
		final String value = validatable.getValue();
		if (value.equalsIgnoreCase("johndoe")) {
			ValidationError error = new ValidationError();
			error.setMessage("Username " + value + " is already taken");
			validatable.error(error);
		}
	}
}
