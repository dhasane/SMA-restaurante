package DOOR;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.AgentBESA;
import Creator.CLCreator;
import Resultados.Resultados;
import Utils.Utils;

public class CLDoor extends Thread {

	private static int cantidad;
	private int total;
	private int maximo;
	private Queue<Long> espera;
	private boolean continuar;

	public CLDoor(double llave) {
		CLCreator.setClave(llave);
		this.total = 0;
		this.maximo = Utils.cantidadMaximaDeClientes;
		espera = new LinkedList<Long>();
		this.continuar = true;
	}

	@Override
	public void run() {

		while (this.continuar) {
			if (cantidad < maximo)
			{
				if ( !espera.isEmpty())
				{
					long tt = System.nanoTime() - espera.remove() ;
					Resultados.agregarTiempoClienteEsperaFilaExternaPromedio( tt ) ;
				}
				

				this.createCL();
				
			}
			else
			{
				espera.add(System.nanoTime());
			}
				
			try {
				TimeUnit.SECONDS.sleep(funcionDeTiempo());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	int funcionDeTiempo() {
		// por el momento solo es cada segundo
		return 1;
	}

	// crea un agente
	private void createCL() {
		try {
			CLCreator.crearCL(this.total);
			this.total++;
			cantidad++;
		} catch (ExceptionBESA e) {
			e.printStackTrace();
		}
	}

	// destruye un agente, aun falta ver como realizar el llamado desde el agente
	public static void destroyCL(AgentBESA agentBESA) {
		agentBESA.shutdownAgent();
		cantidad--;
	}

	public void destruir() {
		this.continuar = false;
		System.out.println("se cierra la puerta del restaurante");
		
	}
}
