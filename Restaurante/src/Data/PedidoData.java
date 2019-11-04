package Data;

import java.util.List;

import BESA.Kernell.Agent.Event.DataBESA;

public class PedidoData extends DataBESA {

	private static final long serialVersionUID = 3494993695923009524L;
	private List<String> pedido;
	private String dueño;

	public PedidoData(List<String> pedido, String dueño) {
		this.pedido = pedido;
		this.dueño = dueño;
	}

	public List<String> getPedido() {
		return this.pedido;
	}
	
	public String getDueño()
	{
		return this.dueño;
	}

}
