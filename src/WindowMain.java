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
import java.sql.Date;
import java.util.Scanner;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Toolkit;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;


public class WindowMain extends JFrame {

	private JPanel contentPane;
	
	/**
	 * Create the frame.
	 */
	public WindowMain(DataBase _dataBase) {
		dataBase = _dataBase;
		setIconImage(Toolkit.getDefaultToolkit().getImage(WindowMain.class.getResource("/Resources/logo.png")));
		
		setTitle("START - Administracja");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 705, 460);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnPlik = new JMenu("Plik");
		menuBar.add(mnPlik);
		
		JMenuItem mntmZakocz = new JMenuItem("Zako\u0144cz");
		mntmZakocz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnPlik.add(mntmZakocz);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		final JButton btnNewSection = new JButton("Nowa sekcja");
		btnNewSection.setEnabled(false);
		btnNewSection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					dataBase.NewSection();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		final JButton btnSQLStatement = new JButton("Polecenie SQL");
		btnSQLStatement.setEnabled(false);
		btnSQLStatement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dataBase.SQLStatement();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		final JButton btnNewTrainer = new JButton("Nowy trener");
		btnNewTrainer.setEnabled(false);
		btnNewTrainer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}
		});
		
		JButton btnZarzdzajTrenerami = new JButton("Zarz\u0105dzaj trenerami");
		btnZarzdzajTrenerami.setToolTipText("");
		btnZarzdzajTrenerami.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							WindowsDisplayTrainers frame = new WindowsDisplayTrainers(dataBase);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		
		JLabel lbl_loggedass = new JLabel("Zalogowany jako: ");
		lbl_loggedass.setBackground(SystemColor.activeCaption);
		lbl_loggedass.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton btnZarzdzajZawodnikami = new JButton("Zarz\u0105dzaj zawodnikami");
		
		JButton btnZarzdzajSekcjami = new JButton("Zarz\u0105dzaj sekcjami");
		
		JButton btnZarzdzajOrzeczeniami = new JButton("Zarz\u0105dzaj orzeczeniami");
		
		JButton btnZarzdzajSkadkami = new JButton("Zarz\u0105dzaj sk\u0142adkami");
		
		JButton btnZarzdzajPatnociami = new JButton("Zarz\u0105dzaj p\u0142atno\u015Bciami");
		
		JButton btnZarzdzajSprawozdaniami = new JButton("Zarz\u0105dzaj sprawozdaniami");
		
		JButton btnZarzdzajTreningami = new JButton("Zarz\u0105dzaj treningami");
		
		JButton btnZarzdzajTrofeami = new JButton("Zarz\u0105dzaj trofeami");
		
		JButton btnZarzdzajZawodami = new JButton("Zarz\u0105dzaj zawodami");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewSection, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbl_loggedass)
						.addComponent(btnNewTrainer, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSQLStatement, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnZarzdzajSprawozdaniami)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnZarzdzajOrzeczeniami, 0, 0, Short.MAX_VALUE)
								.addComponent(btnZarzdzajZawodnikami, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
								.addComponent(btnZarzdzajTrenerami, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnZarzdzajSekcjami, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnZarzdzajZawodami)
								.addComponent(btnZarzdzajTrofeami)
								.addComponent(btnZarzdzajTreningami)))
						.addComponent(btnZarzdzajSkadkami)
						.addComponent(btnZarzdzajPatnociami))
					.addGap(128))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(7)
							.addComponent(lbl_loggedass, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnZarzdzajTreningami)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(14)
									.addComponent(btnNewSection, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnZarzdzajTrofeami)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnZarzdzajZawodami))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(43)
							.addComponent(btnZarzdzajTrenerami, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnZarzdzajZawodnikami)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewTrainer, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnZarzdzajSekcjami))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSQLStatement, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnZarzdzajOrzeczeniami)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnZarzdzajSkadkami)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnZarzdzajPatnociami)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnZarzdzajSprawozdaniami)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		lbl_loggedass.setText("Zalogowany jako: " + dataBase.getUsername());
		contentPane.setLayout(gl_contentPane);
		
		if(dataBase.isConnected())
		{
			
			btnSQLStatement.setEnabled(true);
			btnNewSection.setEnabled(true);
			btnNewTrainer.setEnabled(true);
			
			
			
		}
	}
	DataBase dataBase;
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
