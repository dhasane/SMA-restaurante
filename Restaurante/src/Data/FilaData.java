package Data;

import BESA.Kernell.Agent.Event.DataBESA;

public class FilaData extends DataBESA {
	
	// esta clase es para enviar la informacion de una fila entre agentes 

	private static final long serialVersionUID = -5892807834296218923L;
	private int longitud;
	private String idOwner;
	
	public FilaData( int logitud, String idOwner )
	{
		this.longitud = logitud;
		this.idOwner = idOwner;
	}
	
	public int getLogitud()
	{
		return this.longitud;
	}
	
	public String getOwner()
	{
		return this.idOwner;
	}
}
