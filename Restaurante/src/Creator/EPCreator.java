package Creator;

import java.util.List;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.StructBESA;
import EP.EPAgent;
import EP.Behavior.EPSensorGuard;
import EP.State.EPState;
import javafx.util.Pair;


public class EPCreator {
	
	private static double clave;
	
	public static void setClave( double clave )
	{
		EPCreator.clave = clave ;
		
	}

	// crea varios agentes
    public static void crearEP( int x , int y , List< Pair<Integer, Integer> > positions ) throws ExceptionBESA
    {
        for ( int a = 0 ; a < positions.size() ; ++a )
            agente( x, y, "EP"+Integer.toString( a ), positions.get(a).getKey() , positions.get(a).getValue() );
    }

    // crea un unico agente, pasandole el tamaño del mapa y su nombre
    private static void agente( int sizex, int sizey, String name, int x, int y) throws ExceptionBESA
    {
        StructBESA c1Struct = new StructBESA();
        c1Struct.addBehavior("EPPerception");
        c1Struct.bindGuard  ("EPPerception", EPSensorGuard.class);
        ( new EPAgent( name, new EPState(sizex, sizey), c1Struct, clave, x, y ) ).start();
    }
}
