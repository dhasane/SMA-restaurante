/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CO.Behavior;

import BESA.Kernell.Agent.Event.EventBESA;
import Informacion.Cocinar;
import Informacion.Ingredientes;
import BESA.Kernell.Agent.GuardBESA;

public class COCocinar extends GuardBESA {

	@Override
	public void funcExecGuard(EventBESA ebesa) {

		System.out.println(" holaaa soy " + getAgent().getAlias() + "ito-kun ");
		while (Cocinar.size() > 0) {
			String preparar = Cocinar.pop();
			Ingredientes.agregarIngrediente(preparar);
		}
	}

}
