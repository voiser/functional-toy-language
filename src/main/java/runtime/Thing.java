package runtime;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Thing {

    public void inspect() throws IllegalArgumentException, IllegalAccessException {
        System.out.println("Hi, this is " + this);
        for (Field f : this.getClass().getDeclaredFields()) {
            System.out.println("  field " + f.getName() + " = " + f.get(this));
        }
        for (Method m : this.getClass().getMethods()) {
            System.out.println("  method " + m.getName());
        }
    }
}
