package test;

import runtime.*;

public class Kk extends Func {

	public Thing a;
	public Thing b;

    public class Blah extends Thing {
        public Thing a;
        public Thing b;
    }

	public Thing lala(Thing x) {

        Thing a;
        Thing b;
        Thing ret;

		if (x instanceof Blah) {
            a = ((Blah)x).a;
            b = ((Blah)x).b;
            ret = a;
		} else {
            ret = new Bool(false);
        }

        return ret;
	}

	@Override
	public String[] repr() {
		return new String[] { a.toString(), b.toString() };
	}
}
