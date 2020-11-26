package BuscaBinaria;

public class Cidade {
	private String nome;
	private int temp;
	
	public Cidade(String nome, int tmp) {
		this.nome = nome;
		this.temp = temp;
	}
	
	public void set_nome(String n) {
		this.nome = n;
	}
	
	public void set_temp(int t) {
		this.temp = t;
	}
	
	public String get_nome() {
		return this.nome;
	}
	
	public int get_temp() {
		return this.temp;
	}
	
	public int compareTo(Cidade c) {
		return this.nome.compareTo(c.get_nome());
	}

}
