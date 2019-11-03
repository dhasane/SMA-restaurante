package CL.Behavior;

import BESA.Kernell.Agent.Event.EventBESA;
import BESA.Kernell.System.Directory.AgHandlerBESA;
import BESA.ExceptionBESA;
import BESA.Kernell.Agent.GuardBESA;
import BESA.Kernell.Agent.StateBESA;
import CL.State.ClienteState;
import TP.Behavior.RecibirPedido;
import Utils.Nombres;

public class ProductsReques extends GuardBESA{


    @Override
    public boolean funcEvalBool(StateBESA objEvalBool) {
        return true;
    }

    @Override
    public void funcExecGuard(EventBESA ebesa) {

        ClienteState cs = (ClienteState) this.getAgent().getState();


        String agid =  getAgent().getAdmLocal().lookupSPServiceInDirectory( Nombres.tomaPedido );
        System.out.println(" este es el string : " +agid);
        
//        EventBESA event = new EventBESA( RecibirPedido.class.getName(),  );
//        try {
//			AgHandlerBESA agh = getAgent().getAdmLocal().getHandlerByAid(agid);
//		} catch (ExceptionBESA e) {
//			e.printStackTrace();
//		}

        return;

    }


}
