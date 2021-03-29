package me.aandster.uon_webeng21_a1.model;

/* Project: SENG2050 A1 (2021)
 * Author:     Jordan Maddock
 * Email:      c3259753@uon.edu.au
 * Created:    27/3/21
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class BookingTable {
	
	public static final String[] ROW_HEADS = {"A", "B", "C", "D", "E", "F", "G", "H"};
	
	public static final String[] COL_HEADS = {"1", "2", "3", "4", "5", "6", "7", "8"};
	
	public static final int MAX_ALLOWED_BOOKINGS_PER_USER = 3;
	
	/**
	 * Collection of bookings in theatre
	 */
	private final Collection<Booking> bookings;
	
	/**
	 * Just creates an empty bookings model
	 */
	public BookingTable() {
		this.bookings = new ArrayList<>();
	}
	
	/**
	 * Uses the bookings model that is passed to it
	 *
	 * @param bookings Simple collection of Booking objects
	 */
	public BookingTable(Collection<Booking> bookings) {
		if (Objects.isNull(bookings)) bookings = new ArrayList<>();
		this.bookings = (bookings);
	}
	
	/**
	 * @param seat_row Key of row, e.g. "A"
	 * @param seat_col Key of column, e.g. "1"
	 * @return True if seat is available, false if not.
	 */
	public boolean checkSeatAvailability(String seat_row, String seat_col) {
		
		// Look for a matching one
		for (Booking b : bookings) {
			if (b.getSeat_row().equals(seat_row) && b.getSeat_col().equals(seat_col)) return false;
		}
		return true;
		
	}
	
	/**
	 * @param seat_row Key of row, e.g. "A"
	 * @param seat_col Key of column, e.g. "1"
	 * @return The Booking object if booking is found, otherwise just null.
	 */
	public Booking getSeatBooking(String seat_row, String seat_col) {
		
		for (Booking b : bookings) {
			if (b.getSeat_row().equals(seat_row) && b.getSeat_col().equals(seat_col)) return b;
		}
		
		return null;
		
	}
	
	/**
	 * @param userId The user ID to check for max booking limit with
	 * @return true if they can book another, false if they have the limit already
	 */
	public boolean hasUserReachedBookingLimit(String userId) {
		
		int userBookingCount = 0;
		
		// Check along the way if a user has a booking, and then if the user has more than three bookings, we stop
		for (Booking b : bookings) {
			if (b.getUser_id() != null && b.getUser_id().equals(userId)
					    && ++userBookingCount >= MAX_ALLOWED_BOOKINGS_PER_USER) return true;   // >= 3 bookings
		}
		
		return false;    // < 3 bookings
		
	}
	
	/**
	 * Just insert a booking
	 * @param booking The booking to insert
	 */
	public void insertBooking(Booking booking) {
		
		this.bookings.add(Objects.requireNonNull(booking));
		
	}
	
	public Collection<Booking> getBookings() {
		return this.bookings;
	}
}
