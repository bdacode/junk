import java.util.concurrent.ArrayBlockingQueue;


public class Driver {

	static int capacity = 100;

	public static void main(String[] args) {
		
		// i use java's built in concurrent collection as the data store
		ArrayBlockingQueue<Integer> dataStore = new ArrayBlockingQueue<Integer>(capacity);

		Thread producer = new Thread(new Producer(dataStore,capacity));
		Thread consumer = new Thread(new Consumer(dataStore,capacity));
		
		producer.start();
		consumer.start();
		
		try {
			producer.join();
			consumer.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
