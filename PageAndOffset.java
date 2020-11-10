/**
 * @author joshua warmer
 * ICS 462 Assignment 4
 * October 20, 2020
 * This program simulates passing a 32-bit virtual memory address with a 4-kb page size and then
 * calculates the page amount and the offset amount.
 */
package ics462.assignment4;

import java.io.FileWriter;
import java.io.IOException;

public class PageAndOffset {

	final static int pageSize = 4096;

	//Used to find the page number
	//@param takes in an integer virtual address
	public static int page(int i) {

		int pageNumber = i / pageSize;

		return pageNumber;
	}

	//Used to find the offset amount
	//@param takes in an integer virtual address
	public static int offset(int i) {

		int offset = i % pageSize;
		return offset;
	}

	//Writes to text file the findings
	public static void main(String[] args) {

		try {
			FileWriter writer = new FileWriter("E:\\METRO_STATE\\ICS462\\Assigment4.txt");
			writer.write("Joshua Warner");
			writer.write(System.getProperty("line.separator"));
			writer.write("ICS 462 Assignment #4");
			writer.write(System.getProperty("line.separator"));
			writer.write("October 20, 2020");
			writer.write(System.getProperty("line.separator"));
			writer.write(System.getProperty("line.separator"));
			writer.write("");
			writer.write("The address 19986 is in :");
			writer.write(System.getProperty("line.separator"));
			writer.write("	Page number = " + page(19986));
			writer.write(System.getProperty("line.separator"));
			writer.write("	Offset = " + offset(19986));
			writer.write(System.getProperty("line.separator"));
			writer.write(System.getProperty("line.separator"));
			writer.write("");
			writer.write("The address 347892 is in :");
			writer.write(System.getProperty("line.separator"));
			writer.write("	Page number = " + page(347892));
			writer.write(System.getProperty("line.separator"));
			writer.write("	Offset = " + offset(347892));
			writer.write(System.getProperty("line.separator"));
			writer.write(System.getProperty("line.separator"));
			writer.write("");
			writer.write("The address 5978 is in :");
			writer.write(System.getProperty("line.separator"));
			writer.write("	Page number = " + page(5978));
			writer.write(System.getProperty("line.separator"));
			writer.write("	Offset = " + offset(5978));
			writer.write(System.getProperty("line.separator"));
			writer.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();

		}

	}
}
