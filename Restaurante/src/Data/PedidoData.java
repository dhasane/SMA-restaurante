package Data;

import java.util.List;

import BESA.Kernell.Agent.Event.DataBESA;

public class PedidoData extends DataBESA {

	private static final long serialVersionUID = 3494993695923009524L;
	private List<String> pedido;
	private String owner;

	public PedidoData(List<String> pedido, String owner) {
		this.pedido = pedido;
		this.owner = owner;
	}

	public List<String> getPedido() {
		return this.pedido;
	}
	
	public String getOwner()
	{
		return this.owner;
	}

}
