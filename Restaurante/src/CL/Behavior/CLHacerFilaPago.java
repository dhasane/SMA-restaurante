package CL.Behavior;

import BESA.Kernell.Agent.Event.EventBESA;
import CA.Behavior.CAIncluirEnFilaPago;
import CL.State.CLState;
import BESA.Kernell.Agent.GuardBESA;
import BESA.Kernell.Agent.StateBESA;
import Data.FilaData;
import Data.IDData;
import Utils.Utils;

public class CLHacerFilaPago extends GuardBESA {

	@Override
	public boolean funcEvalBool(StateBESA objEvalBool) {
		return true;
	}

	@Override
	public void funcExecGuard(EventBESA ebesa) {

		FilaData fd = (FilaData) ebesa.getData();
		CLState cs = (CLState) getAgent().getState();

//		System.out.println(getAgent().getAlias() + " evalua la mejor fila a tomar " + cs.getPreguntas() + " preguntas");

		cs.setFila(fd.getDueño(), fd.getLogitud());
		cs.reducirPregunta();

		// en caso de ya haber visto todas las filas, entra a una
		if (cs.getPreguntas() <= 0) {
			System.out.println(getAgent().getAlias() + " entrando a fila de pago con " + cs.getFila());
			Utils.send(getAgent().getAdmLocal(), cs.getFila(), CAIncluirEnFilaPago.class.getName(),
					new IDData(getAgent().getAid()));
		}

		return;

	}

}
