package runtime;

public class Str extends Thing implements Eq {

	protected char delim;
    protected String s;

    public static Str from(String s) {
        return new Str("'" + s + "'");
    }

    public Str(String s) {
    	this.delim = s.charAt(0);
        this.s = s.substring(1, s.length() - 1);
    }
    
    @Override
    public Thing eq(Eq other) {
    	boolean equals = s.equals(((Str)other).s);
    	return equals ? new True() : new False();
    }
    
    @Override
    public String toString() {
        return s;
    }    
}
