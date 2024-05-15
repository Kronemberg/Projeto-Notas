package trabalho;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;


public class TelaPrincipal extends JFrame{ 
	
	static TelaCriarAtiv criar;
	static TelaLancarNotas lancar;
	private static JFrame inicial = null;
	
	public static JFrame getTelaPrincipal(){
		if(inicial == null) {
			inicial = new JFrame();
			inicial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			inicial.setSize(250,300);
			inicial.setTitle("Notas");
			inicial.setLocationRelativeTo(null);
			inicial.setLayout(null);
			
			JButton criarAtiv = new JButton("Criar Atividade");
			criarAtiv.setBounds(40,30,150,50);
			inicial.add(criarAtiv);
			
			JButton lancarNotas = new JButton("Lancar Notas");
			lancarNotas.setBounds(40,110,150,50);
			inicial.add(lancarNotas);
			
			criarAtiv.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					criar = new TelaCriarAtiv();
					criar.setVisible(true);
					inicial = getTelaPrincipal();
					inicial.setVisible(false);
				}
			});
			
			lancarNotas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					lancar = new TelaLancarNotas();
					lancar.setVisible(true);
					inicial = getTelaPrincipal();
					inicial.setVisible(false);
				}
			});
		}
		
		return inicial;
		
	}
}