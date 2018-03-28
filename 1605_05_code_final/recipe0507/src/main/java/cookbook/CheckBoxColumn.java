package cookbook;

import java.util.UUID;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.AbstractBehavior;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public abstract class CheckBoxColumn<T> extends AbstractColumn<T> {

	private final String uuid = UUID.randomUUID().toString().replace("-", "");
	private final String js="var val=$(this).attr('checked'); $('." + uuid + "').each(function() { $(this).attr('checked', val); });";
	
	public CheckBoxColumn() {
		super(null);
	}

	public void populateItem(Item<ICellPopulator<T>> cellItem,
			String componentId, IModel<T> rowModel) {
		cellItem.add(new CheckPanel(componentId, newCheckBoxModel(rowModel)));
	}

	protected CheckBox newCheckBox(String id, IModel<Boolean> checkModel) {
		return new CheckBox("check", checkModel) {
			@Override
			protected void onComponentTag(ComponentTag tag) {
				super.onComponentTag(tag);
				tag.append("class", uuid, " ");
			}
		};
	}

	protected abstract IModel<Boolean> newCheckBoxModel(IModel<T> rowModel);

	@Override
	public Component getHeader(String componentId) {
		CheckPanel panel = new CheckPanel(componentId, new Model<Boolean>());
		panel.get("check").add(new AbstractBehavior() {
			@Override
			public void onComponentTag(Component component, ComponentTag tag) {
				tag.put("onclick", js);
			}
		});
		return panel;
	}

	private class CheckPanel extends Panel {

		public CheckPanel(String id, IModel<Boolean> checkModel) {
			super(id);
			add(newCheckBox("check", checkModel));
		}

	}

}
