package CA.Behavior;

import BESA.Kernell.Agent.GuardBESA;
import BESA.Kernell.Agent.Event.EventBESA;
import CA.State.CAState;
import Data.IDData;
import Mundo.Mapa.Mapa;
import Utils.Utils;

// incluye al cliente en la fila de pago, despues de este pedirle que lo incluya
public class CAIncluirEnFilaPago extends GuardBESA{

	@Override
    public void funcExecGuard(EventBESA ebesa) {
    	CAState state =  (CAState) this.getAgent().getState();
    	state.addFila( ((IDData)ebesa.getData()).getId() );
    	Mapa.repaint();
    }
}
