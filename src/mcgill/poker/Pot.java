package mcgill.poker;


import java.util.ArrayList;

public class Pot {

	private int amount;
	private ArrayList<Player> players;
	private int limit;
	
	public Pot(){
		this.amount = 0;
		this.players = new ArrayList<Player>();
		this.limit = -1;
	}
	
	public void addPlayer(Player player, int amount) {
		players.add(player);
		this.amount += amount;
	}
	
	public void removePlayer(Player player) {
		players.remove(player);
	}
	
	public int getTotalAmount() {
		return amount;
	}
	
	public void addToPot (int amount) {
		this.amount += amount;
	}
	
	public boolean containsPlayer(Player player) {
		if (players.contains(player)) {
			return true;
		} else {
			return false;
		}
	}
	
	public ArrayList<Player> getPlayers() {
		return this.players;
	}
	
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	public int getLimit() {
		return this.limit;
	}
	
	public boolean hasLimit() {
		if (this.limit == -1) {
			return false;
		} else {
			return true;
		}
	}
}
