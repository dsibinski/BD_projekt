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


public class WindowMain extends JFrame {

	private JPanel contentPane;
	public static DataBase dataBase;
    

    public static void main(String[] argv) {
    	dataBase = new DataBase();
    	
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
		setBounds(100, 100, 705, 460);
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
		
		
		
		
		final JButton btnDisplayTrainers = new JButton("Wy\u015Bwietl trener\u00F3w");
		btnDisplayTrainers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		btnDisplayTrainers.setEnabled(false);
		
		// przycisk: po³¹cz z BD
				JButton btnPoczZBd = new JButton("Po\u0142\u0105cz z BD");
				btnPoczZBd.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						
								try {
									DialogSession frame = new DialogSession(dataBase);
									frame.setVisible(true);
								} catch (Exception e) {
									e.printStackTrace();
								}
							
								
						if(dataBase.isConnected())
						{
							
							btnSQLStatement.setEnabled(true);
							btnNewSection.setEnabled(true);
							btnNewTrainer.setEnabled(true);
							btnDisplayTrainers.setEnabled(true);
							
							
						}
					}
				});
				
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnNewSection, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addContainerGap()
								.addComponent(btnPoczZBd, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(btnSQLStatement, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 217, Short.MAX_VALUE)
								.addComponent(btnDisplayTrainers))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addContainerGap()
								.addComponent(btnNewTrainer, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnPoczZBd, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSQLStatement, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDisplayTrainers, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addComponent(btnNewSection, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addComponent(btnNewTrainer, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(184, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
