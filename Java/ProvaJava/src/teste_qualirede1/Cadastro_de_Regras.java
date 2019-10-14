package teste_qualirede1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Cadastro_de_Regras extends JFrame {

	private JPanel contentPane;
	private JTextField tfIdR;
	private JTextField tfProcedimentoR;
	private JTextField tfIdadeR;
	private JTextField tfSexoR;
	private JTextField tfPermissaoR;
	private JTextField tfBuscarR;
	private JTable tbDados;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cadastro_de_Regras frame = new Cadastro_de_Regras();
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
	public Cadastro_de_Regras() {
		setResizable(false);
		setTitle("Cadastro de Regras");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 468);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(29, 23, 46, 14);
		contentPane.add(lblId);
		
		JLabel lblProcedimento = new JLabel("Procedimento");
		lblProcedimento.setBounds(29, 48, 79, 14);
		contentPane.add(lblProcedimento);
		
		JLabel lblIdade = new JLabel("Idade");
		lblIdade.setBounds(29, 73, 46, 14);
		contentPane.add(lblIdade);
		
		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setBounds(29, 98, 46, 14);
		contentPane.add(lblSexo);
		
		JLabel lblNewLabel = new JLabel("Permiss\u00E3o");
		lblNewLabel.setBounds(29, 123, 62, 14);
		contentPane.add(lblNewLabel);
		
		tfIdR = new JTextField();
		tfIdR.setEditable(false);
		tfIdR.setBounds(118, 20, 86, 20);
		contentPane.add(tfIdR);
		tfIdR.setColumns(10);
		
		tfProcedimentoR = new JTextField();
		tfProcedimentoR.setBounds(118, 45, 86, 20);
		contentPane.add(tfProcedimentoR);
		tfProcedimentoR.setColumns(10);
		
		tfIdadeR = new JTextField();
		tfIdadeR.setColumns(10);
		tfIdadeR.setBounds(118, 70, 86, 20);
		contentPane.add(tfIdadeR);
		
		tfSexoR = new JTextField();
		tfSexoR.setColumns(10);
		tfSexoR.setBounds(118, 95, 86, 20);
		contentPane.add(tfSexoR);
		
		tfPermissaoR = new JTextField();
		tfPermissaoR.setColumns(10);
		tfPermissaoR.setBounds(118, 120, 86, 20);
		contentPane.add(tfPermissaoR);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "A\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 374, 414, 54);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tfProcedimentoR.getText().equals("")|| tfIdadeR.getText().equals("")|| tfSexoR.getText().equals("")||tfPermissaoR.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Campo obrigatorio n�o informado.");
				}else {
				
				try {
					
					Connection con = Conexao.faz_conexao();
					String sql = "insert into regras(cd_procedimento,idade_regra,sexo_regra,permitido_regra) values (?,?,?,?)";

					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, tfProcedimentoR.getText());
					stmt.setString(2, tfIdadeR.getText());
					stmt.setString(3, tfSexoR.getText());
					stmt.setString(4, tfPermissaoR.getText());
					
					stmt.execute();
					
					stmt.close();
					con.close();
					
					JOptionPane.showMessageDialog(null, "A regra foi cadastrada com sucesso.");
					tfProcedimentoR.setText("");
					tfIdadeR.setText("");
					tfSexoR.setText("");
					tfPermissaoR.setText("");
					
				} catch (Exception e) {
					// TODO: handle exception
				}// end catch
				
			          }// end else
				}//end tbnsalvar
		});
		btnSalvar.setBounds(10, 23, 89, 23);
		panel.add(btnSalvar);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(tfIdR.getText().contentEquals("")) {
					JOptionPane.showMessageDialog(null, "Informe o ID");
				}else {
				try {
					
					Connection con = Conexao.faz_conexao();

					String sql = "update regras set cd_procedimento=?, idade_regra=?, sexo_regra=?, permitido_regra=? where id_regra=?";
					
					PreparedStatement stmt =con.prepareStatement(sql);
					
					stmt.setString(1, tfProcedimentoR.getText());
					stmt.setString(2, tfIdadeR.getText());
					stmt.setString(3, tfSexoR.getText());
					stmt.setString(4, tfPermissaoR.getText());
					stmt.setString(5, tfIdR.getText());
					
					stmt.execute();
					stmt.close();
					con.close();
					JOptionPane.showMessageDialog(null,"Atualizado com sucesso");
					tfIdR.setText("");
					tfProcedimentoR.setText("");
					tfIdadeR.setText("");
					tfSexoR.setText("");
					tfPermissaoR.setText("");
					
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
			}
		});
		btnAtualizar.setBounds(109, 23, 89, 23);
		panel.add(btnAtualizar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfProcedimentoR.getText().equals("")|| tfIdadeR.getText().equals("")|| tfSexoR.getText().equals("")||tfPermissaoR.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Campo obrigatorio n�o informado.");
				}else {
				try {
					
					Connection con = Conexao.faz_conexao();

					String sql = "delete from regras where id_regra=?";
					
					PreparedStatement stmt =con.prepareStatement(sql);
					
					stmt.setString(1, tfIdR.getText());
					
					stmt.execute();
					stmt.close();
					con.close();
					JOptionPane.showMessageDialog(null,"Excluido com sucesso.");
					tfIdR.setText("");
					tfProcedimentoR.setText("");
					tfIdadeR.setText("");
					tfSexoR.setText("");
					tfPermissaoR.setText("");
					
					
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
				}
			}
		});
		btnExcluir.setBounds(209, 23, 89, 23);
		panel.add(btnExcluir);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setForeground(Color.LIGHT_GRAY);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Buscar Regra pelo ID", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 309, 414, 54);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		tfBuscarR = new JTextField();
		tfBuscarR.setBounds(113, 21, 86, 20);
		panel_1.add(tfBuscarR);
		tfBuscarR.setColumns(10);
		
		JButton btnBuscarR = new JButton("Buscar");
		btnBuscarR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			if (tfBuscarR.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Informe o ID");
			}else {
			try {
				
				Connection con = Conexao.faz_conexao();
				
				String sql = "select * from regras where id_regra =?";
				
				PreparedStatement stmt =con.prepareStatement(sql);
				
				stmt.setString(1,tfBuscarR.getText());
				
				ResultSet rs = stmt.executeQuery();
				
							
				while (rs.next()) {
					tfIdR.setText(rs.getString("id_regra"));
					tfProcedimentoR.setText(rs.getString("cd_procedimento"));
					tfIdadeR.setText(rs.getString("idade_regra"));
					tfSexoR.setText(rs.getString("sexo_regra"));
					tfPermissaoR.setText(rs.getString("permitido_regra"));
				}
				rs.close();
				con.close();
				//}else {
				//	JOptionPane.showMessageDialog(null,"ID n�o encontrado, favor verificar.");
					
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			}
			}
			
			
			
		});
		btnBuscarR.setBounds(10, 20, 89, 23);
		panel_1.add(btnBuscarR);
		
		JButton btnListarRegras = new JButton("Listar Regras");
		btnListarRegras.setBounds(252, 20, 130, 23);
		panel_1.add(btnListarRegras);
		btnListarRegras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			try {
				Connection con = Conexao.faz_conexao(); // realiza a conexao
				
				String sql = "select * from regras"; // comando sql 
				
				PreparedStatement stmt = con.prepareStatement(sql); // preparando as informacoes para enviar par ao banco
				
				ResultSet rs = stmt.executeQuery(); // executando uma consulta no banco
				
				// criando objeto do tipo tabela
				DefaultTableModel modelo = (DefaultTableModel)tbDados.getModel();
				modelo.setNumRows(0);
				
				while (rs.next()) {
					modelo.addRow(new Object[] {rs.getString("id_regra"),rs.getString("cd_procedimento"),rs.getString("idade_regra"),rs.getString("sexo_regra"),rs.getString("permitido_regra")});
					
				}
				rs.close();
				con.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 255, 377, -86);
		contentPane.add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 172, 410, 131);
		contentPane.add(scrollPane_1);
		
		tbDados = new JTable();
		tbDados.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"ID", "Procedimento", "Idade", "Sexo", "Permiss\u00E3o"
			}
		));
		scrollPane_1.setViewportView(tbDados);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Tela_de_regra exibir = new Tela_de_regra();
				exibir.setVisible(true); // torna a tela de cadastro visivel
				setVisible(false); //esconder a tela da guia 
				
				//JOptionPane.showMessageDialog(null, "Btn Cadastro");
			}
		});
		btnVoltar.setBounds(335, 19, 89, 23);
		contentPane.add(btnVoltar);
	}
}
