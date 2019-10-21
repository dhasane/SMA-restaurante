package World.Behavior;

import BESA.Kernell.Agent.Event.EventBESA;
import BESA.Kernell.Agent.GuardBESA;
import Data.SubscribeData;
import World.State.WorldState;

public class SubscribeGuard extends GuardBESA{

    @Override
    public void funcExecGuard(EventBESA ebesa) {
        SubscribeData data = (SubscribeData)ebesa.getData();
        WorldState ws = (WorldState)this.getAgent().getState();
        ws.getBotsAlias().add(data.getAlias());
        String nom = "" + data.getAlias().charAt(0) + data.getAlias().charAt(1);
        
        System.out.println( nom );
        
        if ( nom.equals("CL"))
        {
        	ws.getMap().addCliente(data.getAlias(), data.getX(), data.getY());
        }
        else if ( nom.equals("TP"))
        {
        	ws.getMap().addTP(data.getAlias(), data.getX(), data.getY());
        }
        
        
//        ws.getMap().addBot(data.getAlias(), data.getX(), data.getY());
    }
    
}
