package vista;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controlador.ManejadorEventos;

public class Ventana extends JFrame {
	private static final int ALTO_BOT = 50;
	private static final int ANCHO_BOT = 80;
	private JButton boton[] =new JButton[14];
	private JTextField cajaTexto;
	private JLabel labelAcu;
	Font miFuente=new Font("Calibri", Font.PLAIN, 22);
	public Ventana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,500);
		setLocationRelativeTo(null);
		setLayout(null);
		inicializarComponentes();
		setVisible(true);
	}
	
	private void inicializarComponentes() {
		
		//Pinta Botones numéricos
		int b=0;
		boton[b]=new JButton(String.valueOf(b));
		boton[b].setBounds(160, 400, ANCHO_BOT, ALTO_BOT);
		ponBoton(b);
		for(int f=1;f<=3;f++) 
			for (int c=1;c<=3;c++) {
				b++;
				boton[b]=new JButton(String.valueOf(b));
				boton[b].setBounds(80*c, 200+50*(4-f), ANCHO_BOT, ALTO_BOT);
				ponBoton(b);
			}
		b++; // 11 "la coma"
		boton[b]=new JButton(".");
		boton[b].setBounds(240, 400, ANCHO_BOT, ALTO_BOT);
		ponBoton(b);
		b++; // 12 "igual"
		boton[b]=new JButton("="); //Signo igual
		boton[b].setBounds(320, 400, ANCHO_BOT, ALTO_BOT);
		ponBoton(b);
		b++; //13 "suma"
		boton[b]=new JButton("+");
		boton[b].setBounds(320,350,ANCHO_BOT,ALTO_BOT);
		ponBoton(b);
		b++; //14 "resta"
		boton[b]=new JButton("-");
		boton[b].setBounds(320, 300, ANCHO_BOT, ALTO_BOT);
		ponBoton(b);
		
		//pantalla de la calculadora
		cajaTexto=new JTextField("");
		cajaTexto.setBounds(10, 10, 460, 40);
		cajaTexto.setHorizontalAlignment(JTextField.RIGHT);
		cajaTexto.setFont(miFuente);
		add(cajaTexto);
		labelAcu=new JLabel("");
		labelAcu.setBounds(10,50,300,40);
		add(labelAcu);
		
	
	}

	private void ponBoton(int b) {
		boton[b].setFont(miFuente);
		add(boton[b]);
	}
	
	public void EstablecerManejador(ManejadorEventos manejador) {
		for(int b=0;b<boton.length;b++) 
			boton[b].addActionListener(manejador);
	}

	public JTextField getCajaTexto() {
		return cajaTexto;
	}

	public void setCajaTexto(JTextField cajaTexto) {
		this.cajaTexto = cajaTexto;
	}

	public JLabel getLabelAcu() {
		return labelAcu;
	}

	public void setLabelAcu(JLabel labelAcu) {
		this.labelAcu = labelAcu;
	}

}
