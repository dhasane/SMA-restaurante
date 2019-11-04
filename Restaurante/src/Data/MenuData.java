package Data;

import java.util.ArrayList;
import java.util.List;

import BESA.Kernell.Agent.Event.DataBESA;

public class MenuData extends DataBESA{

	private List<String> comida;
	private String responderA;

	public MenuData(String string) {
		comida = new ArrayList<String>();
		comida.add("arroz");
		comida.add("lentejas");
		comida.add("pollo");
		comida.add("carne");
		this.responderA = string;
	}

	public List<String> getMenu() {
		return this.comida;
	}
	
	public String responder()
	{
		return this.responderA;
	}
}
