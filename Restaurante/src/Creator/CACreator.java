package Creator;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.PeriodicGuardBESA;
import BESA.Kernell.Agent.StructBESA;
import BESA.Util.PeriodicDataBESA;
import CA.CAAgent;
import CA.Behavior.CAAtenderFilaPago;
import CA.Behavior.CAIncluirEnFilaPago;
import CA.Behavior.CAResponderFilaPago;
import CA.State.CAState;
import Utils.Utils;


public class CACreator {

	private static double clave;

	public static void setClave(double clave) {
		CACreator.clave = clave;
	}

	// crea varios agentes
	public static void crearCA(int cantidad) throws ExceptionBESA {
		for (int a = 0; a < cantidad; ++a)
			agente("CA" + Integer.toString(a));
	}

	private static void agente(String name) throws ExceptionBESA {
		StructBESA c1Struct = new StructBESA();

		Utils.agregarAEstructura(c1Struct, CAResponderFilaPago.class);
		Utils.agregarAEstructura(c1Struct, CAIncluirEnFilaPago.class);
		Utils.agregarAEstructura(c1Struct, CAAtenderFilaPago.class);

		CAAgent tpa = new CAAgent(name, new CAState(), c1Struct, clave);
		tpa.start();
		PeriodicDataBESA data = new PeriodicDataBESA(Utils.PERIODIC_TIME, Utils.PERIODIC_DELAY_TIME, PeriodicGuardBESA.START_PERIODIC_CALL);
        
		Utils.send( tpa.getAdmLocal(), tpa.getAid(), CAAtenderFilaPago.class.getName(), data );

	}
}
