package Creator;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.StructBESA;
import Cocinero.CocineroAgent;
import Cocinero.Behavior.CocineroSensorGuard;
import Cocinero.State.CocineroState;

public class COCreator {
	
	private static double clave;
	
	public static void setClave( double clave )
	{
		COCreator.clave = clave ;
		
	}

    // crea varios agentes
    public static void crearCocineros( int x , int y , int cantidad ) throws ExceptionBESA
    {
        for ( int a = 0 ; a < cantidad ; ++a )
            cocinero( x, y, "CO"+Integer.toString( a ) );
    }

    // crea un unico agente, pasandole el tamaño del mapa y su nombre
    private static void cocinero( int sizex, int sizey, String name ) throws ExceptionBESA
    {
        StructBESA c1Struct = new StructBESA();
        c1Struct.addBehavior("CocineroplayerPerception");
        c1Struct.bindGuard("CocineroplayerPerception", CocineroSensorGuard.class);
        ( new CocineroAgent( name, new CocineroState(sizex, sizey), c1Struct, clave ) ).start() ;

    }
}
