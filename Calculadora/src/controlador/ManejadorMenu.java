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
			int ix=0,iy=0;
			//CIENTIFICA
			if (e.getSource()==ventana.getCientifica()) {
				if (ventana.getTipo().getText().equals("Estandar")) {
					ix=0;
					iy=150;
				}
				if (ventana.getTipo().getText().equals("Programador")) {
					ix=-160;
					iy=30;
					ponBotones(32, 37, false);
					basesQP(false);
					ventana.getCajaTexto().setSize(320, 50);
					
				}
				mueveBotones(ix, iy);
				ponBotones(20, 31, true); 
				ventana.setSize(326, 560);
				ventana.getTipo().setText("Científica");
				quitaBotonesProgramador(true);
				ventana.setEsProgramador(false);
				
			}
			//ESTANDAR
			if (e.getSource()==ventana.getEstandar()) {
				if (ventana.getTipo().getText().equals("Científica")) {
					ix=0;
					iy=-150;
					ponBotones(20, 31, false);
				}	
				if (ventana.getTipo().getText().equals("Programador")) {
					ix=-160;
					iy=-120;
					ponBotones(32, 37, false);
					basesQP(false);
					ventana.getCajaTexto().setSize(320, 50);
				}
				mueveBotones(ix, iy);
				ventana.getTipo().setText("Estandar");
				ventana.setSize(326,410);
				quitaBotonesProgramador(true);
				ventana.setEsProgramador(false);
			}
			//PROGRAMADOR
			if (e.getSource()==ventana.getProgramador()) {
				if (ventana.getTipo().getText().equals("Estandar")) {
					ix=160;
					iy=120;
				}
				if (ventana.getTipo().getText().equals("Científica")) {
					ix=160;
					iy=-30;
					ponBotones(20, 31, false);
				}
				ponBotones(32, 37, true);
				mueveBotones(ix, iy);
				basesQP(true);
				ventana.setSize(486, 530);
				ventana.getTipo().setText("Programador");
				quitaBotonesProgramador(false);
				ventana.getCajaTexto().setSize(479, 50);
				ventana.getBarra().setSize(479, 30);
				ventana.setEsProgramador(true);
			}
		}
		public void mueveBotones(int ix, int iy) {
			System.out.println(ix+" "+iy);
			for (int b=0;b<ventana.getBoton().length;b++)
				ventana.getBoton()[b].setBounds(ventana.getBoton()[b].getX()+ix,ventana.getBoton()[b].getY()+iy,ventana.getBoton()[b].getWidth(),ventana.getBoton()[b].getHeight());
			
		}
		public void ponBotones(int bi,int bf,boolean visible) {
			for (int b=bi;b<=bf;b++)
				ventana.getBoton()[b].setVisible(visible);
		}
		public void quitaBotonesProgramador(boolean visible) {
			ventana.getBoton()[10].setEnabled(visible);
			ventana.getBoton()[15].setEnabled(visible);
			ventana.getBoton()[17].setEnabled(visible);
		}
		
		public void basesQP(boolean visible) {
			for (int b=0;b<4;b++) {
				ventana.getlBase()[b].setVisible(visible);
				ventana.getSistema()[b].setVisible(visible);
			}
		}
}


