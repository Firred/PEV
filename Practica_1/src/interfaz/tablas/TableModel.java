package interfaz.tablas;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import interfaz.controlador.Controlador;
import interfaz.controlador.Observador;

public abstract class TableModel<T> extends DefaultTableModel implements Observador {
	protected String[] columnIds;
	protected List<T> list;
	
	public TableModel (String[] columnEventsId, Controlador ctrl) {
		this.list = null;
		this.columnIds = columnEventsId;
		ctrl.addObservador(this);
	}
	
	@Override
	public String getColumnName(int col) { 
		return this.columnIds[col]; 
	}
	
	@Override
	public int getColumnCount() {
		return this.columnIds.length;
	}
	
	@Override
	public int getRowCount() {
		return this.list == null ? 0 : this.list.size();
	}
	
	public void setColumns(String[] columns) {
		this.columnIds = columns;
	}
}
