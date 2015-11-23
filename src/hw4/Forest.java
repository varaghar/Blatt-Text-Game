package hw4;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Forest {
	private Player p;
	private Scanner inp;

	public Forest(Player p, Scanner inp) {
		this.p = p;
		this.inp = inp;
	}

	public void printMenu() {
		System.out.printf("\nWelcome to the forest!  Beware of monsters...\n");
		System.out.printf("What do you want to do?\n");
		System.out.printf("\tH) Go hunting\n");
		System.out.printf("\tN) Take a nap under a tree\n");
		System.out.printf("\tQ) Return to the Main Menu\n");
	}

	public void go() {
		String menuChoice;
		while (true) {
			printMenu();

			menuChoice = inp.nextLine();

			switch (menuChoice) {
			case "H":
			case "h":
				hunt();
				break;
			case "N":
			case "n":
				nap();
				break;
			case "Q":
			case "q":
				// Quit the menu
				return;
			default:
				break;
			}
		}

	}

	/*
	 * A nap is a way to recover HP. The gameplay basically pauses for 20
	 * seconds and player recovers 5 HP.
	 */
	void nap() {
		System.out.printf("You lay down under a tree to take a nap...\n");
		try {
			System.out.printf("Z");
			TimeUnit.SECONDS.sleep(1);
			System.out.printf("z");
			TimeUnit.SECONDS.sleep(1);
			System.out.printf("Z");
			TimeUnit.SECONDS.sleep(1);
			System.out.printf("z");
			TimeUnit.SECONDS.sleep(1);
			System.out.printf("Z\n");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p.setCurrHp(p.getCurrHp() + 5);
		System.out.printf("You wake up feeling refreshed and have %d HP.\n",
				p.getCurrHp());
	}

	void hunt() {
		Random r = new Random();

		int monsterHp = 10;
		System.out.printf("You found a monster!\n");
		while (true) {
			System.out.printf(
					"You are fighting a Monster with %d hit points\n",
					monsterHp);
			System.out.printf("You currently have %d hit points\n",
					p.getCurrHp());
			System.out.printf("\tA) Attack\n");
			System.out.printf("\tQ) Run Away\n");

			String choice = inp.nextLine();

			switch (choice) {
			case "A":
			case "a":
				// Generate a random damage the player does and apply it.
				// The damage is based on the weapon a player has equipped
				int playerAttack = r.nextInt(p.getDamage() + 1);
				System.out.printf("You attack the monster and do %d damage!\n",
						playerAttack);
				monsterHp -= playerAttack;
				if (monsterHp <= 0) {
					// Monster is dead

					// Give the player 10 Gold
					System.out
							.printf("You killed the monster and received 10 gold\n");
					p.setGold(p.getGold() + 10);
					return;
				}

				// Generate a random damage the Monster does
				// Subtract damage protected by the player's armor.
				int monsterAttack;

				int maxHit = 4 - p.getProtection();

				if (maxHit <= 0) {
					monsterAttack = 0;
				} else {
					monsterAttack = r.nextInt(maxHit);
				}
				System.out.printf(
						"The monster attacks you and does %d damage!\n",
						monsterAttack);
				p.setCurrHp(p.getCurrHp() - monsterAttack);

				if (p.getCurrHp() <= 0) {
					// Player is dead
					System.out.printf("You died!");
					// This quits the program...
					System.exit(0);
				}
				break;

			case "Q":
			case "q":
				// Run away!
				return;
			default:
				/*
				 * They entered something useless, print an error and the menu
				 * again.
				 */
				System.out.printf("Invalid entry, please try again!\n");
			}
		}
	}
}
