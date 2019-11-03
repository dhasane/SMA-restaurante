package CL.Behavior;

import BESA.Kernell.Agent.Event.EventBESA;
import CL.State.ClienteState;
import BESA.Kernell.Agent.GuardBESA;
import BESA.Kernell.Agent.StateBESA;
import Data.IDData;
import TP.Behavior.ResponderFila;
import Utils.Utils;

public class VerFila extends GuardBESA{


    @Override
    public boolean funcEvalBool(StateBESA objEvalBool) {
        return true;
    }

    @Override
    public void funcExecGuard(EventBESA ebesa) {

    	System.out.println( getAgent().getAlias() + " pide longitud de filas");
    	
        int cantidad =  Utils.broadcast( getAgent().getAdmLocal() , Utils.tomaPedido, ResponderFila.class.getName(), new IDData( getAgent().getAid() )  );
        
        ClienteState cs = (ClienteState)getAgent().getState();
        cs.setPreguntas(cantidad);
        
        return;

    }


}
