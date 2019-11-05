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
		StructBESA sb = new StructBESA();

		// interaccion con TP
		Utils.agregarAEstructura(sb, CLVerFila.class);
		Utils.agregarAEstructura(sb, CLHacerFila.class);
		Utils.agregarAEstructura(sb, CLRecibirMenu.class);

		// interaccion con CA
		Utils.agregarAEstructura(sb, CLVerFilaPago.class);
		Utils.agregarAEstructura(sb, CLHacerFilaPago.class);
		Utils.agregarAEstructura(sb, CLRealizarPago.class);

		CLAgent ca = new CLAgent(name, new CLState(), sb, clave);
		ca.start();
		Utils.send(ca.getAdmLocal(), ca.getAid(), CLVerFila.class.getName(), new EmptyData());

	}
}
