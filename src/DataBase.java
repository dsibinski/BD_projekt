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
import java.sql.Date;
import java.util.Scanner;
import java.util.Vector;

public class DataBase {
	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521/XE";
	private static String DB_USER = "";
	private static String DB_PASSWORD = "";
	private static Connection dbConnection = null;
	private static Boolean connected = false;

	protected boolean isConnected() {
		return connected;
	}

	protected void SQLStatement() throws SQLException {

		Scanner input = new Scanner(System.in);

		Statement statement = null;

		String selectTableSQL = "DROP TABLE Nowa";

		Console cons = null;
		cons = System.console(); // console object for reading the password

		try {

			if (connected) {

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

					// System.out.println("Imie : " + name);
					// System.out.println("username : " + surname);
					System.out.println(name + " " + surname + " " + PESEL);
				}

				System.out.println("Zapytanie wykonane poprawnie.");

			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (statement != null) {
				statement.close();
			}

		}

	}

	protected void NewSection() throws SQLException {

		Scanner input3 = new Scanner(System.in);
		String nazwa, trener;
		nazwa = "";
		trener = "";
		System.out.print("\nNazwa sekcji: ");
		if (input3.hasNext())
			nazwa = input3.nextLine();

		System.out.print("\nID Trenera: ");
		if (input3.hasNext())
			trener = input3.nextLine();

		Statement statement = null;

		String selectTableSQL = "INSERT INTO Sekcje (id_sekcji, nazwa, trener) VALUES (SEKCJE_ID_SEQ.NEXTVAL,"
				+ "'" + nazwa + "'" + "," + trener + ")";

		Console cons = null;
		cons = System.console(); // console object for reading the password

		try {

			if (connected) {

				statement = dbConnection.createStatement();

				System.out.println(selectTableSQL);

				// execute select SQL statement
				ResultSet rs = statement.executeQuery(selectTableSQL);

				System.out.println("Zapytanie wykonane poprawnie.");
				// input3.close();
			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (statement != null) {
				statement.close();
			}

		}

	}

	/**
	 * 
	 * @throws SQLException
	 *             may return SQL exception
	 * @author DawidSibinski
	 * @return returns no value
	 */
	protected void NewTrainer(String imie, String nazwisko, String PESEL,
			Date data_urodzenia) throws SQLException {
		Scanner input = new Scanner(System.in);

		System.out.print("Imie: ");

		Statement statement = null;

		String selectTableSQL = "INSERT INTO TRENERZY (ID_TRENERA, IMIE, NAZWISKO, PESEL, DATA_URODZENIA) "
				+ "VALUES (TRENERZY_ID_SEQ.NEXTVAL, '"
				+ imie
				+ "', '"
				+ nazwisko + "', " + PESEL + ", '" + data_urodzenia + "')";

		try {

			if (connected) {

				statement = dbConnection.createStatement();

				System.out.println(selectTableSQL);

				// execute select SQL statement
				ResultSet rs = statement.executeQuery(selectTableSQL);

				System.out.println("Zapytanie wykonane poprawnie.");

			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (statement != null) {
				statement.close();
			}

		}

	}

	/**
	 * 
	 * @throws SQLException
	 *             may return SQL exception
	 * @author DawidSibinski
	 * @return returns no value
	 */
	protected void NewCertificate() throws SQLException {
		Scanner input = new Scanner(System.in);
		String stopien, numer, data_waznosci;

	}

	protected Connection getDBConnection(String _username, String _password) {

		DB_USER = _username;
		DB_PASSWORD = _password;

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
			System.out.println("Poprawnie po³¹czono z BD.");
			connected = true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
			if (e.getMessage().contains("ORA-01017")) // if error message is
														// incorrect credentials
			{
				connected = false;
				System.out
						.println("B£ÊDNE DANE LOGOWANIA. Sprobuj jeszcze raz.");

			}

		}

		return dbConnection;

	}

	protected Vector<Vector> getTrainersList() {
		Vector<Vector> rows = new Vector<Vector>();

		Statement statement = null;

		String selectTableSQL = "SELECT * FROM TRENERZY";

		try {

			if (connected) {

				statement = dbConnection.createStatement();

				System.out.println(selectTableSQL);

				// execute select SQL statement
				ResultSet rs = statement.executeQuery(selectTableSQL);

				while (rs.next()) {

					Vector<String> columns = new Vector<String>();

					
					String name = rs.getString("IMIE");
					String surname = rs.getString("NAZWISKO");
					String PESEL = rs.getString("PESEL");
					String DATA_URODZENIA = rs.getString("DATA_URODZENIA");
					DATA_URODZENIA = DATA_URODZENIA.substring(0, 10); // delete hour from data
					
					columns.add(name);
					columns.add(surname);
					columns.add(PESEL);
					columns.add(DATA_URODZENIA);
					rows.add(columns);

				}

				System.out.println("Zapytanie wykonane poprawnie.");

			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return rows;

	}

}
