import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

/**
 * This class implements the frame, panel, and the game
 * 
 * @author Chen Jingyan
 *
 */
public class Panel {

	int tworeplacebt = 0; // the times that replace buttons are being clicked
	int d1, d2, d3, p1, p2, p3; // the number on the six cards
	int dSpecial, pSpecial; // the number of special cards
	int dSum, pSum; // the sum of rest of cards
	int yourMoney = 100, yourBet; // initial money, bet
	boolean d1Special = false, d2Special = false, d3Special = false; // the status of dealer's cards(special or not)
	boolean p1Special = false, p2Special = false, p3Special = false; // the status of player's cards(special or not)
	ArrayList<Integer> deck = new ArrayList<Integer>(); // the deck
	Random generator = new Random();

	// Create components (image labels)
	JLabel label_Image1 = new JLabel();
	JLabel label_Image2 = new JLabel();
	JLabel label_Image3 = new JLabel();
	JLabel label_Image4 = new JLabel();
	JLabel label_Image5 = new JLabel();
	JLabel label_Image6 = new JLabel();

	// Create components (replace buttons)
	JButton btn_rpcard1 = new JButton("Replace Card 1");
	JButton btn_rpcard2 = new JButton("Replace Card 2");
	JButton btn_rpcard3 = new JButton("Replace Card 3");
	JButton btn_start = new JButton("Start");
	JButton btn_result = new JButton("Result");

	// Create components (labels)
	JLabel label_bet = new JLabel("Bet: $");
	JLabel label_info = new JLabel("Please place your bet!");
	JLabel label_money = new JLabel("Amount of money you have: $" + yourMoney);

	// Create components (text field to input the bet)
	JTextField txt_inputbet = new JTextField(10);

	/**
	 * @param args
	 */

