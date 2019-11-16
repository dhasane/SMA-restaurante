package CL.Behavior;

import BESA.Kernell.Agent.Event.EventBESA;
import CA.Behavior.CARecibirPago;
import BESA.Kernell.Agent.GuardBESA;
import BESA.Kernell.Agent.StateBESA;
import Data.PagoData;
import Utils.Utils;

public class CLRealizarPago extends GuardBESA {

	@Override
	public boolean funcEvalBool(StateBESA objEvalBool) {
		return true;
	}

	@Override
	public void funcExecGuard(EventBESA ebesa) {

		PagoData fd = (PagoData) ebesa.getData();

		// Utils.imp(getAgent().getAlias() + " paga por su pedido " + fd.getMonto());

		String responderA = fd.responder();

		fd.setResopnder(getAgent().getAid());

		Utils.send(getAgent().getAdmLocal(), responderA, CARecibirPago.class.getName(), fd);

		return;

	}

}
