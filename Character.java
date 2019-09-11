package application;

public class Character {
	// Private Variables
	private String userName;
	private String password;
	private String name;
	private int level = 1;
	private int keysObtained = 0;
	private double stat_attack = 1;
	
	public Character() {
		this.userName = null;
		this.password = null;
		this.name = null;
	}
	
	public Character(String userName, String password, String name) {
		this.userName = userName;
		this.password = password;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public int getKeyCount() {
		return keysObtained;
	}
}
