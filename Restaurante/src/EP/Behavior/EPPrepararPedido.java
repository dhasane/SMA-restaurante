package EP.Behavior;

import java.util.ArrayList;
import java.util.List;
import BESA.Kernell.Agent.PeriodicGuardBESA;
import BESA.Kernell.Agent.Event.EventBESA;
import CO.Behavior.COCocinar;
import Data.EmptyData;
import Data.PedidoData;
import EP.State.EPState;
import Informacion.Cocinar;
import Informacion.Ingredientes;
import Informacion.PedidosRealizadosPagos;
import Utils.Utils;

public class EPPrepararPedido extends PeriodicGuardBESA {

	@Override
	public void funcPeriodicExecGuard(EventBESA ebesa) {
		EPState state = (EPState) this.getAgent().getState();

		PedidoData pd = PedidosRealizadosPagos.iniciarPreparacion();
		System.out.println("hola");
		if (pd != null && !state.preparando()) {
//			state.comenzandoPedido();

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

				boolean enviar = false;
				if (agregar.size() == 0)
					enviar = true;
				Cocinar.add(agregar);
				if (enviar)
					Utils.broadcast(getAgent().getAdmLocal(), Utils.Cocinero, COCocinar.class.getName(),
							new EmptyData());
			}

				
			while( pd.getPedido().size() > 0 )
			{
				String ing = pd.getPedido().get(0);
				if( Ingredientes.consumirIngrediente( ing ) )
				{
					pd.getPedido().remove(0);
				}
			}
//			Utils.imp
			System.out.println("pedido completadoooooo, aqui se le deberia enviar un mensaje a "+ pd.getDueño());
			System.out.println("holaaa");
		}

	}

}
