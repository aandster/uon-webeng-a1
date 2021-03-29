package me.aandster.uon_webeng21_a1.servlet;

/* Project: SENG2050 A1 (2021)
 * Author:     Jordan Maddock
 * Email:      c3259753@uon.edu.au
 * Created:    22/3/21
 */

import me.aandster.uon_webeng21_a1.htmlgen.ErrorPageGen;
import me.aandster.uon_webeng21_a1.model.Booking;
import me.aandster.uon_webeng21_a1.model.BookingTable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@WebServlet(urlPatterns = {"/book"})
public class BookingServlet extends AbstractWebEngA1Servlet {
	
	/**
	 * Handles GET HTTP requests.
	 *
	 * @param request  Client-request object
	 * @param response Client-response object
	 * @throws ServletException Refer to relevant documentation
	 * @throws IOException      Refer to relevant documentation
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Get the persisted BookingTable
		BookingTable bookingTable = getPersistedData(getServletConfig());
		
		// Get the request parameter
		String requestedSeat = request.getParameter("seat");
		
		// Error Response
		if (requestedSeat.length() != 2) {
			response.setStatus(400);
			response.getOutputStream().println("Error 400: Seat parameter is not 2 chars long.");
			return;
		}
		
		// Extract the row and column separately
		String requestedRow = String.valueOf(requestedSeat.charAt(0));
		String requestedCol = String.valueOf(requestedSeat.charAt(1));
		
		// Check that seat is available (true), and if not forward to error page (else)
		if (bookingTable.checkSeatAvailability(requestedRow, requestedCol)) {
			
			String clientReadyHtml = injectWithContent(
					requestedSeat,                      // raw param text
					getFileAsString(getServletConfig(), "/html/booking.html"),
					"<!--SEAT-CHOSEN-->");
			
			// Write out to client
			response.getWriter().println(clientReadyHtml);
			
		} else {
			
			// Generate dynamic part of error page
			Booking booking = bookingTable.getSeatBooking(requestedRow, requestedCol);
			ErrorPageGen errorPageGen = new ErrorPageGen(booking);
			String errorMessage = errorPageGen.generate();
			
			// Insert the error message
			String clientReadyHtml = injectWithContent(
					errorMessage,
					getFileAsString(getServletConfig(), "/html/error.html"),
					"<!-- INSERT-USER-INFO -->");
			
			// Write out to client
			response.getWriter().println(clientReadyHtml);
			
		}
		
		
		// todo
		
	}
	
	/**
	 * Handles POST HTTP requests.
	 *
	 * @param request  Client-request object
	 * @param response Client-response object
	 * @throws IOException Refer to relevant documentation
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		// Get the persisted BookingTable
		BookingTable bookingTable = getPersistedData(getServletConfig());
		
		// Get the UserID from request
		String userId = Objects.requireNonNull(request.getParameter("user_id"));
		
		// CHECK that the user can book another seat
		if (bookingTable.hasUserReachedBookingLimit(userId)) {
			
			// Insert the error message
			String clientReadyHtml = injectWithContent(
					"User has reached the max amount of bookings allowed.",
					getFileAsString(getServletConfig(), "/html/error.html"),
					"<!-- INSERT-USER-INFO -->");
			
			// Write out to client
			response.getWriter().println(clientReadyHtml);
			return;
			
		}
		
		// Set-up date for new booking
		String dateTime = (new Date()).toString();
		
		// Set-up requested seat row and column for new booking
		String requestedRow = request.getParameter("requested_seat_form").substring(0, 1);
		String requestedCol = request.getParameter("requested_seat_form").substring(1);
		
		// Making the booking
		Booking newBooking = new Booking(
				dateTime,
				requestedRow,
				requestedCol,
				userId,
				request.getParameter("user_phone"),
				request.getParameter("user_address"),
				request.getParameter("user_email")
				);
		
		bookingTable.insertBooking(newBooking);
		
		savePersistedData(getServletConfig(), bookingTable.getBookings());
		
		response.sendRedirect("/main");
		
	}
	
}
