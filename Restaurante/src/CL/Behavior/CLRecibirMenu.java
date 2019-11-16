package CL.Behavior;

import BESA.Kernell.Agent.Event.EventBESA;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import BESA.Kernell.Agent.GuardBESA;
import Data.MenuData;
import Data.PedidoData;
import TP.Behavior.TPProductRequest;
import Utils.Utils;

public class CLRecibirMenu extends GuardBESA {

	@Override
	public void funcExecGuard(EventBESA ebesa) {

		MenuData fd = (MenuData) ebesa.getData();

		Random r = new Random();
		List<String> pedido = new ArrayList<String>();

		while (pedido.size() < 3) {
			pedido.add(fd.getMenu().get(r.nextInt(fd.getMenu().size())));
		}

		Utils.send(getAgent().getAdmLocal(), fd.responder(), TPProductRequest.class.getName() , new PedidoData(pedido, getAgent().getAid()));

		return;
	}

}
