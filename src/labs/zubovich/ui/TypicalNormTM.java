package labs.zubovich.ui;

import labs.zubovich.calculator.RowParam;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alexey on 18.04.2016.
 */
public class TypicalNormTM extends AbstractTableModel {

	private List<TableModelListener> listeners = new ArrayList<>();
	private String[] columns = {"Parm", "Value"};
	private Map<RowParam, Object> values = new HashMap<>();

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
				return values.get(param);
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
