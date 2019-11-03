/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CL.Behavior;

import BESA.Kernell.Agent.Event.EventBESA;
import BESA.Kernell.Agent.GuardBESA;
import BESA.Kernell.Agent.StateBESA;
import CL.State.ClienteState;

public class ClienteSensorGuard extends GuardBESA{


    @Override
    public boolean funcEvalBool(StateBESA objEvalBool) {
        return true;
    }

    @Override
    public void funcExecGuard(EventBESA ebesa) {

        ClienteState cs = (ClienteState) this.getAgent().getState();

        if ( cs.hambre() )
        {
        	System.out.println("busca comida");
        	hacerPedido( cs, ebesa );
        }
        else if( cs.pedidoHecho() )
        {
        	buscarComida(cs, ebesa);
        }

        else return;

    }

    private void hacerPedido(ClienteState cs, EventBESA ebesa)
    {
    	// por si acaso, esta mierda aun no sirve

    }

    private void buscarComida(ClienteState cs, EventBESA ebesa)
    {
    }
}
