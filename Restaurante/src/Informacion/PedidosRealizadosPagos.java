package Informacion;

import java.util.ArrayList;
import java.util.List;
import Data.PedidoData;
import Utils.Utils;

public class PedidosRealizadosPagos {

	private static List<PedidoData> pedidosPagos;

	// agrega un pedido
	public synchronized static void add(PedidoData pd) {
		if (pd != null) {
			if (pedidosPagos == null)
				pedidosPagos = new ArrayList<PedidoData>();

			pedidosPagos.add(pd);
			Utils.imp("Pedidos pagos : " + Utils.pedidosAString(pedidosPagos));
		}
	}

	// retorna el pedido de un id
	public static PedidoData get(String id) {
		for (PedidoData pd : pedidosPagos) {
			if (pd.getDueño().equals(id)) {
				return pd;
			}
		}
		return null;
	}

	// elimina el pedido de un id
	public synchronized static void remove(String idDueño) {
		for (PedidoData pd : pedidosPagos) {
			if (pd.getDueño().equals(idDueño)) {
				pedidosPagos.remove(pd);
				return;
			}
		}
	}
}
