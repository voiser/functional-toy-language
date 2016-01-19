package runtime;

public class Export {
	public String name;
	public String type;
    public String[] overrides;
	
	public Export(String name, String type, String[] overrides) {
		this.name = name;
        this.type = type;
        this.overrides = overrides;
	}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Export(");
        sb.append(name);
        sb.append(",");
        sb.append(type);
        sb.append(",[");
        int i = 0;
        for (String over : overrides) {
            sb.append(over);
            if (++i < overrides.length) {
                sb.append(",");
            }
        }
        sb.append("])");
        return sb.toString();
    }
}
