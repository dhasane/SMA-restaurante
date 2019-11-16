package CA.Behavior;

import BESA.Kernell.Agent.PeriodicGuardBESA;
import BESA.Kernell.Agent.Event.EventBESA;
import CL.Behavior.CLRealizarPago;
import Data.PagoData;
import Mundo.Mapa.Mapa;
import CA.State.CAState;
import Utils.Utils;

// atiende la fila de pagos (estado interno)
public class CAAtenderFilaPago extends PeriodicGuardBESA {

	@Override
	public void funcPeriodicExecGuard(EventBESA arg0) {
		if (((CAState) this.getAgent().getState()).getAtendiendo() > 0) {

			String cl = ((CAState) this.getAgent().getState()).getSiguiente();

			Utils.send(getAgent().getAdmLocal(), cl, CLRealizarPago.class.getName(),
					new PagoData(cl, getAgent().getAid()));
			Mapa.repaint();
		}
	}

}
