package runtime;

import java.util.Iterator;
import java.util.LinkedHashMap;

public class Java extends Thing {

    public Object o;

	public Java(Object o) {
		this.o = o;
	}

	@Override
	public String toString() {
		return o.toString();
	}
}
