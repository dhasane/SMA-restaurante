/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EP.Behavior;

import BESA.Kernell.Agent.Event.EventBESA;
import BESA.Kernell.Agent.GuardBESA;
import BESA.Kernell.Agent.StateBESA;


public class EPSensorGuard extends GuardBESA{

    @Override
    public boolean funcEvalBool(StateBESA objEvalBool) {
        return true;
    }

    @Override
    public void funcExecGuard(EventBESA ebesa) {

    }
    
    public void mostrarProductos()
    {
    	// mostrar al cliente cuales productos hay y que ingredientes tienen
    	// despues de esto, se envia la info al cocinero
    }

}
