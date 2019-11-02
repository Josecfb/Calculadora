package controlador;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import vista.Ventana;

public class ManejadorEventos implements ActionListener {
	private static final String SRC_IMG = "src/img/";
	private static final String SRC_SONIDOS = "src/sonidos/";
	private static final String MATRIX = "Matrix te posee";
	private static final String INFINITO = "No posees las gemas";
	private Ventana ventana; 
	boolean psico=false, color=false;
	char funcion='=';
	boolean opera=true;
	double acumulador=0;
	int base=10;
	//Constructor
	public ManejadorEventos(Ventana ventana) {
		this.ventana=ventana;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Dependiendo del tipo de objeto de donde provenga el evento bifurca a uno de los tres métodos
		if (e.getSource() instanceof JButton) 
			botones(e);
		if (e.getSource() instanceof JRadioButton)
			radios(e);
		if (e.getSource() instanceof JCheckBox)
			interruptores(e);
	}
	//Controla los tres interruptores
	private void interruptores(ActionEvent  e) {
		focoAVentana();
		JCheckBox casilla=(JCheckBox) e.getSource();
		String nomCas=casilla.getName();
		Icon on=new ImageIcon(new ImageIcon(SRC_IMG+"on.png").getImage().getScaledInstance(35, 18, Image.SCALE_DEFAULT));
		Icon off=new ImageIcon(new ImageIcon(SRC_IMG+"off.png").getImage().getScaledInstance(35, 18, Image.SCALE_DEFAULT));
		//Activa desactiva los colores ("Interface Teseracto")----------------------------------
		if (nomCas.equals("color")) 
			if (!color) 
				activaColor(casilla, on);
			else 
				if (psico)
					JOptionPane.showMessageDialog(ventana.getAviso(), "No puedes retirar el interface del Teseractor si está conectado"
							+ "\nPodrias ocasionar una catastrofe planetaria.\nPrimero desconecta el Teseracto y despues retira el interface.", "Peligro de destrucción masiva",0);
				else
					desactivaColor(casilla, off);
		//Activa desctiva el Teseracto
		if (nomCas.equals("teseracto")) {
			if (psico) {
				if (color) {
				suena(SRC_SONIDOS+"teseracto_off.wav");
				casilla.setIcon(off);
				psico=false;
				ventana.pausar();
			}	
			}else {
				suena(SRC_SONIDOS+"teseracto_on.wav");
				casilla.setIcon(on);
				psico=true;
				ventana.reanudar();
			}
		}
		//Activa desctiva el sonido de la aplicación
		if (nomCas.equals("voz"))
			if (ventana.isVoz()) {
				casilla.setIcon(off);
				ventana.setVoz(false);
			}else {
				JOptionPane.showMessageDialog(ventana.getAviso(), "Conecta los altavoces", "Sonido activado",1);
				casilla.setIcon(on);
				ventana.setVoz(true);
			}			
	}


