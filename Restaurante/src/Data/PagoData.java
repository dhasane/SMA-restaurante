package Data;

import BESA.Kernell.Agent.Event.DataBESA;
import Informacion.PedidosRealizadosNoPagos;

public class PagoData extends DataBESA {

	private static final long serialVersionUID = -3697775504214992200L;
	private String responderA;
	private float pedidoPago;

	private float definirMonto(PedidoData pd) {
		return 1;
	}

	public PagoData(String id, String ids) {
		this.pedidoPago = this.definirMonto(PedidosRealizadosNoPagos.get(id));
		this.responderA = ids;
	}

	public String responder() {
		return this.responderA;
	}
	
	public void setResopnder( String id )
	{
		this.responderA = id;
	}

	public float getMonto() {
		return this.pedidoPago;
	}
}
