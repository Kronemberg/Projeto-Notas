package web;

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
	
	private Conexao() {
	}
	
	public static Connection getConnection() throws SQLException {
		if (instancia == null) {
			instancia = DriverManager.getConnection(url, usuario, senha);
			
		}
		return instancia;
		
	}
}
