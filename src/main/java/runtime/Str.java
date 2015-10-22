package runtime;

public class Str extends Thing implements Eq {

    protected String s;
    
    public Str(String s) {
        this.s = s;
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
