package labs.zubovich;

import labs.zubovich.calculator.Calculator;
import labs.zubovich.calculator.RowParam;
import labs.zubovich.calculator.TypicalNormCalculator;
import labs.zubovich.dbutil.GlobalCache;
import labs.zubovich.dbutil.TableParser;
import labs.zubovich.ui.TypicalNormTM;
import labs.zubovich.ui.ValueTableCellEditor;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;


public class Main extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private TypicalNormTM model = new TypicalNormTM();
	private Calculator calculator = new TypicalNormCalculator();

	private JPanel jContentPane = null;
	private JPanel jResultPane = null;
	private JPanel jTablePane = null;
	private JTable jTable = null;

	private JTextField resultTextField = null;
	private JButton calcButton =  null;

	public static void main(String[] args) {
		GlobalCache.init(GlobalCache.Key.KLOC_Map, TableParser.readTable(new File("db/KLOC.txt")));

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Main thisClass = new Main();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	public Main() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(500, 500);
		this.setContentPane(getJContentPane());
		this.setTitle("Beautiful Clock Calendar");
		//this.setUndecorated(true);
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJTablePane(), BorderLayout.CENTER);
			jContentPane.add(getjResultPane(), BorderLayout.SOUTH);
		}
		return jContentPane;
	}

	private JPanel getJTablePane() {
		if (jTablePane == null) {
			jTablePane = new JPanel();
			jTablePane.setOpaque(false);
			jTablePane.setLayout(new BorderLayout());
			jTablePane.add(getJTable(), BorderLayout.CENTER);
			jTablePane.add(getJTable().getTableHeader(), BorderLayout.NORTH);
		}
		return jTablePane;
	}

	private JTable getJTable() {
		if (jTable == null) {
			jTable = new JTable(model);

			jTable.setShowGrid(true);
			jTable.setRowHeight(28);
			jTable.setCellSelectionEnabled(false);

			DefaultTableCellRenderer keyRenderer = new DefaultTableCellRenderer();
			keyRenderer.setHorizontalAlignment(JLabel.CENTER);
			jTable.getTableHeader().getColumnModel().getColumn(0).setCellRenderer(keyRenderer);
			jTable.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(150);
			jTable.getTableHeader().getColumnModel().getColumn(1).setPreferredWidth(300);
			jTable.getTableHeader().getColumnModel().getColumn(1).setCellEditor(new ValueTableCellEditor());

			jTable.setRowHeight(Arrays.asList(RowParam.values()).indexOf(RowParam.DIFFICULTY_UPPER_COEF), 100);
			jTable.setDefaultRenderer(List.class, (table, value, isSelected, hasFocus, row, column) -> {
				java.util.List list = (java.util.List)value;
				StringBuilder builder = new StringBuilder();
				for(Object o : list) {
					if(builder.length() > 0) {
						builder.append(", ");
					}
					builder.append(o.toString());
				}
				return new JLabel(builder.toString());
			});
		}
		return jTable;
	}

	private JButton getCalcButton() {
		if(calcButton == null) {
			calcButton = new JButton("Посчитать!");
			calcButton.addActionListener(this);
		}
		return calcButton;
	}

	public JTextField getResultTextField() {
		if(resultTextField == null) {
			resultTextField = new JTextField();
			resultTextField.setColumns(15);
			resultTextField.setText("Не считалась");
			resultTextField.setEditable(false);
			resultTextField.setBackground(Color.WHITE);
		}
		return resultTextField;
	}

	public JPanel getjResultPane() {
		if(jResultPane == null) {
			jResultPane = new JPanel(new FlowLayout());
			jResultPane.add(new JLabel("Итоговая стоимость: "));
			jResultPane.add(getResultTextField());
			jResultPane.add(getCalcButton());
		}
		return jResultPane;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		resultTextField.setText(calculator.calculate(model.fetch()).toString());
	}

}