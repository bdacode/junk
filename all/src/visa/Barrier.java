package visa;

import java.util.concurrent.atomic.AtomicInteger;

public class Barrier {

	static String lock = "lock";
	static AtomicInteger i = new AtomicInteger();

	public static void first() {
		System.out.println("first");
		i.incrementAndGet();
	}

	public static void second() {
		synchronized (lock) {
			while (i.intValue() != 1) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("second");
			i.incrementAndGet();
		}
	}

	public static void third() {
		synchronized (lock) {
			while (i.intValue() != 2) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("third");
			i.incrementAndGet();
		}
	}

	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				first();
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				second();
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				third();
			}
		}).start();

	}
}