	// controla los radioButtons que cambian de base en la calculadora Programador
	private void radios(ActionEvent e) {
		focoAVentana();
		JRadioButton radio=(JRadioButton) e.getSource();
		String texRa=radio.getText();
		System.out.println(texRa);
		for (int r=0;r<=3;r++) 
			if (ventana.getSistema()[r].isSelected()) {
				ventana.getSistema()[r].setBorderPainted(true);
				ventana.getlBase()[r].setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
				
				ventana.getSistema()[r].setFont(new Font("Calibri", Font.BOLD, 22));
			}else {
				ventana.getSistema()[r].setBorderPainted(false);
				ventana.getSistema()[r].setFont(new Font("Calibri", Font.PLAIN, 22));
				ventana.getlBase()[r].setBorder(null);
			}
		//activa base hexadecimal
		if (texRa.equals("HEX")) {
			activadecimal(true);
			activahexadecimal(true);
			base=16;
			cambiaBase();
		}
		//activa base decimal
		if (texRa.equals("DEC")) {
			activahexadecimal(false);
			activadecimal(true);
			base=10;
			cambiaBase();
		}
		//activa base octal
		if (texRa.equals("OCT")) {
			activahexadecimal(false);
			activadecimal(false);
			activaoctal(true);
			base=8;
			cambiaBase();
		}
		//activa base binaria
		if (texRa.equals("BIN")) {
			activahexadecimal(false);
			activadecimal(false);
			ventana.getBoton()[0].setEnabled(true);
			ventana.getBoton()[1].setEnabled(true);
			base=2;
			cambiaBase();
		}
	
	}
	//Pone en el visor de la calculadora el contenido del visor decimal en la base actual
	private void cambiaBase() {
		ventana.getCajaTexto().setText(convierteABase(Integer.parseInt(ventana.getlBase()[1].getText()), base));
	}
	//habilita/deshabilita los botones de digitos decimales 
	private void activadecimal(boolean activa) {
		for (int b=0;b<=9;b++) 
			ventana.getBoton()[b].setEnabled(activa);
	}
	//habilita/deshabilita los botones de digitos hexadecimales
	private void activahexadecimal(boolean activa) {
		for (int b=32;b<=37;b++)
			ventana.getBoton()[b].setEnabled(activa);
	}
	//habilita/deshabilita los botones de digitos octales
	private void activaoctal(boolean activa) {
		for (int b=0;b<=7;b++)
			ventana.getBoton()[b].setEnabled(activa);
	}
	// Controla los eventos sobre botones
	private void botones(ActionEvent e) {
		quitaP();
		focoAVentana();
		JButton boton=(JButton) e.getSource();
		String nomBo="";
		nomBo=boton.getName();
		//Números
		numOpera(nomBo);
		if (nomBo.equals("borra")) {
			ventana.getCajaTexto().setText(ventana.getCajaTexto().getText().substring(0, ventana.getCajaTexto().getText().length()-1));
			if (ventana.getCajaTexto().getText().equals(""))
				ventana.getCajaTexto().setText("0");
		}
		if (nomBo.equals("masmenos")) {
			ventana.getCajaTexto().setText(String.valueOf(valorPantalla()*-1));
		}
		if (nomBo.equals("inversa")) {
			funcion='i';
			total();
		}
		if(nomBo.equals("cuadrado")) {
			funcion='c';
			total();
		}
		if(nomBo.equals("raizcuadrada")) {
			funcion='r';
			total();
		}

		if (nomBo.equals("del")) {
			acumulador=0;
			saca("0");
			funcion='=';
		}
		if (nomBo.equals("CE")) {
			saca("0");
		}
		if (nomBo.equals("factorial")) {
			funcion='f';
			total();
		}
		if (nomBo.equals("primo")) {
			funcion='p';
			total();
		}
		if (nomBo.equals("pi")) {
			funcion='P';
			System.out.println("pi");
			total();
		}
		if (nomBo.equals("seno")) {
			funcion='S';
			total();
		}
		if (nomBo.equals("coseno")) {
			funcion='C';
			total();
		}
		if (nomBo.equals("tangente")) {
			funcion='T';
			total();
		}

		e=null;
	}
	//prepara para operar
	private void total() {
		sacaAcuDeCaja();
		opera();
		funcion='=';
	}
	
