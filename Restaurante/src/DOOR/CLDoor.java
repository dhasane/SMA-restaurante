package DOOR;

import java.util.concurrent.TimeUnit;

import BESA.ExceptionBESA;
import CL.ClienteAgent;
import Creator.CLCreator;

public class CLDoor extends Thread {
	
	private int cantidad;
	private int total;
	private int maximo;
	
	public CLDoor( double llave, int maximo)
	{
		CLCreator.setClave(llave);
		this.total = 0;
		this.start();
		this.maximo = maximo;
	}
	
	@Override
    public void run() {
        
		while ( true )
		{
			if ( cantidad <= maximo )
			this.createCL();
			try {
				TimeUnit.MILLISECONDS.sleep( funcionDeTiempo() );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
    }
	
	int funcionDeTiempo()
	{
		// por el momento solo es cada segundo 
		return 1000;
	}
	
	// crea un agente 
	private void createCL()
	{
		try {
			CLCreator.crearCL( this.total );
			this.total ++ ;
			this.cantidad ++;
		} catch (ExceptionBESA e) {
			e.printStackTrace();
		}
	}
	
	// destruye un agente, aun falta ver como realizar el llamado desde el agente 
	private void destroyCL( ClienteAgent ca )
	{
		ca.shutdownAgent();
		this.cantidad --;
	}
}
