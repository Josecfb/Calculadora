package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;

import vista.Ventana;

public class ManejadorEventos implements ActionListener, KeyListener {
	private Ventana ventana; 
	char funcion='=';
	boolean opera=false;
	double acumulador=0;
	public ManejadorEventos(Ventana ventana) {
		this.ventana=ventana;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String co="";
		JButton boton=(JButton) e.getSource();
		
			co=boton.getName();
		//Números
		if (co.compareTo("0")>=0 && co.compareTo("9")<=0) {
			if (opera) {
				ventana.getCajaTexto().setText(boton.getText());
				opera=false;}
			else
				ventana.getCajaTexto().setText(ventana.getCajaTexto().getText()+co);
		}
		//coma
		if (co.equals(".") && !ventana.getCajaTexto().getText().contains("."))
			ventana.getCajaTexto().setText(ventana.getCajaTexto().getText()+co);
			
		if ("=+-*/".contains(co)) {
			opera();
			funcion=co.charAt(0);
		}
		if (boton.getName()=="borra")
			ventana.getCajaTexto().setText(ventana.getCajaTexto().getText().substring(0, ventana.getCajaTexto().getText().length()-1));
		if (boton.getName()=="masmenos") {
			acumulador=Double.valueOf(ventana.getCajaTexto().getText());
			funcion='x';
			opera();
			funcion='=';
		}
		if (boton.getName()=="inversa") {
			acumulador=Double.valueOf(ventana.getCajaTexto().getText());
			funcion='i';
			opera();
			funcion='=';
		}
		

	}

//	private void comprueba() {
//		ventana.getLabelAcu().setText(String.valueOf(funcion)+" acum:"+String.valueOf(acumulador));
//	}

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
		return Double.toString(acumulador);
	}
	private String multiplica() {
		acumulador*=Double.parseDouble(ventana.getCajaTexto().getText());
		return Double.toString(acumulador);
	}
	private String divide() {
		if (Double.parseDouble(ventana.getCajaTexto().getText())==0) {
			acumulador=0;
			funcion='=';
			return "Infinito";
		}
		else {
			acumulador/=Double.parseDouble(ventana.getCajaTexto().getText());
			return Double.toString(acumulador);
		}
	}
	private String masmenos() {
		acumulador*=-1;
		return Double.toString(acumulador);
	}
	private String inversa() {
		if (acumulador==0) {
			return "Infinito";
		}
		else {
		acumulador=1/acumulador;
		return Double.toString(acumulador);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		ventana.getLabelAcu().setText(String.valueOf(e.getKeyChar()));
		System.out.println(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {

		System.out.println(e.paramString());
		
	}
}