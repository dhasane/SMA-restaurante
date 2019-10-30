/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TP.Behavior;

import BESA.Kernell.Agent.Event.EventBESA;
import BESA.Kernell.Agent.GuardBESA;
import Data.ActionData;
import World.State.WorldState;

public class PedidoGuard extends GuardBESA{

	// esto realiza acciones de los agentes 
	
    @Override
    public void funcExecGuard(EventBESA ebesa) {
        ActionData data = (ActionData) ebesa.getData();
        WorldState state = (WorldState)this.getAgent().getState();
        switch (data.getAction()) {
            case "clean":
                state.clean(data.getAlias());
                break;
            case "addFood":
                state.ponerComida(data.getAlias(), data.getX(), data.getY());
                break;
            case "move":
                state.move(data.getAlias(), data.getX(), data.getY());
                break;
            case "sit":
                state.sit(data.getAlias(), data.getX(), data.getY());
                break;
        }
    }

}
