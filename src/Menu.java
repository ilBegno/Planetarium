import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Menu {
	private String status;
	private Scanner lettore;
	private SolarSystem solSys;
	public static final String EXIT = "E";
	public static final String ADD = "A";
	public static final String DELETE = "D";
	public static final String RESEARCH = "R";
	public static final String COFMASS = "C";
	public static final String PATH = "P";

	public Menu(SolarSystem solSys) {
		this.solSys = solSys;
		lettore = new Scanner(System.in);
	}

	public void init() {
		System.out.print("Inizializzazione del planetario...");
		System.out.printf("%nInserisci il nome del sistema solare che vuoi creare: ");
		solSys.setName(lettore.next());
		System.out.print("Inserisci il nome del sole: ");
		solSys.setSunName(lettore.next());
		System.out.print("Inserisci la massa del sole: ");
		solSys.setSunMass(lettore.nextDouble());
	}

	public void loop() {

		CICLO: while (true) {
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.printf("%n####################################################"
					+ "%nSalve utente del Consiglio Intergalattico" + "%nBenvenuto nel %s! Cosa desidera fare?"
					+ "%nx Per aggiungere un corpo celeste al sistema digiti A"
					+ "%nx Per eliminare un corpo celeste al sistema digiti D"
					+ "%nx Per cercare un corpo celeste digiti R"
					+ "%nx Per conoscere la posizione del centro di massa del sistema digiti C"
					+ "%nx Per conoscere il percorso tra 2 corpi celesti digiti P"
					+ "%nx Per terminare l'esecuzione del programma digiti E%n"
					+ "####################################################%n", solSys.getName());
			status = lettore.next();
			status = status.substring(0, 1);
			status = status.toUpperCase();
			// input utente ridotto ad un solo carattere

			switch (status) {
			case ADD:
				add();
				break;

			case DELETE:
				delete();
				break;

			case RESEARCH:
				research();
				break;

			case COFMASS:
				solSys.calcCoF();
				System.out.printf("La posizione del centro di massa del sistema e':" + "%n X: %f" + "%n Y: %f",
						solSys.getCenterOfMass().getX(), solSys.getCenterOfMass().getY());
				break;

			case PATH:
				findRoute();
				break;

			case EXIT:
				break CICLO;
			}

		}
		lettore.close();
		System.out.println("Esecuzione terminata");
	}

	private void research() {
		System.out.println("Inserire il nome del corpo celeste che si desidera cercare nel sistema: ");
		String tosearch = lettore.next();
		boolean found = false;
		int index = solSys.findPlanetbyName(tosearch);
		if (index >= 0) {
			found = true;
			System.out.printf("Le lune del pianeta %s sono %s", tosearch, solSys.getPlanet(index).showMoons());
		}
		if (found == false && solSys.checkIfMoonExists(tosearch)) {
			String planetname = solSys.findPlanetofMoon(tosearch).getName();
			found = true;
			System.out.printf("La luna %s orbita intorno a %s", tosearch, planetname);
			System.out.printf("%nLa rotta per raggiungere questa luna e': %n%s %s %s", "Sun", planetname, tosearch);
		}
		if (found == false) {
			System.out.printf("Il corpo celeste %s non � stato trovato nel sistema", tosearch);
		}
	}

	private void delete() {
		String planetormoon;
		String toremove;
		System.out.printf("%nSi desidera rimuovere un pianeta o una luna dal sistema? "
				+ "%nDigitare P per un pianeta oppure M per una luna: ");
		planetormoon = lettore.next();
		planetormoon = planetormoon.substring(0, 1);
		planetormoon = planetormoon.toUpperCase();
		switch (planetormoon) {
		case "P":
			System.out.print("Inserire il nome del pianeta da rimuovere: ");
			toremove = lettore.next();
			if (solSys.findPlanetbyName(toremove) >= 0) {
				solSys.removePlanet(toremove);
				System.out.println("Pianeta rimosso correttamente");
			} else
				System.out.println("Il pianeta non esiste nel sistema");
			break;
		case "M":
			System.out.print("Inserire il nome della luna da rimuovere: ");
			toremove = lettore.next();
			if (solSys.checkIfMoonExists(toremove)) {
				solSys.findPlanetofMoon(toremove).removeMoon(toremove);
				System.out.println("Luna rimossa correttamente");
			} else {
				System.out.println("La luna non esiste nel sistema");
			}
			break;
		default:
			System.out.println("Errore");
			break;
		}

	}

	private void add() {
		String planetormoon;
		System.out.printf("%nSi desidera aggiungere un pianeta o una luna ad un pianeta del sistema? "
				+ "%nDigitare P per un pianeta oppure M per una luna: ");
		planetormoon = lettore.next();
		planetormoon = planetormoon.substring(0, 1);
		planetormoon = planetormoon.toUpperCase();

		switch (planetormoon) {
		case "P": {
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
			if (numMoons > 0) {
				ArrayList<CelBody> moons = new ArrayList<CelBody>();
				for (int i = 0; i < numMoons; i++) {
					System.out.printf("%nInserire il nome della luna nr %d: ", i + 1);
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
					double radius = Math.sqrt(Math.pow(pos.getX() - (_pos.getX()), 2) + (Math.pow(pos.getY() -_pos.getY(), 2)));
					CelBody moon = new CelBody(_id, _name, _mass, _pos, namePlanet, radius);
					moons.add(i, moon); // aggiunge la luna all'ArrayList moons
				}
				Planet p = new Planet(id, namePlanet, mass, pos, "SUN", moons);
				solSys.addPlanet(p);
				System.out.println("Pianeta e lune aggiunti correttamente al sistema");
			} else {
				Planet p = new Planet(id, namePlanet, mass, pos, "SUN");
				solSys.addPlanet(p);
				System.out.println("Pianeta aggiunto correttamente al sistema");
			}

		}
			break;

		case "M": {
			String planetname;
			System.out.print("Inserire il nome del pianeta a cui aggiungere una luna: ");
			planetname = lettore.next();
			int index = solSys.findPlanetbyName(planetname);
			if (index >= 0) {

				System.out.print("Inserire il nome della luna");
				String _name = lettore.next();
				System.out.print("Inserire la massa della luna: ");
				double _mass = lettore.nextDouble();
				System.out.printf("Inserire la posizione della luna rispetto al Sole (0,0) " + "%nx: ");
				Position _pos = new Position();
				_pos.setX(lettore.nextDouble());
				System.out.print("y: ");
				_pos.setY(lettore.nextDouble());
				String _id = _name + solSys.getCurrentId();
				solSys.incrId();
				CelBody moon = new CelBody(_id, _name, _mass, _pos, planetname);
				solSys.getPlanet(index).addMoon(moon);
			} else {
				System.out.print("Il pianeta non esiste");
			}
		}
			break;
		default:
			System.out.print("Errore");
			break;
		}
	}

	private void findRoute() {
		System.out.print("Inserire il nome del corpo celeste di partenza: ");
		String start = lettore.next();
		System.out.print("Inserire il nome del corpo celeste di arrivo: ");
		String end = lettore.next();
		
		String sType = "";
		
		String eType = "";
			
		// Created due Strings which memorize if the CelBodies are 2 planets, 2 moons or 1 planet and 1 moon ...
		
		if (solSys.checkIfMoonExists(start))
			sType = "moon";
		
		if (solSys.checkIfMoonExists(end))
			eType = "moon";
		
		if(solSys.checkIfPlanetExists(start))
			sType = "planet";
		
		if(solSys.checkIfPlanetExists(end))
			eType = "planet";
		
		if (solSys.checkIfMoonExists(start) == false && solSys.checkIfPlanetExists(start) == false)
			sType = "errore";
		
		if (solSys.checkIfMoonExists(end) == false && solSys.checkIfPlanetExists(end) == false)
			eType = "errore";
		
		if (sType.equals("moon")&& eType.equals("planet"))
			solSys.fromMoonToPlanet(start, end);
		
		if (sType.equals("moon")&& eType.equals("moon"))
			solSys.fromMoonToMoon(start, end);
		
		if (sType.equals("planet")&& eType.equals("moon"))
			solSys.fromPlanetToMoon(start, end);

		if (sType.equals("planet")&& eType.equals("planet"))
			solSys.fromPlanetToPlanet(start, end);
		
		if (sType.equals("errore") || eType.equals("errore")) {
			System.out.print("Impossibile calcolare la rotta, i corpi celesti inseriti non esistono nel sistema"
					+ "%nSi utilizzi la funzione ricerca per verificare che i corpi appartengano al sistema");
		}
	}
}