package CL.Behavior;

import BESA.Kernell.Agent.Event.EventBESA;
import CL.State.CLState;
import BESA.Kernell.Agent.GuardBESA;
import Data.IDData;
import TP.Behavior.TPResponderFila;
import Utils.Utils;

public class CLVerFila extends GuardBESA {

	@Override
	public void funcExecGuard(EventBESA ebesa) {

//    	Util.imp( getAgent().getAlias() + " pide longitud de filas");
		((CLState) this.getAgent().getState()).sinPreguntas();
		int cantidad = Utils.broadcast(getAgent().getAdmLocal(), Utils.tomaPedido, TPResponderFila.class.getName(),
				new IDData(getAgent().getAid()));

		CLState cs = (CLState) getAgent().getState();
		cs.setPreguntas(cantidad);

		return;

	}

}
