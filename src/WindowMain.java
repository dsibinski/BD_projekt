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
		setResizable(false);
		dataBase = _dataBase;
		setIconImage(Toolkit.getDefaultToolkit().getImage(WindowMain.class.getResource("/Resources/logo.png")));
		
		setTitle("START - Administracja");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 502, 399);
		
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
		
		JButton btnZarzdzajTrenerami = new JButton("Zarz\u0105dzaj trenerami");
		btnZarzdzajTrenerami.setToolTipText("");
		btnZarzdzajTrenerami.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							WindowDisplayTrainers frame = new WindowDisplayTrainers(dataBase);
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
		btnZarzdzajSekcjami.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							WindowDisplaySections frame = new WindowDisplaySections(dataBase);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}
		});
		
		JButton btnZarzdzajOrzeczeniami = new JButton("Zarz\u0105dzaj orzeczeniami");
		
		JButton btnZarzdzajSkadkami = new JButton("Zarz\u0105dzaj sk\u0142adkami");
		
		JButton btnZarzdzajPatnociami = new JButton("Zarz\u0105dzaj p\u0142atno\u015Bciami");
		
		JButton btnZarzdzajTreningami = new JButton("Zarz\u0105dzaj treningami");
		
		JButton btnZarzdzajTrofeami = new JButton("Zarz\u0105dzaj trofeami");
		
		JButton btnZarzdzajZawodami = new JButton("Zarz\u0105dzaj zawodami");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lbl_loggedass)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(btnZarzdzajSkadkami, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnZarzdzajSekcjami, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnZarzdzajZawodnikami, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnZarzdzajTrenerami, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)))
					.addGap(1)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnZarzdzajZawodami, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addComponent(btnZarzdzajPatnociami, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnZarzdzajTrofeami, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(btnZarzdzajTreningami, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED))))
					.addGap(183)
					.addComponent(btnZarzdzajOrzeczeniami, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
					.addGap(246))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lbl_loggedass, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnZarzdzajTrenerami, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnZarzdzajTreningami, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnZarzdzajZawodnikami, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnZarzdzajTrofeami, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnZarzdzajSekcjami, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnZarzdzajZawodami, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnZarzdzajSkadkami, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnZarzdzajOrzeczeniami)
							.addComponent(btnZarzdzajPatnociami, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(70, Short.MAX_VALUE))
		);
		lbl_loggedass.setText("Zalogowany jako: " + dataBase.getUsername());
		contentPane.setLayout(gl_contentPane);
		
		
		/*
		 * setting the users' privileges
		 */
		System.out.println("+"+dataBase.getUsername()+"+");
		if(dataBase.getUsername().equals("TRENER"))
		{
			
			btnZarzdzajSekcjami.setEnabled(false);
			btnZarzdzajSkadkami.setEnabled(false);
			btnZarzdzajPatnociami.setEnabled(false);
			btnZarzdzajZawodnikami.setEnabled(false);
			btnZarzdzajTrenerami.setEnabled(false);
			
		}
		
		if(dataBase.getUsername().equals("KSIEGOWY"))
		{
			btnZarzdzajTrenerami.setEnabled(false);
			btnZarzdzajSekcjami.setEnabled(false);
			btnZarzdzajZawodnikami.setEnabled(false);
			btnZarzdzajTreningami.setEnabled(false);
			btnZarzdzajTrofeami.setEnabled(false);
			btnZarzdzajZawodami.setEnabled(false);
			
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
