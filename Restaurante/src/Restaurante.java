
import BESA.ExceptionBESA;
//import BESA.Kernell.System.AdmBESA;
import Creator.COCreator;
import Creator.EPCreator;
import Creator.TPCreator;
import DOOR.CLDoor;

public class Restaurante {


    private static double clave = 0.91;

    // CL -> cliente		TODO
    // CO -> cocinero		TODO
    // TP -> toma pedido	TODO
    // EP -> entrega pedido	TODO
    // CA -> caja			TODO
    // AY -> ayudante		TODO

    public static void main(String[] args) throws ExceptionBESA {

        TPCreator.setClave(clave);
        EPCreator.setClave(clave);
        COCreator.setClave(clave);
        
        int cantidadMaximaDeClientes = 7;
        
        TPCreator.crearTP( 4 );

        EPCreator.crearEP( 5 );

        COCreator.crearCocineros( 3 );
        
        // siendo que los clientes son los que inician la cadena, deben ser llamados de ultimo,
        // cuando ya todos los demas agentes esten iniciados
        new CLDoor( clave, cantidadMaximaDeClientes );
        
    }

}
