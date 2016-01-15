package runtime;

public class Str extends Thing {

	protected char delim;
    protected String s;

    public static Str from(String s) {
        return new Str("'" + s + "'");
    }

    public Str(String s) {
    	this.delim = s.charAt(0);
        this.s = s.substring(1, s.length() - 1);
    }

    public static class eq extends Func {
        @Override
        public Thing apply2(Thing a, Thing b) {
            Str s1 = (Str)a;
            Str s2 = (Str)b;
            return new Bool(s1.s.equals(s2.s));
        }
    }

    @Override
    public String toString() {
        return s;
    }    
}
