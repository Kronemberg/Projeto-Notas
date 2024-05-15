package trabalho;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexao {

	private static Connection instancia = null;
	
	private static String url = "jdbc:postgresql://ec2-34-196-34-142.compute-1.amazonaws.com:5432/d4mdnop8nmfgg7";
	private static String usuario = "wpuoprryevyuzx";
	private static String senha = "e10895c0ab78830e9d69e7a5d0be66063838db1707e387789483b68e71579b3d";
	
	static final String teste = "select * from aluno";
	static final String testeA = "select aluno.nome,atividade.nota FROM aluno JOIN atividade ON (aluno.matricula = atividade.matricula_aluno) WHERE atividade.nome_atividade = 'AV1'";
	static final String limpar = "DELETE FROM atividade";
	static final String valorAtiv = "select distinct nome_atividade,valor_atividade FROM atividade ";
	static final String consultar = "select aluno.nome,atividade.nome_atividade,atividade.nota from aluno join atividade on (aluno.matricula = atividade.matricula_aluno) WHERE matricula_aluno = (?)";

	
	private Conexao() {
	}
	
	public static Connection getConnection() throws SQLException {
		if (instancia == null) {
			instancia = DriverManager.getConnection(url, usuario, senha);
			//System.out.println("conectado: " + instancia);
			
		}
		return instancia;
		
	}
	
	public static void teste() throws SQLException {
		Connection conexao = getConnection();
		
		//PreparedStatement comandoSQL = conexao.prepareStatement(teste);
		//comandoSQL.execute();
		
		PreparedStatement comandoOSQL = conexao.prepareStatement(testeA);
		comandoOSQL.execute();
		
		ResultSet dados = comandoOSQL.getResultSet();
		
		while(dados.next()) {
			String Nome = dados.getString(1);
			System.out.println(Nome);
			
			Float Nota = dados.getFloat(2);
			System.out.println(Nota);
		}
	}
	
	public static void teste1() throws SQLException {
		Connection conexao = getConnection();
		
		//PreparedStatement comandoSQL = conexao.prepareStatement(teste);
		//comandoSQL.execute();
		
		PreparedStatement comandoOSQL = conexao.prepareStatement(valorAtiv);
		comandoOSQL.execute();
		
		ResultSet dados = comandoOSQL.getResultSet();
		
		while(dados.next()) {
			String Nome = dados.getString(1);
			System.out.println(Nome);
			
			String Valor = dados.getString(2);
			System.out.println(Valor);
		}
	}
	
	public static void limpar() throws SQLException {
		Connection conexao = getConnection();
		
		//PreparedStatement comandoSQL = conexao.prepareStatement(teste);
		//comandoSQL.execute();
		
		PreparedStatement comandoSQL = conexao.prepareStatement(limpar);
		comandoSQL.execute();
	}
	
	public static void teste2() throws SQLException {
		Connection conexao = getConnection();
		
		//PreparedStatement comandoSQL = conexao.prepareStatement(teste);
		//comandoSQL.execute();
		
		PreparedStatement comandoOSQL = conexao.prepareStatement(consultar);
		comandoOSQL.setInt(1, 3);
		comandoOSQL.execute();
		
		ResultSet dados = comandoOSQL.getResultSet();
		
		while(dados.next()) {
			String Nome = dados.getString(2);
			System.out.println(Nome);
			
			Float Valor = dados.getFloat(3);
			System.out.println(Valor);
		}
	}
	
	public static void main(String[] args) throws SQLException {
		//Aluno.getAlunos();
		//Aluno.imprimir();
		//Atividade.getAtividades();
		//limpar();
		teste2();
		//teste1();
		//teste();
		//getConnection();
	}
}
