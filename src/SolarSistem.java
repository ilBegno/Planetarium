import java.util.ArrayList;

public class SolarSistem {
	
	private static int numPlanets = 0;
	private static ArrayList<CelBody> planets;

	public static void setSolarSistem() {

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
			
	}
	
	public static boolean research(ArrayList<CelBody> planets, int numPlanets, String name) {
		for(int i = 0; i < numPlanets; i++) {
			if( (planets.get(i).getName()).equals(name)) return true;
		}
		return false;
	}
	public static ArrayList<CelBody> getPlanets(){
		return planets;
	}
	public static int getNumPlanets() {
		return numPlanets;
	}
	
}
