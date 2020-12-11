/**
 * @author joshua warner
 * Simulates disk scheduling algorithms
 */
package diskScheduling;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.io.FileWriter;
import java.io.IOException;

public class DiskSchedule {

	static int numCylinders;
	static int begin;
	static ArrayList<Integer> first = new ArrayList<Integer>();
	static int numCylinders2;
	static int begin2;
	static ArrayList<Integer> second = new ArrayList<Integer>();

	/**
	 * reads in text files
	 * @param path
	 */
	public static void reader(String path) {

		File file = new File(path);
		FileInputStream fstream = null;
		try {
			fstream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("File Not Accessible");
		}
		Scanner input = new Scanner(new InputStreamReader(fstream));

		input.useDelimiter("[\\ \\n\\r\\f]+");

		numCylinders = Integer.parseInt(input.next());

		begin = Integer.parseInt(input.next());

		int cursor = 0;

		while (cursor < 8) {

			first.add(Integer.parseInt(input.next()));
			cursor++;

		}
		numCylinders2 = Integer.parseInt(input.next());

		begin2 = Integer.parseInt(input.next());

		cursor = 0;

		while (cursor < 10) {

			second.add(Integer.parseInt(input.next()));
			cursor++;

		}
		input.close();

	}

	/**
	 * fcfs disk scheduling algorithm
	 * @param head
	 * @param list
	 * @return
	 */
	public static int fcfs(int head, ArrayList<Integer> list) {

		int sum = 0;

		sum = Math.abs(head - list.get(0));

		int cursor = 0;

		while (cursor < list.size() - 1) {
			sum += Math.abs(list.get(cursor) - list.get(cursor + 1));
			cursor++;
		}

		return sum;
	}

	//node for sstf algorithm
	static class sstfNode {
		int dist = 0;
		boolean used = false;
	}

	//difference between nodes
	public static void difference(ArrayList<Integer> list, int head, sstfNode listDifference[]) {
		for (int i = 0; i < listDifference.length; i++)
			listDifference[i].dist = Math.abs(list.get(i) - head);
	}

	//finds minimum difference
	public static int min(sstfNode listDifference[]) {
		int cursor = -Integer.MAX_VALUE, min = Integer.MAX_VALUE;

		for (int i = 0; i < listDifference.length; i++) {
			if (!listDifference[i].used && min > listDifference[i].dist) {
				min = listDifference[i].dist;
				cursor = i;
			}
		}
		return cursor;
	}

	/**
	 * sstf disk scheduling algorithm
	 * @param list
	 * @param head
	 * @return
	 */
	public static int sstf(ArrayList<Integer> list, int head)

