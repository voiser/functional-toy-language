package runtime;

public class Int extends Thing {

    protected int i;
    
    public Int(int i) {
        this.i = i;
    }
    
    @Override
    public String toString() {
        return "" + i;
    }    
}
