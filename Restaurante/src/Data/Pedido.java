package Data;

import BESA.Kernell.Agent.Event.DataBESA;
import Model.Food;

public class Pedido extends DataBESA {
	private Food food;
	
	public Pedido( Food f )
	{
		this.food = f;
	}
	
	public Food getFood()
	{
		return this.food;
	}
	
	public void setFood( Food food )
	{
		this.food = food;
	}
}
