package trabalho;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;


public class Arquivo {
	
	public static List<Aluno> alunos = Aluno.getListaAlunos();
	
	static final String inserirNota = "UPDATE atividade SET nota = (?) WHERE nome_atividade = (?) AND matricula_aluno = (?)";
	
	public static void imprimir() {
		for(Aluno a : alunos) {
			System.out.println(a.getNome());
		}
	}
	
	public static void gerarArquivo(String caminho) {
		
		try {
			PrintWriter w = new PrintWriter(
					Files.newBufferedWriter(Path.of(caminho + "\\notas.csv"),
							StandardOpenOption.CREATE)
			);
		
			for(Aluno as : alunos) {
				w.println(as.getMatricula() + ";" + as.getNome() + ";" +  as.getEmail() + ";" + "0");
				}
			w.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void lerArquivo(String caminho, String nomeAtividade) throws SQLException{
		
		try (BufferedReader br = (new BufferedReader(new FileReader(caminho)))) {
		
			String line = br.readLine();
			while (line != null) {
				
				String[] info = line.split(";");		
				
				Float nota = Float.parseFloat(info[3]);
				Integer matricula = Integer.parseInt(info[0]);
				
				Atividade comp = Atividade.notaMaxima(nomeAtividade);
				
				if(nota <= comp.getNotaMax()) {
					Connection conexao = Conexao.getConnection();
					
					PreparedStatement comandoSQL = conexao.prepareStatement(inserirNota);
					
					comandoSQL.setFloat(1, nota);
					comandoSQL.setString(2, nomeAtividade);
					comandoSQL.setInt(3, matricula);
					comandoSQL.execute();
					
					line = br.readLine();
				} else {
					line = br.readLine();
				}
			}
			
			JOptionPane.showMessageDialog(null, "Notas Lancadas","Notas", JOptionPane.INFORMATION_MESSAGE);
			
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao lancar notas","Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		
	}
	
}
