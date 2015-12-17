package runtime;

public class Nil extends List {

	public Nil() {
		super(null, null);
	}
	
	@Override
	public String toString() {
		return "[]";
	}
}
