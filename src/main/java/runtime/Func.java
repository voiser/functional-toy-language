package runtime;

public abstract class Func extends Thing {

    public Thing apply0() {
        throw new RuntimeException("This is a compiler bug");
    }

    public Thing apply1(Thing a1) {
        throw new RuntimeException("This is a compiler bug");
    }
    
    public Thing apply2(Thing a1, Thing a2) {
        throw new RuntimeException("This is a compiler bug");
    }
    
}
