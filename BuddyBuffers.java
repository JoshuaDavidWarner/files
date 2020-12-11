/**
 * @author joshua warner
 * ICS462 Phil Lamb
 * This program simulates a buffer system.
 */
package buffer;

public class BuddyBuffers {

	int[] buffer = new int[7];
	int[] memory = new int[800];
	
	int pointer = 0;
	//these push data
	public BuddyBuffers() {

		buffer[0] = 10;
	}

	private void five12() {

		buffer[0] = buffer[0] - 1;

	}

	private void two55() {

		buffer[1] = buffer[1] - 1;

	}

	private void one27() {

		buffer[2] = buffer[2] - 1;

	}

	private void six3() {

		buffer[3] = buffer[3] - 1;

	}

	private void three1() {

		buffer[4] = buffer[4] - 1;

	}

	private void one5() {

		buffer[5] = buffer[5] - 1;

	}

	private void seven() {

		buffer[6] = buffer[6] - 1;

	}

	//these check to make sure there is data to be pushed
	private int push511() {

		if (buffer[0] > 0) {
			five12();
			return 1;
		}

		return -1;
	}

	private int push255() {

		if (buffer[1] > 0) {
			two55();
			return 1;
		}

		return -1;
	}

	private int push127() {

		if (buffer[2] > 0) {
			one27();
			return 1;
		}

		return -1;
	}

	private int push63() {

		if (buffer[3] > 0) {
			six3();
			return 1;
		}

		return -1;
	}

	private int push31() {

		if (buffer[4] > 0) {
			three1();
			return 1;
		}

		return -1;
	}

	private int push15() {

		if (buffer[5] > 0) {
			one5();
			return 1;
		}
		return -1;
	}

	private int push7() {

		if (buffer[6] > 0) {
			seven();
			return 1;
		}

		return -1;
	}

	//gets data from other buffer sizes
	private int req255() {

		if (push255() == -1) {
			if (push511() == -1) {
				return -1;
			} else
				buffer[1] += 1;
		}

		return 1;
	}

	private int req127() {

		if (push127() == -1) {
			if (req255() == -1) {
				return -1;
			} else
				buffer[2] += 1;
		}

		return 1;
	}

	private int req63() {

		if (push63() == -1) {
			if (req127() == -1) {
				return -1;
			} else
				buffer[3] += 1;
		}

		return 1;
	}

	private int req31() {

		if (push31() == -1) {
			if (req63() == -1) {
				return -1;
			} else
				buffer[4] += 1;
		}

		return 1;
	}

	private int req15() {

		if (push15() == -1) {
			if (req31() == -1) {
				return -1;
			} else
				buffer[5] += 1;
		}

		return 1;
	}

	private int req7() {

		if (push7() == -1) {
			if (req15() == -1) {
				return -1;
			} else
				buffer[6] += 1;
		}

		return 1;
	}

	//actual request public method
	public int request(int value) {
		if(value > 511) {
			return -2;
		}
		if(value < 7) {
			return -2;
		}
		
		if(value > 255 & value < 512) {
			
			if(this.push511() == -1) {
				return -1;
			} else {
				memory[pointer] = 511;
				
				pointer++;
			}
		}
		if(value > 127&value < 256) {
			
			if(this.req255() == -1) {
				return -1;
			}else {
				memory[pointer] = 255;
				
				pointer++;
			}
		}
		if(value > 63&value < 128) {
			if(this.req127() == -1) {
				return -1;
			}else {
				memory[pointer] = 127;
				
				pointer++;
			}
		}
		if(value > 31&value < 64) {
			if(this.req63() == -1) {
				return -1;
			}else {
				memory[pointer] = 63;
				
				pointer++;
			}
		}
		if(value > 15&value < 32) {
			if(this.req15() == -1) {
				

				return -1;
			}else {
				memory[pointer] = 31;
				
				pointer++;
			}
		}
		//Problem here
		if(value > 7&value < 16) {
			if(this.req7() == -1) {
				
				return -1;
			}else {
				memory[pointer] = 7;
				
				pointer++;
			}
		}
		
		return pointer - 1;
	}

	//the return method
	public void rtrn (int addr) {
		
		if (memory[addr] == 511) {
			buffer[0]++;
		}
		if (memory[addr] == 255) {
			buffer[1]++;
		}
		if (memory[addr] == 127) {
			buffer[2]++;
		}
		if (memory[addr] == 63) {
			buffer[3]++;
		}
		if (memory[addr] == 31) {
			buffer[4]++;
		}
		if (memory[addr] == 15) {
			buffer[5]++;
		}
		if (memory[addr] == 7) {
			buffer[6]++;
		}
		
				
		
		if(buffer[6] == 2) {
			buffer[6] = 0;
			buffer[5]++;
		}
		if(buffer[5] == 2) {
			buffer[5] = 0;
			buffer[4]++;
		}
		if(buffer[4] == 2) {
			buffer[4] = 0;
			buffer[3]++;
		}
		if(buffer[3] == 2) {
			buffer[3] = 0;
			buffer[2]++;
		}
		if(buffer[2] == 2) {
			buffer[2] = 0;
			buffer[1]++;
		}
		if(buffer[1] == 2) {
			buffer[1] = 0;
			buffer[0]++;
		}
		
		
	}

	//tells status of buffer manager
	public String isTight() {
		
		if (this.buffer[0] < 3) {
			return "tight";
		}
		
		return "OK";
	}

	//checks the buffer manager
	public void debug() {
		System.out.println("Status: " + this.isTight());
		System.out.println("");
		System.out.println(buffer[0] + " 511 size buffers");
		System.out.println(buffer[1] + " 255 size buffers");
		System.out.println(buffer[2] + " 127 size buffers");
		System.out.println(buffer[3] + " 63 size buffers");
		System.out.println(buffer[4] + " 31 size buffers");
		System.out.println(buffer[5] + " 15 size buffers");
		System.out.println(buffer[6] + " 7 size buffers");
	}
	
	


}
