import java.util.Scanner;

public class InputClass {
	
	public static Scanner lettore = new Scanner(System.in);
	
	public static void setPlanet(CelBody c) {
		System.out.print("Inserire il nome del pianeta: ");
		c.setName(lettore.next());
		System.out.print("Inserire la massa del pianeta: ");
		c.setMass(lettore.nextDouble());
		System.out.printf("Inserire la posizione del pianeta rispetto al Sole (0,0) %nx: ");
		(c.getPos()).setX(lettore.nextDouble());
		System.out.print("y: ");
		(c.getPos()).setX(lettore.nextDouble());
		System.out.print("Questo pianeta possiede lune? Digitare il numero di lune,"
				+ " in caso non ne possieda inserire 0: ");
		c.setNumMoons(lettore.nextInt());
		
		for (int i = 0; i < c.getNumMoons(); i++) {
			CelBody moon = new CelBody(c); 
			setMoon(moon, i);
			(c.getMoons()).add(moon);
		}
	}
	private static void setMoon(CelBody m, int i) {
		System.out.printf("Inserire il nome della luna nr%d: ", i+1);
		m.setName(lettore.next());
		System.out.print("Inserire la massa della luna: ");
		m.setMass(lettore.nextDouble());
		System.out.printf("Inserire la posizione della luna rispetto al Sole (0,0) %nx: ");
		(m.getPos()).setX(lettore.nextDouble());
		System.out.print("y: ");
		(m.getPos()).setY(lettore.nextDouble());
	}
}
