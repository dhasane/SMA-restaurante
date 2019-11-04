package Creator;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.StructBESA;
import CL.CLAgent;
import CL.Behavior.CLHacerFila;
import CL.Behavior.CLHacerFilaPago;
import CL.Behavior.CLRealizarPago;
import CL.Behavior.CLRecibirMenu;
import CL.Behavior.CLVerFila;
import CL.Behavior.CLVerFilaPago;
import CL.State.CLState;
import Data.EmptyData;
import Utils.Utils;

public class CLCreator {

	private static double clave;

	public static void setClave(double clave) {
		CLCreator.clave = clave;
	}

	// crea varios agentes
	public static void crearCL(int numero) throws ExceptionBESA {
		cliente("CL" + numero);
	}

	// crea un unico agente, pasandole el tamaño del mapa y su nombre
	private static void cliente(String name) throws ExceptionBESA {
		StructBESA c1Struct = new StructBESA();

		Utils.agregarAEstructura(c1Struct, CLVerFila.class);
		Utils.agregarAEstructura(c1Struct, CLHacerFila.class);
		Utils.agregarAEstructura(c1Struct, CLVerFilaPago.class);
		Utils.agregarAEstructura(c1Struct, CLHacerFilaPago.class);
		Utils.agregarAEstructura(c1Struct, CLRealizarPago.class);
		Utils.agregarAEstructura(c1Struct, CLRecibirMenu.class);

		CLAgent ca = new CLAgent(name, new CLState(), c1Struct, clave);
		ca.start();
		Utils.send(ca.getAdmLocal(), ca.getAid(), CLVerFila.class.getName(), new EmptyData());

	}
}
