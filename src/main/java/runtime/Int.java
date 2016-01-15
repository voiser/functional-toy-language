package runtime;

public class Int extends Thing implements Num {

    protected int i;
    
    public Int(int i) {
        this.i = i;
    }
    
    @Override
    public Num add(Num other) {
    	return new Int(i + ((Int)other).i);
    }
    
    @Override
    public Num sub(Num other) {
    	return new Int(i - ((Int)other).i);
    }
    
    @Override
    public Num times(Num other) {
    	return new Int(i * ((Int)other).i);
    }
    
    @Override
    public Num div(Num other) {
        return new Int(i / ((Int)other).i);
    }
    
    @Override
    public String toString() {
        return "" + i;
    }

    public static class eq extends Func {
        @Override
        public Thing apply2(Thing a, Thing b) {
            Int s1 = (Int)a;
            Int s2 = (Int)b;
            return new Bool(s1.i == s2.i);
        }
    }
}
