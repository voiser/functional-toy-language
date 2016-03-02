package runtime;

public class nil extends List {

	public nil() {
		super(null, null);
	}
	
	@Override
	public String toString() {
		return "[]";
	}
}
