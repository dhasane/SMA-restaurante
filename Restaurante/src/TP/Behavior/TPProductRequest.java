package TP.Behavior;

import BESA.Kernell.Agent.Event.EventBESA;
import CL.Behavior.CLVerFilaPago;
import Data.EmptyData;
import Data.PedidoData;
import Informacion.PedidosRealizadosNoPagos;
import Utils.Utils;
import BESA.Kernell.Agent.GuardBESA;

public class TPProductRequest extends GuardBESA {

	@Override
	public void funcExecGuard(EventBESA ebesa) {

		PedidoData pd = (PedidoData) ebesa.getData();
		PedidosRealizadosNoPagos.add(pd);
		// decirle al cliente que pague
		Utils.send(getAgent().getAdmLocal(), pd.getDueño(), CLVerFilaPago.class.getName(), new EmptyData());

	}

}