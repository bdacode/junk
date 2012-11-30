package twitter;

public class LockedLinkedList {

	Object data;

	@Override
	protected void finalize() throws Throwable {
		// getSingleton().add(this);
	}

}
