import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dialog.ModalityType;
import java.awt.Toolkit;


public class DialogSession extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JButton cancelButton;
	private JTextField txtStartwroclaw;
	private JPasswordField passwordField;

	

	/**
	 * Create the dialog.
	 */
	public DialogSession(DataBase _dataBase) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(DialogSession.class.getResource("/Resources/logo.png")));
		setModalityType(ModalityType.APPLICATION_MODAL);
		dataBase = _dataBase;
		setTitle("Logowanie");
		setBounds(100, 100, 337, 182);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			passwordField = new JPasswordField();
		}
		{
			txtStartwroclaw = new JTextField();
			txtStartwroclaw.setText("STARTWROCLAW");
			txtStartwroclaw.setColumns(10);
		}
		JLabel lblUytkownik = new JLabel("U\u017Cytkownik:");
		
		JLabel lblHaso = new JLabel("Has\u0142o:");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblUytkownik)
						.addComponent(lblHaso))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(passwordField, Alignment.LEADING)
						.addComponent(txtStartwroclaw, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtStartwroclaw, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUytkownik))
					.addGap(15)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHaso))
					.addContainerGap(132, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Zaloguj");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String username, password;
						username = txtStartwroclaw.getText();
						password = passwordField.getText();
						dataBase.getDBConnection(username, password);
						if(dataBase.isConnected())
						{
							dispose();
							WindowMain frame = new WindowMain(dataBase);
							frame.setVisible(true);
							
						}
						
						else
						{
							final JOptionPane info = new JOptionPane("B³êdne dane logowania! Spróbuj ponownie.");
							final JDialog dialog = info.createDialog((JFrame)null, "Odmowa dostêpu");
							dialog.setLocationRelativeTo(contentPanel);
							dialog.setVisible(true);
						}
							
						
						
						
					}
				});
				okButton.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Anuluj");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
			}
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addComponent(okButton, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(cancelButton, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
			);
			gl_buttonPane.setVerticalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addGap(5)
						.addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(okButton)
							.addComponent(cancelButton)))
			);
			buttonPane.setLayout(gl_buttonPane);
			passwordField.setText("polibuda8");
		}
	}
	
	DataBase dataBase;
}
