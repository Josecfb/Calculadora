package controlador;


import vista.Ventana;
public class Principal {

	public static void main(String[] args) {
		Ventana miVentana=new Ventana();
		ManejadorEventos manejador=new ManejadorEventos(miVentana);
		ManejadorMenu manejadorMenu=new ManejadorMenu(miVentana);
		ManejadorTeclado manejadorTeclado=new ManejadorTeclado(miVentana);
		miVentana.EstablecerManejador(manejador);
		miVentana.EstableceManejadorMenu(manejadorMenu);
		miVentana.EstablecerManejadorTeclado(manejadorTeclado);
	}

}
