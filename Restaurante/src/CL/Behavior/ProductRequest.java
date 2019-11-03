package CL.Behavior;

import BESA.Kernell.Agent.Event.EventBESA;
import BESA.Kernell.Agent.GuardBESA;
import BESA.Kernell.Agent.StateBESA;
import Data.EmptyData;
import TP.Behavior.RecibirPedido;
import Utils.Utils;

public class ProductRequest extends GuardBESA{


    @Override
    public boolean funcEvalBool(StateBESA objEvalBool) {
        return true;
    }

    @Override
    public void funcExecGuard(EventBESA ebesa) {

    	System.out.println( getAgent().getAlias() + " pidiendo la carta");


    	
        Utils.broadcast( getAgent().getAdmLocal() , Utils.tomaPedido, RecibirPedido.class.getName(), new EmptyData()  );

        return;

    }


}
