package interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

import common.Cromosoma;
import interfaz.controlador.Controlador;
import interfaz.tablas.CromosomaTableModel;
import interfaz.tablas.TablePanel;

public class PanelCentral extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PanelCentral (Controlador ctrl) {
		this.setLayout(new GridLayout(2, 1));
		JPanel panel_1 = new PanelGrafica();
		CromosomaTableModel cromosomaTable = new CromosomaTableModel(ctrl);
		TablePanel<Cromosoma> panel_2 = new TablePanel<>("Mejor Cromosoma", cromosomaTable);
		
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new BorderLayout());
		
		this.add(panel_1, BorderLayout.CENTER);
		this.add(panel_2, BorderLayout.SOUTH);
	}
}
