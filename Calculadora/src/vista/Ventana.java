package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.LineListener;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
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

public class Ventana extends JFrame{

	private static final String SRC_IMG = "src/img/";
	private static final long serialVersionUID = 8410119930200270173L;
	private static final int DY=10;
	private JFrame aviso;
	private JButton boton[] =new JButton[38]; //Array de botones--------------------------------
	private JButton incrementoTS[]=new JButton[4];
	private JLabel visorIncre[]=new JLabel[2];
	private JCheckBox interruptor[]=new JCheckBox[3];
	private JLabel eInterruptor[]=new JLabel[3];
	private JLabel labelAcu;
	private JLabel visorCalc;
	private JLabel[] codigoMatrix=new JLabel[60];
	private JLabel lBase[]=new JLabel[4];
	private ButtonGroup gSistemas;
	private JRadioButton rSistema[]=new JRadioButton[4];
	private JMenuBar barra;
	private JMenu tipo;
	private JMenuItem estandar,cientifica,programador;
	private boolean esProgramador=false;
	private boolean voz=false;
	
	Font miFuente=new Font("Calibri", Font.PLAIN, 22);
	Font intFuente=new Font("Calibri", Font.PLAIN, 11);
	Font fuentePantalla=new Font("Calibri", Font.PLAIN, 30);
	
	boolean luces=true;
	boolean suspender=false;
	Colores colores;
	
