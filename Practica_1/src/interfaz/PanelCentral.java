package interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

import common.Cromosoma;
import common.Poblacion;
import interfaz.controlador.Controlador;
import interfaz.controlador.Observador;
import interfaz.tablas.CromosomaTableModel;
import interfaz.tablas.TablePanel;

public class PanelCentral extends JPanel implements Observador {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TablePanel<Cromosoma> panel_2;
	private JPanel panel_3;
	private JPanel panelInferior;

	public PanelCentral (Controlador ctrl) {
		ctrl.addObservador(this);
		
		this.setLayout(new GridLayout(2, 1));
		JPanel panel_1 = new PanelGrafica();
		
		CromosomaTableModel cromosomaTable = new CromosomaTableModel(ctrl);
		panel_2 = new TablePanel<>("Mejor Cromosoma", cromosomaTable);
		
		panel_3 = new PanelTexto(ctrl);
		
		panelInferior = new JPanel();
		panelInferior.setLayout(new GridLayout(1, 1));
		
		this.add(panel_1);
		panelInferior.add(panel_2);
		this.add(panelInferior);	
	}

	@Override
	public void start(int generaciones, int variables) {}

	@Override
	public void update(Poblacion pobl, Cromosoma mejorG) {}

	@Override
	public void finish(Cromosoma mejor, String texto) {
		if(texto.isEmpty()) {
			panelInferior.remove(panel_3);
			panelInferior.add(panel_2);
			panelInferior.repaint();
		}
		else {
			panelInferior.remove(panel_2);
			panelInferior.add(panel_3);
			panelInferior.repaint();
		}
	}
}
