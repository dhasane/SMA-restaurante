package CO.Behavior;

import BESA.Kernell.Agent.Event.EventBESA;
import CO.State.COState;
import Mundo.Cocinar;
import Mundo.Ingredientes;
import Mundo.Mapa.Mapa;
import BESA.Kernell.Agent.GuardBESA;

public class COCocinar extends GuardBESA {

	@Override
	public void funcExecGuard(EventBESA ebesa) {

		while (Cocinar.size() > 0) {
			((COState) this.getAgent().getState()).inicioCocina();
			String preparar = Cocinar.pop();
			Ingredientes.agregarIngrediente(preparar);

		}
		((COState) this.getAgent().getState()).finalCocina();
		Mapa.repaint();
	}

}
