package serviceDesk.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import serviceDesk.model.Ticket;
import serviceDesk.model.User;

public class UserDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String email;
	private String name;
	private String phone;
	
	private List<Ticket> tickets = new ArrayList<>();
	
	public UserDTO(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.phone = user.getPhone();
		this.email = user.getEmail();
		this.tickets = user.getTickets();
	}
	
	public UserDTO(Long id, String email, String name, String phone, List<Ticket> tickets) {
		this.id = id;
		this.email = email;
		this.name = name;
		this.phone = phone;
		this.tickets = tickets;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}
}