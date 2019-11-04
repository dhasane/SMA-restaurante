package CL.Behavior;

import BESA.Kernell.Agent.Event.EventBESA;
import BESA.Kernell.Agent.GuardBESA;
import BESA.Kernell.Agent.StateBESA;
import Data.PagoData;
import TP.Behavior.TPProductRequest;
import Utils.Utils;

public class CLRealizarPago extends GuardBESA {

	@Override
	public boolean funcEvalBool(StateBESA objEvalBool) {
		return true;
	}

	@Override
	public void funcExecGuard(EventBESA ebesa) {

		PagoData fd = (PagoData) ebesa.getData();
//		ClienteState cs = (ClienteState) getAgent().getState();

		System.out.println(getAgent().getAlias() + " paga por su pedido " + fd.getMonto());

		Utils.send(getAgent().getAdmLocal(), fd.responder(), TPProductRequest.class.getName(), fd);

		return;

	}

}
