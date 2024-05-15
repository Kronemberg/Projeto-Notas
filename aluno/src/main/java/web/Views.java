package web;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spark.Route;
import spark.ModelAndView;

public class Views {
	
	static final String consultar = "select aluno.nome,atividade.nome_atividade,atividade.nota from aluno join atividade on (aluno.matricula = atividade.matricula_aluno) WHERE matricula_aluno = (?)";
	static final String consultarNome = "select nome FROM aluno WHERE matricula = (?)";

	public static Route paginaInicial = (requisicao, resposta) -> {
		return "<html>\r\n"
				+ "	<head>\r\n"
				+ "		<meta charset=\"utf-8\">\r\n"
				+ "	</head>\r\n"
				+ "	<body>\r\n"
				+ "		<h2>Consultar Notas</h2>\r\n"
				+ "		\r\n"
				+ "		<p>Informe o número de matricula:</p>\r\n"
				+ "		<p>\r\n"
				+ "			<form method=\"post\" action=\"/consulta\">\r\n"
				+ "				<input type=\"text\" name=\"Matricula\">\r\n"
				+ "				<input type=\"hidden\" name=\"foo\" value=\"bar\">\r\n"
				+ "				<button type=\"submit\">Consultar</button>\r\n"
				+ "			</form>\r\n"
				+ "		</p>\r\n"
				+ "	</body>\r\n"
				+ "</html>";
	};
	
	
	public static Route consulta = (requisicao, reposta) -> {
		StringBuilder texto	= new StringBuilder();
		String Nome = new String();
		String Matricula = new String();
		
		try {
			Matricula = requisicao.queryParams("Matricula");
			Integer MatriculaA = Integer.parseInt(Matricula);
			
			Connection con = Conexao.getConnection();
			
			PreparedStatement comandoSQLA = con.prepareStatement(consultarNome);
			comandoSQLA.setInt(1, MatriculaA);
			comandoSQLA.execute();
			
			ResultSet dadosA = comandoSQLA.getResultSet();
			
			while(dadosA.next()) {
				Nome = dadosA.getString(1);
			}
			
			
			PreparedStatement comandoSQL = con.prepareStatement(consultar);
			comandoSQL.setInt(1, MatriculaA);
			comandoSQL.execute();
			
			ResultSet dados = comandoSQL.getResultSet();
			
			
			while(dados.next()) {
				
				String nomeAtiv = dados.getString(2);
				System.out.println(nomeAtiv);
				
				Float nota = dados.getFloat(3);
				System.out.println(nota);
								
				texto.append("<tr>\n" + " 	<h3>").append(nomeAtiv).append("</h3>").append("  	<h3>").append(nota).append("</h3>\n").append("</tr>\n");
				
			}
			

		} catch (Exception e) {
			return e.getClass().getName() + ": " + e.getMessage();
		}
		return "<html>\r\n"
				+ "	<head>\r\n"
				+ "		<meta charset=\"utf-8\">\r\n"
				+ "	</head>\r\n"
				+ "	<body>\r\n"
				+ "		<h1>Sistema Academico</h1>\r\n"
				+ "		\r\n"
				+ "		<h2>Dados do aluno</h2>\r\n"
				+ "		<p>Nome : " + Nome + "</p>"
				+ "		<p>Matricula : " + Matricula + "</p>"
				+ "		\r\n"
				+ "		<h2>Atividades/Notas</h2>\r\n"
				+ "		<p>" + texto + "</p>"
				+ "		\r\n"
				+ "	</body>\r\n"
				+ "</html>";
	};
	
	
}