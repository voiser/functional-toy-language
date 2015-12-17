package runtime;

import java.lang.reflect.Method;

public class DynamicDispatch {

	public static Thing dispatch(String[] options, String[] types, Thing[] args) {
		int nOptions = options.length;
		int nArgs = args.length;
		
		String key = "";
		for (int i = 0; i < nArgs; i++) {
			key = key + args[i].getClass().getSimpleName();
			if (i < args.length - 1) {
				key = key + ";";
			}
		}
		
		String chosen = null;
		
		for (int i = 0; i < nOptions; i++) {
			if (key.equals(types[i])) {
				chosen = options[i];
			}
		}
		
		if (chosen == null) { 
			throw new RuntimeException("Can't dispatch");
		}
		
		try {
			Class<?> k = Class.forName(chosen);
			Class ak[] = new Class[nArgs];
			for (int i = 0; i < nArgs; i++) {
				ak[i] = Thing.class;
			}
			Method m = k.getDeclaredMethod("apply" + nArgs, ak);
			Object f = k.newInstance();
			
			Object ret = m.invoke(f, args);
			
			return (Thing)ret;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
