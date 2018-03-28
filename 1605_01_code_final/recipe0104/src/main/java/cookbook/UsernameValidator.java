package cookbook;

import org.apache.wicket.validation.CompoundValidator;
import org.apache.wicket.validation.validator.PatternValidator;
import org.apache.wicket.validation.validator.StringValidator;

public class UsernameValidator extends CompoundValidator<String> {
  public UsernameValidator() {
    add(StringValidator.lengthBetween(5, 20));
    add(new PatternValidator("[a-z]+"));
  }
}
