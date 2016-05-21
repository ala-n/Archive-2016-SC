package labs.zubovich.ui;

import labs.zubovich.calculator.RowParam;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;

/**
 * Created by Alexey on 19.04.2016.
 */
public class ValueTableCellEditor extends AbstractCellEditor implements TableCellEditor{

	RowEditorType editor;

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

		RowParam rowParam = RowParam.values()[row];

		editor = rowParam.getEditorType();

		editor.setValue(value);

		return editor.getComponent(rowParam, table, value, isSelected);
	}

	@Override
	public Object getCellEditorValue() {
		return editor.getValue();
	}

}
