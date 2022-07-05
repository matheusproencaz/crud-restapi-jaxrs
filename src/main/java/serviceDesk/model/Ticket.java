package serviceDesk.model;

import java.time.Instant;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import serviceDesk.model.enums.Priority;
import serviceDesk.model.enums.Status;

@Entity
@Table(name = "Tickets")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String requestType;
	private String requestDetail; 
	private String notes;
	private Boolean isCompleted;
	private Integer status;
	private Integer priority;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Instant updatedTime;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public Ticket() {
	}
	
	public Ticket(Long id, String requestType, String requestDetail, String notes, Boolean isCompleted,
			Instant updatedTime, User user) {
		this.id = id;
		this.requestType = requestType;
		this.requestDetail = requestDetail;
		this.notes = notes;
		this.isCompleted = isCompleted;
		this.updatedTime = updatedTime;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getRequestDetail() {
		return requestDetail;
	}

	public void setRequestDetail(String requestDetail) {
		this.requestDetail = requestDetail;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Boolean getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(Boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public Instant getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Instant updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	public User getUser() {
		return user;
	}
	
	public Status getStatus() {
		return Status.toEnum(status);
	}

	public void setStatus(Status status) {
		this.status = status.getCod();
	}

	public Priority getPriority() {
		return Priority.toEnum(priority);
	}

	public void setPriority(Priority priority) {
		this.priority = priority.getCod();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		return Objects.equals(id, other.id);
	}
	
	@Override
	public String toString() {
		return "Ticket [id=" + id + ", requestType=" + requestType + ", requestDetail=" + requestDetail + ", notes="
				+ notes + ", isCompleted=" + isCompleted + ", updatedTime=" + updatedTime + "]";
	}
}
