package me.aandster.uon_webeng21_a1.model;

/* Project: SENG2050 A1 (2021)
 * Author:     Jordan Maddock
 * Email:      c3259753@uon.edu.au
 * Created:    27/3/21
 */

public class Booking {
	
	// Booking creation date
	private String date_booked;
	
	// Booked seat specification
	private String seat_row;
	private String seat_col;
	
	// User detail specifications
	private String user_id;
	private String user_phone;
	private String user_address;
	private String user_email;
	
	/**
	 * @param date_booked  Date booked in ISO_LOCAL_DATE_TIME format
	 * @param seat_row     Seat row key
	 * @param seat_col     Seat column key
	 * @param user_id      User UID
	 * @param user_phone   User phone number
	 * @param user_address User mailing address
	 * @param user_email   User email address
	 */
	public Booking(String date_booked,
	               String seat_row,
	               String seat_col,
	               String user_id,
	               String user_phone,
	               String user_address,
	               String user_email) {
		
		this.date_booked = date_booked;
		this.seat_row = seat_row;
		this.seat_col = seat_col;
		this.user_id = user_id;
		this.user_phone = user_phone;
		this.user_address = user_address;
		this.user_email = user_email;
		
	}
	
	public String getDate_booked() {
		return date_booked;
	}
	
	public void setDate_booked(String date_booked) {
		this.date_booked = date_booked;
	}
	
	public String getSeat_row() {
		return seat_row;
	}
	
	public void setSeat_row(String seat_row) {
		this.seat_row = seat_row;
	}
	
	public String getSeat_col() {
		return seat_col;
	}
	
	public void setSeat_col(String seat_col) {
		this.seat_col = seat_col;
	}
	
	public String getUser_id() {
		return user_id;
	}
	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public String getUser_phone() {
		return user_phone;
	}
	
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	
	public String getUser_address() {
		return user_address;
	}
	
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	
	public String getUser_email() {
		return user_email;
	}
	
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
}
