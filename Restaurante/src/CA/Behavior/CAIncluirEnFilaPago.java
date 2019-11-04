package CA.Behavior;

import BESA.Kernell.Agent.GuardBESA;
import BESA.Kernell.Agent.Event.EventBESA;
import CA.State.CAState;
import Data.IDData;

public class CAIncluirEnFilaPago extends GuardBESA{

	@Override
    public void funcExecGuard(EventBESA ebesa) {
    	
    	CAState state =  (CAState) this.getAgent().getState();
    	
    	state.addFila( ((IDData)ebesa.getData()).getId() );
    	
    	System.out.println( getAgent().getAlias() + " ahora tiene : " +state.getAtendiendo() );
//    	state.print( getAgent().getAlias() );
    }
}
