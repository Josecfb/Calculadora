package controlador;


import vista.Ventana;
public class Principal {

	public static void main(String[] args) {
		Ventana miVentana=new Ventana();
		
		//Crea los objetos manejadores de eventos pasandoles como argumento la ventana
		
		ManejadorEventos manejador=new ManejadorEventos(miVentana);
		ManejadorMenu manejadorMenu=new ManejadorMenu(miVentana);
		ManejadorTeclado manejadorTeclado=new ManejadorTeclado(miVentana);
		ManejadorVentana manejadorVentana=new ManejadorVentana(miVentana);
		
		//llama a los metodos de ventana que establecen cada menejador de eventos
		
		miVentana.EstableceManejadorMenu(manejadorMenu);
		miVentana.EstablecerManejador(manejador);
		miVentana.EstablecerManejadorTeclado(manejadorTeclado);
		miVentana.EstablecerManejadorVentana(manejadorVentana);
	}
}
