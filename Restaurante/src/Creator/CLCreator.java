package Creator;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.StructBESA;
import CL.ClienteAgent;
import CL.Behavior.ProductRequest;
import CL.State.ClienteState;
import Data.EmptyData;
import Utils.Utils;

public class CLCreator {
	
	private static double clave;
	
	public static void setClave( double clave )
	{
		CLCreator.clave = clave ;
	}

	// crea varios agentes
    public static void crearCL( int numero ) throws ExceptionBESA
    {
        cliente( "CL"+ numero );
    }

    // crea un unico agente, pasandole el tamaño del mapa y su nombre
    private static void cliente( String name ) throws ExceptionBESA
    {
        StructBESA c1Struct = new StructBESA();
        c1Struct.addBehavior("ClientPlayerPerception");
        c1Struct.bindGuard  ("ClientPlayerPerception", ProductRequest.class);
        ClienteAgent ca = new ClienteAgent( name, new ClienteState( ), c1Struct, clave ) ;
        ca.start();
        Utils.send( ca.getAdmLocal(), ca.getAid(), ProductRequest.class.getName(), new EmptyData() );
    }
}