	private void numOpera(String co) {
		//Pasa los digitos pulsados al visor de la calculadora
		if ((co.compareTo("0")>=0 && co.compareTo("9")<=0)||(co.compareTo("A")>=0 && co.compareTo("F")<=0)) {
			suena(SRC_SONIDOS+co+".wav");
			if (opera||ventana.getCajaTexto().getText().equals("0")) {
				ventana.getCajaTexto().setText(co);
				if (ventana.isEsProgramador()) actualizaBases();
				opera=false;}
			else {
				ventana.getCajaTexto().setText(ventana.getCajaTexto().getText()+co);
				if (ventana.isEsProgramador()) actualizaBases();
			}
		}
		//coma
		if (co.equals(".") && !ventana.getCajaTexto().getText().contains(".")) {
			suena(SRC_SONIDOS+"coma.wav");
			ventana.getCajaTexto().setText(ventana.getCajaTexto().getText()+co);
		}
		
		if ("=+-*/#@R".contains(co)) {
			switch (co) {//sonidos de operadores
			case "*":
				suena(SRC_SONIDOS+"x.wav");
				break;
			case "/":
				suena(SRC_SONIDOS+"en.wav");
				break;
			default:
				if (!"#@R".contains(co))
				suena(SRC_SONIDOS+co+".wav");
				break;
			}
			
			opera();
			funcion=co.charAt(0);
			System.out.println(funcion);
		}
	}
	//cambia la base de numeracion predeterminada
	private void actualizaBases() {
		int i=1;	
		switch (base) {
		case 2:
			i=3;
			break;
		case 8:
			i=2;
			break;
		case 10:
			i=1;
			break;
		case 16:
			i=0;
			break;
		default:
			break;
		}
		ventana.getlBase()[i].setText(ventana.getCajaTexto().getText());
		ventana.getlBase()[1].setText(convierteADecimal(ventana.getCajaTexto().getText(), base));
		ventana.getlBase()[0].setText(convierteABase(Integer.parseInt(ventana.getlBase()[1].getText()), 16));
		ventana.getlBase()[2].setText(convierteABase(Integer.parseInt(ventana.getlBase()[1].getText()), 8));
		ventana.getlBase()[3].setText(convierteABase(Integer.parseInt(ventana.getlBase()[1].getText()), 2));
	}
	//asigna a acumulador el valor del visor de la calculadora
	private void sacaAcuDeCaja() {
		if (!ventana.isEsProgramador())
			acumulador=valorPantalla();
		else acumulador =valorDecimal();
	}
	//devuelve el balor de la base decimal en double
	private Double valorDecimal() {
		return Double.valueOf(ventana.getlBase()[1].getText());
	}
	//bifurca segun el tipo de operación
	private void opera() {
		if (funcion=='=')
			sacaAcuDeCaja();
		else
			switch (funcion) {
			case '+':
				saca(suma());
				break;
			case '-':
				saca(resta());
				break;
			case '*':
				saca(multiplica());
				break;
			case '/':
				saca(divide());
				break;

			case 'i':
				saca(inversa());
				break;
			case 'c':
				saca(cuadrado());
				break;
			case 'r':
				saca(raizCuadrada());
				break;
			case 'f':
				saca(factorial());
				break;
			case '#':
				saca(raizEnesima());
				break;
			case '@':
				saca(potenciaEnesima());
				break;
			case 'p':
				saca(primo());
				break;
			case 'R':
				saca(resto());
				break;
			case 'P':
				saca(pi());
				break;
			case 'S':
				saca(seno());
				break;
			case 'C':
				saca(coseno());
				break;
			case 'T':
				saca(tangente());
				break;
			default:
				break;
			}
		opera=true;
	}
	//retorna un double con el valor del visor
	private Double valorPantalla() {
		if (!ventana.isEsProgramador())
			return Double.valueOf(ventana.getCajaTexto().getText());
		else return Double.valueOf(ventana.getlBase()[1].getText());
	}
	//Muestra y pronuncia el resultado de una operación en el visor
	private void saca(String res) {
		if (res.equals("Infinity")) {
			alInfinito();
		}else if (!ventana.isEsProgramador()) {
				ventana.getCajaTexto().setText(res);
				if (ventana.isVoz()) new Pronuncia(res);
		}
		else {
			ventana.getlBase()[1].setText(res);
			ventana.getCajaTexto().setText(convierteABase(Integer.valueOf(ventana.getlBase()[1].getText().split("\\.")[0]), base));
			actualizaBases();
			if (ventana.isVoz()) new Pronuncia(convierteABase(Integer.valueOf(ventana.getlBase()[1].getText().split("\\.")[0]), base));
		}
	}

