package runtime;

public class List extends Thing {

	protected Thing head;
	protected List tail;
	
	public List(Thing head) {
		this.head = head;
		this.tail = null;
	}
	
	public List(Thing head, List tail) {
		this.head = head;
		this.tail = tail;
	}
	
	public String repr() {
		return head + (tail instanceof Nil ? "" : " " + tail.repr());
	}
	
	@Override
	public String toString() {
		return "[" + repr() + "]";
	}
	
	public static class size extends Func {
		@Override
		public Thing apply1(Thing a) {
			List l = (List)a;
			int i = 0; // the last element is Nil
			while (l.tail != null) {
				l = l.tail;
				i++;
			}
			return new Int(i);
		}
	}
}
