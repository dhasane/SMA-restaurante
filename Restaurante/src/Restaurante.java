
import java.util.concurrent.TimeUnit;

import BESA.ExceptionBESA;
import Creator.CACreator;
import Creator.COCreator;
import Creator.EPCreator;
import Creator.TPCreator;
import DOOR.CLDoor;
import Mundo.Mapa.Mapa;
import Resultados.Resultados;
import Utils.VisualUtils.ExitListener;

public class Restaurante {

	private static double clave = 0.91;

	// CL -> cliente
	// CO -> cocinero
	// TP -> toma pedido
	// EP -> entrega pedido
	// CA -> caja
	// AY -> ayudante TODO

	public static void main(String[] args) throws ExceptionBESA {
		
		Resultados.iniciar();
		Mapa.createMapa(10, 20, 5);

		CACreator.setClave(clave);
		COCreator.setClave(clave);
		TPCreator.setClave(clave);
		EPCreator.setClave(clave);

		int[][] tpp = { { 3, 5 }, { 4, 5 } };
		TPCreator.crearTP(tpp);

		int[][] cap = { { 6, 5 }, { 6, 6 } };
		CACreator.crearCA(cap);

		int[][] epp = { { 4, 6 }, { 4, 7 } };
		EPCreator.crearEP(epp);

		int[][] cop = { { 8, 5 }, { 9, 5 }, { 7, 7 } };
		COCreator.crearCO(cop);

		// TODO para las sillas, habria que hacer lo mismo que para los agentes para
		// poder posicionarlos

		// siendo que los clientes son los que inician la cadena,
		// deben ser llamados de ultimo, cuando ya todos los demas
		// agentes hayan sido inicializados
		CLDoor cld = new CLDoor(clave);
		cld.start();

		System.out.println("en esperaaaaa");
		// tiempo de prueba
		try {
			TimeUnit.MINUTES.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		cld.destruir();
		
		// matar a todos los agentes o algo
		
		Resultados.mostrarResultados();
		return;
	}

}
