import java.util.concurrent.ArrayBlockingQueue;

public class Consumer implements Runnable {

	ArrayBlockingQueue<Integer> dataStore;
	int itemsToProcess;

	public Consumer(ArrayBlockingQueue<Integer> dataStore, int itemsToProcess) {
		this.dataStore = dataStore;
		this.itemsToProcess = itemsToProcess;
	}

	public void run() {
		int count = 0;
		try {
			while (count < itemsToProcess) {
				// developer#1: work like there's no tomorrow
				int i;
				i = dataStore.take();
				System.out.println(i);
				count++;
			}
		} catch (InterruptedException e) {
			// bad concurrency
			e.printStackTrace();
		}
	}

}
