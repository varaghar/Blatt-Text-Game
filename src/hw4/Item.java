package hw4;

public class Item {
	/* The name of the item. */
	private String name;
	
	/* The type of item.  Right now we have...
	 * 0: Armor (defensive)
	 * 1: Weapon (offensive)
	 */
	private int type;
	
	/* The power of the item.
	 * For armor, this is how much damage they can absorb.
	 * For weapons, this is the maximum amount of damage it can do.
	 */
	private int power;

	/* The buy and sell price */
	private int buyPrice;
	private int sellPrice;	
	
	public Item(String name, int type, int power, int buyPrice, int sellPrice) {
		this.name = name;
		this.type = type;
		this.power = power;
		this.buyPrice = buyPrice;
		this.sellPrice = sellPrice;
	}	
		
	public String getName() {
		return name;
	}

	public int getBuyPrice() {
		return buyPrice;
	}
	
	public int getSellPrice() {
		return sellPrice;
	}

	public int getType() {
		return type;
	}

	public int getPower() {
		return power;
	}

	public String toString() {
		return String.format("%s (Power %d)", this.name, this.power);
	}
}
