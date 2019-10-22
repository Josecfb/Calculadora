package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.Ventana;

public class ManejadorMenu implements ActionListener{
	private Ventana ventana; 

	public ManejadorMenu(Ventana ventana) {
		this.ventana=ventana;
	}
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource()==ventana.getCientifica()) {
				ventana.getTipo().setText("Científica");
				ventana.setSize(326, 560);
				for (int b=0;b<ventana.getBoton().length;b++)
					ventana.getBoton()[b].setBounds(ventana.getBoton()[b].getX(),ventana.getBoton()[b].getY()+150,ventana.getBoton()[b].getWidth(),ventana.getBoton()[b].getHeight());
				for (int b=20;b<=31;b++)
					ventana.getBoton()[b].setVisible(true);
			}
			if (e.getSource()==ventana.getEstandar()) {
				ventana.getTipo().setText("Estandar");
				ventana.setSize(326,410);
				for (int b=0;b<ventana.getBoton().length;b++)
					ventana.getBoton()[b].setBounds(ventana.getBoton()[b].getX(),ventana.getBoton()[b].getY()-150,ventana.getBoton()[b].getWidth(),ventana.getBoton()[b].getHeight());
				for (int b=20;b<=32;b++)
					ventana.getBoton()[b].setVisible(false);
			}
			if (e.getSource()==ventana.getProgramador()) {
				ventana.getTipo().setText("Programador");
			}

	}

}
