package runtime;

import java.util.Iterator;
import java.util.LinkedHashMap;

public class Dict extends Thing {

	protected LinkedHashMap<Thing, Thing> map = new LinkedHashMap<>();

	public Dict(Thing key, Thing value) {
		map.put(key, value);
	}
	
	private Dict(LinkedHashMap<Thing, Thing> old) {
		map.putAll(old);
	}
	
	public Thing extend(Thing k, Thing v) {
		Dict m = new Dict(map);
		m.map.put(k, v);
		return m;
	}
	
	public String repr() {
		StringBuilder sb = new StringBuilder();
		
		Iterator<Thing> it = map.keySet().iterator();
		while (it.hasNext()) {
			Thing key = it.next();
			Thing value = map.get(key);
			sb.append(key.toString() + ":" + value.toString());
			if (it.hasNext()) sb.append(", ");
		}
		return sb.toString();
	}
	
	@Override
	public String toString() {
		return "[" + repr() + "]";
	}
}
