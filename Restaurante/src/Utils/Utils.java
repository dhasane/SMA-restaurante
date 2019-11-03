package Utils;

import java.util.Iterator;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.Event.DataBESA;
import BESA.Kernell.Agent.Event.EventBESA;
import BESA.Kernell.System.AdmBESA;

public class Utils {

	// en esta clase están los datos y funciones para que puedan ser llamados por
	// cualquier agente

	// nombres de los agentes
	public static String Cocinero = "Cocinero";
	public static String EntregaPedido = "EntregaPedido";
	public static String tomaPedido = "tomaPedido";
	public static String Puerta = "Puerta";

	// funciones genericas

	// se envía un broadcast a todos los agentes con un rol en especifico, en envío
	// contiene data y la guarda que le responderá
	// retorna la cantidad de agentes a los que les envio mensaje
	public static int broadcast(AdmBESA admLocal, String rol, String guardaRespuesta, DataBESA data) {
		Iterator cos = admLocal.searchAidByService(rol);
		int cantidad = 0;

		while (cos.hasNext()) {
			Object element = cos.next();
			System.out.println("enviando mensaje a : " + element + " ");

			send(admLocal, element, guardaRespuesta, data);
			cantidad++;
		}

		return cantidad;
	}

	// dado un id y una data, se crea un evento y se le envía al agente
	// correspondiente a el id
	public static void send(AdmBESA admLocal, Object id, String clase, DataBESA data) {
		try {
			(admLocal.getHandlerByAid((String) id)).sendEvent(new EventBESA(clase, data));
		} catch (ExceptionBESA e) {
			e.printStackTrace();
		}
	}

}
