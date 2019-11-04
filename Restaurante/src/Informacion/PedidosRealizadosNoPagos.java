package Informacion;

import java.util.ArrayList;
import java.util.List;

import Data.PedidoData;

public class PedidosRealizadosNoPagos {

	private static List<PedidoData> pedidosNoPagos;

	public static void add(PedidoData pd) {

		if (pedidosNoPagos == null)
			pedidosNoPagos = new ArrayList<PedidoData>();

		pedidosNoPagos.add(pd);
	}

	public static PedidoData get(String id) {
		PedidoData pdf = null;
		for (PedidoData pd : pedidosNoPagos) {
			if (pd.getDueño().equals(id)) {
				pdf = pd;
				pedidosNoPagos.remove(pd);
				break;
			}
		}

		return pdf;
	}
}
