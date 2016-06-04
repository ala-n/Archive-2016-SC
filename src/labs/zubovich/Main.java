package labs.zubovich;

import labs.zubovich.calculator.Calculator;
import labs.zubovich.calculator.RowParam;
import labs.zubovich.calculator.TypicalNormCalculator;
import labs.zubovich.dbutil.GlobalCache;
import labs.zubovich.dbutil.TableParser;
import labs.zubovich.ui.MultyLineCellRenderer;
import labs.zubovich.ui.TypicalNormTM;
import labs.zubovich.ui.ValueTableCellView;

import javax.swing.*;
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
		this.setTitle("Подсчет стоимости проекта по типовам нормам");
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
			jTable.setRowHeight(34);
			jTable.setCellSelectionEnabled(false);

			jTable.getTableHeader().getColumnModel().getColumn(0).setCellRenderer(new MultyLineCellRenderer(false));
			jTable.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(150);


			ValueTableCellView valueTableCellView = new ValueTableCellView();
			jTable.getTableHeader().getColumnModel().getColumn(1).setPreferredWidth(300);
			jTable.getTableHeader().getColumnModel().getColumn(1).setCellEditor(valueTableCellView);
			jTable.getTableHeader().getColumnModel().getColumn(1).setCellRenderer(valueTableCellView);

			jTable.setRowHeight(Arrays.asList(RowParam.values()).indexOf(RowParam.DIFFICULTY_UPPER_COEF), 100);
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
		jTable.editingStopped(null);
		resultTextField.setText(calculator.calculate(model.fetch()).toString());
	}

}