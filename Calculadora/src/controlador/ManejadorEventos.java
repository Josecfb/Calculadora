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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

//import javax.swing.JLabel;
import vista.Ventana;

public class ManejadorEventos implements ActionListener {
	private static final String MATRIX = "Matrix te posee";
	private static final String INFINITO = "No posees las gemas";
	private Ventana ventana; 
	boolean psico=false, color=false;
	char funcion='=';
	boolean opera=true;
	double acumulador=0;
	private int base=10;

	public ManejadorEventos(Ventana ventana) {
		this.ventana=ventana;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) 
			botones(e);
		
		if (e.getSource() instanceof JRadioButton)
			radios(e);
		
		if (e.getSource() instanceof JCheckBox)
			interruptores(e);
	}
	
	private void interruptores(ActionEvent  e) {
		JCheckBox casilla=(JCheckBox) e.getSource();
		String nomCas=casilla.getName();
		if (nomCas.equals("color")) {
			if (!color) {
				suena("interfaceTS2.wav");
				color=true;
				System.out.println("color");
				ventana.geteInterruptor()[1].setVisible(true);
				ventana.getInterruptor()[1].setVisible(true);
				casilla.setIcon(new ImageIcon(new ImageIcon("on.png").getImage().getScaledInstance(35, 18, Image.SCALE_DEFAULT)));
				int abajo=180;
				int[] r =new int[ventana.getBoton().length];
				int[] g =new int[ventana.getBoton().length];
				int[] b =new int[ventana.getBoton().length];
				int[] rb =new int[ventana.getlBase().length];
				int[] gb =new int[ventana.getlBase().length];
				int[] bb =new int[ventana.getlBase().length];
				for (int i=0;i<=ventana.geteInterruptor().length-1;i++)
					ventana.geteInterruptor()[i].setForeground(new Color(255,255,255));
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
					
			}else {
				suena("interfaceTS.wav");
				ventana.geteInterruptor()[1].setVisible(false);
				ventana.getInterruptor()[1].setVisible(false);
				for (int i=0;i<=ventana.geteInterruptor().length-1;i++)
					ventana.geteInterruptor()[i].setForeground(new Color(0,0,0));
				casilla.setIcon(new ImageIcon(new ImageIcon("off.png").getImage().getScaledInstance(35, 18, Image.SCALE_DEFAULT)));
				for (int bo=0;bo<ventana.getBoton().length;bo++) 
					ventana.getBoton()[bo].setBackground(null);
				for (int ba=0;ba<ventana.getlBase().length;ba++)
					ventana.getlBase()[ba].setBackground(null);
				ventana.getCajaTexto().setBackground(null);
				ventana.getContentPane().setBackground(null);
				ventana.getBarra().setBackground(null);
				ventana.getEstandar().setBackground(null);
				ventana.getCientifica().setBackground(null);
				ventana.getProgramador().setBackground(null);
				for (int s=0;s<ventana.getSistema().length;s++)
					ventana.getSistema()[s].setForeground(new Color(0,0,0));
				color=false;
			}
		}
		
		if (nomCas.equals("teseracto")) {
			if (psico) {
				suena("teseracto_off.wav");
				casilla.setIcon(new ImageIcon(new ImageIcon("off.png").getImage().getScaledInstance(35, 18, Image.SCALE_DEFAULT)));
				psico=false;
				ventana.pausar();
				
			}else {
				suena("teseracto_on.wav");
				casilla.setIcon(new ImageIcon(new ImageIcon("on.png").getImage().getScaledInstance(35, 18, Image.SCALE_DEFAULT)));
				psico=true;
				ventana.reanudar();
			}
		}
	
	}
	
	private void radios(ActionEvent e) {
		quitafoco();
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
		if (texRa.equals("HEX")) {
			activadecimal(true);
			activahexadecimal(true);
			base=16;
			cambiaBase();
		}
		if (texRa.equals("DEC")) {
			activahexadecimal(false);
			activadecimal(true);
			base=10;
			cambiaBase();
		}
		if (texRa.equals("OCT")) {
			activahexadecimal(false);
			activadecimal(false);
			activaoctal(true);
			base=8;
			cambiaBase();
		}
		if (texRa.equals("BIN")) {
			activahexadecimal(false);
			activadecimal(false);
			ventana.getBoton()[0].setEnabled(true);
			ventana.getBoton()[1].setEnabled(true);
			base=2;
			cambiaBase();
		}
	
	}

	private void cambiaBase() {
		ventana.getCajaTexto().setText(convierteABase(Integer.parseInt(ventana.getlBase()[1].getText()), base));
	}
	private void activadecimal(boolean activa) {
		for (int b=0;b<=9;b++) 
			ventana.getBoton()[b].setEnabled(activa);
	}
	private void activahexadecimal(boolean activa) {
		for (int b=32;b<=37;b++)
			ventana.getBoton()[b].setEnabled(activa);
	}
	private void activaoctal(boolean activa) {
		for (int b=0;b<=7;b++)
			ventana.getBoton()[b].setEnabled(activa);
	}
	private void botones(ActionEvent e) {
		quitafoco();
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
			funcion='x';
			total();
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

	private void total() {
		sacaAcuDeCaja();
		opera();
		funcion='=';
	}

	private void numOpera(String co) {
		if ((co.compareTo("0")>=0 && co.compareTo("9")<=0)||(co.compareTo("A")>=0 && co.compareTo("F")<=0)) {
			suena(co+".wav");
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
			suena("coma.wav");
			ventana.getCajaTexto().setText(ventana.getCajaTexto().getText()+co);
		}
		if ("=+-*/#@R".contains(co)) {
			switch (co) {
			case "*":
				suena("x.wav");
				break;
			case "/":
				suena("en.wav");
				break;
			default:
				if (!"#@R".contains(co))
				suena(co+".wav");
				break;
			}
			
			opera();
			funcion=co.charAt(0);
			System.out.println(funcion);
		}
	}

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

	private void sacaAcuDeCaja() {
		if (!ventana.isEsProgramador())
			acumulador=valorPantalla();
		else acumulador =valorDecimal();
	}

	private Double valorDecimal() {
		return Double.valueOf(ventana.getlBase()[1].getText());
	}

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
			case 'x':
				saca(masmenos());
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

	private Double valorPantalla() {
		if (!ventana.isEsProgramador())
			return Double.valueOf(ventana.getCajaTexto().getText());
		else return Double.valueOf(ventana.getlBase()[1].getText());
	}
	private void saca(String res) {
		if (res.equals("Infinity")) {
			ventana.getCajaTexto().setText(INFINITO);
			new Pronuncia(INFINITO);
			new Terremoto(ventana);
			saca("0");
		}else if (!ventana.isEsProgramador()) {
				ventana.getCajaTexto().setText(res);
				new Pronuncia(res);
		}
		else {
			ventana.getlBase()[1].setText(res);
			ventana.getCajaTexto().setText(convierteABase(Integer.valueOf(ventana.getlBase()[1].getText().split("\\.")[0]), base));
			actualizaBases();
			new Pronuncia(convierteABase(Integer.valueOf(ventana.getlBase()[1].getText().split("\\.")[0]), base));
		}
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
		return acuStr();
	}
	private String multiplica() {
		acumulador*=valorPantalla();
		return acuStr();
	}
	private String divide() {
		if (valorPantalla()==0) {
			acumulador=0;
			funcion='=';
			opera=true;
			new Terremoto(ventana);
			saca("0");
			return INFINITO;
		}
		else {
			acumulador/=valorPantalla();
			return acuStr();
		}
	}
	private String masmenos() {
		acumulador*=-1;
		return acuStr();
	}
	private String inversa() {
		if (acumulador==0) {
			return "Infinito";
		}
		else {
		acumulador=1/acumulador;
		return acuStr();
		}
	}
	private String cuadrado() {
		acumulador=Math.pow(acumulador, 2);
		return acuStr();
	}
	private String raizCuadrada() {
		if (acumulador>0) {
			acumulador=Math.sqrt(acumulador);
			return acuStr();
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
			return acuStr();
		}
	}
	private String potenciaEnesima() {
		acumulador=Math.pow(acumulador, valorPantalla());
		return acuStr();
	}
	private String factorial() {
		if(acumulador==Math.floor(acumulador)) {
			double num=acumulador;
			for (int i=2;i<num;i++) {
				acumulador*=i;
				if (Double.isInfinite(acumulador))
					break;
			}
			return acuStr();
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
		return acuStr();
	}
	private void suena(String archivo) {
		Clip sonido=null;

		try {
			sonido = AudioSystem.getClip();
			sonido.open(AudioSystem.getAudioInputStream(new File(archivo)));
			sonido.start();
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private String acuStr() {
		return Double.toString(acumulador);
	}
	private String pi() {
		acumulador=Math.PI;
		return acuStr();
	}
	private String seno() {
		acumulador=Math.sin(acumulador);
		return acuStr();
	}
	private String coseno() {
		acumulador=Math.cos(acumulador);
		return acuStr();
	}
	private String tangente() {
		acumulador=Math.tan(acumulador);
		return acuStr();
	}
	private void quitafoco() {
		ventana.requestFocus();
	}	
}