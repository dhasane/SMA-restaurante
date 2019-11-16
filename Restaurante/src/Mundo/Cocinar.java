package Mundo;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Cocinar {

	private static Queue<String> ingredientesCocinar;

	public synchronized static void create() {
		if (ingredientesCocinar == null)
			ingredientesCocinar = new LinkedList<String>();
	}

	// agrega un pedido
	public synchronized static void add(String pd) {
		create();
		if (pd != null) {
			ingredientesCocinar.add(pd);
		}
	}

	public synchronized static String pop() {
		create();
		return ingredientesCocinar.remove();
	}

	public synchronized static void add(List<String> agregar) {
		create();
		ingredientesCocinar.addAll(agregar);
	}

	public static int size() {
		create();
		return ingredientesCocinar.size();
	}
}
