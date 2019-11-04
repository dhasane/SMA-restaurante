package TP.Behavior;

import BESA.Kernell.Agent.Event.EventBESA;
import CL.Behavior.HacerFila;
import Data.FilaData;
import Data.IDData;
import TP.State.TPState;
import BESA.Kernell.Agent.GuardBESA;

import Utils.Utils;

public class ResponderFila extends GuardBESA{
	
    @Override
    public void funcExecGuard(EventBESA ebesa) {
    	
    	TPState state = (TPState) this.getAgent().getState();
//    	System.out.println( getAgent().getAlias() + " responde longitud de filas ( "+ state.getAtendiendo() + " )");
    	
    	IDData idd = (IDData) ebesa.getData();
    	
    	Utils.send( getAgent().getAdmLocal(), idd.getId() , HacerFila.class.getName(), new FilaData( state.getAtendiendo(), getAgent().getAid()  ));
    }

}
