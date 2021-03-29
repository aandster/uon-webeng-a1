package me.aandster.uon_webeng21_a1.htmlgen;

/* Project: SENG2050 A1 (2021)
 * Author:     Jordan Maddock
 * Email:      c3259753@uon.edu.au
 * Created:    22/3/21
 */

import java.util.Objects;

/**
 * This class kind-of lays out a base for other HTML generator classes to extend.
 * <p>
 * Intended usage: instantiate with content, optionally change set content, and generate/return HTML for
 * corresponding content
 *
 * @param <T> Type of data that the concrete class will accept to generate input
 */
public abstract class AbstractHtmlGen<T> {
	
	 private T content;
	
	/**
	 * Just a convenience method for creating individual tag elements
	 *
	 * @param tag Tag type, e.g. "H1"
	 * @param contents Tag contents, e.g. "Title Name"
	 * @param attributes Tag attributes, e.g. "id=\"yes\""
	 * @return Generated HTML element, e.g. <h1 id="yes">Title Name</h1>
	 */
	public static String wrapWithTag(String tag, String contents, String... attributes) {
		
		StringBuilder stringBuilder = new StringBuilder();
		
		// Open opening tag, leaving open for HTML attributes
		stringBuilder.append("<").append(tag);
		
		// Add in attributes
		for (String a : attributes) stringBuilder.append(a);
		
		// Close the opening tag
		stringBuilder.append(">");
		
		// Append the contents
		stringBuilder.append(contents);
		
		// Close it all up
		stringBuilder.append("</").append(tag).append(">");
		
		// Give it all back :)
		return stringBuilder.toString();
		
	}
	
	/**
	 * @param content Non-nullable - Content to be generated.
	 */
	public AbstractHtmlGen(T content) {
		setContent(content);
	}
	
	/**
	 * Generates and returns HTML.
	 *
	 * @return Generated HTML
	 */
	public abstract String generate();
	
	/**
	 * Set content to be generated in the HTML.
	 *
	 * @param content Non-nullable heading will be empty
	 */
	protected void setContent(T content) {
		this.content = Objects.requireNonNull(content);
	}
	
	/**
	 * @return Content previously set
	 */
	protected T getContent() {
		return this.content;
	}
}
