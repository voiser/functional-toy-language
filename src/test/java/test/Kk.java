package test;

import runtime.Func;
import runtime.Int;
import runtime.Thing;
import runtime.add;

public class Kk extends Func {
    
    public Kk() {
    	
	}
    
    public void initialize (Thing other) {
    	
    }
    
    public static void main(String[] args) {
    	Func kk1 = new Kk();
    	Func kk2 = new Kk();
    	((Kk)kk1).initialize(kk2);
	} 
}