	{
		if (list.size() == 0)
			return 0;

		sstfNode difference[] = new sstfNode[list.size()];

		for (int i = 0; i < difference.length; i++)

			difference[i] = new sstfNode();

		int[] seek_sequence = new int[list.size() + 1];

		int sum = 0;
		for (int i = 0; i < list.size(); i++) {

			seek_sequence[i] = head;
			difference(list, head, difference);

			int index = min(difference);

			difference[index].used = true;

			sum += difference[index].dist;

			head = list.get(index);
		}

		return sum;
	}

	
	/**
	 * scan disk scheduling algorithm
	 * @param head
	 * @param list
	 * @return
	 */
	public static int scan(int head, ArrayList<Integer> list) {
		int sum = 0;

		ArrayList<Integer> left = new ArrayList<Integer>();
		ArrayList<Integer> right = new ArrayList<Integer>();

		left.add(0);

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) < head) {
				left.add(list.get(i));
			}
			if (list.get(i) > head) {
				right.add(list.get(i));
			}
		}

		Collections.sort(left);
		Collections.sort(right);

		sum = head - left.get(left.size() - 1);

		for (int i = left.size() - 1; i > 0; i--) {

			sum += Math.abs(left.get(i) - left.get(i - 1));

		}

		sum += Math.abs(left.get(0) - right.get(0));

		for (int i = 0; i < right.size() - 1; i++) {

			sum += Math.abs(right.get(i) - right.get(i + 1));

		}

		return sum;
	}
	
	/**
	 * cscan disk scheduling algorithm
	 * @param head
	 * @param list
	 * @param cyl
	 * @return
	 */

	public static int cscan(int head, ArrayList<Integer> list, int cyl) {
		int sum = 0;

		ArrayList<Integer> left = new ArrayList<Integer>();
		ArrayList<Integer> right = new ArrayList<Integer>();

		left.add(0);

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) < head) {
				left.add(list.get(i));
			}
			if (list.get(i) > head) {
				right.add(list.get(i));
			}
		}

		Collections.sort(left);
		Collections.sort(right);

		sum += Math.abs(head - right.get(0));

		for (int i = 0; i < right.size() - 1; i++) {

			sum += Math.abs(right.get(i) - right.get(i + 1));

		}

		sum += Math.abs(right.get(right.size() - 1) - cyl) ;

		sum += Math.abs(cyl - left.get(0)) ;

		for (int i = 0; i < left.size() - 1; i++) {

			sum += Math.abs(left.get(i) - left.get(i + 1));

		}

		return sum;

	}

	
	/**
	 * look disk scheduling algorithm
	 * @param head
	 * @param list
	 * @return
	 */
	public static int look(int head, ArrayList<Integer> list) {
		int sum = 0;

		ArrayList<Integer> left = new ArrayList<Integer>();
		ArrayList<Integer> right = new ArrayList<Integer>();

		left.add(0);

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) < head) {
				left.add(list.get(i));
			}
			if (list.get(i) > head) {
				right.add(list.get(i));
			}
		}

		Collections.sort(left);
		Collections.sort(right);

		//System.out.println(left.toString());
		//System.out.println(right.toString());

		

		sum += Math.abs(head - right.get(0));
		//System.out.println(sum);

		for (int i = 0; i < right.size() - 1; i++) {

			sum += Math.abs(right.get(i) - right.get(i + 1));
			//System.out.println(sum);

		}
		
		sum += Math.abs(right.get(right.size() - 1) - left.get(left.size() - 1));
		//System.out.println(sum);
		
		for (int i = left.size() - 1; i > 0; i--) {

			sum += Math.abs(left.get(i) - left.get(i - 1));
			//System.out.println(sum);

		}

		return sum;
	}

	/**
	 * clook disk scheduling algorithm
	 * @param head
	 * @param list
	 * @return
	 */
	public static int clook(int head, ArrayList<Integer> list) {
		int sum = 0;

		ArrayList<Integer> left = new ArrayList<Integer>();
		ArrayList<Integer> right = new ArrayList<Integer>();

		;

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) < head) {
				left.add(list.get(i));
			}
			if (list.get(i) > head) {
				right.add(list.get(i));
			}
		}

		Collections.sort(left);
		Collections.sort(right);

		//System.out.println(left.toString());
		//System.out.println(right.toString());

		

		sum += Math.abs(head - right.get(0));
		//System.out.println(sum);

		for (int i = 0; i < right.size() - 1; i++) {

			sum += Math.abs(right.get(i) - right.get(i + 1));
			//System.out.println(sum);

		}
		
		sum += Math.abs(right.get(right.size() - 1) - left.get(0));
		//System.out.println(sum);
		
		for (int i = 0; i < left.size() - 1; i++) {

			sum += Math.abs(left.get(i) - left.get(i + 1));
			//System.out.println(sum);

		}

		return sum;
	}
	
	//writes to a file

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		reader("E:\\METRO_STATE\\ICS462\\assignment 6\\Asg6Data.txt");
//		System.out.println(numCylinders);
//		System.out.println(begin);
//		System.out.println(first.toString());
//		System.out.println(numCylinders2);
//		System.out.println(begin2);
//		System.out.println(second.toString());
//		System.out.println(fcfs(begin, first));
//		System.out.println(sstf(first, begin));
//		System.out.println(fcfs(begin2, second));
//		System.out.println(sstf(second, begin2));
//		System.out.println(scan(begin, first));
//		System.out.println(scan(begin2, second));
//		System.out.println(cscan(begin, first,numCylinders));
//		System.out.println(cscan(begin2, second, numCylinders2));
//		System.out.println(look(begin, first));
//		System.out.println(look(begin2, second));
//		System.out.println(clook(begin, first));
//		System.out.println(clook(begin2, second));
		try {
			FileWriter writer = new FileWriter("E:\\METRO_STATE\\ICS462\\Assigment6.txt");
			writer.write("Joshua Warner,12.2.2020, Assignment 6.");
			writer.write(System.getProperty("line.separator"));
			writer.write(System.getProperty("line.separator"));
			writer.write("For FCFS, the total head movement was " + fcfs(begin, first) + " cylinders.");
			writer.write(System.getProperty("line.separator"));
			writer.write("For SSTF, the total head movement was " + sstf(first, begin) + " cylinders.");
			writer.write(System.getProperty("line.separator"));
			writer.write("For SSTF, the total head movement was " + scan(begin, first) + " cylinders.");
			writer.write(System.getProperty("line.separator"));
			writer.write("For SSTF, the total head movement was " + cscan(begin, first,numCylinders) + " cylinders.");
			writer.write(System.getProperty("line.separator"));
			writer.write("For SSTF, the total head movement was " + look(begin, first) + " cylinders.");
			writer.write(System.getProperty("line.separator"));
			writer.write("For SSTF, the total head movement was " + clook(begin, first) + " cylinders.");
			writer.write(System.getProperty("line.separator"));
			writer.write(System.getProperty("line.separator"));
			writer.write(System.getProperty("line.separator"));
			
			writer.write("For FCFS, the total head movement was " + fcfs(begin2, second) + " cylinders.");
			writer.write(System.getProperty("line.separator"));
			writer.write("For SSTF, the total head movement was " + sstf(second, begin2) + " cylinders.");
			writer.write(System.getProperty("line.separator"));
			writer.write("For SSTF, the total head movement was " + scan(begin2, second) + " cylinders.");
			writer.write(System.getProperty("line.separator"));
			writer.write("For SSTF, the total head movement was " + cscan(begin2, second,numCylinders2) + " cylinders.");
			writer.write(System.getProperty("line.separator"));
			writer.write("For SSTF, the total head movement was " + look(begin2, second) + " cylinders.");
			writer.write(System.getProperty("line.separator"));
			writer.write("For SSTF, the total head movement was " + clook(begin2, second) + " cylinders.");
			writer.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();

		}
		

	}

}
