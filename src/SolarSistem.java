import java.util.ArrayList;

public class SolarSistem {
	
	private static int numPlanets = 0;
	private static ArrayList<CelBody> planets;

	public static void main(String[] args) {

		System.out.print("Inserire il numero di pianeti del Sistema Solare: ");
		numPlanets = InputClass.lettore.nextInt();
		planets = new ArrayList<CelBody>();
		
		for(int i = 0; i < numPlanets; i++) {
			System.out.printf("Registrazione del pianeta nr%d %n", i+1);
			CelBody planet = new CelBody();
			InputClass.setPlanet(planet);
			System.out.printf("Registrato il pianeta nr%d %n%n", i+1);
			planets.add(planet);
		}
		
		while (true)
		{
			System.out.printf("Premere R per ricercare un pianeta %nPremere A per aggiungere un pianeta"
					+ "%Premere E per eliminare un pianeta");
			String s = InputClass.lettore.next();
			
			switch(s) {
			
			case "R": 
				break;
			case "A": 
				break;
			case "E":
				break;
			}
			
			
			
		}
	}

}
