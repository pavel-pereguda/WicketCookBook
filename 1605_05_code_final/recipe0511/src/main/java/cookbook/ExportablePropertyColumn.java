package cookbook;

import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;

public class ExportablePropertyColumn<T> extends PropertyColumn<T> implements
		ExportableColumn<T> {

	public ExportablePropertyColumn(IModel displayModel,
			String propertyExpression) {
		super(displayModel, propertyExpression);
	}

	public void exportCsv(final T object, CsvWriter writer) {
		IModel<?> textModel = createLabelModel(new AbstractReadOnlyModel<T>() {
			@Override
			public T getObject() {
				return object;
			}
		});
		writer.write(textModel.getObject());
		textModel.detach();
	}
}
