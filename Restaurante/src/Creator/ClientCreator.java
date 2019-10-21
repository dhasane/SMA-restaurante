package Creator;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.StructBESA;
import Cliente.ClienteAgent;
import Cliente.Behavior.ClienteSensorGuard;
import Cliente.State.ClienteState;

public class ClientCreator {
	
	private static double clave;
	
	public static void setClave( double clave )
	{
		ClientCreator.clave = clave ;
		
	}

	// crea varios agentes
    public static void crearClientes( int x , int y , int cantidad ) throws ExceptionBESA
    {
        for ( int a = 0 ; a < cantidad ; ++a )
            cliente( x, y, "CL"+Integer.toString( a ) );
    }

    // crea un unico agente, pasandole el tamaño del mapa y su nombre
    public static void cliente( int sizex, int sizey, String name ) throws ExceptionBESA
    {
        StructBESA c1Struct = new StructBESA();
        c1Struct.addBehavior("playerPerception");
        c1Struct.bindGuard("playerPerception", ClienteSensorGuard.class);
        ( new ClienteAgent( name, new ClienteState(sizex, sizey), c1Struct, clave ) ).start();
    }
}
