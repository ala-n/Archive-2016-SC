package labs.zubovich.ui;

import labs.zubovich.calculator.RowParam;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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

	class MultyselectType <T> implements RowEditorType{

		JScrollPane scrollPane;
		T[] values;
		JCheckBox[] checkBoxes;

		public MultyselectType(T[] values) {
			this.values = values;
			scrollPane = new JScrollPane();
			scrollPane.setMinimumSize(new Dimension(200, 60));
			JPanel container = new JPanel(new GridLayout(values.length, 1));
			checkBoxes = new JCheckBox[values.length];
			for(int i = 0; i < values.length ; ++i) {
				T value = values[i];
				JCheckBox cb = new JCheckBox(value.toString());
				checkBoxes[i] = cb;
				container.add(cb);
			}
			scrollPane.setViewportView(container);
		}

		@Override
		public void setValue(Object value) {
			if(value != null && value instanceof List) {
				List<T> valuesList = (List<T>) value;
				for (int i =0; i< checkBoxes.length ; ++i ) {
					checkBoxes[i].setSelected(valuesList.indexOf(values[i]) >= 0);
				}
			}
		}

		@Override
		public Object getValue() {
			List<T> valuesList = new ArrayList<>();
			for (int i =0; i< checkBoxes.length ; ++i ) {
				if( checkBoxes[i].isSelected() ) {
					valuesList.add(values[i]);
				}
			}
			return valuesList;
		}

		@Override
		public Component getComponent(RowParam rowParam, JTable table, Object value, boolean isSelected) {
			return scrollPane;
		}
	}

}


