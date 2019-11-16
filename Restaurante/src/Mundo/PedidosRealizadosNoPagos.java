package Mundo;

import java.util.ArrayList;
import java.util.List;
import Data.PedidoData;
import Utils.Utils;

public class PedidosRealizadosNoPagos {

	private static List<PedidoData> pedidosNoPagos;
	
	private synchronized static void create()
	{
		if (pedidosNoPagos == null)
			pedidosNoPagos = new ArrayList<PedidoData>();
	}

	public synchronized static void add(PedidoData pd) {
		create();
		pedidosNoPagos.add(pd);
		Utils.imp("Pedidos no pagos : [" + Utils.pedidosAString(pedidosNoPagos) + "] " + pedidosNoPagos.size());
	}

	public static PedidoData get(String id) {
		create();
		for (PedidoData pd : pedidosNoPagos) {
			if (pd.getOwner().equals(id)) {
				return pd;
			}
		}
		return null;
	}

	public synchronized static void remove(String idOwner) {
		create();
		for (PedidoData pd : pedidosNoPagos) {
			if (pd.getOwner().equals(idOwner)) {
				pedidosNoPagos.remove(pd);
				return;
			}
		}
	}
}
