package TP.Behavior;

import BESA.Kernell.Agent.Event.EventBESA;
import Data.IDData;
import TP.State.TPState;
import BESA.Kernell.Agent.GuardBESA;

public class IncluirEnFila extends GuardBESA{
	
    @Override
    public void funcExecGuard(EventBESA ebesa) {
    	
    	TPState state = (TPState) this.getAgent().getState();
    	
    	state.addFila( ((IDData)ebesa.getData()).getId() );
    	
    	System.out.println( getAgent().getAlias() + " ahora tiene : " +state.getAtendiendo() );
//    	state.print( getAgent().getAlias() );
    }

}