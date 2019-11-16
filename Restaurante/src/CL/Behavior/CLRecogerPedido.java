package CL.Behavior;

import BESA.Kernell.Agent.Event.EventBESA;
import DOOR.CLDoor;
import java.util.Random;
import BESA.Kernell.Agent.GuardBESA;
import Utils.Utils;
import java.util.concurrent.TimeUnit;

public class CLRecogerPedido extends GuardBESA {

	@Override
	public void funcExecGuard(EventBESA ebesa) {


        Random r = new Random();
        comer( r.nextInt( Utils.tiempoMaximoComida ) + Utils.tiempoMinimoComida );
        System.out.println("el agente " + getAgent().getAlias() + " come su pedido y sale " );
        CLDoor.destroyCL( getAgent() );

	}

	private static void comer( int tiempo )
	{
		try {
			TimeUnit.SECONDS.sleep( tiempo );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
