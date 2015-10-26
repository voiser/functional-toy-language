package runtime;

public class eq_ extends Func {

	@Override
	public Thing apply2(Thing a1, Thing a2) {
		Eq eq1 = (Eq)a1;
		Eq eq2 = (Eq)a2;
		return (Thing)eq1.eq(eq2);
	}
}
