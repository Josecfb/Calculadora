package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import controlador.*;

public class Ventana extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final int ALTO_BOT = 0;
	private static final int ANCHO_BOT = 0;
	private static final int DY=10;
	private JButton boton[] =new JButton[32]; //Array de botones--------------------------------
	private JButton interruptor;
	private JLabel labelAcu;
	private JLabel cajaTexto;
	private JMenuBar barra;
	private JMenu tipo;
	private JMenuItem estandar,cientifica,programador;
	Font miFuente=new Font("Calibri", Font.PLAIN, 22);
	Font fuentePantalla=new Font("Calibri", Font.PLAIN, 30);
	boolean luces=true;
	boolean suspender=false;
	Colores colores;
	
	public Ventana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);
		setResizable(false);
		setSize(326,410);
		setLocationRelativeTo(null);
		inicializarComponentes();
		colores =new Colores(this);
	}
	public void pausar() {
		colores.pausar();

	}
	public void reanudar() {
		colores.reanudar();

	}

	private void inicializarComponentes() {
		barra=new JMenuBar();
		barra.setBounds(0, 0, this.getWidth(), 30);
		add(barra);
		tipo=new JMenu("Estandard");
		barra.add(tipo);
		estandar =new JMenuItem("Estándar");
		cientifica=new JMenuItem("Científica");
		programador=new JMenuItem("Programador");
		tipo.add(estandar);
		tipo.add(cientifica);
		tipo.add(programador);
		
		interruptor=new JButton();
		interruptor.setBounds(10, 90, 40, 30);
		interruptor.setIcon(new ImageIcon(new ImageIcon("off.png").getImage().getScaledInstance(35, 25, Image.SCALE_DEFAULT)));
		interruptor.setName("interruptor");
		interruptor.setBackground(new Color(1,1,1));
		interruptor.setOpaque(false);
		interruptor.setBorder(null);	
		interruptor.setVisible(false);
		interruptor.setVisible(true);
		add(interruptor);
		
		//Pinta Botones numéricos
		int b=0;
		boton[b]=new JButton(String.valueOf(b));
		boton[b].setName(String.valueOf(b));
		boton[b].setBounds(80, 320+DY, ANCHO_BOT, ALTO_BOT);
		for(int f=1;f<=3;f++) 
			for (int c=1;c<=3;c++) {
				b++;
				boton[b]=new JButton(String.valueOf(b));
				boton[b].setName(String.valueOf(b));
				boton[b].setBounds(80*(c-1), 120+DY+50*(4-f), ANCHO_BOT, ALTO_BOT);
			}
		b++; // 11 "la coma"
		boton[b]=new JButton(".");
		boton[b].setName(".");
		boton[b].setBounds(160, 320+DY, ANCHO_BOT, ALTO_BOT);
		b++; // 12 "igual"
		boton[b]=new JButton("="); //Signo igual
		boton[b].setName("=");
		boton[b].setBounds(240, 320+DY, ANCHO_BOT, ALTO_BOT);
		b++; //13 "suma"
		boton[b]=new JButton("+");
		boton[b].setName("+");
		boton[b].setBounds(240,270+DY,ANCHO_BOT,ALTO_BOT);
		b++; //14 "resta"
		boton[b]=new JButton("-");
		boton[b].setName("-");
		boton[b].setBounds(240, 220+DY, ANCHO_BOT, ALTO_BOT);
		b++;//15 "multiplicación"
		boton[b]=new JButton("*");
		boton[b].setName("*");
		boton[b].setBounds(240, 170+DY, ANCHO_BOT, ALTO_BOT);
		b++;//16 "didisión"
		boton[b]=new JButton("/");
		boton[b].setName("/");
		boton[b].setBounds(240, 120+DY, ANCHO_BOT, ALTO_BOT);
		b++; //17
		ImageIcon imagen;
		Icon icono;
		imagen=new ImageIcon("retro.png");
		icono=new ImageIcon(imagen.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		boton[b]=new JButton();
		boton[b].setBounds(160, 120+DY, ANCHO_BOT, ALTO_BOT);
		boton[b].setIcon(icono);
		boton[b].setName("borra");
		b++;//18
		boton[b]=new JButton("±");
		boton[b].setBounds(0,320+DY,ANCHO_BOT,ALTO_BOT);
		boton[b].setName("masmenos");
		b++;//19
		boton[b]=new JButton("C");
		boton[b].setBounds(80,120+DY,ANCHO_BOT,ALTO_BOT);
		boton[b].setName("C");
		b++;//20
		boton[b]=new JButton("CE");
		boton[b].setBounds(0,120+DY,ANCHO_BOT,ALTO_BOT);
		boton[b].setName("CE");
		b++;//21
		boton[b]=new JButton("r2");
		boton[b].setBounds(80,70+DY,ANCHO_BOT,ALTO_BOT);
		boton[b].setName("raizcuadrada");
		boton[b].setVisible(false);
		b++;//22
		boton[b]=new JButton();
		boton[b].setBounds(240, 70+DY, ANCHO_BOT, ALTO_BOT);
		boton[b].setName("inversa");
		boton[b].setIcon(new ImageIcon(new ImageIcon("inversa.png").getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT)));
		boton[b].setVisible(false);
		b++;//23
		boton[b]=new JButton("x^2");
		boton[b].setBounds(160,70+DY,ANCHO_BOT,ALTO_BOT);
		boton[b].setName("cuadrado");
		boton[b].setVisible(false);
		b++;//24
		boton[b]=new JButton("n!");
		boton[b].setBounds(0,70+DY,ANCHO_BOT,ALTO_BOT);
		boton[b].setName("factorial");
		boton[b].setVisible(false);
		b++;//25
		boton[b]=new JButton();
		boton[b].setBounds(80,20+DY,ANCHO_BOT,ALTO_BOT);
		boton[b].setName("#");//raiz enesima
		boton[b].setIcon(new ImageIcon(new ImageIcon("raizenesima.png").getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT)));
		boton[b].setVisible(false);
		b++;//26
		boton[b]=new JButton();
		boton[b].setBounds(160,20+DY,ANCHO_BOT,ALTO_BOT);
		boton[b].setName("@");//potencia enesima
		boton[b].setIcon(new ImageIcon(new ImageIcon("potenciaenesima.png").getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT)));
		boton[b].setVisible(false);
		b++;//27
		boton[b]=new JButton("pri?");
		boton[b].setBounds(0,20+DY,ANCHO_BOT,ALTO_BOT);
		boton[b].setName("primo");//esprimo
		boton[b].setVisible(false);	
		b++;// 28
		boton[b]=new JButton("mod");
		boton[b].setBounds(240,20+DY,ANCHO_BOT,ALTO_BOT);
		boton[b].setName("R");//resto
		boton[b].setVisible(false);	
		b++;//29
		boton[b]=new JButton("pi");
		boton[b].setBounds(0,-30+DY,ANCHO_BOT,ALTO_BOT);
		boton[b].setName("P");//pi
		boton[b].setVisible(false);	
		b++;//30
		boton[b]=new JButton("sen");
		boton[b].setBounds(80,-30+DY,ANCHO_BOT,ALTO_BOT);
		boton[b].setName("seno");//seno
		boton[b].setVisible(false);	
		b++;//31
		boton[b]=new JButton("cos");
		boton[b].setBounds(160,-30+DY,ANCHO_BOT,ALTO_BOT);
		boton[b].setName("coseno");//coseno
		boton[b].setVisible(false);	
		b++;//32
		boton[b]=new JButton("tan");
		boton[b].setBounds(240,-30+DY,ANCHO_BOT,ALTO_BOT);
		boton[b].setName("P");//pi
		boton[b].setVisible(false);	

		for (b=0;b<boton.length;b++)
			ponBoton(b);

		for (int h=0;h<=50;h+=2)
			for (b=0;b<boton.length;b++)	{
				boton[b].setSize(80, h);
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		//pantalla de la calculadora		
		cajaTexto=new JLabel("");
		cajaTexto.setBounds(0, 30, 320, 50);
		cajaTexto.setOpaque(true);
		cajaTexto.setHorizontalAlignment(JTextField.RIGHT);
		cajaTexto.setFont(fuentePantalla);
		cajaTexto.requestFocus();
		add(cajaTexto);
		
	}

	public JButton[] getBoton() {
		return boton;
	}

	private void ponBoton(int b) {
		boton[b].setFont(miFuente);
		add(boton[b]);
	}
	
	public void EstablecerManejador(ManejadorEventos manejador) {

		for(int b=0;b<boton.length;b++) 
			boton[b].addActionListener(manejador);
		interruptor.addActionListener(manejador);
	}
	public void EstableceManejadorMenu(ManejadorMenu manejador) {
		estandar.addActionListener(manejador);
		cientifica.addActionListener(manejador);
		programador.addActionListener(manejador);
	}

	public void EstablecerManejadorTeclado(ManejadorTeclado manejador) {
		this.addKeyListener(manejador);
	}

	public JLabel getCajaTexto() {
		return cajaTexto;
	}

	public void setCajaTexto(JLabel cajaTexto) {
		this.cajaTexto = cajaTexto;
	}

	public JLabel getLabelAcu() {
		return labelAcu;
	}

	public void setLabelAcu(JLabel labelAcu) {
		this.labelAcu = labelAcu;
	}

	public JMenuItem getEstandar() {
		return estandar;
	}

	public JMenuItem getCientifica() {
		return cientifica;
	}

	public JMenuItem getProgramador() {
		return programador;
	}

	public JMenu getTipo() {
		return tipo;
	}

	public JMenuBar getBarra() {
		return barra;
	}	
}
