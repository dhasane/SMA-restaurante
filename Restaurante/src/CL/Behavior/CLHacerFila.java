package CL.Behavior;

import BESA.Kernell.Agent.Event.EventBESA;
import CL.State.CLState;
import BESA.Kernell.Agent.GuardBESA;
import BESA.Kernell.Agent.StateBESA;
import Data.FilaData;
import Data.IDData;
import TP.Behavior.TPIncluirEnFila;
import Utils.Utils;

public class CLHacerFila extends GuardBESA {

	@Override
	public boolean funcEvalBool(StateBESA objEvalBool) {
		return true;
	}

	@Override
	public void funcExecGuard(EventBESA ebesa) {

		FilaData fd = (FilaData) ebesa.getData();
		CLState cs = (CLState) getAgent().getState();

//		Util.imp(getAgent().getAlias() + " evalua la mejor fila a tomar " + cs.getPreguntas() + " preguntas");

		cs.setFila(fd.getOwner(), fd.getLogitud());
		cs.reducirPregunta();
		

		// en caso de ya haber visto todas las filas, entra a una
		if (cs.getPreguntas() <= 0) {
			cs.inicioTiempoEspera();
			Utils.imp(getAgent().getAlias() + " entrando a fila con " + cs.getFila());
			Utils.send(getAgent().getAdmLocal(), cs.getFila(), TPIncluirEnFila.class.getName(),
					new IDData(getAgent().getAid()));
		}

		return;

	}

}
