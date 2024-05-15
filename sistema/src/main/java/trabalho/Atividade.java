package trabalho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Atividade {
	private String nome;
	private String prazo;
	private Float notaMax;
	
	static final String teste = "select distinct nome_atividade FROM atividade ";
	static final String valorAtiv = "select distinct nome_atividade,valor_atividade FROM atividade ";
	public static List<Atividade> atividades = new ArrayList<>();
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPrazo() {
		return prazo;
	}
	public void setPrazo(String prazo) {
		this.prazo = prazo;
	}
	public Float getNotaMax() {
		return notaMax;
	}
	public void setNotaMax(Float notaMax) {
		this.notaMax = notaMax;
	}
	
	@Override
	public String toString() {
		return "Atividade [nome=" + nome + ", prazo=" + prazo + ", notaMax=" + notaMax + "]";
	}
	
	public static void getAtividades() throws SQLException {
		Connection conexao = Conexao.getConnection();
		
		PreparedStatement comandoSQL = conexao.prepareStatement(valorAtiv);
		comandoSQL.execute();
		
		ResultSet dados = comandoSQL.getResultSet();
		
		
		while(dados.next()) {
			Atividade a = new Atividade();
			String ativ = dados.getString(1);
			a.setNome(ativ);
			
			String nota = dados.getString(2);
			Float notaMax = Float.parseFloat(nota);
			a.setNotaMax(notaMax);
			
			atividades.add(a);
			
		}
	}
	
	public static String procurarAtividade(String nome){
		for(Atividade a : atividades) {
			if(a.getNome().equals(nome)) {
				return "existe";
			}
		}
		return null;
	}
	
	public static Atividade notaMaxima(String nome) {
		for(Atividade a : atividades) {
			if(a.getNome().equals(nome)) {
				return a;
			}
		}
		return null;
	}
	
	public static List<Atividade> getListaAtividades() {
		return atividades;
	}
	
}