	public Panel() {

		// Create the frame
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and add the menu bar to the frame
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Control");
		JMenuItem Exit = new JMenuItem("Exit");
		Exit.addActionListener(new exitApp());
		menuBar.add(menu);
		menu.add(Exit);
		frame.setJMenuBar(menuBar);

		// Initialize image icons
		ImageIcon Image1 = new ImageIcon("Images/card_back.gif");
		ImageIcon Image2 = new ImageIcon("Images/card_back.gif");
		ImageIcon Image3 = new ImageIcon("Images/card_back.gif");
		ImageIcon Image4 = new ImageIcon("Images/card_back.gif");
		ImageIcon Image5 = new ImageIcon("Images/card_back.gif");
		ImageIcon Image6 = new ImageIcon("Images/card_back.gif");
		label_Image1.setIcon(Image1);
		label_Image2.setIcon(Image2);
		label_Image3.setIcon(Image3);
		label_Image4.setIcon(Image4);
		label_Image5.setIcon(Image5);
		label_Image6.setIcon(Image6);

		// Create panels
		JPanel MainPanel = new JPanel();
		JPanel DealerPanel = new JPanel();
		JPanel PlayerPanel = new JPanel();
		JPanel RpCardBtnPanel = new JPanel();
		JPanel ButtonPanel = new JPanel();
		JPanel InfoPanel = new JPanel();

		// Set the main panel
		MainPanel.setLayout(new GridLayout(5, 1));
		MainPanel.add(DealerPanel);
		MainPanel.add(PlayerPanel);
		MainPanel.add(RpCardBtnPanel);
		MainPanel.add(ButtonPanel);
		MainPanel.add(InfoPanel);

		// Set the sub-panels
		DealerPanel.add(label_Image1);
		DealerPanel.add(label_Image2);
		DealerPanel.add(label_Image3);
		PlayerPanel.add(label_Image4);
		PlayerPanel.add(label_Image5);
		PlayerPanel.add(label_Image6);
		RpCardBtnPanel.add(btn_rpcard1);
		RpCardBtnPanel.add(btn_rpcard2);
		RpCardBtnPanel.add(btn_rpcard3);
		// Disable the replace buttons
		btn_rpcard1.setEnabled(false);
		btn_rpcard2.setEnabled(false);
		btn_rpcard3.setEnabled(false);
		ButtonPanel.add(label_bet);
		ButtonPanel.add(txt_inputbet);
		ButtonPanel.add(btn_start);
		ButtonPanel.add(btn_result);
		btn_result.setEnabled(false); // Disable the result button
		InfoPanel.add(label_info);
		InfoPanel.add(label_money);

		// Background color setting
		DealerPanel.setBackground(Color.green);
		PlayerPanel.setBackground(Color.green);
		RpCardBtnPanel.setBackground(Color.green);

		// Set the frame
		frame.getContentPane().add(MainPanel);
		frame.setTitle("A Simple Card Game");
		frame.setSize(400, 700);
		frame.setVisible(true);

		// Add cards to the deck
		for (int i = 11; i < 20; i++) {
			deck.add(i);
		}
		for (int i = 21; i < 30; i++) {
			deck.add(i);
		}
		for (int i = 31; i < 40; i++) {
			deck.add(i);
		}
		for (int i = 41; i < 50; i++) {
			deck.add(i);
		}
		for (int i = 110; i < 114; i++) {
			deck.add(i);
		}
		for (int i = 210; i < 214; i++) {
			deck.add(i);
		}
		for (int i = 310; i < 314; i++) {
			deck.add(i);
		}
		for (int i = 410; i < 414; i++) {
			deck.add(i);
		}

		// Set the start button
		btn_start.addActionListener(new ActionListener() {
			/**
			 * This method implements the Start button. The Start button draws and displays
			 * three cards from the deck for the player. After clicking the Start button,
			 * the Repeat Buttons and the Result button will be enabled.
			 *
			 */
			public void actionPerformed(ActionEvent e) {

				// Enable the Repeat Buttons and the Result button, disable the Start Button
				btn_rpcard1.setEnabled(true);
				btn_rpcard2.setEnabled(true);
				btn_rpcard3.setEnabled(true);
				btn_result.setEnabled(true);
				btn_start.setEnabled(false);

				// Change the image of Image4
				p1 = deck.get((generator.nextInt(deck.size()))); // draw the card from the deck
				label_Image4.setIcon(new ImageIcon("Images/card_" + (p1) + ".gif"));
				// Check if the card is a special card
				if (p1 > 100 && p1 % 100 > 10) {
					p1Special = true;
					pSpecial++;
				} else
					pSum += p1 % 10;
				// Remove the card from the deck
				for (int i = 0; i < deck.size(); i++) {
					if (deck.get(i) == p1) {
						deck.remove(i);
					}
				}

				// Change the image of Image5
				p2 = deck.get((generator.nextInt(deck.size()))); // draw the card from the deck
				label_Image5.setIcon(new ImageIcon("Images/card_" + (p2) + ".gif"));
				// Check if the card is a special card
				if (p2 > 100 && p2 % 100 > 10) {
					p2Special = true;
					pSpecial++;
				} else
					pSum += p2 % 10;
				// Remove the card from the deck
				for (int i = 0; i < deck.size(); i++) {
					if (deck.get(i) == p2) {
						deck.remove(i);
					}
				}

				// Change the image of Image6
				p3 = deck.get((generator.nextInt(deck.size())));
				label_Image6.setIcon(new ImageIcon("Images/card_" + (p3) + ".gif")); // draw the card from the deck
				// Check if the card is a special card
				if (p3 > 100 && p3 % 100 > 10) {
					p3Special = true;
					pSpecial++;
				} else
					pSum += p3 % 10;
				// Remove the card from the deck
				for (int i = 0; i < deck.size(); i++) {
					if (deck.get(i) == p3) {
						deck.remove(i);
					}
				}

				// Display the bet
				label_info.setText("Your current bet is:$" + txt_inputbet.getText());
				yourBet = Integer.valueOf(txt_inputbet.getText());
			}
		});

		// Set the replace button1
		btn_rpcard1.addActionListener(new ActionListener() {
			/**
			 * This method implements the Replace button1. The Replace button1 enables the
			 * player to replace the first card. The button only works once.
			 *
			 */
			public void actionPerformed(ActionEvent e) {
				if (tworeplacebt < 2) {

					deck.add(p1);// add the original card back to the deck

					// If the original card is special
					if (p1Special != true) {
						pSum -= p1 % 10;
						p1 = deck.get((generator.nextInt(deck.size())));
						label_Image4.setIcon(new ImageIcon("Images/card_" + (p1) + ".gif"));
						btn_rpcard1.setEnabled(false);
						tworeplacebt++;
						// Check if the new card is special
						if (p1 > 100 && p1 % 100 > 10) {
							p1Special = true;
							pSpecial++;
						} else
							pSum += p1 % 10;
					}

					// If the original card is not special
					else if (p1Special == true) {
						p1 = deck.get((generator.nextInt(deck.size())));
						label_Image4.setIcon(new ImageIcon("Images/card_" + (p1) + ".gif"));
						btn_rpcard1.setEnabled(false);
						tworeplacebt++;
						// Check if the new card is special
						if (!(p1 > 100 && p1 % 100 > 10)) {
							p1Special = false;
							pSpecial--;
							pSum += p1 % 10;
						}
					}

					// Remove the new card from the deck
					for (int i = 0; i < deck.size(); i++) {
						if (deck.get(i) == p1) {
							deck.remove(i);
						}
					}
				}
			}
		});

		// Set the replace button2
		btn_rpcard2.addActionListener(new ActionListener() {
			/**
			 * This method implements the Replace button2. The Replace button2 enables the
			 * player to replace the second card. The button only works once.
			 *
			 */
			public void actionPerformed(ActionEvent e) {
				if (tworeplacebt < 2) {

					deck.add(p2); // add the original card back to the deck

					// If the original card is special
					if (p2Special != true) {
						pSum -= p2 % 10;
						p2 = deck.get((generator.nextInt(deck.size())));
						label_Image5.setIcon(new ImageIcon("Images/card_" + (p2) + ".gif"));
						btn_rpcard2.setEnabled(false);
						tworeplacebt++;
						// Check if the new card is special
						if (p2 > 100 && p2 % 100 > 10) {
							p2Special = true;
							pSpecial++;
						} else
							pSum += p2 % 10;
					}

					// If the original card is not special
					else if (p2Special == true) {
						p2 = deck.get((generator.nextInt(deck.size())));
						label_Image5.setIcon(new ImageIcon("Images/card_" + (p2) + ".gif"));
						btn_rpcard2.setEnabled(false);
						tworeplacebt++;
						// Check if the new card is special
						if (!(p2 > 100 && p2 % 100 > 10)) {
							p2Special = false;
							pSpecial--;
							pSum += p2 % 10;
						}
					}

					// Remove the new card from the deck
					for (int i = 0; i < deck.size(); i++) {
						if (deck.get(i) == p2) {
							deck.remove(i);
						}
					}
				}
			}
		});

		// Set the replace button3
		btn_rpcard3.addActionListener(new ActionListener() {
			/**
			 * This method implements the Replace button3. The Replace button3 enables the
			 * player to replace the third card. The button only works once.
			 *
			 */
			public void actionPerformed(ActionEvent e) {
				if (tworeplacebt < 2) {

					deck.add(p3); // add the original card back to the deck

					// If the original card is special
					if (p3Special != true) {
						pSum -= p3 % 10;
						p3 = deck.get((generator.nextInt(deck.size())));
						label_Image6.setIcon(new ImageIcon("Images/card_" + (p3) + ".gif"));
						btn_rpcard3.setEnabled(false);
						tworeplacebt++;
						// Check if the new card is special
						if (p3 > 100 && p3 % 100 > 10) {
							p3Special = true;
							pSpecial++;
						} else
							pSum += p3 % 10;
					}

					// If the original card is not special
					else if (p3Special == true) {
						p3 = deck.get((generator.nextInt(deck.size())));
						label_Image6.setIcon(new ImageIcon("Images/card_" + (p3) + ".gif"));
						btn_rpcard3.setEnabled(false);
						tworeplacebt++;
						// Check if the new card is special
						if (!(p3 > 100 && p3 % 100 > 10)) {
							p3Special = false;
							pSpecial--;
							pSum += p3 % 10;
						}
					}

					// Remove the new card from the deck
					for (int i = 0; i < deck.size(); i++) {
						if (deck.get(i) == p3) {
							deck.remove(i);
						}
					}

				}

			}
		});

		// Set the result button
		btn_result.addActionListener(new ActionListener() {
			/**
			 * This method implements the Result button. The Result button displays the card
			 * of the dealer. After clicking the Result button, the result of this round
			 * will be shown as a dialog.
			 *
			 */
			public void actionPerformed(ActionEvent e) {

				// Change the image of Image1
				d1 = deck.get((generator.nextInt(deck.size())));
				label_Image1.setIcon(new ImageIcon("Images/card_" + (d1) + ".gif")); // draw the card from the deck
				// Check if the card is special
				if (d1 > 100 && d1 % 100 > 10) {
					d1Special = true;
					dSpecial++;
				} else
					dSum += d1 % 10;
				// Remove the card from the deck
				for (int i = 0; i < deck.size(); i++) {
					if (deck.get(i) == d1) {
						deck.remove(i);
					}
				}

				// Change the image of Image2
				d2 = deck.get((generator.nextInt(deck.size())));
				label_Image2.setIcon(new ImageIcon("Images/card_" + (d2) + ".gif")); // draw the card from the deck
				// Check if the card is special
				if (d2 > 100 && d2 % 100 > 10) {
					d2Special = true;
					dSpecial++;
				} else
					dSum += d2 % 10;
				// Remove the card from the deck
				for (int i = 0; i < deck.size(); i++) {
					if (deck.get(i) == d2) {
						deck.remove(i);
					}
				}

				// Change the image of Image3
				d3 = deck.get((generator.nextInt(deck.size())));
				label_Image3.setIcon(new ImageIcon("Images/card_" + (d3) + ".gif")); // draw the card from the deck
				// Check if the card is special
				if (d3 > 100 && d3 % 100 > 10) {
					d3Special = true;
					dSpecial++;
				} else
					dSum += d3 % 10;
				// Remove the card from the deck
				for (int i = 0; i < deck.size(); i++) {
					if (deck.get(i) == d3) {
						deck.remove(i);
					}
				}

				// Put the card back to the deck after each round
				deck.add(p1);
				deck.add(p2);
				deck.add(p3);
				deck.add(d1);
				deck.add(d2);
				deck.add(d3);

				// Check Rule1
				if (dSpecial < pSpecial) {
					JOptionPane.showMessageDialog(frame, "Congratulations! You win this round!");
					yourMoney += yourBet;
				} else if (dSpecial > pSpecial) {
					JOptionPane.showMessageDialog(frame, "Sorry! The dealer wins this round!");
					yourMoney -= yourBet;
				}
				// Check Rule2
				else if (dSpecial == pSpecial) {
					if (dSum % 10 < pSum % 10) {
						JOptionPane.showMessageDialog(frame, "Congratulations! You win this round!");
						yourMoney += yourBet;
					} else if (dSum % 10 > pSum % 10) {
						JOptionPane.showMessageDialog(frame, "Sorry! The dealer wins this round!");
						yourMoney -= yourBet;
					}
					// Check Rule3
					else if (dSum % 10 == pSum % 10) {
						JOptionPane.showMessageDialog(frame, "Sorry! The dealer wins this round!");
						yourMoney -= yourBet;
					}
				}

				// Continue the game if the player still have money
				if (yourMoney > 0) {

					// Reset the status of buttons
					btn_rpcard1.setEnabled(false);
					btn_rpcard2.setEnabled(false);
					btn_rpcard3.setEnabled(false);
					btn_result.setEnabled(false);
					btn_start.setEnabled(true);
					// Reset the images
					label_Image1.setIcon(new ImageIcon("Images/card_back.gif"));
					label_Image2.setIcon(new ImageIcon("Images/card_back.gif"));
					label_Image3.setIcon(new ImageIcon("Images/card_back.gif"));
					label_Image4.setIcon(new ImageIcon("Images/card_back.gif"));
					label_Image5.setIcon(new ImageIcon("Images/card_back.gif"));
					label_Image6.setIcon(new ImageIcon("Images/card_back.gif"));
					// Reset the values
					tworeplacebt = 0;
					d1 = d2 = d3 = p1 = p2 = p3 = 0;
					dSpecial = pSpecial = 0;
					dSum = pSum = 0;
					d1Special = d2Special = d3Special = false;
					p1Special = p2Special = p3Special = false;
					label_info.setText("Please place your bet!");
					label_money.setText("Amount of money you have: $" + yourMoney);
				}
				// End the game if the player lose all the money
				else {
					// The dialog of losing the game
					JOptionPane.showMessageDialog(frame,
							"Game over!" + "\n" + "You have no money!" + "\n" + "Please start a new Game!");

					// Reset all the buttons, images, and values
					btn_rpcard1.setEnabled(false);
					btn_rpcard2.setEnabled(false);
					btn_rpcard3.setEnabled(false);
					btn_result.setEnabled(false);
					btn_start.setEnabled(true);
					label_Image1.setIcon(new ImageIcon("Images/card_back.gif"));
					label_Image2.setIcon(new ImageIcon("Images/card_back.gif"));
					label_Image3.setIcon(new ImageIcon("Images/card_back.gif"));
					label_Image4.setIcon(new ImageIcon("Images/card_back.gif"));
					label_Image5.setIcon(new ImageIcon("Images/card_back.gif"));
					label_Image6.setIcon(new ImageIcon("Images/card_back.gif"));
					tworeplacebt = 0;
					d1 = d2 = d3 = p1 = p2 = p3 = 0;
					dSpecial = pSpecial = 0;
					dSum = pSum = 0;
					d1Special = d2Special = d3Special = false;
					p1Special = p2Special = p3Special = false;
					yourMoney = 100;
					label_info.setText("Please place your bet!");
					label_money.setText("Amount of money you have: $" + yourMoney);
				}
			}
		});
	}

	/**
	 * This class implements the Exit button.
	 *
	 */
	static class exitApp implements ActionListener {
		/**
		 * This method implements the Result button. The player can quit the game by
		 * clicking the Exit button.
		 *
		 */
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

}
