/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EP.Behavior;

import BESA.Kernell.Agent.Event.DataBESA;
import BESA.Kernell.Agent.Event.EventBESA;
import BESA.Kernell.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import Data.ActionData;
import EP.State.EPState;
import World.Behavior.UpdateGuard;
import BESA.ExceptionBESA;
import BESA.Kernell.Agent.GuardBESA;
import BESA.Kernell.Agent.StateBESA;


public class EPSensorGuard extends GuardBESA{

    @Override
    public boolean funcEvalBool(StateBESA objEvalBool) {
        return true;
    }

    @Override
    public void funcExecGuard(EventBESA ebesa) {
    	
    	System.out.println(" holaaaaaaa puta vida ");

        EPState state = (EPState) this.getAgent().getState();
        
        System.out.println( state.size() );
        
    	if ( state.size() > 0 )
    	{
    		ponerComida( ebesa , state );
    	}
    	else return;
    }
    
    private void ponerComida(EventBESA ebesa, EPState state) 
    {

        System.out.println("poniendo comida");
        
        DataBESA dataAction = new ActionData(this.getAgent().getAlias(), "addFood", state.getX() -1, state.getY() );
        
        EventBESA event = new EventBESA(UpdateGuard.class.getName(), dataAction);
        AgHandlerBESA ah;
        try {
            ah = getAgent().getAdmLocal().getHandlerByAlias("WORLD");
            ah.sendEvent(event);
        }
        catch (ExceptionBESA e) {
            ReportBESA.error(e);
        }

	}

	public void mostrarProductos()
    {
    	// mostrar al cliente cuales productos hay y que ingredientes tienen
    	// despues de esto, se envia la info al cocinero
    }

}
