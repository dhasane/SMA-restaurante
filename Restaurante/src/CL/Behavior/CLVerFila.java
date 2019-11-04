package CL.Behavior;

import BESA.Kernell.Agent.Event.EventBESA;
import CL.State.CLState;
import BESA.Kernell.Agent.GuardBESA;
import BESA.Kernell.Agent.StateBESA;
import Data.IDData;
import TP.Behavior.TPResponderFila;
import Utils.Utils;

public class CLVerFila extends GuardBESA{


    @Override
    public boolean funcEvalBool(StateBESA objEvalBool) {
        return true;
    }

    @Override
    public void funcExecGuard(EventBESA ebesa) {

    	System.out.println( getAgent().getAlias() + " pide longitud de filas");
    	
        int cantidad =  Utils.broadcast( getAgent().getAdmLocal() , Utils.tomaPedido, TPResponderFila.class.getName(), new IDData( getAgent().getAid() )  );
        
        CLState cs = (CLState)getAgent().getState();
        cs.setPreguntas(cantidad);
        
        return;

    }


}
