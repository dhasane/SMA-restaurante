package TP.Behavior;

import BESA.Kernell.Agent.Event.EventBESA;
import CL.Behavior.CLRecibirMenu;
import Data.MenuData;
import TP.State.TPState;
import BESA.Kernell.Agent.PeriodicGuardBESA;
import Utils.Utils;

public class TPAtenderFila extends PeriodicGuardBESA {

	@Override
	public void funcPeriodicExecGuard(EventBESA arg0) {
		if (((TPState) this.getAgent().getState()).getAtendiendo() > 0) {
			Utils.imp(getAgent().getAlias()
					+ " atiende la fila -----------------------------------------------------------------------------");

			Utils.send(getAgent().getAdmLocal(), ((TPState) this.getAgent().getState()).getSiguiente(),
					CLRecibirMenu.class.getName(), new MenuData(getAgent().getAid()));
		}
	}

}
