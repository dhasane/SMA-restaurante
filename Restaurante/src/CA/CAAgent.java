package CA;

import BESA.Kernell.Agent.AgentBESA;
import BESA.Kernell.Agent.KernellAgentExceptionBESA;
import BESA.Kernell.Agent.StateBESA;
import BESA.Kernell.Agent.StructBESA;
import BESA.Log.ReportBESA;
import Mundo.Mapa.Mapa;
import Utils.Utils;

public class CAAgent extends AgentBESA {

	private int[] pos;
	public CAAgent(String alias, StateBESA state, StructBESA structAgent, double passwd, int[] pos)
			throws KernellAgentExceptionBESA {
		super(alias, state, structAgent, passwd);
		this.pos = pos;
	}

	@Override
	public void setupAgent() {
		ReportBESA.info("SETUP AGENT -> " + getAlias());
		getAdmLocal().bindSPServiceInDirectory(this.getAid(), Utils.Caja);
		Mapa.addAgent(this, pos);
		
	}

	@Override
	public void shutdownAgent() {
		ReportBESA.info("SHUTDOWN AGENT -> " + getAlias());
	}

}
