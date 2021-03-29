package me.aandster.uon_webeng21_a1.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import me.aandster.uon_webeng21_a1.model.Booking;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Collection;

/* Project: SENG2050 A1 (2021)
 * Author:     Jordan Maddock
 * Email:      c3259753@uon.edu.au
 * Created:    28/3/21
 */
public class BookingTablePersistence {
	
	private final File persistenceFile;
	
	/**
	 * @param persistenceFile The file that will be used to read serialised JSON persistence file on server
	 */
	public BookingTablePersistence(File persistenceFile) {
		this.persistenceFile = persistenceFile;
	}
	
	/**
	 * Deserializes the JSON from file
	 * @return Collection of Booking objects
	 * @throws FileNotFoundException If file is not found...
	 */
	public Collection<Booking> deserialize() throws FileNotFoundException {
		
		// Define the type that Gson is deserializing into
		Type bookingCollectionType = new TypeToken<Collection<Booking>>(){}.getType();
		
		// Just creating the Gson object that will perform deserialization
		Gson gson = new Gson();
		
		// Deserialize and return
		return gson.fromJson(new FileReader(this.persistenceFile), bookingCollectionType);
	}
	
	/**
	 * @param bookings the Collection of Bookings that will be serialized
	 * @throws IOException Passed on from FileWriter operations
	 */
	public void serialize(Collection<Booking> bookings) {
		
		// Create the Gson object to serialize it
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		try (PrintWriter print = new PrintWriter(new FileWriter(this.persistenceFile))) {
			// Create a FileWriter for the file
			
			// Perform deserialization and write resulting Json to the FileWriter from above
			print.write(gson.toJson(bookings));
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
	}
}
