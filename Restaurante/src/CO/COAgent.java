package CO;

import BESA.Kernell.Agent.AgentBESA;
import BESA.Kernell.Agent.KernellAgentExceptionBESA;
import BESA.Kernell.Agent.StateBESA;
import BESA.Kernell.Agent.StructBESA;
import BESA.Log.ReportBESA;
import Utils.Utils;

public class COAgent extends AgentBESA {

	public COAgent(String alias, StateBESA state, StructBESA structAgent, double passwd)
			throws KernellAgentExceptionBESA {
		super(alias, state, structAgent, passwd);

	}

	@Override
	public void setupAgent() {
		ReportBESA.info("SETUP AGENT -> " + getAlias());

		getAdmLocal().bindSPServiceInDirectory(this.getAid(), Utils.Cocinero);

	}

	@Override
	public void shutdownAgent() {
		ReportBESA.info("SHUTDOWN AGENT -> " + getAlias());
	}

}
