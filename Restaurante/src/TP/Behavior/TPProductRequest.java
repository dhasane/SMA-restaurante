package TP.Behavior;

import BESA.Kernell.Agent.Event.EventBESA;
import CL.Behavior.CLVerFilaPago;
import Data.EmptyData;
import Data.PedidoData;
import Mundo.PedidosRealizadosNoPagos;
import Mundo.Mapa.Mapa;
import Utils.Utils;
import BESA.Kernell.Agent.GuardBESA;

public class TPProductRequest extends GuardBESA {

	@Override
	public void funcExecGuard(EventBESA ebesa) {

		PedidoData pd = (PedidoData) ebesa.getData();
		PedidosRealizadosNoPagos.add(pd);
		// decirle al cliente que pague
		Utils.send(getAgent().getAdmLocal(), pd.getOwner(), CLVerFilaPago.class.getName(), new EmptyData());
		Mapa.repaint();
	}

}
