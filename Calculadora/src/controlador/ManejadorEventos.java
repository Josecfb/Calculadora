package controlador;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
//import javax.swing.JLabel;
import vista.Ventana;

public class ManejadorEventos implements ActionListener {
	private Ventana ventana; 
	public boolean psico=false;
	char funcion='=';
	boolean opera=false;
	double acumulador=0;


	public ManejadorEventos(Ventana ventana) {
		this.ventana=ventana;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().toString().contains("JButton")) {
			botones(e);
		}
	}

	public void botones(ActionEvent e) {
		
		JButton boton=(JButton) e.getSource();
		String co="";
		
		co=boton.getName();
		if (co=="interruptor") {
			System.out.println(psico);
			if (psico) {
				ImageIcon off;
				Icon iconooff;
				off=new ImageIcon("off.png");
				iconooff=new ImageIcon(off.getImage().getScaledInstance(40, 30, Image.SCALE_DEFAULT));
				boton.setIcon(iconooff);
				psico=false;
				System.out.println("psico"+psico);
				ventana.pausar();
				
			}else {
				ImageIcon on;
				Icon iconoon;
				on=new ImageIcon("on.png");
				iconoon=new ImageIcon(on.getImage().getScaledInstance(40, 30, Image.SCALE_DEFAULT));
				boton.setIcon(iconoon);
				psico=true;
				System.out.println("psico"+psico);
				ventana.reanudar();
			}
			quitafoco();
		}
		//Números
		numOpera(co);
		if (co=="borra") {
			ventana.getCajaTexto().setText(ventana.getCajaTexto().getText().substring(0, ventana.getCajaTexto().getText().length()-1));
			quitafoco();
		}
		if (co=="masmenos") {
			funcion='x';
			total();
		}
		if (co=="inversa") {
			funcion='i';
			total();
		}
		if(co=="cuadrado") {
			funcion='c';
			total();
		}
		if(co=="raizcuadrada") {
			funcion='r';
			total();
		}

		if (co=="C") {
			acumulador=0;
			saca("0");
			funcion='=';
			quitafoco();
		}
		if (co=="CE") {
			saca("0");
			quitafoco();
			
		}
		if (co=="factorial") {
			funcion='f';
			total();
		}
		if (co=="primo") {
			funcion='p';
			total();
		}
		if (co=="pi") {
			funcion='P';
			total();
		}
		if (co=="seno") {
			funcion='S';
			total();
		}
		if (co=="coseno") {
			funcion='C';
			total();
		}
		if (co=="tangente") {
			funcion='T';
			total();
		}

		e=null;
	}

	private void total() {
		sacaAcuDeCaja();
		opera();
		funcion='=';
		quitafoco();
	}

	private void numOpera(String co) {
		if (co.compareTo("0")>=0 && co.compareTo("9")<=0) {
			if (opera) {
				ventana.getCajaTexto().setText(co);
				opera=false;}
			else
				ventana.getCajaTexto().setText(ventana.getCajaTexto().getText()+co);
			quitafoco();
		}
		//coma
		if (co.equals(".") && !ventana.getCajaTexto().getText().contains("."))
			ventana.getCajaTexto().setText(ventana.getCajaTexto().getText()+co);
			
		if ("=+-*/#@R".contains(co)) {
			opera();
			funcion=co.charAt(0);
			System.out.println(funcion);
			quitafoco();
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
			return "Infinito";
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
		}else return "Solo números enteros";
	}
	private String primo() {
		boolean es=true;
		for (int d=2;d<=(int)Math.sqrt(acumulador);d++)
			if(acumulador%d==0) 
				es=false;
		if (es) return "Si es primo";
		else return " No es primo";
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
	
	private void quitafoco() {
		ventana.getCajaTexto().requestFocus();
	}
}