package cookbook;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

public class MaximumStringLengthValidator implements IValidator<String> {
  private final int max;

  public MaximumStringLengthValidator(int max) {
    this.max = max;
  }

  public void validate(IValidatable<String> validatable) {
    if (validatable.getValue().length() > max) {
      ValidationError error = new ValidationError();
      error.addMessageKey(getClass().getSimpleName());
      error.setVariable("max", max);
      validatable.error(error);
    }
  }
}
