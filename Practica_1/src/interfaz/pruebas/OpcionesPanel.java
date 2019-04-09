package interfaz.pruebas;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import interfaz.controlador.Opcion;

import java.awt.GridLayout;
import java.awt.TextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class OpcionesPanel extends JPanel {
	
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	/**
	 * Create the panel.
	 */
	public OpcionesPanel() {
		setLayout(new GridLayout(10, 2, 0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		add(lblNewLabel_2);
		
		textField = new JTextField();
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("New label");
		add(lblNewLabel);
		
		textField_3 = new JTextField();
		add(textField_3);
		textField_3.setColumns(10);
		
		lblNewLabel_5 = new JLabel("New label");
		add(lblNewLabel_5);
		
		textField_4 = new JTextField();
		add(textField_4);
		textField_4.setColumns(10);
		
		lblNewLabel_4 = new JLabel("New label");
		add(lblNewLabel_4);
		
		textField_5 = new JTextField();
		add(textField_5);
		textField_5.setColumns(10);
		
		lblNewLabel_6 = new JLabel("New label");
		add(lblNewLabel_6);
		
		textField_6 = new JTextField();
		add(textField_6);
		textField_6.setColumns(10);

		
	}
/*	public Opcion getOpciones() {
				try {
					double elit;
					if(textElitismo.isVisible())
						elit = 0;
					else 
						elit = Double.parseDouble(this.textElitismo.getText());
					return new Opcion(Integer.parseInt(this.textPobl.getText()), Integer.parseInt(this.textGen.getText()),
							Double.parseDouble(this.textCruces.getText()), Double.parseDouble(this.textMut.getText()), 
							Double.parseDouble(this.textPrec.getText()), elit, (String)this.comboSelec.getSelectedItem(), this.comboFunc.getSelectedIndex());
				}
				catch (Exception e) {
					return null;
				}
				
	}*/

}
