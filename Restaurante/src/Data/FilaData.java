package Data;

import BESA.Kernell.Agent.Event.DataBESA;

public class FilaData extends DataBESA {
	
	// esta clase es para enviar la informacion de una fila entre agentes 
	
	private int longitud;
	private String idDueño;
	
	public FilaData( int logitud, String idDueño )
	{
		this.longitud = logitud;
		this.idDueño = idDueño;
	}
	
	public int getLogitud()
	{
		return this.longitud;
	}
	
	public String getDueño()
	{
		return this.idDueño;
	}
}
