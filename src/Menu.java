import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
	private String status;
	private Scanner lettore;
	private SolarSystem solSys;
	public static final String EXIT = "E";
	public static final String ADD = "A";
	public static final String DELETE = "D";
	public static final String RESEARCH = "R";
	public static final String COFMASS = "C";
	
	public Menu(SolarSystem solSys) {
		this.solSys = solSys;
		lettore = new Scanner(System.in);
	}
	
	public void loop() 
	{
	 
		CICLO: while (true)
		{
			System.out.printf("####################################################" 
					+ "%nSalve utente del Consiglio Intergalattico, cosa desidera fare?"
					+ "%nPer aggiungere un corpo celeste al sistema digiti A"
					+ "%nPer eliminare un corpo celeste al sistema digiti D"
					+ "%nPer cercare un corpo celeste digiti R"
					+ "%nPer conoscere la posizione del centro di massa del sistema digiti C"
					+ "%nPer terminare l'esecuzione del programma digiti E%n"
					+ "####################################################%n");
			status = lettore.next();
			status = status.substring(0, 1);
			status = status.toUpperCase();
			//input utente ridotto ad un solo carattere
			
			switch(status) 
			{
				case ADD:
					add();
					break;
					
				case DELETE:
					break;
					
				case RESEARCH:
					break;
					
				case COFMASS:
					break;
					
				case EXIT: 
					break CICLO;
			}
			
		}
		lettore.close();
	}
	
	private void add() {
		String planetormoon;
		System.out.printf("%nSi desidera aggiungere un pianeta o una luna ad un pianeta del sistema? "
				+ "%nDigitare P per un pianeta oppure M per una luna: ");
		planetormoon = lettore.next();
		
		switch(planetormoon) 
		{
		case "P":
		{
			System.out.print("Inserire il nome del pianeta: ");
			String namePlanet = lettore.next();
			System.out.print("Inserire la massa del pianeta: ");
			double mass = lettore.nextDouble();
			System.out.printf("Inserire la posizione del pianeta rispetto al Sole (0,0) %nx: ");
			Position pos = new Position();
			pos.setX(lettore.nextDouble());
			System.out.print("y: ");
			pos.setY(lettore.nextDouble());
			System.out.print("Questo pianeta possiede lune? Digitare il numero di lune,"
					+ " in caso non ne possieda inserire 0: ");
			int numMoons = lettore.nextInt();
			String id = namePlanet + solSys.getCurrentId();
			solSys.incrId();
			if (numMoons > 0) 
			{
			ArrayList<CelBody> moons = new ArrayList<CelBody>();
			for (int i = 0; i < numMoons; i++) {
				System.out.printf("%nInserire il nome della luna nr%d: ", i+1);
				String _name = lettore.next();
				System.out.print("Inserire la massa della luna: ");
				double _mass = lettore.nextDouble();
				System.out.printf("Inserire la posizione della luna rispetto al Sole (0,0) %nx: ");
				Position _pos = new Position();
				_pos.setX(lettore.nextDouble());
				System.out.print("y: ");
				_pos.setY(lettore.nextDouble());
				String _id = _name + solSys.getCurrentId();
				solSys.incrId();
				CelBody moon = new CelBody(_id, _name, _mass, _pos, namePlanet); 
				moons.add(i, moon); //aggiunge la luna all'ArrayList moons
			}
			Planet p = new Planet(id, namePlanet, mass, pos, "SUN", moons);
			solSys.addPlanet(p);
			System.out.println("Pianeta e lune aggiunti correttamente al sistema");
			}
			else {
				Planet p = new Planet(id, namePlanet, mass, pos, "SUN");
				solSys.addPlanet(p);
				System.out.println("Pianeta aggiunto correttamente al sistema");
			}
			
		}
		break;
		
		case "M":
		{
			String planetname; 
			System.out.print("Inserire il nome del pianeta a cui aggiungere una luna: ");
			planetname = lettore.next();
			int index = solSys.findPlanetbyName(planetname);
			if(index >= 0)
			{
				
				System.out.print("Inserire il nome della luna");
				String _name = lettore.next();
				System.out.print("Inserire la massa della luna: ");
				double _mass = lettore.nextDouble();
				System.out.printf("Inserire la posizione della luna rispetto al Sole (0,0) "
						+ "%nx: ");
				Position _pos = new Position();
				_pos.setX(lettore.nextDouble());
				System.out.print("y: ");
				_pos.setY(lettore.nextDouble());
				String _id = _name + solSys.getCurrentId();
				solSys.incrId();
				CelBody moon = new CelBody(_id, _name, _mass, _pos, planetname); 
				solSys.getPlanet(index).addMoon(moon);
			}
			else {
				System.out.print("Il pianeta non esiste");
			}	
		}
		break;
		default:
			System.out.print("Errore");
		break;
		}
	}
}