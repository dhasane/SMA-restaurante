package Creator;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.PeriodicGuardBESA;
import BESA.Kernell.Agent.StructBESA;
import BESA.Util.PeriodicDataBESA;
import CA.CAAgent;
import CA.Behavior.CAAtenderFilaPago;
import CA.Behavior.CAIncluirEnFilaPago;
import CA.Behavior.CARecibirPago;
import CA.Behavior.CAResponderFilaPago;
import CA.State.CAState;
import Utils.Utils;

public class CACreator {

	private static double clave;

	public static void setClave(double clave) {
		CACreator.clave = clave;
	}

	// crea varios agentes
	public static void crearCA(int[][]  cantidad) throws ExceptionBESA {
		for (int a = 0; a < cantidad.length; ++a)
			agente("CA" + Integer.toString(a), cantidad[a]);
	}

	private static void agente(String name, int[] pos) throws ExceptionBESA {
		StructBESA sb = new StructBESA();

		Utils.agregarAEstructura(sb, CAResponderFilaPago.class);
		Utils.agregarAEstructura(sb, CAIncluirEnFilaPago.class);
		Utils.agregarAEstructura(sb, CARecibirPago.class);
		Utils.agregarAEstructura(sb, CAAtenderFilaPago.class);

		CAAgent tpa = new CAAgent(name, new CAState(), sb, clave, pos);
		tpa.start();
		PeriodicDataBESA data = new PeriodicDataBESA(Utils.PERIODIC_TIME, Utils.PERIODIC_DELAY_TIME, PeriodicGuardBESA.START_PERIODIC_CALL);

		Utils.send(tpa.getAdmLocal(), tpa.getAid(), CAAtenderFilaPago.class.getName(), data);

	}
}
