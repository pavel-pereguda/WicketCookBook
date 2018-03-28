package cookbook;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.wicket.Component;
import org.apache.wicket.MetaDataKey;

public class Permissions {
	private static MetaDataKey<ArrayList<Permission>> RENDER = new MetaDataKey<ArrayList<Permission>>() {
	};
	private static MetaDataKey<ArrayList<Permission>> ENABLE = new MetaDataKey<ArrayList<Permission>>() {
	};

	public static Permissions of(Component c) {
		return new Permissions(c);
	}

	private final Component component;

	private Permissions(Component component) {
		this.component = component;
	}

	public Permissions render(Permission... permissions) {
		set(RENDER, permissions);
		return this;
	}

	public Permissions enable(Permission... permissions) {
		set(ENABLE, permissions);
		return this;
	}

	public boolean canRender() {
		return can(RENDER);
	}

	public boolean canEnable() {
		return can(ENABLE);
	}

	public boolean can(MetaDataKey<ArrayList<Permission>> key) {
		ArrayList<Permission> stored = component.getMetaData(key);
		if (stored == null) {
			return true;
		}

		MySession session = (MySession) component.getSession();

		for (Permission permission : stored) {
			if (session.hasPermission(permission)) {
				return true;
			}
		}
		return false;
	}

	private void set(MetaDataKey<ArrayList<Permission>> key,
			Permission... permissions) {

		if (permissions.length == 0) {
			component.setMetaData(key, null);
		} else {
			ArrayList<Permission> stored = component.getMetaData(key);
			if (stored == null) {
				stored = new ArrayList<Permission>();
				component.setMetaData(key, stored);
			}
			stored.clear();
			stored.addAll(Arrays.asList(permissions));
		}
	}

}
