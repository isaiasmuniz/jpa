package basico;

import java.util.Scanner;

public class TestesProjeto {
	public static void main(String[] args) {
		Scanner kbm = new Scanner(System.in);
		System.out.println("digite o ling:\n");
		long id = kbm.nextLong();
		System.out.println(id+2L);
		kbm.close();
	}
}
