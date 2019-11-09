package Informacion;

import java.util.ArrayList;
import java.util.List;
import Data.PedidoData;
import Utils.Utils;
import javafx.util.Pair;

public class PedidosRealizadosPagos {

	private static List<Pair<PedidoData, Boolean>> pedidosPagos;

	private static synchronized void create() {
		if (pedidosPagos == null)
			pedidosPagos = new ArrayList<Pair<PedidoData, Boolean>>();
	}

	// agrega un pedido
	public synchronized static void add(PedidoData pd) {
		if (pd != null) {
			create();
			pedidosPagos.add(new Pair<PedidoData, Boolean>(pd, false));
			Utils.imp("Pedidos pagos : " + Utils.pedidosAStringPair(pedidosPagos));
		}
	}

	// retorna el pedido de un id
	public synchronized static PedidoData get(String id) {
		create();
		for (Pair<PedidoData, Boolean> pd : pedidosPagos) {
			if (pd.getKey().getDueño().equals(id)) {
				return pd.getKey();
			}
		}
		return null;
	}

	public synchronized static PedidoData iniciarPreparacion() {
		create();
		for (Pair<PedidoData, Boolean> pd : pedidosPagos) {
			if (!pd.getValue()) {
				pd = new Pair<PedidoData, Boolean>(pd.getKey(), true);
				return pd.getKey();
			}
		}
		return null;
	}

	// elimina el pedido de un id
	public synchronized static void remove(String idDueño) {
		create();
		for (Pair<PedidoData, Boolean> pd : pedidosPagos) {
			if (((PedidoData) pd.getKey()).getDueño().equals(idDueño)) {
				pedidosPagos.remove(pd);
				return;
			}
		}
	}
}
