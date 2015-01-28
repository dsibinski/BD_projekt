import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import java.util.Vector;
import java.awt.Toolkit;


public class WindowSection extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public WindowSection(DataBase _dataBase) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(WindowSection.class.getResource("/Resources/logo.png")));
		setTitle("Sekcje");
		dataBase = _dataBase;
		rows = dataBase.getSectionsList();
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 972, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		scrollPane = new JScrollPane();
		
		btnNowaSekcja = new JButton("Nowa sekcja");
		
		btnZastosujZmiany = new JButton("Zastosuj zmiany");
		
		lblUsuTrenera = new JLabel("Usu\u0144 trenera:");
		
		comboBox = new JComboBox();
		
		button = new JButton("Usu\u0144");
		
		button_1 = new JButton("Od\u015Bwie\u017C");
		 gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 905, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnNowaSekcja)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnZastosujZmiany)
							.addPreferredGap(ComponentPlacement.RELATED, 246, Short.MAX_VALUE)
							.addComponent(lblUsuTrenera)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addGap(29)
							.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)))
					.addGap(18))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(4)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 336, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNowaSekcja)
						.addComponent(btnZastosujZmiany)
						.addComponent(lblUsuTrenera)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button)
						.addComponent(button_1))
					.addContainerGap(65, Short.MAX_VALUE))
		);
		
		
		scrollPane.setColumnHeaderView(table);
		contentPane.setLayout(gl_contentPane);
		
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("Nazwa");
		columnNames.add("Trener");
		
		
		table = new JTable(dataBase.getSectionsList(), columnNames);
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
		columnNames.add("Nazwa");
		columnNames.add("Trener");
		
		rows = dataBase.getSectionsList();
		
		table = new JTable(rows, columnNames);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		
		comboBox.removeAllItems();
		for (Vector v : rows)
		{
			comboBox.addItem(v.get(0) + " " + v.get(1) );
		}
	}
	
	
	DataBase dataBase;
	private JTable table;
	private JButton btnNowaSekcja;
	private JButton btnZastosujZmiany;
	private JLabel lblUsuTrenera;
	GroupLayout gl_contentPane;
	JScrollPane scrollPane;
	private JComboBox comboBox;
	private JButton button;
	private JButton button_1;
	Vector<Vector> rows;
}
