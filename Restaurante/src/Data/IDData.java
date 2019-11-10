package Data;

import BESA.Kernell.Agent.Event.DataBESA;

public class IDData extends DataBESA{
	
	// esta clase sirve para enviar la informacion de identidad entre agentes 

	private static final long serialVersionUID = 4407171152522633957L;
	private String id;
	
	public IDData( String id )
	{
		this.id = id;
	}
	
	public String getId()
	{
		return this.id;
	}
}
