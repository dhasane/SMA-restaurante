/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EP.Behavior;

import java.util.ArrayList;
import java.util.List;

import BESA.Kernell.Agent.PeriodicGuardBESA;
import BESA.Kernell.Agent.Event.EventBESA;
import CO.Behavior.COCocinar;
import Data.PedidoData;
import Data.PreparacionData;
import EP.State.EPState;
import Informacion.Ingredientes;
import Informacion.PedidosRealizadosPagos;
import Utils.Utils;

public class RevisarPedidos extends PeriodicGuardBESA {

	@Override
	public void funcPeriodicExecGuard(EventBESA ebesa) {
		EPState state = (EPState) this.getAgent().getState();

		PedidoData pd = PedidosRealizadosPagos.iniciarPreparacion();

		if (pd != null && !state.preparando()) {
			state.comenzandoPedido();

			// revisar cantidaad ingredientes, en caso que haya pocos, le envia un mensaje a
			// cocina para que prepare mas, con los ingredientes prepara la comida y le dice
			// al cliente que lo recoja

			// en caso que que haya suficientes ingredientes seria chevere que hubiera otra
			// guarda, que es la que realmente hace la comida
			// en caso de no haber suficientes ingredientes, envia un mensaje a cocina para
			// que preparen mas, y esta guarda termina
			// entonce lo unico que deberia hhacer esta guarda, es verificar si hayy
			// suficientes ingredientes
			boolean factible = true;
			List<String> agregar = new ArrayList<String>();

			// revisarIngredientes
			for (String ing : pd.getPedido()) {
				int cantidad = Ingredientes.getCantidad(ing);
				if (cantidad == 0)
					factible &= false;
				if (cantidad < Utils.cantidadCriticaComida)
					agregar.add(ing);
			}

			if (agregar.size() > 0) {
				Utils.send(getAgent().getAdmLocal(), id // no sé a quien mandarle esto, se podria otra funcion periodica
														// para lo cocineros, para que estén revisando si falta o no
														// comida
						, COCocinar.class.getName(), new PreparacionData(agregar, getAgent().getAid(), !factible));
			}

			if (factible) {
				Utils.send(getAgent().getAdmLocal(), getAgent().getAid(), PrepararPedido.class.getName(), pd);
			}

		}

	}

}
