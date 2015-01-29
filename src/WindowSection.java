import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;


public class WindowSection extends JFrame {

	private JPanel contentPane;
	private JTextField txtNazwaSekcji;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public WindowSection(DataBase _dataBase) {
		dataBase = _dataBase;
		
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(WindowSection.class.getResource("/Resources/logo.png")));
		setTitle("Dodawanie sekcji");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 316, 206);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNazwaSekcji = new JLabel("Nazwa sekcji:");
		
		txtNazwaSekcji = new JTextField();
		txtNazwaSekcji.setColumns(10);
		
		JLabel lblTrener = new JLabel("Wybierz trenera:");
		
		JComboBox comboBox = new JComboBox();
		
		JButton btnDodajTrenera = new JButton("Nowy trener");
		
		JButton btnDodajSekcje = new JButton("Dodaj sekcj\u0119");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnDodajSekcje, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNazwaSekcji)
								.addComponent(lblTrener))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnDodajTrenera, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(comboBox, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtNazwaSekcji, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNazwaSekcji)
						.addComponent(txtNazwaSekcji, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTrener)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDodajTrenera)
					.addGap(18)
					.addComponent(btnDodajSekcje, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
		);
		
		Vector<Vector> values = dataBase.getSectionsList();
		
		// adds trainers' data to comboBox selections
		for (Vector v : values)
		{
			comboBox.addItem(v.elementAt(1));
		}
		
		contentPane.setLayout(gl_contentPane);
	}
	
	DataBase dataBase;
}
