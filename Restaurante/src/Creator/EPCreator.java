package Creator;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.StructBESA;
import EP.EPAgent;
import EP.Behavior.EPSensorGuard;
import EP.State.EPState;
import Utils.Utils;

public class EPCreator {

	private static double clave;

	public static void setClave(double clave) {
		EPCreator.clave = clave;
	}

	// crea varios agentes
	public static void crearEP(int cantidad) throws ExceptionBESA {
		for (int a = 0; a < cantidad; ++a)
			agente("EP" + Integer.toString(a));
	}

	// crea un unico agente, pasandole el tamaño del mapa y su nombre
	private static void agente(String name) throws ExceptionBESA {
		StructBESA c1Struct = new StructBESA();

		Utils.agregarAEstructura(c1Struct, EPSensorGuard.class);

		(new EPAgent(name, new EPState(), c1Struct, clave)).start();
	}
}
