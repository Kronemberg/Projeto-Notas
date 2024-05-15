package trabalho;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;


public class TelaLancarNotas extends JFrame {
	
	FileNameExtensionFilter filter = new FileNameExtensionFilter(
			"Selecione apenas arquivos CSV",
			"csv");
	
	String caminhoLer = null;
	private static JFrame inicial;
	
	private static List<Aluno> alunos = Aluno.getListaAlunos();
	
	public static void main(String[] args) {
	TelaLancarNotas a = new TelaLancarNotas();
	a.setVisible(true);
}
	
	public TelaLancarNotas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450,400);
		setTitle("Lancar Notas");
		setLocationRelativeTo(null);
		
		JPanel painel = new JPanel();
		painel.setLayout(null);
		
		JLabel modeloTxt = new JLabel("Modelo de planilha para lancar as notas");
		modeloTxt.setBounds(20, 30, 350, 30);
		modeloTxt.setFont(new Font("Times New Roman", Font.BOLD, 18));
		painel.add(modeloTxt);
		
		JButton salvarModelo = new JButton("Salvar Modelo");
		salvarModelo.setBounds(290, 70, 120, 30);
		painel.add(salvarModelo);
		
		JLabel insiraTxt = new JLabel("Insira o nome da atividade para lancar as notas");
		insiraTxt.setBounds(20, 120, 400, 30);
		insiraTxt.setFont(new Font("Times New Roman", Font.BOLD, 18));
		painel.add(insiraTxt);
		
		JTextField ativField = new JTextField(50);
		ativField.setBounds(10, 160, 300, 30);
		painel.add(ativField);
		
		JLabel selecioneTxt = new JLabel("Selecione o arquivo para lancar as notas");
		selecioneTxt.setBounds(20, 210, 350, 30);
		selecioneTxt.setFont(new Font("Times New Roman", Font.BOLD, 18));
		painel.add(selecioneTxt);
		
		JTextField caminho = new JTextField(50);
		caminho.setBounds(10, 260, 300, 30);
		painel.add(caminho);
		
		JButton abrirArquivo = new JButton("Abrir");
		abrirArquivo.setBounds(320, 260, 80, 29);
		painel.add(abrirArquivo);
		
		JButton lancarNotas = new JButton("Lancar Notas");
		lancarNotas.setBounds(300, 310, 110, 29);
		painel.add(lancarNotas);
		
		JButton voltar = new JButton("Voltar");
		voltar.setBounds(210, 310, 80, 29);
		painel.add(voltar);
		
		
		//Colocar a��o nos bot�es
		
		voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inicial = TelaPrincipal.getTelaPrincipal();
				inicial.setVisible(true);
				TelaPrincipal.lancar.setVisible(false);
			}
		});
		
		salvarModelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fc.showSaveDialog(painel);
				File resultSalvar = fc.getSelectedFile();
					
				try {
					if(alunos.size() == 0) {
						Aluno.getAlunos();
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				Arquivo.gerarArquivo(resultSalvar.getPath());
				
				JOptionPane.showMessageDialog(null, "Modelo Gerado","Modelo", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		
		abrirArquivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser ch = new JFileChooser();
				ch.setFileFilter(filter);
				ch.setFileSelectionMode(JFileChooser.FILES_ONLY);
				ch.showOpenDialog(painel);
				File resultAbrir = ch.getSelectedFile();
				caminho.setText(resultAbrir.getPath());
				caminhoLer = resultAbrir.getPath();
			}
		});
		
		lancarNotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomeAtividade = ativField.getText();
				try {
					Atividade.getAtividades();
					String verifica = Atividade.procurarAtividade(nomeAtividade);
					
					if(verifica != null) {
						try {
							Arquivo.lerArquivo(caminhoLer, nomeAtividade);
							inicial = TelaPrincipal.getTelaPrincipal();
							inicial.setVisible(true);
							TelaPrincipal.lancar.setVisible(false);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}else {
						JOptionPane.showMessageDialog(null, "Atividade nao encontrada","Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				
				
			}
		});
		
		add(painel);
	}
		
}
