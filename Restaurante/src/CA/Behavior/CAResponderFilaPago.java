package CA.Behavior;

import BESA.Kernell.Agent.Event.EventBESA;
import CA.State.CAState;
import CL.Behavior.CLHacerFilaPago;
import Data.FilaData;
import Data.IDData;
import Utils.Utils;
import BESA.Kernell.Agent.GuardBESA;
import BESA.Kernell.Agent.StateBESA;

public class CAResponderFilaPago extends GuardBESA {

	@Override
	public boolean funcEvalBool(StateBESA objEvalBool) {
		return true;
	}

	@Override
	public void funcExecGuard(EventBESA ebesa) {

		CAState state = (CAState) this.getAgent().getState();
		
		Utils.send(getAgent().getAdmLocal(), ((IDData) ebesa.getData()).getId(), CLHacerFilaPago.class.getName(),
				new FilaData(state.getAtendiendo(), getAgent().getAid()));
	}

}
