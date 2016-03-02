package runtime;

public class dict extends Func {

	@Override
	public Thing apply2(Thing key, Thing value) {
		return new Dict(key, value);
	}
}
