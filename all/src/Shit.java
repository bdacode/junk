public class Shit extends Thread {

	static I i = new I();
	
	int role;
	Shit(int role) {
		this.role = role;
	}
	
	@Override
	public void run() {		
		while (true) {
			if (role == 0) {
				i.getI();
				System.out.println(".");
			} else {
				try {
					Thread.sleep(10*1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				synchronized (i) {
					i.setI((int)(Math.random()*100));
				}
			}
		}
	}
	
	public static void main(String[] args) {
		String name = "amy";
		name.toUpperCase();
		System.out.println(name);
		new Shit(0).start();
		Thread t = new Shit(1);
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
