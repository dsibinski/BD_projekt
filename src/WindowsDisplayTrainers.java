import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Toolkit;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JComboBox;


public class WindowsDisplayTrainers extends JFrame {

	private JPanel contentPane;



	/**
	 * Create the frame.
	 */
	public WindowsDisplayTrainers(DataBase _dataBase) {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(WindowsDisplayTrainers.class.getResource("/Resources/logo.png")));
		setTitle("Trenerzy");
		dataBase = _dataBase;
		rows = dataBase.getTrainersList();
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1000, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		scrollPane = new JScrollPane();
		
		JButton btnNowyTrener = new JButton("Nowy trener");
		btnNowyTrener.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							WindowTrainer frame = new WindowTrainer(dataBase);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		
		JButton btnOdwie = new JButton("Od\u015Bwie\u017C");
		btnOdwie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			refreshTable();
				
			}
		});
		
		JButton btnZastosujZmiany = new JButton("Zastosuj zmiany");
		btnZastosujZmiany.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		JLabel lblUsuTrenera = new JLabel("Usu\u0144 trenera:");
		
		comboBox = new JComboBox();
		
		JButton btnUsu = new JButton("Usu\u0144");
		btnUsu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String data, name, surname;
				data = (String) comboBox.getSelectedItem();
				String[] name_surname = data.split(" ");
				name = name_surname[0];
				surname = name_surname[1];
				boolean deleted_ok = dataBase.deleteTrainer(name, surname);
				if(!deleted_ok)
				{
					final JOptionPane info = new JOptionPane("Trener jest przypisany do sekcji. Najpierw przypisz do sekcji innego trenera.");
					final JDialog dialog = info.createDialog((JFrame)null, "Odmowa dostêpu");
					dialog.setLocationRelativeTo(contentPane);
					dialog.setVisible(true);
				}
				else
				{
					final JOptionPane info = new JOptionPane("Trener usuniety.");
					final JDialog dialog = info.createDialog((JFrame)null, "Potwierdzenie");
					dialog.setLocationRelativeTo(contentPane);
					dialog.setVisible(true);
					refreshTable();
				}
			}
		});
		gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 972, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnNowyTrener)
							.addGap(18)
							.addComponent(btnZastosujZmiany)
							.addPreferredGap(ComponentPlacement.RELATED, 272, Short.MAX_VALUE)
							.addComponent(lblUsuTrenera)
							.addGap(27)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnUsu, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addGap(31)
							.addComponent(btnOdwie))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 316, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnZastosujZmiany)
						.addComponent(btnNowyTrener)
						.addComponent(btnOdwie)
						.addComponent(btnUsu)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUsuTrenera))
					.addContainerGap(84, Short.MAX_VALUE))
		);
		
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("Imie");
		columnNames.add("Nazwisko");
		columnNames.add("PESEL");
		columnNames.add("Data urodzenia");
		columnNames.add("Adres");
		columnNames.add("Nr telefonu");
		columnNames.add("Email");
		
		
		table = new JTable(dataBase.getTrainersList(), columnNames);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		comboBox.removeAllItems();
		for (Vector v : rows)
		{
			comboBox.addItem(v.get(0) + " " + v.get(1) );
		}
		
		
	}
	
	void refreshTable()
	{
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("Imie");
		columnNames.add("Nazwisko");
		columnNames.add("PESEL");
		columnNames.add("Data urodzenia");
		columnNames.add("Adres");
		columnNames.add("Nr telefonu");
		columnNames.add("Email");
		
		rows = dataBase.getTrainersList();
		
		table = new JTable(rows, columnNames);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		
		comboBox.removeAllItems();
		for (Vector v : rows)
		{
			comboBox.addItem(v.get(0) + " " + v.get(1) );
		}
	}
	JTable table;
	JScrollPane scrollPane;
	GroupLayout gl_contentPane;
	JComboBox comboBox;
	DataBase dataBase;
	Vector<Vector> rows;
}
