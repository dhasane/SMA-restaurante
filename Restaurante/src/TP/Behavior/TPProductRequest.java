package TP.Behavior;

import BESA.Kernell.Agent.Event.EventBESA;
import CL.Behavior.CLVerFilaPago;
import CO.Behavior.COCocinar;
import Data.EmptyData;
import Data.PedidoData;
import Mundo.Cocinar;
import Mundo.Ingredientes;
import Mundo.PedidosRealizadosNoPagos;
import Mundo.Mapa.Mapa;
import Utils.Utils;

import java.util.ArrayList;
import java.util.List;

import BESA.Kernell.Agent.GuardBESA;

public class TPProductRequest extends GuardBESA {

	@Override
	public void funcExecGuard(EventBESA ebesa) {

		PedidoData pd = (PedidoData) ebesa.getData();
		PedidosRealizadosNoPagos.add(pd);
		// decirle al cliente que pague
		Utils.send(getAgent().getAdmLocal(), pd.getOwner(), CLVerFilaPago.class.getName(), new EmptyData());
		
		List<String> agregar = new ArrayList<String>();

		// revisarIngredientes
		for (String ing : pd.getPedido()) {
			int cantidad = Ingredientes.getCantidad(ing);
			if (cantidad < Utils.cantidadCriticaComida)
				agregar.add(ing);
		}

		if (agregar.size() > 0) {

			boolean enviar = false;
			if (agregar.size() != 0)
				enviar = true;
			Cocinar.add(agregar);
			if (enviar)
				Utils.broadcast(getAgent().getAdmLocal(), Utils.Cocinero, COCocinar.class.getName(),
						new EmptyData());
		}
		Mapa.repaint();
	}

}
