package labs.zubovich.ui;

import labs.zubovich.calculator.RowParam;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ValueTableCellView extends AbstractCellEditor implements TableCellEditor, TableCellRenderer{

	RowViewType editor;
	RowViewType view;

	@Override
	public Object getCellEditorValue() {
		return editor.getValue();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

		RowParam rowParam = RowParam.values()[row];

		editor = rowParam.getViewType();

		return editor.getEditorComponent(rowParam, table, value, isSelected);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

		RowParam rowParam = RowParam.values()[row];

		view = rowParam.getViewType();

		return view.getRendererComponent(rowParam, table, value, isSelected, hasFocus);
	}
}
