package cookbook;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.Session;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.IAuthorizationStrategy;

public class AuthorizationStrategy implements IAuthorizationStrategy {

	public <T extends Component> boolean isInstantiationAuthorized(
			Class<T> componentClass) {

		if (!Page.class.isAssignableFrom(componentClass)) {
			return true;
		}

		if (((MySession) Session.get()).getUsername() != null) {
			return true;
		}

		if (LoginPage.class.isAssignableFrom(componentClass)) {
			return true;
		}

		throw new RestartResponseAtInterceptPageException(LoginPage.class);

	}

	public boolean isActionAuthorized(Component component, Action action) {
		if (action == Component.RENDER) {
			return Permissions.of(component).canRender();
		} else if (action == Component.ENABLE) {
			return Permissions.of(component).canEnable();
		}
		return true;
	}

}
