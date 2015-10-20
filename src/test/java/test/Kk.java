package test;

import runtime.Func;
import runtime.Int;
import runtime.Thing;
import runtime.add;
import runtime.add.a;

public class Kk extends Func {

    public Thing outer;
    public Func id = new runtime.id();
    public Func do_ = new runtime.do_();
    
    public Kk() {
    	outer  = new runtime.Float(1.1f);
	}
    
    @Override
    public Thing apply1(Thing thing) {
    	
    	if (id.apply1(outer) instanceof runtime.True) {
    		return this;
    	} else {
    		return thing;
    	}
    }
    
    public static void main(String[] args) {
		new Kk().apply0();
	} 
}
