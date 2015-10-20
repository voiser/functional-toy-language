package runtime;

public class do_ extends Func {

	@Override
	public Thing apply2(Thing f, Thing p) {
		System.out.println("Hi, I'm do!");
		Func f_ = (Func)f;
		return f_.apply1(p);
	}
}
