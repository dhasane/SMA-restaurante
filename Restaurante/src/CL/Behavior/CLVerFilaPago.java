package CL.Behavior;

import BESA.Kernell.Agent.GuardBESA;
import BESA.Kernell.Agent.Event.EventBESA;
import CA.Behavior.CAResponderFilaPago;
import CL.State.CLState;
import Data.IDData;
import Utils.Utils;

public class CLVerFilaPago extends GuardBESA {

	@Override
	public void funcExecGuard(EventBESA arg0) {

		((CLState) this.getAgent().getState()).sinPreguntas();

		int cantidad = Utils.broadcast(getAgent().getAdmLocal(), Utils.Caja, CAResponderFilaPago.class.getName(),
				new IDData(getAgent().getAid()));

		CLState cs = (CLState) getAgent().getState();
		cs.setPreguntas(cantidad);

		return;
	}

}
