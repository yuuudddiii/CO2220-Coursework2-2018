
public class DeadLock {	
	
	String s1 = "hello, ";
	String s2 = " world";

	Thread t1 = new Thread("Thread 1") {
		public void run() {
			while (true) {
				synchronized(this) {
					System.out.print(s1);
					}


			}
		}
	};

	Thread t2 = new Thread("Thread 2") {
		public void run() {
			while (true) {	
				synchronized(this) {
				System.out.print(s2);
				}
			} 
		}
	};

	public static void main(String [] args) {
		
		DeadLock d1 = new DeadLock();
		d1.t1.start();
		d1.t2.start();
	}
}
