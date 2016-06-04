package labs.zubovich.ui;

import labs.zubovich.calculator.RowParam;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public interface RowViewType {

	void setValue(Object value);
	Object getValue();

	Component getRendererComponent(RowParam rowParam, JTable table, Object value, boolean isSelected, boolean hasFocus);
	Component getEditorComponent(RowParam rowParam, JTable table, Object value, boolean isSelected);

	class TextView implements RowViewType {
		private JTextField text;

		public TextView() {
			text = new JTextField();
			text.setBorder(null);
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
		public Component getEditorComponent(RowParam rowParam, JTable table, Object value, boolean isSelected) {
			setValue(value);
			return text;
		}
		@Override
		public Component getRendererComponent(RowParam rowParam, JTable table, Object value, boolean isSelected, boolean hasFocus) {
			setValue(value);
			return text;
		}
	}

	class NumberView implements RowViewType {
		private JSpinner spinner;
		public NumberView() {
			spinner = new JSpinner();
			spinner.setBorder(null);
		}
		public NumberView(int min, int max) {
			spinner = new JSpinner(new SpinnerNumberModel(min, min, max, 1));
			spinner.setBorder(null);
		}
		public NumberView(double min, double max) {
			spinner = new JSpinner(new SpinnerNumberModel(min, min, max, 1.0));
			spinner.setBorder(null);
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
		public Component getRendererComponent(RowParam rowParam, JTable table, Object value, boolean isSelected, boolean hasFocus) {
			setValue(value);
			return spinner;
		}

		@Override
		public Component getEditorComponent(RowParam rowParam, JTable table, Object value, boolean isSelected) {
			setValue(value);
			return spinner;
		}
	}

	class SelectType <T> implements RowViewType {
		JComboBox<T> select;
		ComboBoxModel<T> model;

		public SelectType(T[] values) {
			model = new DefaultComboBoxModel<>(values);
			select = new JComboBox<>(model);
			select.setBorder(null);
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
		public Component getRendererComponent(RowParam rowParam, JTable table, Object value, boolean isSelected, boolean hasFocus) {
			setValue(value);
			return select;
		}

		@Override
		public Component getEditorComponent(RowParam rowParam, JTable table, Object value, boolean isSelected) {
			setValue(value);
			return select;
		}

	}

	class MultySelectType<T> implements RowViewType {

		JScrollPane scrollPane;
		T[] values;
		JCheckBox[] checkBoxes;

		public MultySelectType(T[] values) {
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
			scrollPane.setBorder(null);
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
		public Component getRendererComponent(RowParam rowParam, JTable table, Object value, boolean isSelected, boolean hasFocus) {
			setValue(value);
			return scrollPane;
		}

		@Override
		public Component getEditorComponent(RowParam rowParam, JTable table, Object value, boolean isSelected) {
			setValue(value);
			return scrollPane;
		}
	}

}


