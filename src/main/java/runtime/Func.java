package runtime;

public abstract class Func extends Thing {

    public Thing apply0() {
        throw new RuntimeException("This is a compiler bug");
    }

    public Thing apply1(Thing a1) {
        throw new RuntimeException("This is a compiler bug " + this.getClass());
    }
    
    public Thing apply2(Thing a1, Thing a2) {
        throw new RuntimeException("This is a compiler bug");
    }

    public Thing apply3(Thing a1, Thing a2, Thing a3) {
        throw new RuntimeException("This is a compiler bug");
    }
    
    public Thing apply4(Thing a1, Thing a2, Thing a3, Thing a4) {
        throw new RuntimeException("This is a compiler bug");
    }
    
    public Thing apply5(Thing a1, Thing a2, Thing a3, Thing a4, Thing a5) {
        throw new RuntimeException("This is a compiler bug");
    }
    
    public Thing apply6(Thing a1, Thing a2, Thing a3, Thing a4, Thing a5, Thing a6) {
        throw new RuntimeException("This is a compiler bug");
    }
    
    public Thing apply7(Thing a1, Thing a2, Thing a3, Thing a4, Thing a5, Thing a6, Thing a7) {
        throw new RuntimeException("This is a compiler bug");
    }
    
    public Thing apply78(Thing a1, Thing a2, Thing a3, Thing a4, Thing a5, Thing a6, Thing a7, Thing a8) {
        throw new RuntimeException("This is a compiler bug");
    }
}
