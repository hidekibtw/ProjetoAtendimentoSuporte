package br.com.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
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

public class Atendimento extends JFrame {
	private JTextField txtId;
	private JTextField txtResponsavel;
	private JFormattedTextField txtDataResolucao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Atendimento frame = new Atendimento();
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
	public Atendimento() {
		setBounds(100, 100, 588, 447);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Status Chamado:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(378, 35, 105, 14);
		getContentPane().add(lblNewLabel);

		JLabel lblResponsvelDoChamado = new JLabel("Responsável do Chamado:");
		lblResponsvelDoChamado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblResponsvelDoChamado.setBounds(378, 137, 148, 14);
		getContentPane().add(lblResponsvelDoChamado);

		JLabel lblIdDoChamado = new JLabel("ID do Chamado:");
		lblIdDoChamado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblIdDoChamado.setBounds(137, 35, 105, 14);
		getContentPane().add(lblIdDoChamado);

		JLabel lblChamado = new JLabel(" Data de Resolução do Chamado:");
		lblChamado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblChamado.setBounds(137, 137, 184, 14);
		getContentPane().add(lblChamado);

		JLabel lblObservaes = new JLabel("Observações:");
		lblObservaes.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblObservaes.setBounds(310, 199, 105, 14);
		getContentPane().add(lblObservaes);

		txtId = new JTextField();
		txtId.setBounds(137, 60, 184, 20);
		getContentPane().add(txtId);
		txtId.setColumns(10);

		txtResponsavel = new JTextField();
		txtResponsavel.setColumns(10);
		txtResponsavel.setBounds(378, 162, 184, 20);
		getContentPane().add(txtResponsavel);

		JTextArea txtObservacoes = new JTextArea();
		txtObservacoes.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtObservacoes.setBounds(137, 224, 425, 173);
		getContentPane().add(txtObservacoes);
		
		JComboBox cboStatus = new JComboBox();
		cboStatus.setModel(new DefaultComboBoxModel(new String[] { "Em Aberto", "Pendente", "Fechado" }));
		cboStatus.setBounds(378, 59, 184, 22);
		getContentPane().add(cboStatus);

		JLabel lblEditar = new JLabel("");
		lblEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Chamado cr = new Chamado();
				CRUDChamado cc = new CRUDChamado();

				if (txtResponsavel.getText().trim().equals("") || txtId.getText().trim().equals("")
						|| txtDataResolucao.getText().trim().equals("") 
					|| cboStatus.getSelectedItem().equals("")){
					JOptionPane.showMessageDialog(null,
							"Os campos Responsável Chamado, Id do Chamado, Status do Chamado e Data de Resolução devem ser preenchidos",
							"Erro 4000765x", JOptionPane.ERROR_MESSAGE);
				} else {

					cr.setDataResolucao(txtDataResolucao.getText());
					cr.setStatusChamado(cboStatus.getSelectedItem().toString());
					cr.setAtendente(txtResponsavel.getText());
					cr.setObservacoes(txtObservacoes.getText());
					cr.setIdChamado(Long.parseLong(txtId.getText()));
					cc.atualizar(cr);
					JOptionPane.showMessageDialog(null, "Chamado Atualizado");
				}
			}
		});
		lblEditar.setToolTipText("Editar Chamado");
		lblEditar.setIcon(new ImageIcon(Atendimento.class.getResource("/br/com/img/editar.png")));
		lblEditar.setBounds(30, 137, 64, 64);
		getContentPane().add(lblEditar);

		JLabel lblExcluir = new JLabel("");
		lblExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblExcluir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CRUDChamado cc = new CRUDChamado();
				if (txtId.equals(null)) {
					JOptionPane.showMessageDialog(null, "Selecione o chamado a ser excluído.", "Erro 4000770x",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (JOptionPane.showConfirmDialog(null,
							"Você tem certeza desta ação? \nEstá ação é permanente " + "e não pode ser desfeita",
							"ATENÇÃO", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
						Chamado cr = new Chamado();
						cr.setIdChamado(Long.parseLong(txtId.getText()));
						JOptionPane.showMessageDialog(null, cc.apagar(cr));
					}

				}
			}
		});
		lblExcluir.setIcon(new ImageIcon(Atendimento.class.getResource("/br/com/img/excluir.png")));
		lblExcluir.setToolTipText("Excluir Chamado");
		lblExcluir.setBounds(30, 212, 64, 64);
		getContentPane().add(lblExcluir);

		

		MaskFormatter msf = null;
		try {
			msf = new MaskFormatter("##/##/####");
		} catch (Exception e) {
			e.printStackTrace();
		}
		txtDataResolucao = new JFormattedTextField(msf);
		txtDataResolucao.setBounds(137, 162, 184, 20);
		getContentPane().add(txtDataResolucao);

	}
}
