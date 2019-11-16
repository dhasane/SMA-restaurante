package EP.Behavior;

import java.util.ArrayList;
import java.util.List;
import BESA.Kernell.Agent.PeriodicGuardBESA;
import BESA.Kernell.Agent.Event.EventBESA;
import CL.Behavior.CLRecogerPedido;
import CO.Behavior.COCocinar;
import Data.EmptyData;
import Data.PedidoData;
import EP.State.EPState;
import Mundo.Cocinar;
import Mundo.Ingredientes;
import Mundo.PedidosRealizadosPagos;
import Mundo.Mapa.Mapa;
import Utils.Utils;

public class EPPrepararPedido extends PeriodicGuardBESA {

	@Override
	public void funcPeriodicExecGuard(EventBESA ebesa) {
		EPState state = (EPState) this.getAgent().getState();

		PedidoData pd = PedidosRealizadosPagos.iniciarPreparacion();
		if (pd != null && !state.preparando()) {

			// revisar cantidaad ingredientes, en caso que haya pocos, le envia un mensaje a
			// cocina para que prepare mas, con los ingredientes prepara la comida y le dice
			// al cliente que lo recoja

			// en caso que que haya suficientes ingredientes seria chevere que hubiera otra
			// guarda, que es la que realmente hace la comida
			// en caso de no haber suficientes ingredientes, envia un mensaje a cocina para
			// que preparen mas, y esta guarda termina
			// entonce lo unico que deberia hhacer esta guarda, es verificar si hayy
			// suficientes ingredientes
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

			// busy waiting, pero tampoco es como que puedan hacer mucho mas si no hay
			// ingredientes ...
			List<String> preparado = new ArrayList<String>();
			while (pd.getPedido().size() > 0) {
				if (Ingredientes.consumirIngrediente(pd.getPedido().get(0))) {
					preparado.add(pd.getPedido().get(0));
					pd.getPedido().remove(0);
				}
			}
			Utils.send(getAgent().getAdmLocal(), pd.getOwner(), CLRecogerPedido.class.getName(),
					new PedidoData(preparado, getAgent().getAid()));
		}
		Mapa.repaint();

	}

}
