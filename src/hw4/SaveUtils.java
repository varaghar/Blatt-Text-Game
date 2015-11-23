package hw4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class SaveUtils {
	public static final String SAVE_FILE_NAME = "player_save.txt";
	//Array to make sure each attribute is read exactly once
	private static int[] attributesRead = {0,0,0,0,0};

	/**
	 * Read player from save file.
	 * @return
	 * @throws IOException
	 * @throws LoadSaveFileException
	 */
	public static Player loadPlayer() throws IOException, LoadSaveFileException {
	    
		Player player = new Player("clean");
		BufferedReader br = null;

		String currentLine;

		br = new BufferedReader(new FileReader(SAVE_FILE_NAME));	
		
		while ((currentLine = br.readLine()) != null) {
			parseLine(player, currentLine, br);
		}
		
		br.close();
		
		for(Integer flag : attributesRead){
			if(flag!=1){
				throw new LoadSaveFileException();
			}
		}
		
		return player;
	}
	
	/**
	 * Method that parses a line and updates the player.
	 * @param player
	 * @param currentLine
	 * @throws IOException 
	 * @throws LoadSaveFileException 
	 */
	private static void parseLine(Player player, String currentLine, BufferedReader br) throws IOException, LoadSaveFileException{
		
		String[] parts = currentLine.split(":");
		if(parts[0].equals("name")) {
			player.setName(parts[1].trim());
			if(attributesRead[0]==1){
				throw new LoadSaveFileException();
			}
			attributesRead[0]=1;
			return;
		}
		
		if(parts[0].equals("type")) {
			player.setType(CharacterType.toType(parts[1].trim()));
			if(attributesRead[1]==1){
				throw new LoadSaveFileException();
			}
			attributesRead[1]=1;
			return;
		}
		
		
		if(parts[0].equals("currhp")) {
			player.setCurrHp(Integer.valueOf(parts[1].trim()));
			if(attributesRead[2]==1){
				throw new LoadSaveFileException();
			}
			attributesRead[2]=1;
			return;
		}
		
		if(parts[0].equals("gold")) {
			player.setGold(Integer.valueOf(parts[1].trim()));
			if(attributesRead[3]==1){
				throw new LoadSaveFileException();
			}
			attributesRead[3]=1;
			return;
		}
		
		if(currentLine.equals("items:")) {
			if(attributesRead[4]==1){
				throw new LoadSaveFileException();
			}
			attributesRead[4]=1;
			
			Item item = null;
			while ((currentLine = br.readLine()) != null) {
				try {
					item = lineToItem(currentLine);
					player.getInventory().add(item);
				} catch (Exception e) {
					item = null;
				}
				
				if(item == null){
					parseLine( player, currentLine, br);
				}
			}
			return;
		}
		
		throw new LoadSaveFileException();
	}
		
	private static Item lineToItem(String line) {
		String[] itemParts = line.split(",");
		
		return new Item(itemParts[0].trim(), Integer.valueOf(itemParts[1].trim()), Integer.valueOf(itemParts[2].trim()), Integer.valueOf(itemParts[3].trim()), Integer.valueOf(itemParts[4].trim()));
	}
	
	
		
		
	/**
	 * Write player to save file.
	 * @param player
	 * @throws IOException
	 * @throws LoadSaveFileException
	 */
	public static void savePlayer(Player player) {
		
		try {
			PrintWriter writer = new PrintWriter(SAVE_FILE_NAME);
			writer.println("name: "+player.getName());
			writer.println("type: "+player.getTypeString());
			writer.println("currhp: "+player.getCurrHp());
			writer.println("gold: "+player.getGold());
			writer.println("items: ");
			
			List<Item> inventory = player.getInventory();
			for(Item item : inventory) {
				writer.println(item.getName()+","+item.getType()+","+item.getPower()+","+item.getSellPrice()+","+item.getBuyPrice());
			}
			
			writer.close();
		} catch (FileNotFoundException e) {
			System.err.println("Could not save the player");
		}
		
	}
}
