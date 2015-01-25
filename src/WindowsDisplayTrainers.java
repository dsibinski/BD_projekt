import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class WindowsDisplayTrainers extends JFrame {

	private JPanel contentPane;



	/**
	 * Create the frame.
	 */
	public WindowsDisplayTrainers(DataBase _dataBase) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(WindowsDisplayTrainers.class.getResource("/Resources/logo.png")));
		setTitle("Trenerzy");
		dataBase = _dataBase;
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 699, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
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
				Vector<String> columnNames = new Vector<String>();
				columnNames.add("Imie");
				columnNames.add("Nazwisko");
				columnNames.add("PESEL");
				columnNames.add("Data urodzenia");
				
				table = new JTable(dataBase.getTrainersList(), columnNames);
				
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 651, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnNowyTrener)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnOdwie)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 316, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNowyTrener)
						.addComponent(btnOdwie))
					.addContainerGap(69, Short.MAX_VALUE))
		);
		
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("Imie");
		columnNames.add("Nazwisko");
		columnNames.add("PESEL");
		columnNames.add("Data urodzenia");
		
		
		
		
		
		table = new JTable(dataBase.getTrainersList(), columnNames);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		
		
	}
	JTable table;
	DataBase dataBase;
	
	
	
}
