import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


public class WindowMain extends JFrame {

	private JPanel contentPane;
	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521/XE";
	private static String DB_USER = "";
	private static String DB_PASSWORD = "";
    private static Boolean connected = false;
    

    public static void main(String[] argv) {
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowMain frame = new WindowMain();
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
	public WindowMain() {
		
		setTitle("START - aplikacja bazodanowa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 467, 364);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnDagusia = new JButton("Nowa sekcja");
		btnDagusia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					NewSection();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		JButton btnNewButton = new JButton("Polecenie SQL");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SQLStatement();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnNewButton_1 = new JButton("Nowy trener");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					NewTrainer();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(48)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
							.addGap(83)
							.addComponent(btnDagusia, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(151)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(54, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(106)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDagusia, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
					.addGap(50)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(55, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	protected static void SQLStatement() throws SQLException {
		 
		Scanner input = new Scanner(System.in);
		Connection dbConnection = null;
		Statement statement = null;
		
	   String selectTableSQL = "DROP TABLE Nowa";
		
		Console cons = null;
		cons = System.console(); // console object for reading the password
		
		
		System.out.print("\nNazwa uzytkownika: ");
		DB_USER = input.nextLine();
		System.out.print("\nHaslo: ");
		DB_PASSWORD = input.nextLine();
		
		try {
			dbConnection = getDBConnection();
			if(connected)
				{
				
				
				
				
				System.out.println("POLACZONO Z BD (" + DB_USER + ")");
				System.out.print("\nSQL>> ");
				selectTableSQL = input.nextLine();
				
				statement = dbConnection.createStatement();
 
				
				System.out.println(selectTableSQL);
 
				// execute select SQL statement
				ResultSet rs = statement.executeQuery(selectTableSQL);
				
			
				while (rs.next()) {
				
					String name = rs.getString("IMIE");
					String surname = rs.getString("NAZWISKO");
					String PESEL = rs.getString("PESEL");
 
					//System.out.println("Imie : " + name);
					//System.out.println("username : " + surname);
					System.out.println(name +" "+  surname + " " + PESEL);
				}
				
				System.out.println("Zapytanie wykonane poprawnie.");
			
			}
 
		} catch (SQLException e) {
 
			System.out.println(e.getMessage());
 
			
			
		} finally {
 
			if (statement != null) {
				statement.close();
			}
 
			if (dbConnection != null) {
				dbConnection.close();
				System.out.println("ROZLACZONO Z BD (" + DB_USER + ")");
			}
 
		}
		
		
 
	}
	
	private static void NewSection() throws SQLException {
		 
		Scanner input3 = new Scanner(System.in);
		String nazwa, trener;
		nazwa = "";
		trener = "";
		System.out.print("\nNazwa sekcji: ");
		if(input3.hasNext()) nazwa = input3.nextLine();
		
		System.out.print("\nID Trenera: ");
		if(input3.hasNext()) trener = input3.nextLine();
		
		
		
		
		Connection dbConnection = null;
		Statement statement = null;
		
		String selectTableSQL = "INSERT INTO Sekcje (id_sekcji, nazwa, trener) VALUES (SEKCJE_ID_SEQ.NEXTVAL,"
				+ "'" + nazwa + "'"+ "," + trener + ")";
		
		Console cons = null;
		cons = System.console(); // console object for reading the password
		
		
		System.out.print("\nNazwa uzytkownika: ");
		DB_USER = input3.nextLine();
		System.out.print("\nHaslo: ");
		DB_PASSWORD = input3.nextLine();
		
		try {
			dbConnection = getDBConnection();
			if(connected)
				{
				
				
				
				
				System.out.println("POLACZONO Z BD (" + DB_USER + ")");
				
				
				statement = dbConnection.createStatement();
 
				
				System.out.println(selectTableSQL);
 
				// execute select SQL statement
				ResultSet rs = statement.executeQuery(selectTableSQL);
				
				
			
				
				
				System.out.println("Zapytanie wykonane poprawnie.");
			//input3.close();
			}
 
		} catch (SQLException e) {
 
			System.out.println(e.getMessage());
 
			
			
		} finally {
 
			if (statement != null) {
				statement.close();
			}
 
			if (dbConnection != null) {
				dbConnection.close();
				System.out.println("ROZLACZONO Z BD (" + DB_USER + ")");
			}
 
		}
		
		
 
	}
	
	
	/**
	 * 
	 * @throws SQLException may return SQL exception
	 * @author DawidSibinski
	 * @return returns no value
	 */
	private static void NewTrainer() throws SQLException {
		Scanner input = new Scanner(System.in);
		
		String imie, nazwisko, adres, email;
		long PESEL;
		
		System.out.print("Imie: ");
		
	}
		
	
 
	private static Connection getDBConnection() {
 
		Connection dbConnection = null;
 
		try {
 
			Class.forName(DB_DRIVER);
 
		} catch (ClassNotFoundException e) {
 
			System.out.println(e.getMessage());
 
		}
 
		try {
			
 
			/*
			 * opens a DB connection
			*/
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,
					DB_PASSWORD);
			connected = true;
			return dbConnection;
			
 
		} catch (SQLException e) {
 
			
			System.out.println(e.getMessage());
			if(e.getMessage().contains("ORA-01017")) // if error message is 
													  // incorrect  credentials
			{
				connected = false;
				System.out.println("B£ÊDNE DANE LOGOWANIA. Sprobuj jeszcze raz.");
				
			}
			
			
			
		}
 
		return dbConnection;
 
	}
	
	
}
