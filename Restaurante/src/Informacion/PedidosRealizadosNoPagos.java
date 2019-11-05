package Informacion;

import java.util.ArrayList;
import java.util.List;
import Data.PedidoData;
import Utils.Utils;

public class PedidosRealizadosNoPagos {

	private static List<PedidoData> pedidosNoPagos;

	public static void add(PedidoData pd) {

		if (pedidosNoPagos == null)
			pedidosNoPagos = new ArrayList<PedidoData>();

		pedidosNoPagos.add(pd);
		
		Utils.imp("Pedidos no pagos : [" + Utils.pedidosAString(pedidosNoPagos) + "]" );
	}

	public static PedidoData get(String id) {
		Utils.imp( "[[[[[[[[[[[[[[[[[[[[[[[[[[ id buscando " + id);
		for (PedidoData pd : pedidosNoPagos) {

			Utils.imp( "[[[[[[[[[[[[[[[[[[[[[[[[[[ id " + pd.getDueño());
			if (pd.getDueño().equals(id)) {
				Utils.imp( "[[[[[[[[[[[[[[[[[[[[[[[[[[ encontrado " );
				return pd;

			}
		}
		return null;
	}

	public static void remove( String idDueño ) {
		for (PedidoData pd : pedidosNoPagos) {
			if (pd.getDueño().equals(idDueño)) {
				pedidosNoPagos.remove(pd);
				break;
			}
		}
	}
}
