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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class WindowSection extends JFrame {

	private JPanel contentPane;
	private JTextField txtNazwaSekcji;
	private static boolean filled;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public WindowSection(DataBase _dataBase, String[] row) {
		if (row != null)
			filled = true;
		else
			filled = false;
		
		if (row != null)
			nazwa_old = row[0];
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dataBase = _dataBase;
		
		
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(WindowSection.class.getResource("/Resources/logo.png")));
		setTitle("Dodawanie sekcji");
		setBounds(100, 100, 316, 206);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNazwaSekcji = new JLabel("Nazwa sekcji:");
		
		txtNazwaSekcji = new JTextField();
		txtNazwaSekcji.setColumns(10);
		
		JLabel lblTrener = new JLabel("Wybierz trenera:");
		
		comboBox = new JComboBox();
		comboBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				refreshComboBox();
			}
		});
		
		JButton btnDodajTrenera = new JButton("Nowy trener");
		btnDodajTrenera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				WindowTrainer frame = new WindowTrainer(dataBase, null);
				frame.setVisible(true);
			}
		});
		
		JButton btnDodajSekcje;
		btnDodajSekcje = new JButton("Dodaj");
		if (filled)
			btnDodajSekcje = new JButton("Edytuj");
		
		
		btnDodajSekcje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nazwa_sekcji = txtNazwaSekcji.getText();
				String trener = comboBox.getSelectedItem().toString();
				String[] trener_data = trener.split(" ");
				String trener_imie, trener_nazwisko;
				trener_imie = trener_data[0];
				trener_nazwisko = trener_data[1];
				
				if(!filled) dataBase.NewSection(nazwa_sekcji, trener_imie, trener_nazwisko);
				else dataBase.updateSections(nazwa_sekcji, nazwa_old, trener_imie, trener_nazwisko);
				dispose();
				
			}
		});
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
		
		Vector<Vector> values = dataBase.getTrainersNames();
		
		// adds trainers' data to comboBox selections
		for (Vector v : values)
		{
			comboBox.addItem(v.elementAt(0).toString());
			
		}
		
		if(filled)
		{
			txtNazwaSekcji.setText(row[0]);
			comboBox.setSelectedItem(row[1]);
		}
		
		contentPane.setLayout(gl_contentPane);
	}
	
	void refreshComboBox()
	{
		comboBox.removeAllItems();
		Vector<Vector> values = dataBase.getTrainersNames();
		
		// adds trainers' data to comboBox selections
		for (Vector v : values)
		{
			comboBox.addItem(v.elementAt(0).toString());
		}
	}
	JComboBox comboBox;
	DataBase dataBase;
	String nazwa_old;
}
