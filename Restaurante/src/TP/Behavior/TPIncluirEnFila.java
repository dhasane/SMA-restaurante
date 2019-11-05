package TP.Behavior;

import BESA.Kernell.Agent.Event.EventBESA;
import Data.IDData;
import TP.State.TPState;
import Utils.Utils;
import BESA.Kernell.Agent.GuardBESA;

public class TPIncluirEnFila extends GuardBESA{
	
    @Override
    public void funcExecGuard(EventBESA ebesa) {
    	
    	TPState state = (TPState) this.getAgent().getState();
    	
    	state.addFila( ((IDData)ebesa.getData()).getId() );
    	
    	Utils.imp( getAgent().getAlias() + " ahora tiene : " +state.getAtendiendo() );
//    	state.print( getAgent().getAlias() );
    }

}
