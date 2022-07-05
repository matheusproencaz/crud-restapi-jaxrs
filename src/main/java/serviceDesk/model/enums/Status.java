package serviceDesk.model.enums;

public enum Status {

	RESOLVED(1, "Resolved"),
	PENDING(2, "Pending"),
	ASSIGNED(3, "Assigned"),
	APPROVED(4, "Approved"),
	CANCELED(5, "Canceled");
	
	private int cod;
	private String description;
	
	private  Status(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}
	
	public int getCod() {
		return cod;
	}

	public String getDescription() {
		return description;
	}

	public static Status toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(Status x : Status.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid ID: "+cod);
	}
}