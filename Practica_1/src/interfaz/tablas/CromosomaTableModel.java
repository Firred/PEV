package interfaz.tablas;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import common.Cromosoma;
import common.Poblacion;
import interfaz.controlador.Controlador;

public class CromosomaTableModel extends TableModel<Cromosoma> {
	public CromosomaTableModel(String[] columnEventId, Controlador ctrl) {
		super(columnEventId, ctrl);
	}
	
	public CromosomaTableModel(Controlador ctrl) {
		super(new String[] {}, ctrl);
	}
	
	@Override
	public Object getValueAt(int indiceFil, int indiceCol) {
		Object s = null;
		
		if(indiceCol == 0)
			s = this.list.get(indiceFil).getX();
		else {
			int i = 1, max = this.list.get(indiceFil).getNumGenes();
			
			while(i <= max && i <= indiceCol) {
				if (indiceCol == i)
					s = this.list.get(indiceFil).getGen(i-1).getCaracteristica();
				
				i++;
			}
		}
		 
		return s;
	}
	
	@Override
	public void start(int generaciones, int variables) {		
		String[] columns = new String[variables+1];
		this.list = new ArrayList<>();
		
		columns[0] = "Atributo";
		
		for(int i = 1; i <= variables; i++) {
			columns[i] = "X" + i;
		}
		
		setColumns(columns);
	}
	
	@Override
	public void update(Poblacion pobl, Cromosoma mejorG) {
/*		if(pobl.getGeneracion() == 100) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					list.add(pobl.getMejor());
					fireTableStructureChanged();	
				}
			});	
		}*/
	}

	@Override
	public void finish(Cromosoma mejor, String texto) {
		if(texto.isEmpty()) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					list.add(mejor);
					fireTableStructureChanged();	
				}
			});		
		}
	}




}
