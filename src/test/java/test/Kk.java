package test;

import runtime.Func;
import runtime.Int;
import runtime.Thing;

public class Kk extends Func {

	private Func divide;
	private Func ifok;
	private Func ifko;
	
	@Override
	public Thing apply0() {
		return divide.apply3(ifok, ifko, new Int(189887));
	}

}
