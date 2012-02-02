package mcgill.poker;

import java.util.HashMap;
import java.util.Map;

public class ChipStack {
	
	private int[] values;
	private Map<Chip, Integer> chips;
	
	public ChipStack(int[] values) {
		this.values = values;
		this.chips = new HashMap<Chip, Integer>();
		
		for(int value : values) {
			this.chips.put(new Chip(value), 0);
		}
	}

	public int[] getValues() {
		return values;
	}
	
	public int addChips(Chip chip, Integer amount) {
		Integer current = this.chips.get(chip);
		this.chips.put(chip, current + amount);
		
		return this.totalChips();
	}
	
	public int removeChips(Chip chip, Integer amount) throws TooFewChipsException {
		Integer current = this.chips.get(chip);
		
		if(current > amount) {
			throw new TooFewChipsException();
		}
		
		this.chips.put(chip, current - amount);
		
		return this.totalChips();
	}
	
	public int totalChips() {
		int total = 0;
		
		for(Map.Entry<Chip, Integer> entry : this.chips.entrySet()) {
			total += entry.getKey().getValue() * entry.getValue();
		}
		
		return total;
	}

}
