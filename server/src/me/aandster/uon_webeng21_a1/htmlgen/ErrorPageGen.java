package me.aandster.uon_webeng21_a1.htmlgen;

import me.aandster.uon_webeng21_a1.model.Booking;

import java.util.Objects;

/* Project: SENG2050 A1 (2021)
 * Author:     Jordan Maddock
 * Email:      c3259753@uon.edu.au
 * Created:    28/3/21
 */
public class ErrorPageGen extends AbstractHtmlGen<Booking> {
	
	
	/**
	 * @param content Non-nullable - Content to be generated.
	 */
	public ErrorPageGen(Booking content) {
		super(Objects.requireNonNull(content));
	}
	
	@Override
	public String generate() {
		// Build the message to show which person booked the seat
		return "The requested seat has already been booked. Details below:" +
				       "<br>" +
				       "<br>" +
				       "Sear Row: " + getContent().getSeat_row() + "<br>" +
				       "Sear Column: " + getContent().getSeat_col() + "<br>" +
				       "<br>" +
				       "Date Booked: " + getContent().getDate_booked() + "<br>" +
				       "<br>" +
				       "User ID: " + getContent().getUser_id() + "<br>" +
				       "User Phone: " + getContent().getUser_phone() + "<br>" +
				       "User Address: " + getContent().getUser_address() + "<br>" +
				       "User Email: " + getContent().getUser_email() + "<br>" +
				       "<br>";
	}
}
