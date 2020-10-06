import java.util.Scanner;

import javax.swing.JOptionPane;

public class Fila {

	public static void main(String[] args) {
		Queue fila = new Queue();
		
		Scanner sc = new Scanner(System.in);

		while (sc.hasNextLine()) {
			String linha = sc.nextLine();

			if (linha.equals("-")) {
				System.out.println(fila.dequeue() + " ");
				
			}else {
				fila.enqueue(linha);
			}
			System.out.println(fila.size());
		}
		
		

	}

}