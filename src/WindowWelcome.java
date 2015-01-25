import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class WindowWelcome extends JFrame {

	private JPanel contentPane;
	public static DataBase dataBase;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		dataBase = new DataBase();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowWelcome frame = new WindowWelcome();
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
	public WindowWelcome() {
		setTitle("START WROC\u0141AW");
		setIconImage(Toolkit.getDefaultToolkit().getImage(WindowWelcome.class.getResource("/Resources/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 574, 263);
		
		
		contentPane = new JPanel();
		contentPane.setSize(100,100);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		
		
		String path = "logo_main.PNG";
        File file = new File(path);
        BufferedImage image = null;
        try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       JLabel label = new JLabel(new ImageIcon(image));
       
        
        JButton btnNewButton = new JButton("ZALOGUJ");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		try {
					DialogSession frame = new DialogSession(dataBase);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			
				
		  if(dataBase.isConnected())
		  {
			  setVisible(false);
		  }
		
        	}
        });
        btnNewButton.setBackground(SystemColor.info);
        
        JButton btnNewButton_1 = new JButton("O PROGRAMIE");
        btnNewButton_1.setBackground(SystemColor.info);
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGap(5)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        				.addComponent(label, GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
        				.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
        				.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 709, Short.MAX_VALUE)))
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addGap(5)
        			.addComponent(label)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
        			.addGap(26)
        			.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(78, Short.MAX_VALUE))
        );
        contentPane.setLayout(gl_contentPane);
        pack();
        setLocation(0,0);
        
        
		
		
		
		
	}
}
