
public class MainClass {

	public static void main(String[] args) {
		SolarSistem solarSys = new SolarSistem();
		solarSys.setSolarSistem();
		
		while (true)
		{
			System.out.printf("Premere R per ricercare un pianeta %nPremere A per aggiungere un pianeta"
					+ "%nPremere E per eliminare un pianeta%n");
			String s = InputClass.lettore.next();
			s = s.toUpperCase();
			switch(s) 
			{
			case "R":
				System.out.print("Inserire il nome del pianeta da cercare: ");
				String toSearch = InputClass.lettore.next();
				if (solarSys.research(solarSys.getPlanets(), solarSys.getNumPlanets(), toSearch))
						System.out.println("Il pianeta è presente nel sistema solare");
				else System.out.println("Il pianeta non è presente nel sistema solare");
				break;
				
			case "A": 
				
				break;
				
			case "E":
				break;
			}
			
		}
	}
}
