import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.math.BigDecimal;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.AbstractTableModel;

import java.awt.Toolkit;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.FilteredImageSource;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WindowsDisplayTrainers extends JFrame {

	private JPanel contentPane;
	TableRowSorter sorter;

	/**
	 * œ c Create the frame.
	 */
	
	DefaultTableModel tableModel;
	
	public WindowsDisplayTrainers(DataBase _dataBase) {
		
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(
						WindowsDisplayTrainers.class
								.getResource("/Resources/logo.png")));
		setTitle("Trenerzy");
		dataBase = _dataBase;
		rows = dataBase.getTrainersList();

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1139, 496);
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
							WindowTrainer frame = new WindowTrainer(dataBase, null);
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

		btnZastosujZmiany = new JButton("Edytuj");
		btnZastosujZmiany.setEnabled(false);
		btnZastosujZmiany.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row_nr = table.getSelectedRow();
				final String row[] = new String[7];
				
				
				for(int i = 0; i < row.length; i++)
				{
					row[i] = table.getValueAt(row_nr, i).toString();
				}
				
				
						
							WindowTrainer frame = new WindowTrainer(dataBase, row);
							frame.setVisible(true);
						
				
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
				if (!deleted_ok) {
					final JOptionPane info = new JOptionPane(
							"Trener jest przypisany do sekcji. Najpierw przypisz do sekcji innego trenera.");
					final JDialog dialog = info.createDialog((JFrame) null,
							"Odmowa dostêpu");
					dialog.setLocationRelativeTo(contentPane);
					dialog.setVisible(true);
				} else {
					final JOptionPane info = new JOptionPane("Trener usuniety.");
					final JDialog dialog = info.createDialog((JFrame) null,
							"Potwierdzenie");
					dialog.setLocationRelativeTo(contentPane);
					dialog.setVisible(true);
					refreshTable();
				}
			}
		});

		txtNazwisko = new JTextField();
		//txtNazwisko.setText("nazwisko");
		txtNazwisko.setColumns(10);
		
		JLabel lblWyszukaj = new JLabel("Wyszukaj:");
		gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 974, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnNowyTrener)
							.addGap(18)
							.addComponent(btnZastosujZmiany)
							.addGap(90)
							.addComponent(lblWyszukaj)
							.addGap(18)
							.addComponent(txtNazwisko)
							.addGap(45)
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
						.addComponent(lblUsuTrenera)
						.addComponent(txtNazwisko, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWyszukaj))
					.addContainerGap(100, Short.MAX_VALUE))
		);

		Vector<String> columnNames = new Vector<String>();
		columnNames.add("Imie");
		columnNames.add("Nazwisko");
		columnNames.add("PESEL");
		columnNames.add("Data urodzenia");
		columnNames.add("Adres");
		columnNames.add("Nr telefonu");
		columnNames.add("Email");

		 tableModel = new DefaultTableModel(columnNames, 0) {

			    @Override
			    public boolean isCellEditable(int row, int column) {
			       //all cells false
			       return false;
			    }
			};
			
			
		
	
		rows = dataBase.getTrainersList();

		
		
		for (int i = 0; i < rows.size(); i++)
		{
			tableModel.addRow(rows.elementAt(i));
		}
		
		table = new JTable(tableModel);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnZastosujZmiany.setEnabled(true);
			}
		});
		
		
		
		
		
	
		
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		comboBox.removeAllItems();
		
		/**table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            // do some actions here, for example
	            // print first column value from selected row
	            System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
	        }
	    });*/
		
		
		for (Vector v : rows) {
			comboBox.addItem(v.get(0) + " " + v.get(1));
		}

	
		sortowanie(table);

	}

	void refreshTable() {
		
		
		
		
		
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("Imie");
		columnNames.add("Nazwisko");
		columnNames.add("PESEL");
		columnNames.add("Data urodzenia");
		columnNames.add("Adres");
		columnNames.add("Nr telefonu");
		columnNames.add("Email");

		tableModel = new DefaultTableModel(columnNames, 0) {

		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		rows = dataBase.getTrainersList();
        
		
		
		
		
		
		
		
		
		for (int i = 0; i < rows.size(); i++)
		{
			tableModel.addRow(rows.elementAt(i));
		}
		table = new JTable(tableModel);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnZastosujZmiany.setEnabled(true);
			}
		});
		
		
		table.setCellSelectionEnabled(false);
		
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);

		comboBox.removeAllItems();
		for (Vector v : rows) {
			comboBox.addItem(v.get(0) + " " + v.get(1));
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
			////////// wyszukiwanie frazy w kolumnach: 0, 1, 2, 3, 4, 5, 6 <- czyli wszystkich 
			try {
				rf = RowFilter.regexFilter(txtNazwisko.getText(), 0, 1, 2, 3, 4, 5, 6);
			} catch (java.util.regex.PatternSyntaxException e) {
				return;
			}

			sorter.setRowFilter(rf);
		}
		
		
	

	JTable table;
	final JButton btnZastosujZmiany;
	JScrollPane scrollPane;
	GroupLayout gl_contentPane;
	JComboBox comboBox;
	DataBase dataBase;
	Vector<Vector> rows;
	private JTextField txtNazwisko;
}
