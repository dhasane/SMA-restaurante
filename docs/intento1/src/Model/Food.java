package Model;

import java.util.ArrayList;
import java.util.List;

public class Food {

		private List<String> ingredientes;
		
		public Food()
		{
			ingredientes = new ArrayList<String>();
		}
		
		public void add( String ing )
		{
			this.ingredientes.add( ing );
		}
}
