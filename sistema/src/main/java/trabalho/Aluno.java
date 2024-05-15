package trabalho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Aluno {
	private Integer matricula;
	private String nome;
	private String email;
	
	
	static final String teste = "select * from aluno";
	public static List<Aluno> alunos = new ArrayList<>();
	
	public Integer getMatricula() {
		return matricula;
	}
	public void setMatricula(Integer matricula2) {
		this.matricula = matricula2;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Aluno [matricula=" + matricula + ", nome=" + nome + ", email=" + email + "]";
	}
	
	public static void getAlunos() throws SQLException {
		Connection conexao = Conexao.getConnection();
		
		PreparedStatement comandoSQL = conexao.prepareStatement(teste);
		comandoSQL.execute();
		
		ResultSet dados = comandoSQL.getResultSet();
		
		while(dados.next()) {
			Aluno a = new Aluno();
			Integer matricula = dados.getInt(1);
			a.setMatricula(matricula);
			//System.out.println(matricula);
			
			String nome = dados.getString(2);
			a.setNome(nome);
			//System.out.println(nome);
			
			String email = dados.getString(3);
			a.setEmail(email);
			//System.out.println(email);
			
			alunos.add(a);
		}
	}
	
	public static List<Aluno> getListaAlunos(){
		return alunos;
	}
	
	public static void imprimir() {
		alunos.forEach(System.out::println);
	}
	
}
