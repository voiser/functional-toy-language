package runtime;

public class Float extends Thing implements Num {

    protected float f;
    
    public Float(float f) {
        this.f = f;
    }
    
    @Override
    public Num add(Num other) {
    	return new Float(f + ((Float)other).f);
    }
    
    @Override
    public Num sub(Num other) {
    	return new Float(f - ((Float)other).f);
    }
    
    @Override
    public Num times(Num other) {
    	return new Float(f * ((Float)other).f);
    }
    
    @Override
    public Num div(Num other) {
    	return new Float(f / ((Float)other).f);
    }
    
    @Override
    public String toString() {
        return "" + f;
    }

    public static class eq extends Func {
        @Override
        public Thing apply2(Thing a, Thing b) {
            Float s1 = (Float)a;
            Float s2 = (Float)b;
            return new Bool(s1.f == s2.f);
        }
    }
}
