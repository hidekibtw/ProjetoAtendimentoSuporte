package br.com.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import br.com.dao.CRUDChamado;
import br.com.dominio.Chamado;
import java.awt.Cursor;
import javax.swing.JFormattedTextField;

public class ChamadoView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNome;
	private JTextField txtDepartamento;
	private JFormattedTextField txtDataAbertura;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChamadoView frame = new ChamadoView();
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
	public ChamadoView() {
		setBounds(100, 100, 551, 392);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Preencha todos os campos para efetuar um chamado");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(148, 11, 377, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Insira seu nome:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(148, 36, 94, 14);
		getContentPane().add(lblNewLabel_1);
		
		txtNome = new JTextField();
		txtNome.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtNome.setBounds(148, 61, 258, 20);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Informe com qual departamento deseja falar:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(148, 92, 258, 14);
		getContentPane().add(lblNewLabel_2);
		
		txtDepartamento = new JTextField();
		txtDepartamento.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtDepartamento.setBounds(148, 117, 258, 20);
		getContentPane().add(txtDepartamento);
		txtDepartamento.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Conte-nos mais sobre seu problema:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(148, 210, 206, 14);
		getContentPane().add(lblNewLabel_3);
		
		JTextArea txtDescricao = new JTextArea();
		txtDescricao.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtDescricao.setBounds(148, 235, 377, 107);
		getContentPane().add(txtDescricao);
		
		JLabel lblRegistrar = new JLabel("");
		lblRegistrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblRegistrar.setIcon(new ImageIcon(ChamadoView.class.getResource("/br/com/img/4781840_+_add_circle_create_expand_icon.png")));
		lblRegistrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Chamado soliChamado = new Chamado(); 
				CRUDChamado cc = new CRUDChamado();
				if(txtNome.getText().trim().equals("") || 
				txtDepartamento.getText().trim().equals("") ||
				txtDataAbertura.getText().trim().equals("") ||
				txtDescricao.getText().trim().equals("")) { 
				 JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos.", "Erro 4000765x" , JOptionPane.ERROR_MESSAGE); 
				} 
				else { 
					
				soliChamado.setSolicitacao(txtNome.getText());
				 
				soliChamado.setDepSolicitado(txtDepartamento.getText()); 
				
				soliChamado.setDataAbertura(txtDataAbertura.getText());
				
				soliChamado.setDescChamado(txtDescricao.getText()); 
				 
				 JOptionPane.showMessageDialog(null, cc.registrar(soliChamado)); 
				 
				}
			}
		});
		lblRegistrar.setBounds(39, 142, 64, 64);
		getContentPane().add(lblRegistrar);
		
		JLabel lblNewLabel_2_1 = new JLabel("Informe a data de abertura:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_1.setBounds(148, 148, 258, 14);
		getContentPane().add(lblNewLabel_2_1);
		
		MaskFormatter msf = null;
		try {
			msf = new MaskFormatter("##/##/####");
		} catch (Exception e) {
			e.printStackTrace();
		}
		txtDataAbertura = new JFormattedTextField(msf);
		txtDataAbertura.setBounds(148, 173, 258, 20);
		getContentPane().add(txtDataAbertura);
		
 
	}//Fim do Construtor
}//Fim do Código
