package runtime;

public class dict_of extends Func {

	@Override
	public Thing apply2(Thing key, Thing value) {
		return new Dict(key, value);
	}
}
