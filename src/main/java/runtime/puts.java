package runtime;

public class puts extends Func {

	private static Thing unit = new Unit();
	
	@Override
	public Thing apply1(Thing a) {
		System.out.println(a.toString());
		return unit;
	}	
}
