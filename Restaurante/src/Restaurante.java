
import BESA.ExceptionBESA;
import BESA.Kernell.System.AdmBESA;
import Creator.COCreator;
import Creator.EPCreator;
import Creator.TPCreator;
import DOOR.CLDoor;

public class Restaurante {

    public static int GAME_PERIODIC_TIME = 1000;
    public static int GAME_PERIODIC_DELAY_TIME = 100;

    private static double clave = 0.91;


    // EP -> entrega pedido
    // TP -> toma pedido
    // CO -> cocinero
    // CL -> cliente
    // CA -> caja
    // AY -> ayudante // TODO


    public static void main(String[] args) throws ExceptionBESA {

        AdmBESA admLocal = AdmBESA.getInstance();
        TPCreator.setClave(clave);
        EPCreator.setClave(clave);
        COCreator.setClave(clave);
        
        CLDoor cd = new CLDoor( clave );
        
        TPCreator.crearTP( 4 );

        EPCreator.crearEP( 5 );

        

        COCreator.crearCocineros( 3 );

    }

}
