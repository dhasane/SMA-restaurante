package DOOR;

import java.util.concurrent.TimeUnit;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.AgentBESA;
import Creator.CLCreator;

public class CLDoor extends Thread {

	private static int cantidad;
	private int total;
	private int maximo;

	public CLDoor(double llave, int maximo) {
		CLCreator.setClave(llave);
		this.total = 0;
		this.start();
		this.maximo = maximo;
	}

	@Override
	public void run() {

		while (true) {
			if (cantidad < maximo)
				this.createCL();
			try {
				TimeUnit.MILLISECONDS.sleep(funcionDeTiempo());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	int funcionDeTiempo() {
		// por el momento solo es cada segundo
		return 1000;
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
}
