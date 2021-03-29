package me.aandster.uon_webeng21_a1.servlet;

/* Project: SENG2050 A1 (2021)
 * Author:     Jordan Maddock
 * Email:      c3259753@uon.edu.au
 * Created:    22/3/21
 */

import me.aandster.uon_webeng21_a1.htmlgen.BookingTableGen;
import me.aandster.uon_webeng21_a1.model.BookingTable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet(urlPatterns = {"/main"})
public class MainServlet extends AbstractWebEngA1Servlet {
	
	/**
	 * Handles GET HTTP requests.
	 *
	 * @param request  Client-request object
	 * @param response Client-response object
	 * @throws ServletException Refer to relevant documentation
	 * @throws IOException      Refer to relevant documentation
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BookingTable bookingTable = getPersistedData(getServletConfig());
		
		// Generate the HTML for the BookingTable
		BookingTableGen tableGen = new BookingTableGen(bookingTable);
		String payload = tableGen.generate();
		
		// Inject the page with the table contents
		String clientReadyHtml = injectWithContent(
				payload,
				getFileAsString(getServletConfig(), "/html/index.html"),
				"<!-- TABLE-INSERT -->");
		
		// Return it
		response.getWriter().println(clientReadyHtml);
	
	}
	
	/**
	 * Handles POST HTTP requests.
	 *
	 * @param request  Client-request object
	 * @param response Client-response object
	 * @throws ServletException Refer to relevant documentation
	 * @throws IOException      Refer to relevant documentation
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Using doGet(...) to avoid code duplication
		doGet(request, response);
	}
	
}
