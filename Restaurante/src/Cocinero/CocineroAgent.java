 package Cocinero;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.AgentBESA;
import BESA.Kernell.Agent.Event.DataBESA;
import BESA.Kernell.Agent.Event.EventBESA;
import BESA.Kernell.Agent.KernellAgentExceptionBESA;
import BESA.Kernell.Agent.StateBESA;
import BESA.Kernell.Agent.StructBESA;
import BESA.Kernell.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import Cocinero.State.CocineroState;
import Data.SubscribeData;
import World.Behavior.SubscribeGuard;
import java.util.Random;

public class CocineroAgent extends AgentBESA {

    public CocineroAgent(String alias, StateBESA state, StructBESA structAgent, double passwd ) throws KernellAgentExceptionBESA {
    	super(alias, state, structAgent, passwd);

    }

    // public void locate( int x, int y )
    // {
    //     System.out.println( x + "  " + y);
    //     CocineroState cs = (CocineroState) this.getState();
    //     cs.setX(x);
    //     cs.setY(y);
    //     DataBESA data = new SubscribeData(this.getAlias(), cs.getX(), cs.getY() );
    //     EventBESA event = new EventBESA(SubscribeGuard.class.getName(), data);
    //     AgHandlerBESA ah;
    //
    //     try {
    //         ah = this.getAdmLocal().getHandlerByAlias("WORLD");
    //         ah.sendEvent(event);
    //     } catch (ExceptionBESA e) {
    //         ReportBESA.error(e);
    //     }
    // }

    @Override
    public void setupAgent( ) {
        ReportBESA.info("SETUP AGENT -> " + getAlias());
        CocineroState cs = (CocineroState) this.getState();
        Random r = new Random();
        int initialx = r.nextInt(cs.getX());
        int initialy = r.nextInt(cs.getY());
        cs.setX(initialx);
        cs.setY(initialy);
        DataBESA data = new SubscribeData(this.getAlias(), cs.getX(), cs.getY() );
        EventBESA event = new EventBESA(SubscribeGuard.class.getName(), data);
        AgHandlerBESA ah;

        try {
            ah = this.getAdmLocal().getHandlerByAlias("WORLD");
            ah.sendEvent(event);
        } catch (ExceptionBESA e) {
            ReportBESA.error(e);
        }
    }

    @Override
    public void shutdownAgent() {
        ReportBESA.info("SHUTDOWN AGENT -> " + getAlias());
    }

}
