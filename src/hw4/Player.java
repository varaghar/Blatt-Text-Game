package hw4;

import java.util.ArrayList;

public class Player {
	private String name;
	private CharacterType type;
	private int currHp;
	private int gold;
	private ArrayList<Item> inventory = new ArrayList<Item>();

	public Player() {
		this.currHp = 20;
		this.gold = 0;
		
		Item stick = new Item("Stick",1,2,10,5);
		this.inventory.add(stick);
	}
	
	public Player(String clean) {

	}
	
	public Player(String name, int type) {
		this();
		this.name = name;
		this.type = new CharacterType(type);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CharacterType getType() {
		return type;
	}

	public String getTypeString() {
		return type.toString();
	}

	public int setType(int numType) {
		if (numType < 1 || numType > 3) {
			return -1;
		} else {
			type = new CharacterType(numType);
			return type.getType();
		}
	}

	public int getCurrHp() {
		return currHp;
	}

	public void setCurrHp(int currHp) {
		this.currHp = currHp;
		if (this.currHp > 20) {
			this.currHp = 20;
		}
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public ArrayList<Item> getInventory() {
		return inventory;
	}

	/* Return the protection offered by a Player's armor. */
	public int getProtection() {
		int prot = 0;
		for(Item i: inventory) {
			if (i.getType() == 0) {
				prot += i.getPower();
			}
		}
		return prot;
	}
	
	/* Return the maximum damage a player can do. */
	public int getDamage() {
		// Find the player's weapon and use its power
		for (Item i : inventory) {			
			if (i.getType() == 1) {
				return i.getPower();
			}
		}
		// With no weapons, we return 0.
		return 0;
	}	

	public String toString() {
		String playerInfo = String.format(
				"Name:\t%s\nType:\t%s\nHP:\t%d\nGold:\t%d\ninventory:",
				this.name, this.type, this.currHp, this.gold);
		for(Item i: inventory) {
			playerInfo = playerInfo + "\t" + i + "\n";
		}
		return playerInfo;
	}

}
