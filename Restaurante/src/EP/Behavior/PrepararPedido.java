/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EP.Behavior;

import BESA.Kernell.Agent.Event.EventBESA;
import EP.State.EPState;
import Utils.Utils;
import BESA.Kernell.Agent.GuardBESA;
import BESA.Kernell.Agent.StateBESA;


public class PrepararPedido extends GuardBESA{

    @Override
    public boolean funcEvalBool(StateBESA objEvalBool) {
        return true;
    }

    @Override
    public void funcExecGuard(EventBESA ebesa) {
    	
        EPState state = (EPState) this.getAgent().getState();
        
    	return;
    }

}
