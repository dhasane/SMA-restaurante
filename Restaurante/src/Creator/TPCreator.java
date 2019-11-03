package Creator;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.StructBESA;
import TP.TPAgent;
import TP.Behavior.IncluirEnFila;
import TP.Behavior.ResponderFila;
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
        c1Struct.addBehavior("TPRecibirPedido");
        c1Struct.bindGuard  ("TPRecibirPedido", ResponderFila.class);
        c1Struct.addBehavior("TPPerception");
        c1Struct.bindGuard  ("TPPerception", IncluirEnFila.class);
        
        ( new TPAgent( name, new TPState( ), c1Struct, clave ) ).start();
    }
}
