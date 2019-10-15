package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vista.Ventana;

public class ManejadorEventos implements ActionListener{
	private Ventana ventana; 
	char funcion;
	boolean opera=false;
	double acumulador=0;
	public ManejadorEventos(Ventana ventana) {
		this.ventana=ventana;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		JButton boton=(JButton) e.getSource();
		//Números
		if (boton.getText().compareTo("0")>=0 && boton.getText().compareTo("9")<=0) {
			if (opera) {
				ventana.getCajaTexto().setText(boton.getText());
				opera=false;}
			else
				ventana.getCajaTexto().setText(ventana.getCajaTexto().getText()+boton.getText());
		}
		//coma
		if (boton.getText().equals(".") && !ventana.getCajaTexto().getText().contains("."))
			ventana.getCajaTexto().setText(ventana.getCajaTexto().getText()+boton.getText());
		if (boton.getText().equals("+")) {
			if (funcion=='=')
				acumulador=Double.parseDouble(ventana.getCajaTexto().getText());
			else
				saca(suma());
			funcion='+';
			opera=true;
		}
		if (boton.getText().equals("-")) {
			if (funcion=='=')
				acumulador=Double.parseDouble(ventana.getCajaTexto().getText());
			else			
				saca(resta());
			funcion='-';
			opera=true;
		}
		if (boton.getText().equals("=")) {
			opera();
			opera=true;
		}
	}

	private void opera() {
		switch (funcion) {
		case '+':
			saca(suma());
			break;
		case '-':
			saca(resta());
			break;
		default:
			break;
		}
		acumulador=0;
		funcion='=';
	}
	private void saca(String res) {
		ventana.getCajaTexto().setText(res);
	}
	private String suma() {
		acumulador+=Double.parseDouble(ventana.getCajaTexto().getText());
		return Double.toString(acumulador);
	}
	private String resta() {
		acumulador-=Double.parseDouble(ventana.getCajaTexto().getText());
		return Double.toString(acumulador);
	}
}