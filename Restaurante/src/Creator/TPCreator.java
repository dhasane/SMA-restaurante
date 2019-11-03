package Creator;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.StructBESA;
import TP.TPAgent;
import TP.Behavior.TPSensorGuard;
import TP.State.TPState;

public class TPCreator {
	
	private static double clave;
	
	public static void setClave( double clave )
	{
		TPCreator.clave = clave ;
	}

	// crea varios agentes
    public static void crearTP( int cantidad) throws ExceptionBESA
    {
        for ( int a = 0 ; a <  cantidad ; ++a )
            agente( "TP"+Integer.toString( a ) );
    }

    // crea un unico agente, pasandole el tamaño del mapa y su nombre
    private static void agente( String name ) throws ExceptionBESA
    {
        StructBESA c1Struct = new StructBESA();
        c1Struct.addBehavior("TPPlayerPerception");
        c1Struct.bindGuard  ("TPPlayerPerception", TPSensorGuard.class);
        ( new TPAgent( name, new TPState( ), c1Struct, clave ) ).start();
    }
}
