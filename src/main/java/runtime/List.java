package runtime;

public class List extends Thing {

	protected Thing head;
	protected List tail;
	
	public List(Thing head) {
		this.head = head;
		this.tail = new nil();
	}
	
	public List(Thing head, List tail) {
		this.head = head;
		this.tail = tail;
	}
	
	public String myrepr() {
		return head + (tail instanceof nil ? "" : ", " + tail.myrepr());
	}
	
	@Override
	public String toString() {
		return "[" + myrepr() + "]";
	}
	
	public static class size extends Func {
		@Override
		public Thing apply1(Thing a) {
			List l = (List)a;
			int i = 0; // the last element is nil
			while (l.tail != null) {
				l = l.tail;
				i++;
			}
			return new Int(i);
		}
	}
}