	public Ventana() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		setLayout(null);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(SRC_IMG+"icono.png"));
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
		double w=0,he=0;
		 do{
			he+=2;
			w+=0.795*2;
			this.setSize((int)w,(int)he);
			setLocationRelativeTo(null);
		}while (he<410);
		this.setSize(326,410);
		Dimension tamaPantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int anchoPantalla=(int)tamaPantalla.getWidth();
		int altoPantalla=(int)tamaPantalla.getHeight();
		Icon off=new ImageIcon(new ImageIcon(SRC_IMG+"off.png").getImage().getScaledInstance(35, 18, Image.SCALE_DEFAULT));
		
		//Menús ------------------------------
		barra=new JMenuBar();
		barra.setBounds(0, 0, this.getWidth(), 30);
		add(barra);
		tipo=new JMenu("Estandar");
		estandar =new JMenuItem("Estándar");
		cientifica=new JMenuItem("Científica");
		programador=new JMenuItem("Programador");
		barra.add(tipo);
		tipo.add(estandar);
		tipo.add(cientifica);
		tipo.add(programador);
		
		//Crea interruptores
		for (int i=0;i<interruptor.length;i++) {
			interruptor[i]=new JCheckBox();
			interruptor[i].setBounds(i*50+5,85,40,20);
			interruptor[i].setIcon(off);
			interruptor[i].setOpaque(false);
			add(interruptor[i]);
			eInterruptor[i]=new JLabel();
			eInterruptor[i].setBounds(50*i+10, 100, 80, 20);
			eInterruptor[i].setFont(intFuente);
			add(eInterruptor[i]);
		}
		
		interruptor[0].setName("voz");
		interruptor[0].setVisible(true);
		eInterruptor[0].setText("Sonido");
		eInterruptor[0].setVisible(true);
		interruptor[1].setName("color");
		interruptor[1].setVisible(true);
		eInterruptor[1].setText("ITS");
		eInterruptor[1].setVisible(true);
		interruptor[2].setName("teseracto");
		interruptor[2].setVisible(false);
		eInterruptor[2].setText("Teseracto");
		eInterruptor[2].setVisible(false);
		
		//Pinta Botones numéricos
		int b=0; //el cero-----------------------------
		boton[b]=new JButton(String.valueOf(b));
		boton[b].setName(String.valueOf(b));
		boton[b].setBounds(80, 320+DY, 0, 0);
		//del 1 al 9---------------------------------
		for(int fila=1;fila<=3;fila++) 
			for (int c=1;c<=3;c++) {
				b++;
				boton[b]=new JButton(String.valueOf(b));
				boton[b].setName(String.valueOf(b));
				boton[b].setBounds(80*(c-1), 120+DY+50*(4-fila), 0, 0);
			}
		b++; // 10 "la coma"-----------------------------------
		boton[b]=new JButton(".");
		boton[b].setName(".");
		boton[b].setBounds(160, 320+DY, 0, 0);
		b++; // 11 "igual"
		boton[b]=new JButton("="); //Signo igual
		boton[b].setName("=");
		boton[b].setBounds(240, 320+DY, 0, 0);
		b++; //12 "suma"
		boton[b]=new JButton("+");
		boton[b].setName("+");
		boton[b].setBounds(240,270+DY,0,0);
		b++; //13 "resta"
		boton[b]=new JButton("-");
		boton[b].setName("-");
		boton[b].setBounds(240, 220+DY, 0, 0);
		b++;//14 "multiplicación"
		boton[b]=new JButton("*");
		boton[b].setName("*");
		boton[b].setBounds(240, 170+DY, 0, 0);
		b++;//15 "didisión"
		boton[b]=new JButton("/");
		boton[b].setName("/");
		boton[b].setBounds(240, 120+DY, 0, 0);
		b++; //16 "borra"
		boton[b]=new JButton();
		boton[b].setBounds(160, 120+DY, 0, 0);
		iconoBoton(b, "retro.png", 28);
		boton[b].setName("borra");
		b++;//17 "masmenos"
		boton[b]=new JButton("±");
		boton[b].setBounds(0,320+DY,0,0);
		boton[b].setName("masmenos");
		boton[b].setBackground(new Color(255,255,255));
		boton[b].setBackground(null);
		b++;//18 
		boton[b]=new JButton("C");
		boton[b].setBounds(80,120+DY,0,0);
		boton[b].setName("del");
		b++;//19
		boton[b]=new JButton("CE");
		boton[b].setBounds(0,120+DY,0,0);
		boton[b].setName("CE");
		b++;//20
		boton[b]=new JButton();
		boton[b].setBounds(80,70+DY,0,0);
		boton[b].setName("raizcuadrada");
		iconoBoton(b, "raiz.png", 28);
		b++;//21
		boton[b]=new JButton();
		boton[b].setBounds(240, 70+DY, 0, 0);
		boton[b].setName("inversa");
		iconoBoton(b,"inversa.png",35);
		b++;//22
		boton[b]=new JButton("");
		boton[b].setBounds(160,70+DY,0,0);
		boton[b].setName("cuadrado");
		iconoBoton(b, "cuadrado.png", 28);
		b++;//23
		boton[b]=new JButton("n!");
		boton[b].setBounds(0,70+DY,0,0);
		boton[b].setName("factorial");
		b++;//24
		boton[b]=new JButton();
		boton[b].setBounds(80,20+DY,0,0);
		boton[b].setName("#");//raiz enesima
		iconoBoton(b, "raizenesima.png", 35);
		b++;//25
		boton[b]=new JButton();
		boton[b].setBounds(160,20+DY,0,0);
		boton[b].setName("@");//potencia enesima
		iconoBoton(b, "potenciaenesima.png", 25);
		b++;//26
		boton[b]=new JButton("pri?");
		boton[b].setBounds(0,20+DY,0,0);
		boton[b].setName("primo");//esprimo
		b++;// 27
		boton[b]=new JButton("mod");
		boton[b].setBounds(240,20+DY,0,0);
		boton[b].setName("R");//resto
		b++;//28
		boton[b]=new JButton();
		boton[b].setBounds(0,-30+DY,0,0);
		boton[b].setName("pi");//pi
		iconoBoton(b, "pi.png", 25);
		b++;//29
		boton[b]=new JButton("sen");
		boton[b].setBounds(80,-30+DY,0,0);
		boton[b].setName("seno");//seno
		b++;//30
		boton[b]=new JButton("cos");
		boton[b].setBounds(160,-30+DY,0,0);
		boton[b].setName("coseno");//coseno
		b++;//31
		boton[b]=new JButton("tan");
		boton[b].setBounds(240,-30+DY,0,0);
		boton[b].setName("tangente");//pi
		b++;//32
		boton[b]=new JButton("A");
		boton[b].setBounds(-160,170+DY,0,0);
		boton[b].setName("A");//A
		b++;//33
		boton[b]=new JButton("B");
		boton[b].setBounds(-160,220+DY,0,0);
		boton[b].setName("B");//B
		b++;//34
		boton[b]=new JButton("C");
		boton[b].setBounds(-160,270+DY,0,0);
		boton[b].setName("C");//C
		b++;//35
		boton[b]=new JButton("D");
		boton[b].setBounds(-80,170+DY,0,0);
		boton[b].setName("D");//D
		b++;//36
		boton[b]=new JButton("E");
		boton[b].setBounds(-80,220+DY,0,0);
		boton[b].setName("E");//E
		b++;//37
		boton[b]=new JButton("F");
		boton[b].setBounds(-80,270+DY,0,0);
		boton[b].setName("F");//C
		for(int i=0;i<incrementoTS.length;i++)
		for(b=20;b<=37;b++)
			boton[b].setVisible(false);
		for (b=32;b<=37;b++)
			boton[b].setEnabled(false);
		for (b=0;b<boton.length;b++)
			ponBoton(b);
		//------------------------tamaño botones----------------------
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
		visorCalc=new JLabel();
		visorCalc.setBounds(0, 30, 320, 50);
		visorCalc.setOpaque(true);
		visorCalc.setHorizontalAlignment(JTextField.RIGHT);
		visorCalc.setBackground(new Color(250,250,250));
		visorCalc.setFont(fuentePantalla);
		visorCalc.requestFocus();
		add(visorCalc);
		visorCalc.setText("0");
		
		//Crea los visores en distintas bases de numeración
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
			lBase[s]=new JLabel("0");
			lBase[s].setBounds(80, 120+s*30, 386, 30);
			lBase[s].setOpaque(true);
