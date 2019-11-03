/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TP.Behavior;

import BESA.Kernell.Agent.Event.EventBESA;
import BESA.Kernell.Agent.GuardBESA;
import BESA.Kernell.Agent.StateBESA;


public class TPSensorGuard extends GuardBESA{

    @Override
    public boolean funcEvalBool(StateBESA objEvalBool) {
        return true;
    }

    @Override
    public void funcExecGuard(EventBESA ebesa) {

    	System.out.println(" hola perra");

    }
    
    // recibir info
    // reenviar info

}
