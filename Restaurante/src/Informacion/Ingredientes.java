package Informacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ingredientes {
	private static List<String> ingredientes;
	private static Map<String, Integer> cantidad;
	
	public Ingredientes( int cantidadCritica ) {
		
		ingredientes = new ArrayList<String>();
		ingredientes.add("arroz");
		ingredientes.add("lentejas");
		ingredientes.add("pollo");
		ingredientes.add("carne");
		
		
		cantidad = new HashMap<String, Integer>();
		
		for ( String str : ingredientes)
		{
			cantidad.put(str, 0);
		}
	}
	
	public static List<String> getIngredientes()
	{
		return ingredientes;
	}
	
	public static int getCantidad( String ing )
	{
		return cantidad.get(ing);
	}
}
