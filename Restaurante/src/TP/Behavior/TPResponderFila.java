package TP.Behavior;

import BESA.Kernell.Agent.Event.EventBESA;
import CL.Behavior.CLHacerFila;
import Data.FilaData;
import Data.IDData;
import Mundo.Mapa.Mapa;
import TP.State.TPState;
import BESA.Kernell.Agent.GuardBESA;

import Utils.Utils;

public class TPResponderFila extends GuardBESA {

	@Override
	public void funcExecGuard(EventBESA ebesa) {

		TPState state = (TPState) this.getAgent().getState();
//    	Utils.imp( getAgent().getAlias() + " responde longitud de filas ( "+ state.getAtendiendo() + " )");

		IDData idd = (IDData) ebesa.getData();

		Utils.send(getAgent().getAdmLocal(), idd.getId(), CLHacerFila.class.getName(),
				new FilaData(state.getAtendiendo(), getAgent().getAid()));
		Mapa.repaint();
	}

}
