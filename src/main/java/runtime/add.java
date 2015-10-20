package runtime;

import runtime.Int;

public class add {

	public static class a extends Func {
		@Override
	    public Thing apply2(Thing a, Thing b) {
	        //System.out.println("Hi, I'm add$a!");
	        Int a_ = (Int)a;
	        Int b_ = (Int)b;
	        return new Int(a_.i + b_.i);
	    }
	}

	public static class b extends Func {
		@Override
	    public Thing apply2(Thing a, Thing b) {
	        //System.out.println("Hi, I'm add$b!");
	        Int a_ = (Int)a;
	        Float b_ = (Float)b;
	        return new Float(a_.i + b_.f);
	    }
	}

	public static class c extends Func {
		@Override
	    public Thing apply2(Thing a, Thing b) {
	        //System.out.println("Hi, I'm add$b!");
	        Float a_ = (Float)a;
	        Int b_ = (Int)b;
	        return new Float(a_.f + b_.i);
	    }
	}

	public static class d extends Func {
		@Override
	    public Thing apply2(Thing a, Thing b) {
	        //System.out.println("Hi, I'm add$b!");
	        Float a_ = (Float)a;
	        Float b_ = (Float)b;
	        return new Float(a_.f + b_.f);
	    }
	}
}
