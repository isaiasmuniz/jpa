package basico;

import java.util.List;
import java.util.Scanner;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	public Usuario() {
		
	}
	public Usuario(String nome) {
		this.nome = nome;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void addTarefa() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("lista");
		EntityManager em = emf.createEntityManager();
		
		Scanner kbm = new Scanner(System.in);
		System.out.println("digite sua tarefa:\n");
		String tarefa = kbm.next();
		Usuario novoUsuario = new Usuario(tarefa);
		
		em.getTransaction().begin();
		em.persist(novoUsuario);
		em.getTransaction().commit();
		
		kbm.close();
		em.close();
		emf.close();
	}
	
	public void consultarTarefa() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("lista");
		EntityManager em = emf.createEntityManager();
		
		String jpql = "select u from Usuario u";
		TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
		
		List<Usuario> usuarios = query.getResultList();
		
		for(Usuario usuario: usuarios) {
			System.out.println("ID: " + usuario.getId() + "\nTarefa: " + usuario.getNome());
		}
		
		em.close();
		emf.close();
	}
	
	public void deletarTarefa() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("lista");
		EntityManager em = emf.createEntityManager();
		
		Scanner kbm = new Scanner(System.in);
		System.out.println("digite a tarefa a ser apagada:\n");
		long tarefa = kbm.nextLong();
		Usuario usuario = em.find(Usuario.class, tarefa);
		
		if(usuario != null) {
			em.getTransaction().begin();
			em.remove(usuario);
			em.getTransaction().commit();
		}
		
		kbm.close();
		em.close();
		emf.close();
	}

}
