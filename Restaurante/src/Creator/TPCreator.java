package Creator;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.PeriodicGuardBESA;
import BESA.Kernell.Agent.StructBESA;
import BESA.Util.PeriodicDataBESA;
import TP.TPAgent;
import TP.Behavior.TPAtenderFila;
import TP.Behavior.TPIncluirEnFila;
import TP.Behavior.TPProductRequest;
import TP.Behavior.TPResponderFila;
import TP.State.TPState;
import Utils.Utils;

public class TPCreator {

	private static double clave;

	public static void setClave(double clave) {
		TPCreator.clave = clave;
	}

	// crea varios agentes
	public static void crearTP(int cantidad) throws ExceptionBESA {
		for (int a = 0; a < cantidad; ++a)
			agente("TP" + Integer.toString(a));
	}

	private static void agente(String name) throws ExceptionBESA {
		StructBESA sb = new StructBESA();

		Utils.agregarAEstructura(sb, TPAtenderFila.class);
		Utils.agregarAEstructura(sb, TPIncluirEnFila.class);
		Utils.agregarAEstructura(sb, TPProductRequest.class);
		Utils.agregarAEstructura(sb, TPResponderFila.class);

		TPAgent tpa = new TPAgent(name, new TPState(), sb, clave);
		tpa.start();
		PeriodicDataBESA data = new PeriodicDataBESA(Utils.PERIODIC_TIME, Utils.PERIODIC_DELAY_TIME,
				PeriodicGuardBESA.START_PERIODIC_CALL);

		Utils.send(tpa.getAdmLocal(), tpa.getAid(), TPAtenderFila.class.getName(), data);

	}
}
