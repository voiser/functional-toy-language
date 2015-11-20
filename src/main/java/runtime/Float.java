package runtime;

public class Float extends Thing implements Num, Eq {

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
    public Thing eq(Eq other) {
    	boolean equals = f == ((Float)other).f;
    	return equals ? new True() : new False();
    }
    
    @Override
    public Num div(Num other) {
    	return new Float(f / ((Float)other).f);
    }
    
    @Override
    public String toString() {
        return "" + f;
    }
}
