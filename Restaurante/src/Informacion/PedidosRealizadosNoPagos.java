package Informacion;

import java.util.ArrayList;
import java.util.List;
import Data.PedidoData;
import Utils.Utils;

public class PedidosRealizadosNoPagos {

	private static List<PedidoData> pedidosNoPagos;

	public synchronized static void add(PedidoData pd) {

		if (pedidosNoPagos == null)
			pedidosNoPagos = new ArrayList<PedidoData>();

		pedidosNoPagos.add(pd);

		Utils.imp("Pedidos no pagos : [" + Utils.pedidosAString(pedidosNoPagos) + "]");
	}

	public static PedidoData get(String id) {
		for (PedidoData pd : pedidosNoPagos) {
			if (pd.getOwner().equals(id)) {
				return pd;
			}
		}
		return null;
	}

	public synchronized static void remove(String idOwner) {
		for (PedidoData pd : pedidosNoPagos) {
			if (pd.getOwner().equals(idOwner)) {
				pedidosNoPagos.remove(pd);
				return;
			}
		}
	}
}
