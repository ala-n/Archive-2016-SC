package labs.zubovich.ui;

import labs.zubovich.calculator.RowParam;

import javax.swing.*;
import java.awt.*;

public interface RowEditorType {
	void setValue(Object value);
	Object getValue();
	Component getComponent(RowParam rowParam, JTable table, Object value, boolean isSelected);

	class TextEditor implements RowEditorType {
		private JTextField text;

		public TextEditor() {
			text = new JTextField();
		}

		@Override
		public void setValue(Object value) {
			if(value != null) {
				text.setText(value.toString());
			}
		}
		@Override
		public Object getValue() {
			return text.getText();
		}
		@Override
		public Component getComponent(RowParam rowParam, JTable table, Object value, boolean isSelected) {
			return text;
		}
	}

	class NumberEditor implements RowEditorType {
		private JSpinner spinner;
		public NumberEditor() {
			spinner = new JSpinner();
		}

		@Override
		public void setValue(Object value) {
			if(value != null && value instanceof Number) {
				spinner.setValue(value);
			}
		}
		@Override
		public Object getValue() {
			return spinner.getValue();
		}
		@Override
		public Component getComponent(RowParam rowParam, JTable table, Object value, boolean isSelected) {
			return spinner;
		}
	}

	class SelectType <T> implements RowEditorType {

		JComboBox<T> select;
		ComboBoxModel<T> model;

		public SelectType(T[] values) {
			model = new DefaultComboBoxModel<>(values);
			select = new JComboBox<>(model);
		}

		@Override
		public void setValue(Object value) {
			if(value != null) {
				model.setSelectedItem(value);
			}
		}

		@Override
		public Object getValue() {
			return model.getSelectedItem();
		}

		@Override
		public Component getComponent(RowParam rowParam, JTable table, Object value, boolean isSelected) {
			return select;
		}
	}
}


