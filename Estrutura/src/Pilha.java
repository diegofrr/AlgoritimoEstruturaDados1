import java.util.Scanner;

import javax.swing.JOptionPane;

public class Pilha {

	public static void main(String[] args) {
		Stack pilha = new Stack();
		
		Scanner sc = new Scanner(System.in);

		while (sc.hasNextLine()) {
			String linha = sc.nextLine();

			if (linha.equals("-")) {
				System.out.println(pilha.pop() + " ");
				
			}else {
				pilha.push(linha);
			}
			
			
		}
		
		System.out.println(pilha.size());
		

	}

}