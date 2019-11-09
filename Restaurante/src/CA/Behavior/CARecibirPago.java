package CA.Behavior;

import BESA.Kernell.Agent.Event.EventBESA;
import Data.PagoData;
import Informacion.PedidosRealizadosNoPagos;
import Informacion.PedidosRealizadosPagos;
import Utils.Utils;
import BESA.Kernell.Agent.GuardBESA;
import BESA.Kernell.Agent.StateBESA;

public class CARecibirPago extends GuardBESA {

	@Override
	public boolean funcEvalBool(StateBESA objEvalBool) {
		return true;
	}

	@Override
	public void funcExecGuard(EventBESA ebesa) {

		PagoData fd = (PagoData) ebesa.getData();

		// agrega el pedido a los pedidos pagos y lo elimina de los pedidos no pagos
		Utils.imp("cambiando estado del pedido para " + fd.responder());
		PedidosRealizadosPagos.add(PedidosRealizadosNoPagos.get(fd.responder()));
		PedidosRealizadosNoPagos.remove(fd.responder());
		
//		Utils.send( getAgent().getAdmLocal(), fd.responder(), CLEsperarPedido.class.getName(), new EmptyData());

	}

}
