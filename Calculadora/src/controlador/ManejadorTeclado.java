package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import vista.Ventana;

public class ManejadorTeclado implements KeyListener{
	private Ventana ventana;
	
	
	public ManejadorTeclado(Ventana ventana) {
		super();
		this.ventana = ventana;
	}
	// Al pulsar teclas hace click en los botones
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==10)
			ventana.getBoton()[11].doClick();
		for (int b=0;b<ventana.getBoton().length-1;b++)
			if(e.getKeyChar()==ventana.getBoton()[b].getName().charAt(0)) 
				ventana.getBoton()[b].doClick();
		ventana.requestFocus();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {

		
	}

}
