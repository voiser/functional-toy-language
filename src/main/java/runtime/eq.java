package runtime;

public class eq extends Func {

	@Override
	public Thing apply2(Thing a1, Thing a2) {
		//System.out.println("Hi! I'm eq and I have to compare " + a1 + " (" + a1.getClass() + ") with " + a2 + " (" + a2.getClass() + ")");
		if (a1 instanceof Int && a2 instanceof Int) {
			return ((Int)a1).i == ((Int)a2).i ? new True() : new False();
		}
		return new False();
	}
}
