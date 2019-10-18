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
				ventana.setSize(80*6+4*6, 489);
				ventana.getCajaTexto().setText("hol");
			}
	}

}
