package CL;

import BESA.Kernell.Agent.AgentBESA;
import BESA.Kernell.Agent.KernellAgentExceptionBESA;
import BESA.Kernell.Agent.StateBESA;
import BESA.Kernell.Agent.StructBESA;
import BESA.Log.ReportBESA;

public class CLAgent extends AgentBESA {

    public CLAgent(String alias, StateBESA state, StructBESA structAgent, double passwd) throws KernellAgentExceptionBESA {
        super(alias, state, structAgent, passwd);
    }

    @Override
    public void setupAgent() {
        ReportBESA.info("SETUP AGENT -> " + getAlias());
//        ClienteState cs = (ClienteState)this.getState();
    }

    @Override
    public void shutdownAgent() {
        ReportBESA.info("SHUTDOWN AGENT -> " + getAlias());
    }

}
