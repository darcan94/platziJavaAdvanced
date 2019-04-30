import java.util.Scanner;
public class ChoiceHelper{
	public static int validateUserResponse(int min, int max){
		Scanner sc = new Scanner(System.in);
		while(!sc.hasNextInt()) {
			sc.next();
			System.out.println("No ingresaste una opción válida1");
			System.out.println("Intenta otra vez1");
		}
		int response = sc.nextInt();
		while(response < min || response > max){
			System.out.println("ingrese una opcion valida (del "+min+" al "+max+")");
			while(!sc.hasNextInt()) {
				sc.next();
				System.out.println("No ingresaste una opción válida");
				System.out.println("Intenta otra vez");
			
		}
		response = sc.nextInt();
		}
		return response;
	
}
}