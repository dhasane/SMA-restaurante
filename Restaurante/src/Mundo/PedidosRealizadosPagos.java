package Mundo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Data.PedidoData;
import Utils.Utils;

public class PedidosRealizadosPagos {

	private static List<PedidoData> pedidosPagos;
	private static Map<PedidoData, Boolean> estado;

	private static synchronized void create() {
		if (pedidosPagos == null) {
			pedidosPagos = new ArrayList<PedidoData>();
			estado = new HashMap<PedidoData, Boolean>();
			for (PedidoData pd : pedidosPagos) {
				estado.put(pd, false);
			}
		}

	}

	// agrega un pedido
	public synchronized static void add(PedidoData pd) {
		if (pd != null) {
			create();
			pedidosPagos.add(pd);
			estado.put(pd, false);
			Utils.imp("Pedidos pagos : [" + Utils.pedidosAString(pedidosPagos) + "] " + pedidosPagos.size());
		}
	}

	// retorna el pedido de un id
	public synchronized static PedidoData get(String id) {
		create();
		for (PedidoData pd : pedidosPagos) {
			if (pd.getOwner().equals(id)) {
				return pd;
			}
		}
		return null;
	}

	public synchronized static PedidoData iniciarPreparacion() {
		create();
		for (PedidoData pd : pedidosPagos) {
			if (!estado.get(pd)) {
				estado.put(pd, true);
				return pd;
			}
		}
		return null;
	}

	// elimina el pedido de un id
	public synchronized static void remove(String idOwner) {
		create();
		for (PedidoData pd : pedidosPagos) {
			if (pd.getOwner().equals(idOwner)) {
				pedidosPagos.remove(pd);
				estado.remove(pd);
				return;
			}
		}
	}
}
