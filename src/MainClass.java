
public class MainClass {

	public static void main(String[] args) {
		
		SolarSystem solSys = new SolarSystem();
		// Creating a menu class that interracts with the class solarSystem and the ArrayList planets
		Menu menu = new Menu(solSys);
		menu.init();
		menu.loop();
	}
}
