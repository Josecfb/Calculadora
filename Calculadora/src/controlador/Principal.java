package controlador;
import vista.Ventana;
public class Principal {

	public static void main(String[] args) {
		Ventana miVentana=new Ventana();
		ManejadorEventos manejador=new ManejadorEventos(miVentana);
		miVentana.EstablecerManejador(manejador);
	}

}
