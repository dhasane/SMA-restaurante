package Data;

import java.util.List;
import BESA.Kernell.Agent.Event.DataBESA;

public class PreparacionData extends DataBESA {

	private static final long serialVersionUID = 3494993695923009524L;
	private List<String> pedido;
	private String responderA;
	private boolean enEspera;

	public PreparacionData(List<String> pedido, String responder, boolean enEspera) {
		this.pedido = pedido;
		this.responderA = responder;
		this.enEspera = enEspera;
	}

	public List<String> getPedido() {
		return this.pedido;
	}
	
	public String responder()
	{
		return this.responderA;
	}
	
	public boolean esperando()
	{
		return this.enEspera;
	}

}
