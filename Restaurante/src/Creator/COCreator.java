package Creator;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.StructBESA;
import CO.COAgent;
import CO.Behavior.COCocinar;
import CO.State.COState;
import Utils.Utils;

public class COCreator {

	private static double clave;

	public static void setClave(double clave) {
		COCreator.clave = clave;
	}

	// crea varios agentes
	public static void crearCO(int[][] cantidad) throws ExceptionBESA {
		for (int a = 0; a < cantidad.length; ++a)
			cocinero("CO" + Integer.toString(a), cantidad[a]);
	}

	// crea un unico agente, pasandole el tamaño del mapa y su nombre
	private static void cocinero(String name, int[] pos) throws ExceptionBESA {
		StructBESA sb = new StructBESA();

		Utils.agregarAEstructura(sb, COCocinar.class);

		COAgent co = new COAgent(name, new COState(), sb, clave, pos);
		co.start();

	}
}
