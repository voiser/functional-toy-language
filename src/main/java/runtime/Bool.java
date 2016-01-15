package runtime;

public class Bool extends Thing {

    public boolean b;

	@Override
	public String toString() {
		return b ? "true" : "false";
	}

    public Bool(boolean b) {
        this.b = b;
    }

    public static class eq extends Func {

        @Override
        public Thing apply2(Thing a, Thing b) {
            Bool b1 = (Bool)a;
            Bool b2 = (Bool)b;
            return new Bool(b1.b == b2.b);
        }
    }
}
