package io;

import runtime.Func;
import runtime.Thing;

public class puts extends Func {

	public static String type = "a -> a";
	
	@Override
	public Thing apply1(Thing a) {
		System.out.println(a.toString());
		return a;
	}	
}
