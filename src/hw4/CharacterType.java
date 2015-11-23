package hw4;

public class CharacterType {
	private int type;
	
	public CharacterType() {
		this.type = 1;
	}
	
	public CharacterType(int type) {
		this();
		
		if (type >0 && type < 4) {
			this.type = type;
		}
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String toString() {
		switch (this.type) {
		case 1:
			return "Warrior";
		case 2:
			return "Thief";
		case 3:
			return "Wizard";
		default:
			return "This should never happen";
		}
	}
	
	// Returns the code for character type string
	public static int toType(String typeString) {
		if(typeString.equals("Warrior")){
			return 1;
		}
		if(typeString.equals("Thief")){
			return 1;
		}
		if(typeString.equals("Wizard")){
			return 1;
		}
			return 0;
	}
}
