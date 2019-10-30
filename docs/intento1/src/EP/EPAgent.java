 package EP;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.AgentBESA;
import BESA.Kernell.Agent.Event.DataBESA;
import BESA.Kernell.Agent.Event.EventBESA;
import BESA.Kernell.Agent.KernellAgentExceptionBESA;
import BESA.Kernell.Agent.StateBESA;
import BESA.Kernell.Agent.StructBESA;
import BESA.Kernell.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import Data.SubscribeData;
import EP.State.EPState;
import World.Behavior.SubscribeGuard;

public class EPAgent extends AgentBESA {

    public EPAgent(String alias, StateBESA state, StructBESA structAgent, double passwd, int x, int y) throws KernellAgentExceptionBESA {
        super(alias, state, structAgent, passwd);

        EPState cs = (EPState)this.getState();
        cs.setX( x );
        cs.setY( y );
    }

    @Override
    public void setupAgent() {
        ReportBESA.info("SETUP AGENT -> " + getAlias());
        EPState cs = (EPState)this.getState();
        
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
