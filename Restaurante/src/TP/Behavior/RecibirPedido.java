package TP.Behavior;

import BESA.Kernell.Agent.Event.EventBESA;
import TP.State.TPState;
import BESA.Kernell.Agent.GuardBESA;

public class RecibirPedido extends GuardBESA{
	
    @Override
    public void funcExecGuard(EventBESA ebesa) {
    	
    	TPState state = (TPState) this.getAgent().getState();
    	System.out.println("kiubo :D");
    	
    	state.aumentar();
    	
    	System.out.println( state.getAtendiendo() );
    }

}
