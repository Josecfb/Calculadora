package controlador;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;


//import javax.swing.JLabel;
import vista.Ventana;

public class ManejadorEventos implements ActionListener {
	private Ventana ventana; 
	public boolean psico=false;
	char funcion='=';
	boolean opera=true;
	double acumulador=0;
	int base;


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
	
	public void interruptores(ActionEvent  e) {
		JCheckBox casilla=(JCheckBox) e.getSource();
		String nomCas=casilla.getName();
		if (nomCas.equals("interruptor")) {
			System.out.println(psico);
			if (psico) {
				ImageIcon off;
				Icon iconooff;
				off=new ImageIcon("off.png");
				iconooff=new ImageIcon(off.getImage().getScaledInstance(35, 18, Image.SCALE_DEFAULT));
				casilla.setIcon(iconooff);
				psico=false;
				System.out.println("psico"+psico);
				ventana.pausar();
				
			}else {
				ImageIcon on;
				Icon iconoon;
				on=new ImageIcon("on.png");
				iconoon=new ImageIcon(on.getImage().getScaledInstance(35, 18, Image.SCALE_DEFAULT));
				casilla.setIcon(iconoon);
				psico=true;
				System.out.println("psico"+psico);
				ventana.reanudar();
			}
		}
	}
	
	public void radios(ActionEvent e) {
		quitafoco();
		JRadioButton radio=(JRadioButton) e.getSource();
		String texRa=radio.getText();
		System.out.println(texRa);
		for (int r=0;r<=3;r++) 
			if (ventana.getSistema()[r].isSelected()) 
				ventana.getSistema()[r].setBorderPainted(true);
			else ventana.getSistema()[r].setBorderPainted(false);
		
		if (texRa.equals("HEX")) {
			activadecimal(true);
			avtivahexadecimal(true);
			base=16;
			cambiaBase();
		}
		if (texRa.equals("DEC")) {
			avtivahexadecimal(false);
			activadecimal(true);
			base=10;
			cambiaBase();
		}
		if (texRa.equals("OCT")) {
			activadecimal(false);
			activadecimal(false);
			activaoctal(true);
			base=8;
			cambiaBase();
		}
		if (texRa.equals("BIN")) {
			activadecimal(false);
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

	public void activadecimal(boolean activa) {
		for (int b=0;b<=9;b++) 
			ventana.getBoton()[b].setEnabled(activa);
	}
	public void avtivahexadecimal(boolean activa) {
		for (int b=32;b<=37;b++)
			ventana.getBoton()[b].setEnabled(activa);
	}
	public void activaoctal(boolean activa) {
		for (int b=0;b<=7;b++)
			ventana.getBoton()[b].setEnabled(activa);
	}

	public void botones(ActionEvent e) {
		quitafoco();
		JButton boton=(JButton) e.getSource();
		String nomBo="";
		nomBo=boton.getName();
		
		//Números
		numOpera(nomBo);
		if (nomBo.equals("borra")) {
			ventana.getCajaTexto().setText(ventana.getCajaTexto().getText().substring(0, ventana.getCajaTexto().getText().length()-1));
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
		System.out.println(co);
		if ((co.compareTo("0")>=0 && co.compareTo("9")<=0)||(co.compareTo("A")>=0 && co.compareTo("F")<=0)) {
			if (opera) {
				ventana.getCajaTexto().setText(co);
				opera=false;}
			else
				ventana.getCajaTexto().setText(ventana.getCajaTexto().getText()+co);
		ventana.getlBase()[1].setText(convierteADecimal(ventana.getCajaTexto().getText(), 10));
		ventana.getlBase()[0].setText(convierteABase(Integer.parseInt(ventana.getlBase()[1].getText()), 16));
		ventana.getlBase()[2].setText(convierteABase(Integer.parseInt(ventana.getlBase()[1].getText()), 8));
		ventana.getlBase()[3].setText(convierteABase(Integer.parseInt(ventana.getlBase()[1].getText()), 2));
		}
		//coma
		if (co.equals(".") && !ventana.getCajaTexto().getText().contains("."))
			ventana.getCajaTexto().setText(ventana.getCajaTexto().getText()+co);
			
		if ("=+-*/#@R".contains(co)) {
			opera();
			funcion=co.charAt(0);
			System.out.println(funcion);
		}
	}

	private void sacaAcuDeCaja() {
		acumulador=Double.valueOf(ventana.getCajaTexto().getText());
	}

	private void opera() {
		if (funcion=='=')
			acumulador=Double.parseDouble(ventana.getCajaTexto().getText());
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
	private void saca(String res) {
		ventana.getCajaTexto().setText(res);
		//ventana.getSistema()[1].setText(convierteADecimal(Integer.parseInt(res), 10));
	}
	private String suma() {
		return Double.toString(acumulador+Double.parseDouble(ventana.getCajaTexto().getText()));
	}
	private String resta() {
		acumulador-=Double.parseDouble(ventana.getCajaTexto().getText());
		return acuStr();
	}
	private String multiplica() {
		acumulador*=Double.parseDouble(ventana.getCajaTexto().getText());
		return acuStr();
	}
	private String divide() {
		if (Double.parseDouble(ventana.getCajaTexto().getText())==0) {
			acumulador=0;
			funcion='=';
			opera=true;

			new Terremoto(ventana);

			
			return "No posees las gemas";
		}
		else {
			acumulador/=Double.parseDouble(ventana.getCajaTexto().getText());
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
		}else return "No es un número real";
	}
	private String raizEnesima() {
		acumulador=Math.pow(acumulador, 1/Double.parseDouble(ventana.getCajaTexto().getText()));
		return acuStr();
	}
	private String potenciaEnesima() {
		acumulador=Math.pow(acumulador, Double.parseDouble(ventana.getCajaTexto().getText()));
		return acuStr();
	}
	private String factorial() {
		if(acumulador==Math.floor(acumulador)) {
			double num=acumulador;
			for (int i=2;i<num;i++)
				acumulador*=i;
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
		acumulador=acumulador%Double.parseDouble(ventana.getCajaTexto().getText());
		return acuStr();
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
	
	public void quitafoco() {
		ventana.requestFocus();
	}
}