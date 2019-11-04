 package CA;

import BESA.Kernell.Agent.AgentBESA;
import BESA.Kernell.Agent.KernellAgentExceptionBESA;
import BESA.Kernell.Agent.StateBESA;
import BESA.Kernell.Agent.StructBESA;
import BESA.Log.ReportBESA;
import CO.State.COState;
import Utils.Utils;

public class CAAgent extends AgentBESA {

	public CAAgent(String alias, StateBESA state, StructBESA structAgent, double passwd ) throws KernellAgentExceptionBESA {
    	super(alias, state, structAgent, passwd);

    }

    @Override
    public void setupAgent( ) {
        ReportBESA.info("SETUP AGENT -> " + getAlias());
//        CocineroState cs = (CocineroState) this.getState();

        getAdmLocal().bindSPServiceInDirectory(this.getAid() , Utils.Caja );

    }

    @Override
    public void shutdownAgent() {
        ReportBESA.info("SHUTDOWN AGENT -> " + getAlias());
    }

}
