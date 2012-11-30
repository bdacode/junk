package visa;

public class TrickClass {

	public static class Base {
		private int val = 100;
		
		public Base() {
			System.out.println("guess what is printed ? " + this.getVal());
		} 
		public int getVal() {
			return val;
		}
	}
	
	public static class Child extends Base {
		private int val = 10;
		public int getVal() {
			return val;
		}
	}
	
	public static void main(String[] args) {
		new Child();
	}
	
}


