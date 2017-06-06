package br.com.roteiro6.main;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.roteiro6.entity.Funcionario;
import br.com.roteiro6.entity.Tarefa;
import br.com.roteiro6.helper.Helper;

public class Teste {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("AtividadeRoteiro6P3");
		EntityManager em = emf.createEntityManager();
		incluirFuncionario(em);
		listarFuncionarios(em);
		buscarFuncionario(em, "2000");
		
		em.close();
	}

	private static void incluirFuncionario(EntityManager em) {
		Helper dao = new Helper(em);
		Funcionario funcionario = new Funcionario();
		funcionario.setMatricula("2000");
		funcionario.setNome("Alberto Santos");
		Tarefa tarefa = new Tarefa();
		tarefa.setDescricao("Teste Unita?rio");
		tarefa.setDuracao(100);
		tarefa.getFuncionarios().add(funcionario);
		funcionario.getTarefas().add(tarefa);
		try {
			dao.salvar(funcionario);
			System.out.println("Funcionario OK");
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void listarFuncionarios(EntityManager em) {
		Helper dao = new Helper(em);
		List<Funcionario> funcionarios = dao.listarTodos();
		for (Funcionario funcionario : funcionarios) {
			System.out.println(funcionario.getMatricula() + ": " + funcionario.getNome());
		}
		em.close();
	}

	private static void buscarFuncionario(EntityManager em, String matricula) {
		Helper dao = new Helper(em);
		Funcionario f = dao.buscarFuncionario(matricula);
		System.out.println(f.getMatricula() + ": " + f.getNome());
	}
}