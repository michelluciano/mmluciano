package teste_qualirede1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Tela_de_regra extends JFrame {

	private JPanel contentPane;
	private JTextField tfProcedimento;
	private JTextField tfSexo;
	private JTextField tfIdade;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_de_regra frame = new Tela_de_regra();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Tela_de_regra() {
		setTitle("Tela de Acesso");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCodigoDoProcedimento = new JLabel("Codigo do Procedimento");
		lblCodigoDoProcedimento.setBounds(36, 32, 138, 20);
		contentPane.add(lblCodigoDoProcedimento);
		
		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setBounds(36, 99, 117, 20);
		contentPane.add(lblSexo);
		
		JLabel lblSexo_1 = new JLabel("Idade");
		lblSexo_1.setBounds(36, 63, 138, 20);
		contentPane.add(lblSexo_1);
		
		tfProcedimento = new JTextField();
		tfProcedimento.setBounds(207, 32, 86, 20);
		contentPane.add(tfProcedimento);
		tfProcedimento.setColumns(10);
		
		tfSexo = new JTextField();
		tfSexo.setColumns(10);
		tfSexo.setBounds(207, 99, 86, 20);
		contentPane.add(tfSexo);
		
		tfIdade = new JTextField();
		tfIdade.setColumns(10);
		tfIdade.setBounds(207, 63, 86, 20);
		contentPane.add(tfIdade);
		
		JButton btnValidar = new JButton("Validar");
		btnValidar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tfProcedimento.getText().equals("")|| tfIdade.getText().equals("")|| tfSexo.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Campo obrigatorio n�o informado.");
				}else {
				try {
				
					Connection con = Conexao.faz_conexao();
				    
					String sql = "Select * from regras where permitido_regra = 'SIM' and cd_procedimento = ? and sexo_regra = ? and idade_regra =?";
				    
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1,tfProcedimento.getText());
					stmt.setString(2, tfSexo.getText());
					stmt.setString(3, tfIdade.getText());
				
					ResultSet rs = stmt.executeQuery();
					
					if (rs.next()) {
						JOptionPane.showMessageDialog(null, "Guia v�lida.");
					}else {
						JOptionPane.showMessageDialog(null, "Guia Inv�lida.");
					}
					
					stmt.close();
					con.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			}
		});
		btnValidar.setBounds(73, 148, 89, 23);
		contentPane.add(btnValidar);
		
		JLabel lblDadosDaGuia = new JLabel("DADOS DA GUIA");
		lblDadosDaGuia.setBounds(130, 11, 107, 14);
		contentPane.add(lblDadosDaGuia);
		
		JButton btnCadastro = new JButton("Cadastro de Regras");
		btnCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Cadastro_de_Regras exibir = new Cadastro_de_Regras();
				exibir.setVisible(true); // torna a tela de cadastro visivel
				setVisible(false); //esconder a tela da guia 
				
				//JOptionPane.showMessageDialog(null, "Btn Cadastro");
			}
		});
		btnCadastro.setBounds(208, 148, 177, 23);
		contentPane.add(btnCadastro);
	}
}
