package labs.zubovich.ui;

import labs.zubovich.calculator.RowParam;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alexey on 18.04.2016.
 */
public class TypicalNormTM extends AbstractTableModel {

	private List<TableModelListener> listeners = new ArrayList<>();
	private String[] columns = {"Параметр", "Значение"};
	private Map<RowParam, Object> values = new HashMap<>();

	public TypicalNormTM() {
		super();
		initDefaultValues();
	}

	public void initDefaultValues() {
		for(RowParam param : RowParam.values()) {
			Object value = getDefaultByClass(param.getType());
			values.put(param, value);
		}
	}

	private Object getDefaultByClass(Class<?> clazz) {
		if(clazz.isArray()) {
			clazz = clazz.getComponentType();
		}
		if(clazz.isEnum() && clazz.getEnumConstants().length > 0) {
			return clazz.getEnumConstants()[0];
		}
		if(clazz.isInstance(List.class)) {
			try {
				return clazz.newInstance();
			} catch (Exception e) {
				return null;
			}
		}
		if(clazz.equals(List.class)) {
			return Collections.emptyList();
		}
		if(clazz.equals(Integer.class) || clazz.equals(Short.class)) {
			return 0;
		}
		if(clazz.equals(Long.class)) {
			return 0l;
		}
		if(clazz.equals(Double.class)) {
			return 0.;
		}
		if(clazz.equals(Float.class)) {
			return 0f;
		}
		if(clazz.equals(String.class)) {
			return "";
		}
		return null;
	}

	@Override
	public int getRowCount() {
		return RowParam.values().length;
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columns[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return columnIndex == 0 ? String.class : Object.class;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex == 1;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		RowParam param = RowParam.values()[rowIndex];
		switch (columnIndex) {
			case 0:
				return param.getTitle();
			case 1:
				return (param.getType()).cast(values.get(param));
		}
		return null;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if(columnIndex != 1){
			return;
		}
		RowParam param = RowParam.values()[rowIndex];
		values.put(param, aValue);
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public Map<RowParam,Object> fetch() {
		return values;
	}
}
