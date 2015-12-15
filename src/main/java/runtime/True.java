package runtime;

public class True extends Thing implements Eq {

	@Override
	public String toString() {
		return "True";
	}

	@Override
	public Thing eq(Eq other) {
		return other instanceof True ? new True() : new False();
	}
}
