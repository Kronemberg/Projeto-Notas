package trabalho;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelaCriarAtiv extends JFrame {
	
	private static JFrame inicial;
	private static List<Aluno> alunos = Aluno.getListaAlunos();
	private static List<Atividade> atividades = Atividade.getListaAtividades();
	static final String inserir = "INSERT INTO atividade (matricula_aluno,nome_atividade,valor_atividade) values (?,?,?)";
	
	public TelaCriarAtiv() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(340,300);
		setTitle("Criar Atividade");
		setLocationRelativeTo(null);
		
		
		JPanel painel = new JPanel();
		painel.setLayout(null);
		setContentPane(painel);
		
		JLabel nomeAtivTxt = new JLabel("Nome da atividade:");
		nomeAtivTxt.setBounds(20, 20, 150, 50);
		painel.add(nomeAtivTxt);
		
		
		JTextField nomeAtiv = new JTextField(20);
		nomeAtiv.setBounds(135, 32, 170, 30);
		painel.add(nomeAtiv);
		
		JLabel prazoTxt = new JLabel("Prazo Limite:");
		prazoTxt.setBounds(20, 80, 150, 50);
		painel.add(prazoTxt);
		
		JTextField prazoEntrega = new JTextField(20);
		prazoEntrega.setBounds(135, 92, 170, 30);
		painel.add(prazoEntrega);
		
		
		JLabel notamaxTxt = new JLabel("Nota Maxima:");
		notamaxTxt.setBounds(20, 140, 150, 50);
		painel.add(notamaxTxt);
		
		JTextField notaMax = new JTextField(20);
		notaMax.setBounds(135, 152, 170, 30);
		painel.add(notaMax);
		
		
		JButton criar = new JButton("Criar");
		criar.setBounds(230, 200, 70, 30);
		painel.add(criar);
		
		JButton voltar = new JButton("Voltar");
		voltar.setBounds(150, 200, 70, 30);
		painel.add(voltar);
		
		voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inicial = TelaPrincipal.getTelaPrincipal();
				inicial.setVisible(true);
				TelaPrincipal.criar.setVisible(false);
			}
		});
		
		criar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Atividade ativ = new Atividade();
				
				String nome = nomeAtiv.getText();
		
				try {
					Atividade.getAtividades();
					String verifica = Atividade.procurarAtividade(nome);
					
					if(verifica == null) {
						ativ.setNome(nome);
						
						String prazo = prazoEntrega.getText();
						ativ.setPrazo(prazo);
						
						String nota = notaMax.getText();
						Float notaMax = Float.parseFloat(nota);
						ativ.setNotaMax(notaMax);
						
						try {
							if(alunos.size()== 0) {
								Aluno.getAlunos();
							} 
							
							Connection conexao = Conexao.getConnection();
							
							PreparedStatement comandoSQL = conexao.prepareStatement(inserir);
							for(Aluno a : alunos) {
								comandoSQL.setInt(1, a.getMatricula());
								comandoSQL.setString(2, nome);
								comandoSQL.setFloat(3, notaMax);
								comandoSQL.execute();
							}
								
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
						JOptionPane.showMessageDialog(null, "Atividade Criada","Atividade", JOptionPane.INFORMATION_MESSAGE);
						
						inicial = TelaPrincipal.getTelaPrincipal();
						inicial.setVisible(true);
						TelaPrincipal.criar.setVisible(false);
						
					}else {
						JOptionPane.showMessageDialog(null, "Nome de atividade j� criada","Error", JOptionPane.ERROR_MESSAGE);
					}
					
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				
			}
		});
	}
	
	public static void main(String[] args) {
		TelaCriarAtiv a = new TelaCriarAtiv();
		a.setVisible(true);
	}
}