//			lBase[s].setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
			lBase[s].setBorder(null);
			lBase[s].setFont(miFuente);
			add(lBase[s]);
			lBase[s].setVisible(false);
			
		}
		lBase[1].setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
		rSistema[1].setSelected(true);
		rSistema[1].setBorderPainted(true);
		rSistema[1].setFont(new Font("Calibri", Font.BOLD, 22));
		lBase[0].setName("hex");
		lBase[1].setName("dec");
		lBase[2].setName("oct");
		lBase[3].setName("bin");
		BufferedInputStream myStream=null;
		Font fuenteMatrix=null;
		try {
			myStream = new BufferedInputStream(new FileInputStream("src/matrix code nfi.ttf"));
			fuenteMatrix = Font.createFont(Font.TRUETYPE_FONT, myStream);
			} catch (FontFormatException | IOException e) {
				e.printStackTrace();
			}
		fuenteMatrix = fuenteMatrix.deriveFont(Font.PLAIN, 26);
		
		//Etiquetas que serán llamada desde la clase Matrix
		
		for (int l=0;l<codigoMatrix.length;l++) {
			codigoMatrix[l]=new JLabel();
			codigoMatrix[l].setBounds(0, l*30, anchoPantalla, 30);
			codigoMatrix[l].setOpaque(true);
			codigoMatrix[l].setVisible(false);
			codigoMatrix[l].setBackground(new Color(0,0,0));
			codigoMatrix[l].setForeground(new Color(0,255,0));
			codigoMatrix[l].setFont(fuenteMatrix);
			add(codigoMatrix[l]);
		}
	}
	private void iconoBoton(int b,String nombre,int tam) {
		boton[b].setIcon(new ImageIcon(new ImageIcon(SRC_IMG+nombre).getImage().getScaledInstance(tam, tam, Image.SCALE_DEFAULT)));
	}

	public JButton[] getBoton() {
		return boton;
	}

	private void ponBoton(int b) {
		boton[b].setFont(miFuente);
		add(boton[b]);
	}
	
	
	//Establece manejador para botones
	public void EstablecerManejador(ManejadorEventos manejador) {

		for(int b=0;b<boton.length;b++) 
			boton[b].addActionListener(manejador);
		for (int i=0;i<interruptor.length;i++)
			interruptor[i].addActionListener(manejador);
		for (int s=0;s<rSistema.length;s++)
			rSistema[s].addActionListener(manejador);
	}
	//Establece manejador para elementos del menú
	public void EstableceManejadorMenu(ManejadorMenu manejador) {
		estandar.addActionListener(manejador);
		cientifica.addActionListener(manejador);
		programador.addActionListener(manejador);
	}
	//Establece manejador para teclado
	public void EstablecerManejadorTeclado(ManejadorTeclado manejador) {
		this.addKeyListener(manejador);
	}
	////Establece manejador para eventos de la ventana (cerrar ventana)
	public void EstablecerManejadorVentana(ManejadorVentana manejador) {
		this.addWindowListener(manejador);
	}

	public JLabel getCajaTexto() {
		return visorCalc;
	}
	public void setCajaTexto(JLabel cajaTexto) {
		this.visorCalc = cajaTexto;
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
	public JLabel[] getEInterruptor() {
		return eInterruptor;
	}
	public void setEsProgramador(boolean esProgramador) {
		this.esProgramador = esProgramador;
	}
	public boolean isEsProgramador() {
		return esProgramador;
	}
	public JCheckBox[] getInterruptor() {
		return interruptor;
	}
	public JLabel[] getCodigoMatrix() {
		return codigoMatrix;
	}
	public boolean isVoz() {
		return voz;
	}
	public void setVoz(boolean voz) {
		this.voz = voz;
	}
	public JFrame getAviso() {
		return aviso;
	}	
	public void maximizar() {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	public void restaurar() {
		setExtendedState(JFrame.NORMAL);
	}
}
