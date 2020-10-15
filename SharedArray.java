/**
 * @author joshua warner
 * ICS 462
 * This program was made to represent a circular buffer.
 */
package ics462.assignment3;

//Random used to simulate buffer, TimeUnit to accomplish that, with FileWriter to write
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.io.IOException;
import java.io.FileWriter;

public class SharedArray {

	//The shared array and 2 variables to finish the program
	static int[] sharedArray = new int[5];
	static boolean[] read = new boolean[5];
	static int cursor = 0;

	//Simulates a producer process which in intervals produces numbers
	public static void producer() {
		int loopCount = 0;
		int arrayCursor = 0;
		Random random = new Random();

		while (loopCount != 100) {

			if (arrayCursor == 5) {
				arrayCursor = 0;
			}

			try {

				if (read[arrayCursor] == true) {
					int wait = random.nextInt(5) + 1;
					TimeUnit.SECONDS.sleep(wait);
					sharedArray[arrayCursor] = loopCount;
					loopCount++;
					read[arrayCursor] = false;
					// System.out.println(sharedArray[arrayCursor]);
					arrayCursor++;
				} else
					TimeUnit.SECONDS.sleep(1);

			} catch (InterruptedException e) {
				System.err.format("IOException: %s%n", e);
			}

		}
		if (arrayCursor == 5) {
			arrayCursor = 0;
		}
		while(read[arrayCursor] == false) {

			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				System.err.format("IOException: %s%n", e);
			}
		}
		sharedArray[arrayCursor] = -1;

	}

	//Simulates a consumer process which takes what the producer produces and writes it
	public static int consumer() {
		try {
			FileWriter writer = new FileWriter("E:\\METRO_STATE\\ICS462\\Assigment3_test3.txt");
			writer.write("Joshua Warner");
			writer.write(System.getProperty("line.separator"));
			writer.write("ICS 462 Assigment #3");

			Random random = new Random();
			while (sharedArray[cursor] != -1) {

				try {
					try {
						int wait = random.nextInt(4) + 2;
						TimeUnit.SECONDS.sleep(wait);
					} catch (InterruptedException e) {
						System.err.format("IOException: %s%n", e);
					}

					if (read[cursor] == false) {
						writer.write(System.getProperty("line.separator"));
						writer.write(String.valueOf(sharedArray[cursor]));

						read[cursor] = true;
						//System.out.println(sharedArray[cursor]);
						cursor++;
						if (cursor == 5) {
							cursor = 0;
						}

					} else {
						writer.write(System.getProperty("line.separator"));
						writer.write("consumer waiting");
						try {
							TimeUnit.SECONDS.sleep(1);
						} catch (InterruptedException e) {
							System.err.format("IOException: %s%n", e);
						}

					}

				} catch (IOException e) {
					System.out.println("An error occurred.");
					e.printStackTrace();
				}

			}
			writer.write(System.getProperty("line.separator"));
			writer.write("Consumer done");
			writer.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		return 0;
	}

	//Instantiates two threads to create the sharedArray process
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 5; i++) {
			read[i] = true;
		}

		Thread thread1 = new Thread() {
			public void run() {
				producer();
			}
		};
		thread1.start();
		consumer();
	}

}
