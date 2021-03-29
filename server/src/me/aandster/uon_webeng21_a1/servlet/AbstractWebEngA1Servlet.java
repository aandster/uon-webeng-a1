package me.aandster.uon_webeng21_a1.servlet;

import me.aandster.uon_webeng21_a1.model.Booking;
import me.aandster.uon_webeng21_a1.model.BookingTable;
import me.aandster.uon_webeng21_a1.persistence.BookingTablePersistence;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;

/* Project: SENG2050 A1 (2021)
 * Author:     Jordan Maddock
 * Email:      c3259753@uon.edu.au
 * Created:    28/3/21
 */
public abstract class AbstractWebEngA1Servlet extends HttpServlet {
	
	private static final String PERSISTENCE_PATH = "/WEB-INF/persistence.json";
	
	/**
	 * Gets a BookingTable (business model) from the persistence file (JSON)
	 *
	 * @param servletConfig Servlet config to get the path from
	 * @return The booking table
	 * @throws IOException If error reading files
	 */
	protected static BookingTable getPersistedData(ServletConfig servletConfig) throws IOException {
		
		// Get the path to the save-location for the .json persistence file
		final String persistencePath = servletConfig.getServletContext().getRealPath(PERSISTENCE_PATH);
		
		// Create a BookingTable from the deserialized JSON
		BookingTablePersistence bookingTablePersistence =
				new BookingTablePersistence(new File(persistencePath));
		
		// Deserialize it
		return new BookingTable(bookingTablePersistence.deserialize());
	}
	
	protected static void savePersistedData(ServletConfig servletConfig, Collection<Booking> bookings)
			throws IOException {
		
		// Get the path to the save-location for the .json persistence file
		final String persistencePath = servletConfig.getServletContext().getRealPath(PERSISTENCE_PATH);
		
		// Create a BookingTable from the deserialized JSON
		BookingTablePersistence bookingTablePersistence =
				new BookingTablePersistence(new File(persistencePath)
				);
		
		// Serialise it
		bookingTablePersistence.serialize(bookings);
		
	}
	
	/**
	 * Injects some HTML in the indicated place
	 *
	 * @param payload   The thing to be injected into a place
	 * @param receiving The thing being injected with payload
	 * @return The full package
	 */
	protected static String injectWithContent(String payload, String receiving, String injectionMarker) {
		
		// Find where to insert the table in the String
		int insertionPosition = receiving.indexOf(injectionMarker);
		
		// Insert the table into the <table> tags
		
		return receiving.substring(0, insertionPosition)
				       + payload
				       + receiving.substring(insertionPosition);
	}
	
	public static String getFileAsString(ServletConfig servletConfig, String relPath) throws IOException {
		String pathToPage = servletConfig.getServletContext().getRealPath(relPath);
		return new String(Files.readAllBytes(Paths.get(pathToPage)));
	}
}
