package serviceDesk.model.enums;

public enum Priority {
	
	LOW(1, "Low"),
	MEDIUM(2, "MEDIUM"),
	HIGH(3, "High"),
	URGENT(4, "URGENT");
	
	private int cod;
	private String description;
	
	private  Priority(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}
	
	public int getCod() {
		return cod;
	}

	public String getDescription() {
		return description;
	}

	public static Priority toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(Priority x : Priority.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid ID: "+cod);
	}
}
