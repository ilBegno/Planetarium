
public class MainClass {

	public static void main(String[] args) {
		
		SolarSystem solSys = new SolarSystem();
		Menu menu = new Menu(solSys);
		
		menu.loop();
	}
}
