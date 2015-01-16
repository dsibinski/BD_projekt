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


public class WindowsDisplayTrainers extends JFrame {

	private JPanel contentPane;



	/**
	 * Create the frame.
	 */
	public WindowsDisplayTrainers(DataBase _dataBase) {
		setTitle("Trenerzy");
		dataBase = _dataBase;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 699, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 651, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(26, Short.MAX_VALUE))
		);
		
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("Imie");
		columnNames.add("Nazwisko");
		columnNames.add("PESEL");
		columnNames.add("Data urodzenia");
		
		table = new JTable(dataBase.getTrainersList(), columnNames);
		
		scrollPane.setViewportView(table);
		scrollPane.setColumnHeaderView(table);
		contentPane.setLayout(gl_contentPane);
		
		
		
	}
	
	DataBase dataBase;
	private JTable table;
	
	
}
