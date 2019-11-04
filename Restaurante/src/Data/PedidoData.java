package Data;

import java.util.List;

import BESA.Kernell.Agent.Event.DataBESA;

public class PedidoData extends DataBESA {

	private static final long serialVersionUID = 3494993695923009524L;
	private List<String> pedido;

	public PedidoData(List<String> pedido) {
		this.pedido = pedido;
	}

	public List<String> getPedido() {
		return this.pedido;
	}

}
