package Creator;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.PeriodicGuardBESA;
import BESA.Kernell.Agent.StructBESA;
import BESA.Util.PeriodicDataBESA;
import Data.EmptyData;
import TP.TPAgent;
import TP.Behavior.AtenderFila;
import TP.Behavior.IncluirEnFila;
import TP.Behavior.ResponderFila;
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
		StructBESA c1Struct = new StructBESA();

		Utils.agregarAEstructura(c1Struct, ResponderFila.class);
		Utils.agregarAEstructura(c1Struct, IncluirEnFila.class);
		Utils.agregarAEstructura(c1Struct, AtenderFila.class);

		TPAgent tpa = new TPAgent(name, new TPState(), c1Struct, clave);
		tpa.start();
		PeriodicDataBESA data = new PeriodicDataBESA(Utils.PERIODIC_TIME, Utils.PERIODIC_DELAY_TIME, PeriodicGuardBESA.START_PERIODIC_CALL);
        
		Utils.send(tpa.getAdmLocal(), tpa.getAid(), AtenderFila.class.getName(), data);

	}
}
