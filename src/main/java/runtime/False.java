package runtime;

public class False extends Thing implements Eq {

	@Override
	public String toString() {
		return "False";
	}
	
	@Override
	public Thing eq(Eq other) {
		return other instanceof False ? new True() : new False();
	}

}
