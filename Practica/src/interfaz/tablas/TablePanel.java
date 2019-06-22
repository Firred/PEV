package interfaz.tablas;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TablePanel<T> extends JPanel {
	private TableModel<T> model;
	
	public TablePanel(String borderId, TableModel<T> model) {
		this.setLayout(new GridLayout(1,1));
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK,2), borderId));
		this.model = model;
		JTable table = new JTable(this.model);
		table.setEnabled(false);
		this.add(new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
	}
}
