import java.util.Scanner;

public class InputClass {
	public static Scanner lettore = new Scanner(System.in);
	
	public static void setPlanet(CelBody c) {
		System.out.print("Inserire il nome del pianeta: ");
		c.setName(lettore.next());
		System.out.print("Inserire la massa del pianeta: ");
		c.setMass(lettore.nextDouble());
	}
}
