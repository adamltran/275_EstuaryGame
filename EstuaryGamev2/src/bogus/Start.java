package bogus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Start extends JFrame {

	private JPanel contentPane;
	private Controller controller;
	private BufferedImage backg = createImage(new File("Background/Background_05.png"));

	public Start(Controller controller) {
		
		this.controller = controller;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		contentPane = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(backg, 0, 0, getWidth(), getHeight(), this);
			}
		};
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		contentPane.add(new TitleLayout());
		
//		JLabel lblNewLabel = new JLabel("Who's Meal Is It Anyway?");
//		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 110));
//		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
//		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
//		gbc_lblNewLabel.gridx = 4;
//		gbc_lblNewLabel.gridy = 2;
//		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
//		JButton btnEasyGame = new JButton("Start Game");
//		btnEasyGame.setFont(new Font("Tahoma", Font.BOLD, 50));
//		btnEasyGame.setPreferredSize(new Dimension(450, 200));
//		btnEasyGame.addActionListener(startButtonListener);
//		
//		GridBagConstraints gbc_btnEasyGame = new GridBagConstraints();
//		gbc_btnEasyGame.insets = new Insets(0, 0, 0, 5);
//		gbc_btnEasyGame.gridx = 2;
//		gbc_btnEasyGame.gridy = 6;
//		contentPane.add(btnEasyGame, gbc_btnEasyGame);
//		
//		JButton btnHowToPlay = new JButton("Tutorial");
//		btnHowToPlay.setFont(new Font("Tahoma", Font.BOLD, 50));
//		btnHowToPlay.setPreferredSize(new Dimension(450, 200));
//		GridBagConstraints gbc_btnHowToPlay = new GridBagConstraints();
//		gbc_btnHowToPlay.insets = new Insets(0, 0, 0, 5);
//		gbc_btnHowToPlay.gridx = 2;
//		gbc_btnHowToPlay.gridy = 7;
//		contentPane.add(btnHowToPlay, gbc_btnHowToPlay);
		
		setVisible(true);
	}
	
	public class TitleLayout extends JPanel {
		
		public TitleLayout() {
			
			this.setBounds(0, 0, WIDTH, HEIGHT);
			setLayout(new GridBagLayout());
			setBackground(new Color(0, 0, 0, 0));
			GridBagConstraints gbcTitle = new GridBagConstraints();
			gbcTitle.gridx = 1;
			gbcTitle.gridy = 0;
			gbcTitle.insets = new Insets(5, 5, 5, 5);
			JLabel title = new JLabel("Whose Prey is it Anyway?");
			title.setFont(new Font("Tahoma", Font.BOLD, 110));
			add(title, gbcTitle);
			
			gbcTitle.gridy++;
			gbcTitle.gridx = 0;
			
			JLabel spacer = new JLabel();
			spacer.setPreferredSize(new Dimension(200, 200));
			add(spacer, gbcTitle);
			
			gbcTitle.gridx = 1;
			gbcTitle.gridy++;
			
			JButton btnEasy = new JButton("Easy");
			btnEasy.setFont(new Font("Tahoma", Font.BOLD, 50));
			btnEasy.setPreferredSize(new Dimension(450, 100));
			btnEasy.setForeground(new Color(10, 170, 0));
			btnEasy.addActionListener(btnEasyListener);
			add(btnEasy, gbcTitle);
			
			gbcTitle.gridy++;
			
			JButton btnHard = new JButton("Hard");
			btnHard.setFont(new Font("Tahoma", Font.BOLD, 50));
			btnHard.setPreferredSize(new Dimension(450, 100));
			btnHard.setForeground(new Color(255, 40, 40));
			btnHard.addActionListener(btnHardListener);
			add(btnHard, gbcTitle);
			
			gbcTitle.gridy++;
			
			JButton btnTutorial = new JButton("Tutorial");
			btnTutorial.setFont(new Font("Tahoma", Font.BOLD, 50));
			btnTutorial.setPreferredSize(new Dimension(450, 100));
			btnTutorial.addActionListener(tutorialButtonListener);
			add(btnTutorial, gbcTitle);
			
		}
		
	}
	
	public class DifficultyLayout extends JPanel {
		
		
		
	}
	
	private BufferedImage createImage(File img) {
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(img);
			return bufferedImage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	ActionListener btnEasyListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			controller.displayMainGameScreen(false, 0);
			dispose();
		}
	};
	
	ActionListener btnHardListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			controller.displayMainGameScreen(false, 1);
			dispose();
		}
	};
	
	ActionListener tutorialButtonListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			controller.displayMainGameScreen(true, 0);
			dispose();
		}
	};

}
