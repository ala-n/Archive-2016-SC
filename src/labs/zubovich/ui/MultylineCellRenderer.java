package labs.zubovich.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Created by Alexey on 04.06.2016.
 */
public class MultylineCellRenderer extends JTextArea implements TableCellRenderer {

	private boolean canHighlighted;

	public MultylineCellRenderer(){
		this(true);
	}

	public MultylineCellRenderer(boolean canHiglighted) {
		setCanHighlighted(canHiglighted);
		setLineWrap(true);
		setWrapStyleWord(true);
		setOpaque(true);
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
	                                               boolean isSelected, boolean hasFocus, int row, int column) {
		if (isSelected) {
			setForeground(table.getSelectionForeground());
			setBackground(table.getSelectionBackground());
		} else {
			setForeground(table.getForeground());
			setBackground(table.getBackground());
		}
		setFont(table.getFont());
		if(canHighlighted) {
			if (hasFocus) {
				setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
				if (table.isCellEditable(row, column)) {
					setForeground(UIManager.getColor("Table.focusCellForeground"));
					setBackground(UIManager.getColor("Table.focusCellBackground"));
				}
			} else {
				setBorder(new EmptyBorder(1, 2, 1, 2));
			}
		}
		setText((value == null) ? "" : value.toString());
		setToolTipText(getText());
		return this;
	}

	public boolean isCanHighlighted() {
		return canHighlighted;
	}

	public void setCanHighlighted(boolean canHighlighted) {
		this.canHighlighted = canHighlighted;
		if(!canHighlighted) {
			setBorder(new EmptyBorder(1, 2, 1, 2));
		}
	}
}
