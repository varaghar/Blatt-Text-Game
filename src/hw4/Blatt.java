package hw4;

import java.util.Scanner;

public class Blatt {
	private Player p;

	void getCharacterInfo(Scanner inp) {
		int characterType=-1;

		System.out.printf("Please enter your name: ");
		String name = inp.nextLine();		

		System.out.printf("Welcome, %s, to Blatt.\n", name);

		do {
			System.out.printf("In order to continue, you must choose what\n");
			System.out.printf("type of character you will be:\n");
			System.out.printf("\t1) Warrior\n");
			System.out.printf("\t2) Thief\n");
			System.out.printf("\t3) Wizard\n");
			System.out.printf("Please enter a number: ");
			String inpLine = inp.nextLine();
			
			try {
				characterType = Integer.parseInt(inpLine);				
			} catch (NumberFormatException nfe) {
				// They didn't enter a number... restart the loop
				continue;
			}
			
		} while (characterType < 0 || characterType > 3);

		p = new Player(name, characterType);
		
		System.out.printf("Ok %s, you are a %s\n", p.getName(),
				p.getTypeString());
	}

	int doMainMenu(Scanner inp) {
		String val;

		System.out.printf("\nMain Menu:\n");
		System.out.printf("\tF) Go to the forest\n");
		System.out.printf("\tS) Display player stats\n");
		System.out.printf("\tQ) Quit\n");

		val = inp.nextLine();
		switch (val) {
		case "F":
		case "f":
			Forest f = new Forest(p,inp);
			f.go();
			break;
		case "S":
		case "s":			
			System.out.print(p);
			break;
		case "Q":
		case "q":			
			return -1;		
		case "42":
			System.out.printf("You found the secret menu.  You now have 5000 Gold.\n");
			p.setGold(5000);
			break;
		}

		return 0;

	}
	
	/**
	 * Screen asking the user if he wants to load the saved player
	 * @param inp
	 * @return
	 */
	int askLoad(Scanner inp) {
		String val;

		System.out.printf("\nFound save file!\n");
		System.out.printf("\tF) Load saved player\n");
		System.out.printf("\tS) New game\n");
		System.out.printf("\tQ) Quit\n");

		val = inp.nextLine();
		switch (val) {
		case "F":
		case "f":
			return 1;
		case "S":
		case "s":			
			return 1;
		case "Q":
		case "q":			
			return -1;		
		}

		return 0;
	}
	
	
	/**
	 * Screen asking the user if he wants to save the current player
	 * @param inp
	 * @return
	 */
	int askSave(Scanner inp) {
		String val;

		System.out.printf("\nDo you want to save your character?\n");
		System.out.printf("\tF) Yes\n");
		System.out.printf("\tQ) Quit\n");

		val = inp.nextLine();
		switch (val) {
		case "F":
		case "f":
			return 1;
		case "Q":
		case "q":			
			return -1;		
		}

		return 0;
	}

	public Blatt() {
		Scanner inp = new Scanner(System.in);

		System.out.printf("                 Legend of Blatt               \n");
		System.out.printf("-----------------------------------------------\n");
		System.out.printf("Welcome, warrior, to the land of Blatt, a world\n");
		System.out.printf("of myth and legend.\n\n");

		try {
			p = SaveUtils.loadPlayer();
		} catch (Exception e) {
			System.err.println("Failed to load save file!");
			e.printStackTrace();
		}
		
		int load = 0;
		//Check if the player was loaded correctly
		if( p != null ) {
			while (load == 0) {
				load = askLoad(inp);
			}
		} else {
			getCharacterInfo(inp);
		}

		int ret = 0;
		while (ret == 0) {
			ret = doMainMenu(inp);
		}
		
		//Ask if the user wants to save
		int save = 0;
		while (save == 0) {
			save = askSave(inp);
		}
		
		if(save == 1){
			SaveUtils.savePlayer(p);
		}
		
		System.out.printf("Thanks for playing Legend of Blatt!\n");
		
		inp.close();
	}

	public static void main(String[] args) {
		new Blatt();
	}
}
