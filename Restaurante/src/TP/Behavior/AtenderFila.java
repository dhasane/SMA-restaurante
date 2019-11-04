package TP.Behavior;

import BESA.Kernell.Agent.Event.EventBESA;
import CL.Behavior.RecibirMenu;
import Data.MenuData;
import TP.State.TPState;
import BESA.Kernell.Agent.PeriodicGuardBESA;
import Utils.Utils;

public class AtenderFila extends PeriodicGuardBESA {

	@Override
	public void funcPeriodicExecGuard(EventBESA arg0) {
		if ( ((TPState) this.getAgent().getState()).getAtendiendo() > 0) {
			System.out.println(getAgent().getAlias() + " atiende la fila -----------------------------------------------------------------------------");
				
			Utils.send(getAgent().getAdmLocal(), ((TPState) this.getAgent().getState()).getSiguiente(), RecibirMenu.class.getName(), new MenuData( getAgent().getAid() ) );
		}
	}

}
