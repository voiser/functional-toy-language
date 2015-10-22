package runtime;

public class Int extends Thing implements Num, Eq {

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
    public Thing eq(Eq other) {
    	boolean equals = i == ((Int)other).i;
    	return equals ? new True() : new False();
    }
    
    @Override
    public String toString() {
        return "" + i;
    }    
}
