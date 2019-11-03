package Creator;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.StructBESA;
import TP.TPAgent;
import TP.Behavior.RecibirPedido;
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

    private static void agente( String name ) throws ExceptionBESA
    {
        StructBESA c1Struct = new StructBESA();
        c1Struct.addBehavior("TPPerception");
        c1Struct.bindGuard  ("TPPerception", TPSensorGuard.class);

        c1Struct.addBehavior("TPRecibirPedido");
        c1Struct.bindGuard  ("TPRecibirPedido", RecibirPedido.class);
        
        ( new TPAgent( name, new TPState( ), c1Struct, clave ) ).start();
    }
}
