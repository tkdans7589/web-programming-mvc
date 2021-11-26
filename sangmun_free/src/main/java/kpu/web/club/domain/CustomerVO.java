package kpu.web.club.domain;

public class CustomerVO {
	
	private String customerId;
	private String customer_Password;
	private String customer_Name;
	private String customer_Phone;
	private String customer_Email;
	private String room_Check = "X";
	private String room_Num = "X";
	private String room_bookday = "X";
	private String room_bookduring = "X";
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerID) {
		this.customerId = customerID;
	}
	public String getCustomer_Password() {
		return customer_Password;
	}
	public void setCustomer_Password(String customer_Password) {
		this.customer_Password = customer_Password;
	}
	public String getCustomer_Name() {
		return customer_Name;
	}
	public void setCustomer_Name(String customer_Name) {
		this.customer_Name = customer_Name;
	}
	public String getCustomer_Phone() {
		return customer_Phone;
	}
	public void setCustomer_Phone(String customer_Phone) {
		this.customer_Phone = customer_Phone;
	}
	public String getCustomer_Email() {
		return customer_Email;
	}
	public void setCustomer_Email(String customer_Email) {
		this.customer_Email = customer_Email;
	}
	public String getRoom_Num() {
		return room_Num;
	}
	public void setRoom_Num(String room_Num) {
		this.room_Num = room_Num;
	}
	public String getRoom_Check() {
		return room_Check;
	}
	public void setRoom_Check(String room_Check) {
		this.room_Check = room_Check;
	}
	public String getRoom_bookday() {
		return room_bookday;
	}
	public void setRoom_bookday(String room_bookday) {
		this.room_bookday = room_bookday;
	}
	public String getRoom_bookduring() {
		return room_bookduring;
	}
	public void setRoom_bookduring(String room_bookduring) {
		this.room_bookduring = room_bookduring;
	}

}
