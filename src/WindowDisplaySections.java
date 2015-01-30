import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;

import java.util.Vector;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;


public class WindowDisplaySections extends JFrame {

	private JPanel contentPane;
	TableRowSorter sorter;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	
	DefaultTableModel tableModel;
	
	public WindowDisplaySections(DataBase _dataBase) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(WindowDisplaySections.class.getResource("/Resources/logo.png")));
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
		btnNowaSekcja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							WindowSection frame = new WindowSection(dataBase, null);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}
		});
		
		btnEdytuj = new JButton("Edytuj ");
		btnEdytuj.setEnabled(false);
		btnEdytuj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row_nr = table.getSelectedRow();
				final String row[] = new String[2];
				
				
				for(int i = 0; i < row.length; i++)
				{
					row[i] = table.getValueAt(row_nr, i).toString();
				}
					
				WindowSection frame = new WindowSection(dataBase, row);
				frame.setVisible(true);
				
				
				
				
			}
		});
		
		btnUsunSekcje = new JLabel("Usu\u0144 sekcj\u0119:");
		
		comboBox = new JComboBox();
		
		button = new JButton("Usu\u0144");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nazwa = comboBox.getSelectedItem().toString();
				dataBase.deleteSection(nazwa);
				refreshTable();
			}
		});
		
		button_1 = new JButton("Od\u015Bwie\u017C");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshTable();
			}
		});
		 
		 txtNazwisko = new JTextField();
		 txtNazwisko.setColumns(10);
		 
		 JLabel label = new JLabel("Wyszukaj:");
		 gl_contentPane = new GroupLayout(contentPane);
		 gl_contentPane.setHorizontalGroup(
		 	gl_contentPane.createParallelGroup(Alignment.LEADING)
		 		.addGroup(gl_contentPane.createSequentialGroup()
		 			.addGap(21)
		 			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
		 				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 905, GroupLayout.PREFERRED_SIZE)
		 				.addGroup(gl_contentPane.createSequentialGroup()
		 					.addComponent(btnNowaSekcja)
		 					.addPreferredGap(ComponentPlacement.UNRELATED)
		 					.addComponent(btnEdytuj)
		 					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		 					.addComponent(label, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
		 					.addPreferredGap(ComponentPlacement.UNRELATED)
		 					.addComponent(txtNazwisko, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
		 					.addPreferredGap(ComponentPlacement.UNRELATED)
		 					.addComponent(btnUsunSekcje)
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
		 				.addComponent(btnEdytuj)
		 				.addComponent(btnUsunSekcje)
		 				.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		 				.addComponent(button)
		 				.addComponent(button_1)
		 				.addComponent(txtNazwisko, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		 				.addComponent(label))
		 			.addContainerGap(54, Short.MAX_VALUE))
		 );
		
		
		scrollPane.setColumnHeaderView(table);
		contentPane.setLayout(gl_contentPane);
		
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("Nazwa");
		columnNames.add("Trener");
		
		tableModel = new DefaultTableModel(columnNames, 0) {

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		rows = dataBase.getSectionsList();
		
		
		for (int i = 0; i < rows.size(); i++)
		{
			tableModel.addRow(rows.elementAt(i));
		}
		
		
		table = new JTable(tableModel);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnEdytuj.setEnabled(true);
			}
		});
		
		table.setCellSelectionEnabled(false);
		
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		comboBox.removeAllItems();
		
		for (Vector v : rows)
		{
			comboBox.addItem(v.get(0));
		}
		
		sortowanie(table);
	}
	
	
	
	// =======================================================================
		// ============== 		Sortowanie i filtrowanie		 =================
		// =======================================================================
		private void sortowanie(JTable table){
			TableModel model = table.getModel();
			sorter = new TableRowSorter<TableModel>(model);
			
			
			table.setRowSorter(sorter);
			txtNazwisko.getDocument().addDocumentListener(new DocumentListener() {

				@Override
				public void removeUpdate(DocumentEvent e) {
					newFilter();
				}

				@Override
				public void insertUpdate(DocumentEvent e) {
					newFilter();
				}

				@Override
				public void changedUpdate(DocumentEvent e) {
					newFilter();
				}
			});
		}
		
		// filtry - wyszukiwanie imienia
				private void newFilter() {
					RowFilter<TableModel, Object> rf = null;
					////////// wyszukiwanie frazy w kolumnach: 0, 1 <- czyli wszystkich 
					try {
						rf = RowFilter.regexFilter(txtNazwisko.getText(), 0, 1);
					} catch (java.util.regex.PatternSyntaxException e) {
						return;
					}

					sorter.setRowFilter(rf);
				}
				
				
		
	void refreshTable()
	{
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("Nazwa");
		columnNames.add("Trener");
		
		tableModel = new DefaultTableModel(columnNames, 0) {

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		
		rows = dataBase.getSectionsList();
		
		for (int i = 0; i < rows.size(); i++)
		{
			tableModel.addRow(rows.elementAt(i));
		}
		table = new JTable(tableModel);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnEdytuj.setEnabled(true);
			}
		});
		
		
		table.setCellSelectionEnabled(false);
		
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);

		comboBox.removeAllItems();
		for (Vector v : rows) {
			comboBox.addItem(v.get(0));
		}
		sortowanie(table);
	}
	
	
	DataBase dataBase;
	private JTable table;
	private JButton btnNowaSekcja;
	private JButton btnEdytuj;
	private JLabel btnUsunSekcje;
	GroupLayout gl_contentPane;
	JScrollPane scrollPane;
	private JComboBox comboBox;
	private JButton button;
	private JButton button_1;
	Vector<Vector> rows;
	private JTextField txtNazwisko;
}
