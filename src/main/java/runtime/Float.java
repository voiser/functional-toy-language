package runtime;

public class Float extends Thing {

    protected float f;
    
    public Float(float f) {
        this.f = f;
    }
    
    @Override
    public String toString() {
        return "" + f;
    }
}
