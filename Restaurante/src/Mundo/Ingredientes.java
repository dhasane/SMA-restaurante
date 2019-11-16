package Mundo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Ingredientes {
	private static List<String> ingredientes;
	private static Map<String, Integer> cantidad;
	private static Map<String, Integer> tiempoPreparacion;

	private static void createIngredientes() {

		if( ingredientes == null)
		{
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

			tiempoPreparacion = new HashMap<String, Integer>();

			tiempoPreparacion.put("arroz", 4);
			tiempoPreparacion.put("lentejas", 6);
			tiempoPreparacion.put("pollo", 7);
			tiempoPreparacion.put("carne", 9);
		}
	}

	public static List<String> getIngredientes()
	{
		createIngredientes();
		return ingredientes;
	}

	public static int getCantidad( String ing )
	{
		createIngredientes();
		return cantidad.get(ing);
	}

	private static void preparacion( String ing )
	{
        createIngredientes();
		try {
			TimeUnit.SECONDS.sleep( tiempoPreparacion.get(ing) );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized static void agregarIngrediente( String ing )
	{
		createIngredientes();
		preparacion( ing ); // el tiempo que demora la preparacion
		cantidad.put(ing, cantidad.get(ing) + 1 );
	}

	public synchronized static boolean consumirIngrediente( String ing )
	{
		createIngredientes();
		boolean exito = false;
		if ( cantidad.get(ing) > 0 )
		{
			cantidad.put(ing, cantidad.get(ing) - 1 );
			exito = true;
		}
		return exito;
	}
}
