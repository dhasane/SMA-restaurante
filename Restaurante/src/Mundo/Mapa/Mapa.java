package Mundo.Mapa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.util.concurrent.TimeUnit;

import BESA.Kernell.Agent.AgentBESA;

public class Mapa {

	// matriz
	private static List<List<AgentBESA>> mapa;
	private static Visual vis;
	private static Map<String, List<AgentBESA>> agentes;

	private static int sizex;
	private static int sizey;


	public static void createMapa(int sizex, int sizey, int cocinaInicio) {
		Mapa.sizex = sizex;
		Mapa.sizey = sizey;


		mapa = new ArrayList<List<AgentBESA>>();
		for (int i = 0; i < sizex; i++) {
			mapa.add(new ArrayList<AgentBESA>());
			for (int j = 0; j < sizey; j++) {
				mapa.get(i).add(null);
			}
		}
		vis = new Visual(cocinaInicio, 50);
		agentes = new HashMap<String, List<AgentBESA>>();
	}

	// esto esta siendo llamado por todos los agentes, excepto CL, al terminar sus
	// guardas, asegura que siempre se muestre el estado actual
	public static synchronized void repaint() {
		vis.repaint();
	}

	// agrega un agente a una nueva posicion y vuelve a pintar el mapa
	public static synchronized void addAgent(AgentBESA caAgent, int[] pos) {
		String name = "" + caAgent.getAlias().charAt(0) + caAgent.getAlias().charAt(1);
		if (agentes.get(name) == null)
			agentes.put(name, new ArrayList<AgentBESA>());
		agentes.get(name).add(caAgent);
		mapa.get(pos[0]).add(pos[1], caAgent);
		vis.repaint();
	}

	// mira posicion en (x,y)
	public synchronized static String see(int x, int y) {
		return mapa.get(x).get(y).getAlias();
	}

	// mira posicion en i
	public static AgentBESA get(int i) {
		return mapa.get(i % sizex).get(i / sizex);
	}

	public static int getsizey() {
		return sizey;
	}

	public static int getsizex() {
		return sizex;
	}

}
