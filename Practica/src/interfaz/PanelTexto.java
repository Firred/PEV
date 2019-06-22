package interfaz;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import common.Cromosoma;
import common.Poblacion;
import interfaz.controlador.Controlador;
import interfaz.controlador.Observador;

public class PanelTexto extends JPanel implements Observador {

	private static final long serialVersionUID = 1L;
	
	private JTextArea area;
	
	public PanelTexto(Controlador ctrl) {
		this.setLayout(new BorderLayout());
		this.area = new JTextArea();
		this.add(this.area);
		this.area.setEditable(false);
		this.area.setLineWrap(true);
		this.area.setWrapStyleWord(true);
		this.add(new JScrollPane(this.area, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		ctrl.addObservador(this);
	}
	
	@Override
	public void start(int generaciones, int variables) {
		this.area.setText("");
	}

	@Override
	public void update(Poblacion pobl, Cromosoma mejorG) {}

	@Override
	public void finish(Cromosoma mejor, String texto) {
		if(!texto.isEmpty()) {
			this.area.setText(texto);		
		}
	}
}
