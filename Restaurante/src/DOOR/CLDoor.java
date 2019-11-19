package DOOR;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.AgentBESA;
import CL.CLAgent;
import CL.State.CLState;
import Creator.CLCreator;
import Resultados.Resultados;
import Utils.Utils;

public class CLDoor extends Thread {

	public static int cantidad;// cantidad de personas actualmente dentro del restaurante
	private int total;			// total de agentes que han entrado durante la simulacion
	private int cupo; 		// cupo maximo para agentes dentro del restaurante
	private Queue<Long> espera;
	private boolean continuar;

	public CLDoor(double llave) {
		CLCreator.setClave(llave);
		this.total = 0;
		this.cupo = Utils.cantidadMaximaDeClientes;
		espera = new LinkedList<Long>();
		this.continuar = true;
	}

	@Override
	public void run() {

		while (this.continuar) {
			if (cantidad < cupo) {
				if (!espera.isEmpty()) {
					long tt = Utils.conseguirTiempo() - espera.remove();
					Resultados.agregarTiempoClienteEsperaFilaExterna(tt);
				}

				this.createCL();

			} else {
				espera.add(Utils.conseguirTiempo());
			}

			try {
				TimeUnit.MILLISECONDS.sleep(funcionDeTiempo());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	int funcionDeTiempo() {
		// por el momento solo es cada segundo
		return 1;
	}

	public static int getPoisson (double lambda){
		double L = Math.exp(-lambda);
		double p = 1.0;
		int k = 0;
		do{
			k++;
			p *= Math.random();
		} while (p > L);
		return k-1;
	}


	// crea un agente
	private void createCL() {
		try {
			CLCreator.crearCL(this.total);
			this.total++;
			cantidad++;
			if ( this.total > Utils.clientesAAtender )
			{
				this.cerrarEntrada();
			}

		} catch (ExceptionBESA e) {
			e.printStackTrace();
		}
	}

	// destruye un agente, aun falta ver como realizar el llamado desde el agente
	public static void destroyCL(AgentBESA agentBESA) {

		CLState cls = (CLState) ((CLAgent) agentBESA).getState();
		long tt = Utils.conseguirTiempo() - cls.getTiempoEntrada();
		Resultados.agregarATiempoClienteVisitaCompleta(tt);

		agentBESA.shutdownAgent();
		cantidad--;

		// solo mostrara los resultados una vez se desocupe el restaurante
		if (cantidad == 0) {
			Resultados.mostrarResultados();
		}
	}

	public void cerrarEntrada() {
		this.continuar = false;
		System.out.println("se cierra la puerta del restaurante");

	}
}
