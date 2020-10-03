/**
 * Author:Joshua Warner
 * Date:9/22/2020
 * Assignment 2
 * This program contains 2 threads in which the program shares time on the CPU. A producer method and a consumer
 * method are used.
 */

package ics462.assignment2;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.io.FileWriter;
import java.io.IOException;

public class SharedMemory {

	static int sharedVariable = 100;

	/**
	 * At random time intervals, changes the shared variable to the loop count.
	 */
	public static void producer() {
		Random random = new Random();
		int loop = 0;

		while (loop < 5) {
			try {

				int wait = random.nextInt(3) + 1;
				// System.out.println(wait);

				TimeUnit.SECONDS.sleep(wait);
				sharedVariable = loop;

			} catch (InterruptedException e) {
				System.err.format("IOException: %s%n", e);
			}
			loop++;

			// System.out.println(System.nanoTime());
			// System.out.println(sharedVariable);
		}

	}

	/**
	 * 
	 * @return the sum of the observations of the shared variable with producer.
	 */
	public static int consumer() {
		Random random = new Random();
		int loop = 0;
		int sum = 0;
		while (loop < 5) {
			try {
				int wait = random.nextInt(3) + 1;
				TimeUnit.SECONDS.sleep(wait);
				sum += sharedVariable;
			} catch (InterruptedException e) {
				System.err.format("IOException: %s%n", e);
			}
			loop++;

			// System.out.println(sharedVariable);

		}

		return sum;
	}

	/**
	 * 
	 * @param args
	 * Runs the fileWriter to create the .txt.
	 */
	public static void main(String[] args) {
		// System.out.println(sharedVariable);
		Thread thread1 = new Thread() {
			public void run() {
				producer();
			}
		};
		thread1.start();
		// System.out.println("Joshua Warner");
		// System.out.println("ICS 462 Assigment #2");
		// System.out.println();
		// System.out.println("The sum is " + consumer());

		try {
			FileWriter writer = new FileWriter("E:\\METRO_STATE\\ICS462\\Assigment2.txt");
			writer.write("Joshua Warner");
			writer.write(System.getProperty("line.separator"));
			writer.write("ICS 462 Assigment #2");
			writer.write(System.getProperty("line.separator"));
			writer.write(System.getProperty("line.separator"));
			writer.write("");
			writer.write("The sum is " + consumer());
			writer.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();

		}
	}
}
