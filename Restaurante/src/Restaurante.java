
import BESA.ExceptionBESA;
import Creator.CACreator;
import Creator.COCreator;
import Creator.EPCreator;
import Creator.TPCreator;
import DOOR.CLDoor;
import Mundo.Mapa.Mapa;
import Resultados.Resultados;

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
		Mapa.createMapa(20, 10, 10);

		CACreator.setClave(clave);
		COCreator.setClave(clave);
		TPCreator.setClave(clave);
		EPCreator.setClave(clave);

		int[][] tpp = { {1, 9 }, { 2, 9 } };
		TPCreator.crearTP(tpp);

		int[][] cap = { { 10, 4 }, { 10, 5 } };
		CACreator.crearCA(cap);

		int[][] epp = { { 10, 3 }, { 10, 4 } };
		EPCreator.crearEP(epp);

		int[][] cop = { { 13, 4 }, { 13, 5 }, { 13, 6 } };
		COCreator.crearCO(cop);

		// TODO para las sillas, habria que hacer lo mismo que para los agentes para
		// poder posicionarlos

		// siendo que los clientes son los que inician la cadena,
		// deben ser llamados de ultimo, cuando ya todos los demas
		// agentes hayan sido inicializados
		CLDoor cld = new CLDoor(clave);
		cld.start();

		
		return;
	}

}
