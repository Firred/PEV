package interfaz;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class PanelTablero extends JFrame {
	
	private char[][] tablero;
	
	public PanelTablero() {
		setSize(450, 450);
        setTitle("Tablero");
	}
	
	public void setTablero(char[][] tablero) {
		this.tablero = tablero;
		repaint();
	}
	
	@Override
	public void update(Graphics g) {
		paint(g);
	}
	
	@Override
	public void paint(Graphics g) {
		int tamF, tamC;
		int width = this.getContentPane().getWidth(), height = this.getContentPane().getHeight();
		int extraX = getWidth() - width, extraY =  getHeight() - height;
		
		if(height < width) {
			tamF = (height-tablero[0].length)/tablero[0].length;
			tamC = (height-tablero.length)/tablero.length;
		}
		else {
			tamF = (width-tablero[0].length)/tablero[0].length;
			tamC = (width-tablero.length)/tablero.length;
		}
		
		for (int f=0; f < tablero.length; f++){
            for (int c=0; c < tablero[0].length; c++){
                int x = c*tamC + c + extraX;
                int y = f*tamF + f + extraY;
/*                if (visible[f][c]==0 && estado==0){
                    g.setColor(Color.gray);
                    g.fillRect(x, y, 40, 40);
                }else if (visible[f][c]==2 && estado==0){
                    g.setColor(Color.blue);
                    g.fillRect(x, y, 40, 40);
                }else if (tablero[f][c] < 9){
                    g.setColor(Color.white);
                    g.fillRect(x, y, 40, 40);
                    if (tablero[f][c] > 0){
                        g.setColor(Color.black);
                        g.drawString("" + tablero[f][c], x+15, y+25);
                    }
                }else{
                    g.setColor(Color.red);
                    g.fillRect(x, y, 40, 40);
                }
                g.setColor(Color.DARK_GRAY);
                g.drawRect(x, y, 40, 40);*/
                
                switch (tablero[f][c]) {
				case '0':
					g.setColor(Color.white);
					break;
					
				case '1':
					g.setColor(Color.black);
					break;
					
				case '2':
					g.setColor(new Color(82, 0, 0));
					break;
					
				case '#':
					g.setColor(Color.red);
					break;
					
				default:
					g.clearRect(0, 0, getWidth(), getHeight());				
					System.out.println("Error en creacion de tablero. " + tablero[f][c] + " no es un valor aceptado.");
					return;
				}
                
                g.fillRect(x, y, tamC, tamF);
            }
        }		
	}
}
