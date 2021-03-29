package me.aandster.uon_webeng21_a1.htmlgen;

/* Project: SENG2050 A1 (2021)
 * Author:     Jordan Maddock
 * Email:      c3259753@uon.edu.au
 * Created:    27/3/21
 */

import me.aandster.uon_webeng21_a1.model.BookingTable;

import java.util.Objects;

public class BookingTableGen extends AbstractHtmlGen<BookingTable> {
	
	/**
	 * @param content Non-nullable - Content to be generated.
	 */
	public BookingTableGen(BookingTable content) {
		super(content);
		if (Objects.isNull(content)) setContent(new BookingTable());
	}
	
	/**
	 * Generates the HTML table that represents the stored TheatreBookingModel
	 *
	 * @return HTML booking table
	 */
	@Override
	public String generate() {
		
		BookingTable content = getContent();
		StringBuilder output = new StringBuilder();
		
		// Add in the header row
		output.append("<tr>");
		output.append("<td></td>");
		for (String col : BookingTable.COL_HEADS) {
			output.append("<th>")
					.append(col)
					.append("</th>");
		}
		output.append("</tr>");
		
		// Add in the rest of the table row-wise
		for (String rowName : BookingTable.ROW_HEADS) {
			
			// Open the row
			output.append("<tr>");
			
			// Put in the row header
			output.append("<td>")
					.append(rowName)
					.append("</td>");
			
			// Fill in the cells
			for (String colName : BookingTable.COL_HEADS) {
				
				// Check seat availability
				boolean seatAvailable = content.checkSeatAvailability(rowName, colName);
				
				// Determine cell class value: seat-free if free and seat-taken if taken
				String htmlClassValue = seatAvailable ? "seat-free" : "seat-taken";
				
				// Fill in a cell while checking its seat availability
				output.append("<td class=\"")
						
						// Indicated whether seat is available or free for formatting by CSS
						.append(htmlClassValue)
						.append("\">")
						
						// Link for proceeding to booking the seat
						.append("<a href=\"book?seat=")
						.append(rowName)
						.append(colName)
						.append("\">")
						
						// Actual text visible to user
						.append(rowName)
						.append(colName)
						
						// Closing up...
						.append("</a>")
						.append("</td>");
				
			}
			
			// Close the row
			output.append("</tr>");
		}
		
		return output.toString();
	}
}
