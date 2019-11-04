package TP.Behavior;

import BESA.Kernell.Agent.Event.EventBESA;
import Data.PedidoData;
import TP.State.TPState;
import BESA.Kernell.Agent.GuardBESA;

public class ProductRequest extends GuardBESA{
	
    @Override
    public void funcExecGuard(EventBESA ebesa) {
    	
    	TPState state = (TPState) this.getAgent().getState();
    	
    	((PedidoData)ebesa.getData()).getPedido() ;

    	// enviar el pedido a la cocina :v 
    	// enviar monto a caja para cobrar
    	// enviar monto a cliente para que pague 
    }

}
