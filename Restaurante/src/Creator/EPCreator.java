package Creator;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.PeriodicGuardBESA;
import BESA.Kernell.Agent.StructBESA;
import BESA.Util.PeriodicDataBESA;
import EP.EPAgent;
import EP.Behavior.EPPrepararPedido;
import EP.State.EPState;
import Utils.Utils;

public class EPCreator {

	private static double clave;

	public static void setClave(double clave) {
		EPCreator.clave = clave;
	}

	// crea varios agentes
	public static void crearEP(int[][] cantidad) throws ExceptionBESA {
		for (int a = 0; a < cantidad.length; ++a)
			agente("EP" + Integer.toString(a), cantidad[a]);
	}

	// crea un unico agente, pasandole el tamaño del mapa y su nombre
	private static void agente(String name, int[] pos) throws ExceptionBESA {
		StructBESA sb = new StructBESA();

		Utils.agregarAEstructura(sb, EPPrepararPedido.class);

		EPAgent ep = new EPAgent(name, new EPState(), sb, clave, pos);
		ep.start();

		PeriodicDataBESA data = new PeriodicDataBESA(Utils.PERIODIC_TIME, Utils.PERIODIC_DELAY_TIME, PeriodicGuardBESA.START_PERIODIC_CALL);

		Utils.send(ep.getAdmLocal(), ep.getAid(), EPPrepararPedido.class.getName(), data);
	}
}