	private void alInfinito() {
		ventana.getCajaTexto().setText(INFINITO);
		if (ventana.isVoz()) suena(SRC_SONIDOS+"infin.wav");
		if (ventana.isVoz()) new Pronuncia(INFINITO);
		new Terremoto(ventana);
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ventana.getCajaTexto().setText("0");
	}
	private String suma() {
		System.out.println("suma"+Double.toString(acumulador+valorPantalla()));
		return Double.toString(acumulador+valorPantalla());
	}
	private String resta() {
		acumulador-=valorPantalla();
		if (acumulador-valorPantalla()<0 && ventana.isEsProgramador()) {
			ventana.getCajaTexto().setText("Negativo");
			return "0";
		}
		return acumulACad();
	}
	private String multiplica() {
		acumulador*=valorPantalla();
		return acumulACad();
	}
	private String divide() {
		if (valorPantalla()==0) {
			acumulador=0;
			funcion='=';
			opera=true;
			suena(SRC_SONIDOS+"infin.wav");
			new Terremoto(ventana);
			ventana.getCajaTexto().setText("0");
			return INFINITO;
		}
		else {
			acumulador/=valorPantalla();
			return acumulACad();
		}
	}
	private String inversa() {
		if (acumulador==0) {
			new Terremoto(ventana);
			return "Infinito";
			
		}
		else {
		acumulador=1/acumulador;
		return acumulACad();
		}
	}
	private String cuadrado() {
		acumulador=Math.pow(acumulador, 2);
		return acumulACad();
	}
	private String raizCuadrada() {
		if (acumulador>0) {
			acumulador=Math.sqrt(acumulador);
			return acumulACad();
		}else {
			new Matrix(ventana);
			return MATRIX;
		}
	}
	private String raizEnesima() {
		if (acumulador<0 && valorPantalla()%2==0) {
			new Matrix(ventana);
			return MATRIX;
		}else {
			acumulador=Math.pow(acumulador, 1/valorPantalla());
			return acumulACad();
		}
	}
	private String potenciaEnesima() {
		acumulador=Math.pow(acumulador, valorPantalla());
		return acumulACad();
	}
	private String factorial() {
		if(acumulador==Math.floor(acumulador)) {
			double num=acumulador;
			for (int i=2;i<num;i++) {
				acumulador*=i;
				if (Double.isInfinite(acumulador))
					break;
			}
			return acumulACad();
		}else {
			opera=true;
			acumulador=0;
			funcion='=';
			return "Solo números enteros";
		}
	}
	private String primo() {
		if(acumulador==Math.floor(acumulador)) {
			boolean es=true;
			for (int d=2;d<=(int)Math.sqrt(acumulador);d++)
				if(acumulador%d==0) 
					es=false;
			if (es) return "Si es primo";
			else return " No es primo";
		}else {
			opera=true;
			acumulador=0;
			funcion='=';
			return "Solo números enteros";
		}
	}
	//convierte un decimal a cualquier base
	private String convierteABase(int n,int base) {
		int entero=n;
		int num;
		String resultado="";
		do {
			num=(int)(entero/base);
			resultado=resto(entero%base).concat(resultado);
			entero=num;
		}while (entero>=base);
		resultado=resto(entero).concat(resultado);
		return resultado;
	}
	//convierte una cadena en cualquier base a decimal
	private String convierteADecimal(String cadena,int base) {
		int decimal=0;
		char digito;
		int valorDig;
		for(int c=cadena.length()-1;c>=0;c--) {
			digito=cadena.charAt(c);
			if (digito>='A') 
				valorDig=(int)digito-55;
			else 
				valorDig=Character.getNumericValue(digito);
			decimal+=valorDig*Math.pow(base,(cadena.length()-1-c));		
		}
		return String.valueOf(decimal);
	}
	//para r entre 10 y 15 devuelve letras entre A y F
	private String sacaLetra(int r) {
		return String.valueOf((char)((r-10)+'A'));
	}
	private String resto(int r) {
		if (r>9)
			return sacaLetra(r);
		else
			return String.valueOf(r);
	}
	private String resto() {
		acumulador=acumulador%valorPantalla();
		return acumulACad();
	}
	//reproduce archivos de sonido dependiendo de la cadena que recibe
	private void suena(String archivo) {
		Clip sonido=null;
		if (ventana.isVoz()) 
			try {
				sonido = AudioSystem.getClip();
				sonido.open(AudioSystem.getAudioInputStream(new File(archivo)));
				sonido.start();
			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
				e1.printStackTrace();
			}
	}
	//Acumulador a cadena---------------------------
	private String acumulACad() {
		return Double.toString(acumulador);
	}
	private String pi() {
		acumulador=Math.PI;
		return acumulACad();
	}
	private String seno() {
		acumulador=Math.sin(acumulador);
		return acumulACad();
	}
	private String coseno() {
		acumulador=Math.cos(acumulador);
		return acumulACad();
	}
	private String tangente() {
		acumulador=Math.tan(acumulador);
		return acumulACad();
	}
	//util para que funcione el teclado tras los eventos de click le da el foco a la ventana ya que la ventana es la que escucha el teclado
	private void focoAVentana() {
		ventana.requestFocus();
	}	
	//asigna a los array r[],g[],b[] el color de todos los botones y visores de forma aleatoria por encima de abajo para que sean claros
	private void activaColor(JCheckBox casilla, Icon on) {
		suena(SRC_SONIDOS+"interfaceTS2.wav");
		color=true;
		System.out.println("color");
		ventana.getEInterruptor()[2].setVisible(true);
		ventana.getInterruptor()[2].setVisible(true);
		casilla.setIcon(on);
		int abajo=180;
		int[] r =new int[ventana.getBoton().length];
		int[] g =new int[ventana.getBoton().length];
		int[] b =new int[ventana.getBoton().length];
		int[] rb =new int[ventana.getlBase().length];
		int[] gb =new int[ventana.getlBase().length];
		int[] bb =new int[ventana.getlBase().length];
		for (int i=0;i<=ventana.getEInterruptor().length-1;i++)
			ventana.getEInterruptor()[i].setForeground(new Color(255,255,255));
		for (int bo=0;bo<ventana.getBoton().length;bo++) {
			r[bo]=(int)(Math.random()*(254-abajo)+abajo);
			g[bo]=(int)(Math.random()*(254-abajo)+abajo);
			b[bo]=(int)(Math.random()*(254-abajo)+abajo);
			ventana.getBoton()[bo].setBackground(new Color(r[bo],g[bo],b[bo]));
		}
		for (int ba=0;ba<ventana.getlBase().length;ba++) {
			rb[ba]=(int)(Math.random()*(254-abajo)+abajo);
			gb[ba]=(int)(Math.random()*(254-abajo)+abajo);
			bb[ba]=(int)(Math.random()*(254-abajo)+abajo);
			ventana.getlBase()[ba].setBackground(new Color(rb[ba],gb[ba],bb[ba]));
		}
		ventana.getCajaTexto().setBackground(new Color(r[5], g[4], b[8]));
		ventana.getContentPane().setBackground(new Color(r[1]-99,g[2]-99,b[3]-99));
		ventana.getBarra().setBackground(new Color(r[10],g[11],b[12]));
		ventana.getEstandar().setBackground(new Color(r[10],g[11],b[12]));
		ventana.getCientifica().setBackground(new Color(r[10],g[11],b[12]));
		ventana.getProgramador().setBackground(new Color(r[10],g[11],b[12]));
		for (int s=0;s<ventana.getSistema().length;s++)
			ventana.getSistema()[s].setForeground(new Color(255,255,255));
	}
	//quita colores
	private void desactivaColor(JCheckBox casilla, Icon off) {
		suena(SRC_SONIDOS+"interfaceTS.wav");
		ventana.getEInterruptor()[2].setVisible(false);
		ventana.getInterruptor()[2].setVisible(false);
		for (int i=0;i<=ventana.getEInterruptor().length-1;i++)
			ventana.getEInterruptor()[i].setForeground(new Color(0,0,0));
		casilla.setIcon(off);
		for (int bo=0;bo<ventana.getBoton().length;bo++) 
			ventana.getBoton()[bo].setBackground(null);
		for (int ba=0;ba<ventana.getlBase().length;ba++)
			ventana.getlBase()[ba].setBackground(null);
		ventana.getCajaTexto().setBackground(new Color(250,250,250));
		ventana.getContentPane().setBackground(null);
		ventana.getBarra().setBackground(null);
		ventana.getEstandar().setBackground(null);
		ventana.getCientifica().setBackground(null);
		ventana.getProgramador().setBackground(null);
		for (int s=0;s<ventana.getSistema().length;s++)
			ventana.getSistema()[s].setForeground(new Color(0,0,0));
		color=false;
	}
	private void quitaP() {
		if (ventana.getCajaTexto().getText().contains("primo"))
			saca("0");
	}
}