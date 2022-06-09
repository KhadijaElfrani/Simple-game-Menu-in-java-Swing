import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import java.awt.Font;

public class Menu extends JFrame {

	public JPanel commencerPanel;
	public JLabel interface1Label, titreLabel, jouerLabel, reglesLabel, exitLabel, musicLabel, interface2Label, interface3Label;
	public JLabel joueurs2Label, joueurs4Label, choixNbrJoueursLabel, continueLabel,checkLabel, pseudosLabel, commencerLabel;
	public JLabel joueurPseudo1Label, joueurPseudo2Label, joueurPseudo3Label, joueurPseudo4Label;

	public File file1, file2, file3; // sound files
	public Clip clipBgSound, clipHoverSound, clipCommencerSound;	
	public static int nbrJoueurs;
	public static ArrayList<String> pseudos;
	public JTextField joueur1PseudoTxt, joueur2PseudoTxt, joueur3PseudoTxt, joueur4PseudoTxt;
	
	public boolean soundOn = true;
	public boolean selected2 = false;
	public boolean selected4 = false;


	/**
	 * Lancer l'application
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws LineUnavailableException 
	 * @throws UnsupportedAudioFileException 
	 */
	public Menu() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1300, 800);
		/*
		 *  Panel du premiere interface du menu .
		 */
		commencerPanel = new JPanel();
		commencerPanel.setBackground(new Color(0, 204, 204));
		commencerPanel.setBorder(new LineBorder(Color.CYAN, 2));
		setContentPane(commencerPanel);
		commencerPanel.setLayout(null);		

		//Lancer la music au lancement du jeu
		file1 = new File("start_sound.wav");
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(file1);
		clipBgSound = AudioSystem.getClip();
		clipBgSound.open(audioStream);
		clipBgSound.start();
		
		//Charger le son de selection
		file2 = new File("hoversound.wav");
		
		//Charger le son de lancement de jeu
		file3 = new File("commencersound.wav");
		
		//Label du la premiere interface
		interface1Label = new JLabel("");
		interface1Label.setBounds(0, 0, 1300, 800);
		interface1Label.setIcon(new ImageIcon(getClass().getResource("bg.png")));
		commencerPanel.add(interface1Label);

		//Label de la 2eme interface
		interface2Label = new JLabel("");
		interface2Label.setBounds(0, 0, 1300, 800);
		interface2Label.setIcon(new ImageIcon(getClass().getResource("bg.png")));
		commencerPanel.add(interface2Label);
		
		//Label de la 3eme interface
		interface3Label = new JLabel("");
		interface3Label.setBounds(0, 0, 1300, 800);
		interface3Label.setIcon(new ImageIcon(getClass().getResource("bg.png")));
		commencerPanel.add(interface3Label);
	
		//Label du titre "THE ISLAND"
		titreLabel = new JLabel("");
		titreLabel.setBounds(400, 10, 480, 80);
		titreLabel.setIcon(new ImageIcon(getClass().getResource("titre.png")));
		interface1Label.add(titreLabel);
		
		//Label du bouton Jouer + ses event handlers
		jouerLabel = new JLabel("");
		jouerLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					playSound(file3 , clipCommencerSound);
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// passer à l'interface suivante
				repaint();
				interface1Label.setVisible(false);
				interface2Label.setVisible(true);
				interface2Label.add(titreLabel);
				interface2Label.add(exitLabel);
				interface2Label.add(musicLabel);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				jouerLabel.setIcon(new ImageIcon(getClass().getResource("jouer_hover.png")));
				try {
					playSound(file2 ,clipHoverSound);
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
			@Override
			public void mouseExited(MouseEvent e) {
				jouerLabel.setIcon(new ImageIcon(getClass().getResource("jouer.png")));

			}
		});
		jouerLabel.setBounds(500, 300, 283, 80);
		jouerLabel.setIcon(new ImageIcon(getClass().getResource("jouer.png")));
		interface1Label.add(jouerLabel);
		
		//Label du bouton Regles de jeu + ses event handlers
		reglesLabel = new JLabel("");
		reglesLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				reglesLabel.setIcon(new ImageIcon(getClass().getResource("regles_hover.png")));
				try {
					playSound(file2 ,clipHoverSound);
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			}
			@Override
			public void mouseExited(MouseEvent e) {
				reglesLabel.setIcon(new ImageIcon(getClass().getResource("regles.png")));

			}
		});
		reglesLabel.setBounds(50, 600, 400, 49);
		reglesLabel.setIcon(new ImageIcon(getClass().getResource("regles.png")));
		interface1Label.add(reglesLabel);
		
		//Label du bouton Exit + ses event handlers
		exitLabel = new JLabel("");
		exitLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				exitLabel.setIcon(new ImageIcon(getClass().getResource("Exit_hover.png")));
				try {
					playSound(file2 ,clipHoverSound);
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitLabel.setIcon(new ImageIcon(getClass().getResource("exit.png")));
			}
		});
		exitLabel.setBounds(1000, 600, 120, 49);
		exitLabel.setIcon(new ImageIcon(getClass().getResource("exit.png")));
		interface1Label.add(exitLabel);
		
		musicLabel = new JLabel("");
		musicLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Arrêter/Lancer la music en cliquant sur l'icone du son
				if(soundOn == true)
				{
					musicLabel.setIcon(new ImageIcon(getClass().getResource("music_off.png")));
					clipBgSound.stop();
					soundOn = false;
				}
				else if(soundOn == false) {
					musicLabel.setIcon(new ImageIcon(getClass().getResource("music_on.png")));
					clipBgSound.start();
					soundOn = true;
				}
			}
		});
		musicLabel.setBounds(1200, 10, 60, 60);
		musicLabel.setIcon(new ImageIcon(getClass().getResource("music_on.png")));
		interface1Label.add(musicLabel);
		
		joueurs2Label = new JLabel("");
		joueurs2Label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					playSound(file3 , clipCommencerSound);
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				nbrJoueurs = 2;
				selected4 = false;
				selected2 = true;
				joueurs2Label.setIcon(new ImageIcon(getClass().getResource("2_joueurs_hover.png")));
				joueurs4Label.setIcon(new ImageIcon(getClass().getResource("4_joueurs1.png")));
				joueurs2Label.add(checkLabel);
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				joueurs2Label.setIcon(new ImageIcon(getClass().getResource("2_joueurs_hover.png")));
				try {
					playSound(file2 ,clipHoverSound);
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(selected2 == false)
					joueurs2Label.setIcon(new ImageIcon(getClass().getResource("2_joueurs1.png")));

			}
	
		});
		joueurs2Label.setBounds(200, 300, 200, 200);
		joueurs2Label.setIcon(new ImageIcon(getClass().getResource("2_joueurs1.png")));
		interface2Label.add(joueurs2Label);
		
		joueurs4Label = new JLabel("");
		joueurs4Label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					playSound(file3 , clipCommencerSound);
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				nbrJoueurs = 4;
				selected4 = true;
				selected2 = false;
				joueurs4Label.setIcon(new ImageIcon(getClass().getResource("4_joueurs_hover.png")));
				joueurs2Label.setIcon(new ImageIcon(getClass().getResource("2_joueurs1.png")));
				joueurs4Label.add(checkLabel);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				joueurs4Label.setIcon(new ImageIcon(getClass().getResource("4_joueurs_hover.png")));
				try {
					playSound(file2 ,clipHoverSound);
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(selected4 == false)
					joueurs4Label.setIcon(new ImageIcon(getClass().getResource("4_joueurs1.png")));

			}

		});
		joueurs4Label.setBounds(800, 300, 200, 200);
		joueurs4Label.setIcon(new ImageIcon(getClass().getResource("4_joueurs1.png")));
		interface2Label.add(joueurs4Label);
		
		choixNbrJoueursLabel = new JLabel("");
		choixNbrJoueursLabel.setBounds(200, 150, 892, 87);
		choixNbrJoueursLabel.setIcon(new ImageIcon(getClass().getResource("nbr_joueurs.png")));
		interface2Label.add(choixNbrJoueursLabel);
		
		continueLabel = new JLabel("");
		continueLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					playSound(file3 , clipCommencerSound);
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(selected2 || selected4 ) {
					System.out.println("nbr de joueurs choisi :"+ nbrJoueurs);
					repaint();
					interface1Label.setVisible(false);
					interface2Label.setVisible(false);
					interface3Label.setVisible(true);
					interface3Label.add(titreLabel);
					interface3Label.add(exitLabel);
					interface3Label.add(musicLabel);
					if(nbrJoueurs == 2) {
						interface3Label.add(joueurPseudo1Label);
						interface3Label.add(joueurPseudo2Label);
					}
					else if(nbrJoueurs == 4) {
						interface3Label.add(joueurPseudo1Label);
						interface3Label.add(joueurPseudo2Label);
						interface3Label.add(joueurPseudo3Label);
						interface3Label.add(joueurPseudo4Label);
					}
				}
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				continueLabel.setIcon(new ImageIcon(getClass().getResource("continue_hover.png")));
				try {
					playSound(file2 ,clipHoverSound);
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				continueLabel.setIcon(new ImageIcon(getClass().getResource("continue.png")));

			}
		});
		continueLabel.setBounds(500, 600,250, 51);
		continueLabel.setIcon(new ImageIcon(getClass().getResource("continue.png")));
		interface2Label.add(continueLabel);
		
		pseudosLabel = new JLabel("");
		pseudosLabel.setBounds(350, 150, 583, 87);
		pseudosLabel.setIcon(new ImageIcon(getClass().getResource("pseudo.png")));
		interface3Label.add(pseudosLabel);
		
		commencerLabel = new JLabel("");
		commencerLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					playSound(file3 , clipCommencerSound);
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				pseudos = new ArrayList<String>();
				//Ajouter les pseudos entrées à la liste
				if(nbrJoueurs == 2) {
					pseudos.add(joueur1PseudoTxt.getText());
					pseudos.add(joueur2PseudoTxt.getText());
				}
				else if(nbrJoueurs == 4) {
					pseudos.add(joueur1PseudoTxt.getText());
					pseudos.add(joueur2PseudoTxt.getText());
					pseudos.add(joueur3PseudoTxt.getText());
					pseudos.add(joueur4PseudoTxt.getText());
				}
				Menu.this.dispose(); // fermer la fenetre menu pour passer au plateau de jeu
				
				//Afficher les élément de la liste ( pour le test )
			      for(String pseudo: pseudos)
			       {
			       	 System.out.println (pseudo);
			       	 /*
			       	  * la liste contient bien les pseudos de joueurs, et puisque elle est statique
			       	  * on peut acceder ses elements a partir de la classe Joueur ( idem pour nbrJoueurs).
			       	  * les pseudos sont - par default - nommées joueur1..4 
			       	  */
			       }
			     

			}
			@Override
			public void mouseEntered(MouseEvent e) {
				commencerLabel.setIcon(new ImageIcon(getClass().getResource("commencer_hover.png")));
				try {
					playSound(file2 ,clipHoverSound);
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				commencerLabel.setIcon(new ImageIcon(getClass().getResource("commencer.png")));

			}
		});
		commencerLabel.setBounds(350, 600, 544, 49);
		commencerLabel.setIcon(new ImageIcon(getClass().getResource("commencer.png")));
		interface3Label.add(commencerLabel);
		
		// text area joueur 1
		joueurPseudo1Label = new JLabel("");
		joueurPseudo1Label.setBounds(270, 300, 200, 50);
		//interface3Label.add(joueurPseudo1Label);
		
		joueur1PseudoTxt = new JTextField();
		joueur1PseudoTxt.setText("Joueur 1");
		joueur1PseudoTxt.setFont(new Font("Arial", Font.BOLD, 14));
		joueur1PseudoTxt.setBounds(0, 0, 200, 50);
		joueurPseudo1Label.add(joueur1PseudoTxt);
		joueur1PseudoTxt.setColumns(10);
		
		// text area joueur 2
		joueurPseudo2Label = new JLabel("");
		joueurPseudo2Label.setBounds(770, 300, 200, 50);
		//interface3Label.add(joueurPseudo2Label);
		
		joueur2PseudoTxt = new JTextField();
		joueur2PseudoTxt.setText("Joueur 2");
		joueur2PseudoTxt.setFont(new Font("Arial", Font.BOLD, 14));
		joueur2PseudoTxt.setBounds(0, 0, 200, 50);
		joueurPseudo2Label.add(joueur2PseudoTxt);
		joueur2PseudoTxt.setColumns(10);
		
		// text area joueur 3
		joueurPseudo3Label = new JLabel("");
		joueurPseudo3Label.setBounds(270, 400, 200, 50);
		//interface3Label.add(joueurPseudo3Label);
		
		joueur3PseudoTxt = new JTextField();
		joueur3PseudoTxt.setText("Joueur 3");
		joueur3PseudoTxt.setFont(new Font("Arial", Font.BOLD, 14));
		joueur3PseudoTxt.setBounds(0, 0, 200, 50);
		joueurPseudo3Label.add(joueur3PseudoTxt);
		joueur3PseudoTxt.setColumns(10);
		
		// text area joueur 4
		joueurPseudo4Label = new JLabel("");
		joueurPseudo4Label.setBounds(770, 400, 200, 50);
		//interface3Label.add(joueurPseudo4Label);
		
		joueur4PseudoTxt = new JTextField();
		joueur4PseudoTxt.setText("Joueur 4");
		joueur4PseudoTxt.setFont(new Font("Arial", Font.BOLD, 14));
		joueur4PseudoTxt.setBounds(0, 0, 200, 50);
		joueurPseudo4Label.add(joueur4PseudoTxt);
		joueur1PseudoTxt.setColumns(10);
		
		
		checkLabel = new JLabel("");
		checkLabel.setBounds(90, 50, 120, 120);
		checkLabel.setIcon(new ImageIcon(getClass().getResource("check.gif")));


	}
	
	public void playSound(File file, Clip clip) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
		clip = AudioSystem.getClip();
		clip.open(audioStream);
		clip.start();
	}
}