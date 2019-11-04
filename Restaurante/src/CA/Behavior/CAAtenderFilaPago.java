package CA.Behavior;

import BESA.Kernell.Agent.PeriodicGuardBESA;
import BESA.Kernell.Agent.Event.EventBESA;
import CL.Behavior.CLRealizarPago;
import Data.PagoData;
import CA.State.CAState;
import Utils.Utils;

public class CAAtenderFilaPago extends PeriodicGuardBESA {

	@Override
	public void funcPeriodicExecGuard(EventBESA arg0) {
		if (((CAState) this.getAgent().getState()).getAtendiendo() > 0) {
			System.out.println(getAgent().getAlias()
					+ " atiende la fila pago -----------------------------------------------------------------------------");

			String cl = ((CAState) this.getAgent().getState()).getSiguiente();

			Utils.send(getAgent().getAdmLocal(), cl, CLRealizarPago.class.getName(),
					new PagoData(cl, getAgent().getAid()));
		}
	}

}