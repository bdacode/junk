import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class Producer implements Runnable {

	ArrayBlockingQueue<Integer> dataStore;
	int itemsToProcess;

	public Producer(ArrayBlockingQueue<Integer> dataStore, int itemsToProcess) {
		this.dataStore = dataStore;
		this.itemsToProcess = itemsToProcess;
	}

	public void run() {
		int count = 0;
		Random r = new Random(0); // make predictable random number seq for
									// testing
		try {
			while (count < itemsToProcess) {
				// developer#2: work like theres always tomorrow
				int sleepTime = (int) (r.nextDouble() * 1000);
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
				}
				//

				// produce
				int i = r.nextInt();
				dataStore.put(i);
				count++;
			}
		} catch (InterruptedException e) {
			// bad concurrency
			e.printStackTrace();
		}
	}

}
