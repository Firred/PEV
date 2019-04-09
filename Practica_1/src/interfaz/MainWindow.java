package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;

import practica1.AlgoritmoGenetico;
import javax.swing.JPanel;

import common.Cromosoma;
import interfaz.controlador.Controlador;
import interfaz.tablas.CromosomaTableModel;
import interfaz.tablas.TablePanel;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;

public class MainWindow {

	private JFrame frame;
	
	private PanelCentral panelCentral;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Controlador ctrl = Controlador.getInstance();
//		AlgoritmoGenetico aG= new AlgoritmoGenetico();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow(ctrl);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow(Controlador ctrl) {
		initialize(ctrl);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Controlador ctrl) {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ConfigPanel<AlgoritmoGenetico> panel = FactoriaOpciones.getConfigPanel();
		panel.setTarget(ctrl.getAG());
		panel.initialize();
		frame.getContentPane().add(panel, BorderLayout.WEST);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new GridLayout(0, 5, 0, 0));
		
		JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				panelCentral.setVisible(false);
				panelCentral = new PanelCentral(ctrl);
				frame.getContentPane().add(panelCentral, BorderLayout.CENTER);
				ctrl.execute();
			}
		});
		panel_1.add(startButton);
		
		panelCentral = new PanelCentral(ctrl);
		frame.getContentPane().add(panelCentral, BorderLayout.CENTER);
	}

}
