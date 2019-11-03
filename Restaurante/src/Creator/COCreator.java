package Creator;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.StructBESA;
import CO.CocineroAgent;
import CO.Behavior.CocineroSensorGuard;
import CO.State.CocineroState;

public class COCreator {
	
	private static double clave;
	
	public static void setClave( double clave )
	{
		COCreator.clave = clave ;
		
	}

    // crea varios agentes
    public static void crearCocineros( int cantidad ) throws ExceptionBESA
    {
        for ( int a = 0 ; a < cantidad ; ++a )
            cocinero( "CO"+Integer.toString( a ) );
    }

    // crea un unico agente, pasandole el tamaño del mapa y su nombre
    private static void cocinero( String name ) throws ExceptionBESA
    {
        StructBESA c1Struct = new StructBESA();
        c1Struct.addBehavior("CocineroplayerPerception");
        c1Struct.bindGuard  ("CocineroplayerPerception", CocineroSensorGuard.class);
        ( new CocineroAgent( name, new CocineroState( ), c1Struct, clave ) ).start() ;

    }
}
