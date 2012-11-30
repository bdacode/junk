package twitter;

public class JavaGCFinalizer {

	Object data;

	static Object hahaha = null;
	
	@Override
	protected void finalize() throws Throwable {
		System.out.println("finalize()");
		// getSingleton().add(this);
		hahaha = this;
	}
	
	public static void main(String[] args) {		
		JavaGCFinalizer f = new JavaGCFinalizer();
		f = null;
		System.out.println(f);
		System.gc();
		hahaha.toString();
	}

}
