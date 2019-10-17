import BESA.ExceptionBESA;
import BESA.Kernell.Agent.Event.EventBESA;
import BESA.Kernell.Agent.PeriodicGuardBESA;
import BESA.Kernell.Agent.StructBESA;
import BESA.Kernell.System.AdmBESA;
import BESA.Kernell.System.Directory.AgHandlerBESA;
import BESA.Util.PeriodicDataBESA;
import Cleaner.Behavior.SensorGuard;
import Cleaner.CleanerAgent;
import Cleaner.State.CleanerState;
import World.Behavior.GameGuard;
import World.Behavior.SubscribeGuard;
import World.Behavior.UpdateGuard;
import World.State.WorldState;
import restaurante.WorldAgent;

/**
 *
 * @author Andres
 */
public class EjemploBESA {

    public static int GAME_PERIODIC_TIME = 1000;
    public static int GAME_PERIODIC_DELAY_TIME = 100;

    private static double clave = 0.91;


    public static void main(String[] args) throws ExceptionBESA {

        AdmBESA admLocal = AdmBESA.getInstance();

        int tamx = 20;
        int tamy = 5;
        int nsuciedad = 11;

        crearRestaurante( tamx, tamy, nsuciedad );

        crearAgentes( tamx, tamy , 5 );

        // creo que esto es para la "sincronizacion" de tiempo de los agentes
        PeriodicDataBESA data  = new PeriodicDataBESA(GAME_PERIODIC_TIME, GAME_PERIODIC_DELAY_TIME, PeriodicGuardBESA.START_PERIODIC_CALL);
        EventBESA startPeriodicEv = new EventBESA(GameGuard.class.getName(), data);
        AgHandlerBESA ah = admLocal.getHandlerByAlias("WORLD");
        ah.sendEvent(startPeriodicEv);

    }

    // crea el mapa (restaurante)
    public static void crearRestaurante( int tamx , int tamy , int nsuciedad ) throws ExceptionBESA
    {
        WorldState ws = new WorldState( tamx , tamy , nsuciedad );
        StructBESA wrlStruct = new StructBESA();
        wrlStruct.addBehavior("WorldBehavior");
        wrlStruct.bindGuard("WorldBehavior", GameGuard.class);
        wrlStruct.addBehavior("ChangeBehavior");
        wrlStruct.bindGuard("ChangeBehavior", SubscribeGuard.class);
        wrlStruct.bindGuard("ChangeBehavior", UpdateGuard.class);
        WorldAgent wa = new WorldAgent("WORLD", ws, wrlStruct, clave );
        wa.start();
    }

    // crea varios agentes
    public static void crearAgentes( int x , int y , int cantidad ) throws ExceptionBESA
    {
        for ( int a = 0 ; a < cantidad ; ++a )
            agente( x, y, "C"+Integer.toString( a ) );
    }

    // crea un unico agente, pasandole el tamaño del mapa y su nombre
    public static void agente( int sizex, int sizey, String name ) throws ExceptionBESA
    {
        StructBESA c1Struct = new StructBESA();
        c1Struct.addBehavior("playerPerception");
        c1Struct.bindGuard("playerPerception", SensorGuard.class);
        CleanerAgent cleaner = new CleanerAgent( name, new CleanerState(sizex, sizey), c1Struct, clave );
        cleaner.start();
    }

}
