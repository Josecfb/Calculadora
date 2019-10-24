package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import controlador.*;

public class Ventana extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final int ALTO_BOT = 0;
	private static final int ANCHO_BOT = 0;
	private static final int DY=10;
	private JButton boton[] =new JButton[38]; //Array de botones--------------------------------
	private JCheckBox interruptor;
	private JLabel labelAcu;
	private JLabel cajaTexto;
	private JLabel lBase[]=new JLabel[4];
	private ButtonGroup gSistemas;
	private JRadioButton rSistema[]=new JRadioButton[4];
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
		colores =new Colores(this,false);
		colores.pausar();
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
		tipo=new JMenu("Estandar");
		barra.add(tipo);
		estandar =new JMenuItem("Estándar");
		cientifica=new JMenuItem("Científica");
		programador=new JMenuItem("Programador");
		tipo.add(estandar);
		tipo.add(cientifica);
		tipo.add(programador);
		
		interruptor=new JCheckBox();
		interruptor.setBounds(10, 85, 40, 20);
		interruptor.setIcon(new ImageIcon(new ImageIcon("off.png").getImage().getScaledInstance(35, 18, Image.SCALE_DEFAULT)));
		interruptor.setName("interruptor");
		interruptor.setBackground(new Color(1,1,1));
		interruptor.setOpaque(false);
		interruptor.setBorder(null);	
		add(interruptor);
		interruptor.setVisible(false);
		interruptor.setVisible(true);
		
		
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
		b++; // 10 "la coma"
		boton[b]=new JButton(".");
		boton[b].setName(".");
		boton[b].setBounds(160, 320+DY, ANCHO_BOT, ALTO_BOT);
		b++; // 11 "igual"
		boton[b]=new JButton("="); //Signo igual
		boton[b].setName("=");
		boton[b].setBounds(240, 320+DY, ANCHO_BOT, ALTO_BOT);
		b++; //12 "suma"
		boton[b]=new JButton("+");
		boton[b].setName("+");
		boton[b].setBounds(240,270+DY,ANCHO_BOT,ALTO_BOT);
		b++; //13 "resta"
		boton[b]=new JButton("-");
		boton[b].setName("-");
		boton[b].setBounds(240, 220+DY, ANCHO_BOT, ALTO_BOT);
		b++;//14 "multiplicación"
		boton[b]=new JButton("*");
		boton[b].setName("*");
		boton[b].setBounds(240, 170+DY, ANCHO_BOT, ALTO_BOT);
		b++;//15 "didisión"
		boton[b]=new JButton("/");
		boton[b].setName("/");
		boton[b].setBounds(240, 120+DY, ANCHO_BOT, ALTO_BOT);
		b++; //16
		boton[b]=new JButton();
		boton[b].setBounds(160, 120+DY, ANCHO_BOT, ALTO_BOT);
		boton[b].setIcon(new ImageIcon(new ImageIcon("retro.png").getImage().getScaledInstance(28, 28, Image.SCALE_DEFAULT)));
		boton[b].setName("borra");
		b++;//17
		boton[b]=new JButton("±");
		boton[b].setBounds(0,320+DY,ANCHO_BOT,ALTO_BOT);
		boton[b].setName("masmenos");
		boton[b].setBackground(new Color(255,255,255));
		boton[b].setBackground(null);
		b++;//18
		boton[b]=new JButton("C");
		boton[b].setBounds(80,120+DY,ANCHO_BOT,ALTO_BOT);
		boton[b].setName("del");
		b++;//19
		boton[b]=new JButton("CE");
		boton[b].setBounds(0,120+DY,ANCHO_BOT,ALTO_BOT);
		boton[b].setName("CE");
		b++;//20
		boton[b]=new JButton();
		boton[b].setBounds(80,70+DY,ANCHO_BOT,ALTO_BOT);
		boton[b].setName("raizcuadrada");
		boton[b].setIcon(new ImageIcon(new ImageIcon("raiz.png").getImage().getScaledInstance(28, 28, Image.SCALE_DEFAULT)));
		boton[b].setVisible(false);
		b++;//21
		boton[b]=new JButton();
		boton[b].setBounds(240, 70+DY, ANCHO_BOT, ALTO_BOT);
		boton[b].setName("inversa");
		boton[b].setIcon(new ImageIcon(new ImageIcon("inversa.png").getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT)));
		boton[b].setVisible(false);
		b++;//22
		boton[b]=new JButton("");
		boton[b].setBounds(160,70+DY,ANCHO_BOT,ALTO_BOT);
		boton[b].setName("cuadrado");
		boton[b].setIcon(new ImageIcon(new ImageIcon("cuadrado.png").getImage().getScaledInstance(28, 28, Image.SCALE_DEFAULT)));
		boton[b].setVisible(false);
		b++;//23
		boton[b]=new JButton("n!");
		boton[b].setBounds(0,70+DY,ANCHO_BOT,ALTO_BOT);
		boton[b].setName("factorial");
		boton[b].setVisible(false);
		b++;//24
		boton[b]=new JButton();
		boton[b].setBounds(80,20+DY,ANCHO_BOT,ALTO_BOT);
		boton[b].setName("#");//raiz enesima
		boton[b].setIcon(new ImageIcon(new ImageIcon("raizenesima.png").getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT)));
		boton[b].setVisible(false);
		b++;//25
		boton[b]=new JButton();
		boton[b].setBounds(160,20+DY,ANCHO_BOT,ALTO_BOT);
		boton[b].setName("@");//potencia enesima
		boton[b].setIcon(new ImageIcon(new ImageIcon("potenciaenesima.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)));
		boton[b].setVisible(false);
		b++;//26
		boton[b]=new JButton("pri?");
		boton[b].setBounds(0,20+DY,ANCHO_BOT,ALTO_BOT);
		boton[b].setName("primo");//esprimo
		boton[b].setVisible(false);	
		b++;// 27
		boton[b]=new JButton("mod");
		boton[b].setBounds(240,20+DY,ANCHO_BOT,ALTO_BOT);
		boton[b].setName("R");//resto
		boton[b].setVisible(false);	
		b++;//28
		boton[b]=new JButton("pi");
		boton[b].setBounds(0,-30+DY,ANCHO_BOT,ALTO_BOT);
		boton[b].setName("pi");//pi
		boton[b].setVisible(false);	
		b++;//29
		boton[b]=new JButton("sen");
		boton[b].setBounds(80,-30+DY,ANCHO_BOT,ALTO_BOT);
		boton[b].setName("seno");//seno
		boton[b].setVisible(false);	
		b++;//30
		boton[b]=new JButton("cos");
		boton[b].setBounds(160,-30+DY,ANCHO_BOT,ALTO_BOT);
		boton[b].setName("coseno");//coseno
		boton[b].setVisible(false);	
		b++;//31
		boton[b]=new JButton("tan");
		boton[b].setBounds(240,-30+DY,ANCHO_BOT,ALTO_BOT);
		boton[b].setName("tangente");//pi
		boton[b].setVisible(false);	
		b++;//32
		boton[b]=new JButton("A");
		boton[b].setBounds(-160,170+DY,ANCHO_BOT,ALTO_BOT);
		boton[b].setName("A");//A
		boton[b].setEnabled(false);
		b++;//33
		boton[b]=new JButton("B");
		boton[b].setBounds(-160,220+DY,ANCHO_BOT,ALTO_BOT);
		boton[b].setName("B");//B
		boton[b].setEnabled(false);		
		b++;//34
		boton[b]=new JButton("C");
		boton[b].setBounds(-160,270+DY,ANCHO_BOT,ALTO_BOT);
		boton[b].setName("C");//C
		boton[b].setEnabled(false);
		b++;//35
		boton[b]=new JButton("D");
		boton[b].setBounds(-80,170+DY,ANCHO_BOT,ALTO_BOT);
		boton[b].setName("D");//D
		boton[b].setEnabled(false);
		b++;//36
		boton[b]=new JButton("E");
		boton[b].setBounds(-80,220+DY,ANCHO_BOT,ALTO_BOT);
		boton[b].setName("E");//E
		boton[b].setEnabled(false);
		b++;//37
		boton[b]=new JButton("F");
		boton[b].setBounds(-80,270+DY,ANCHO_BOT,ALTO_BOT);
		boton[b].setName("F");//C
		boton[b].setEnabled(false);
		for (b=0;b<boton.length;b++)
			ponBoton(b);

		for (int h=0;h<=50;h+=2) {
			try {
					Thread.sleep(8);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			for (b=0;b<boton.length;b++)
				boton[b].setSize(80, h);
		}
		//pantalla de la calculadora		
		cajaTexto=new JLabel();
		cajaTexto.setBounds(0, 30, 320, 50);
		cajaTexto.setOpaque(true);
		cajaTexto.setHorizontalAlignment(JTextField.RIGHT);
		cajaTexto.setFont(fuentePantalla);
		cajaTexto.requestFocus();
		add(cajaTexto);
		cajaTexto.setText("0");
		rSistema[0]=new JRadioButton("HEX");
		rSistema[1]=new JRadioButton("DEC");
		rSistema[2]=new JRadioButton("OCT");
		rSistema[3]=new JRadioButton("BIN");
		gSistemas=new ButtonGroup();
		for (int s=0;s<rSistema.length;s++) {
			System.out.println(rSistema[s].getText());
			rSistema[s].setBounds(-17, 120+s*30, 80, 30);
			rSistema[s].setOpaque(false);
			rSistema[s].setFont(miFuente);
			rSistema[s].setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
			rSistema[s].setBorderPainted(false);
			add(rSistema[s]);
			rSistema[s].setVisible(false);
			gSistemas.add(rSistema[s]);
			lBase[s]=new JLabel("SISTEMA");
			lBase[s].setBounds(80, 120+s*30, 406, 30);
			lBase[s].setOpaque(true);
			lBase[s].setFont(miFuente);
			add(lBase[s]);
			lBase[s].setVisible(false);
		}
		rSistema[1].setSelected(true);
		rSistema[1].setBorderPainted(true);
		lBase[0].setName("hex");
		lBase[1].setName("dec");
		lBase[2].setName("oct");
		lBase[3].setName("bin");
		
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
		for (int s=0;s<rSistema.length;s++)
			rSistema[s].addActionListener(manejador);
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


	public JRadioButton[] getSistema() {
		return rSistema;
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

	public JLabel[] getlBase() {
		return lBase;
	}	
	
}
