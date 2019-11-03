 package TP;

import BESA.Kernell.Agent.AgentBESA;
import BESA.Kernell.Agent.KernellAgentExceptionBESA;
import BESA.Kernell.Agent.StateBESA;
import BESA.Kernell.Agent.StructBESA;
import BESA.Log.ReportBESA;
import Utils.Nombres;

public class TPAgent extends AgentBESA {

    public TPAgent(String alias, StateBESA state, StructBESA structAgent, double passwd) throws KernellAgentExceptionBESA {
        super(alias, state, structAgent, passwd);
    }

    @Override
    public void setupAgent() {
        ReportBESA.info("SETUP AGENT -> " + getAlias());
        getAdmLocal().bindSPServiceInDirectory(this.getAid() , Nombres.tomaPedido );
//        TPState cs = (TPState)this.getState();
        
    }

    @Override
    public void shutdownAgent() {
        ReportBESA.info("SHUTDOWN AGENT -> " + getAlias());
    }

}
