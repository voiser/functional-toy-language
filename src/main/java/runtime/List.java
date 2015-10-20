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
}
