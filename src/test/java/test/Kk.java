package test;

import runtime.DynamicDispatch;
import runtime.Func;
import runtime.Int;
import runtime.Thing;

public class Kk extends Func {

	public Thing a;
	public Thing b;

	public Kk() {
	}

	@Override
	public String[] repr() {
		return new String[] { a.toString(), b.toString() };
	}
}
