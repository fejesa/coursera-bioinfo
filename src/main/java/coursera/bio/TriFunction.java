package coursera.bio;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface TriFunction<T, U, S, R> {

	/**
	 * Applies this function to the given arguments.
	 *
	 * @param t
	 *            the first function argument
	 * @param u
	 *            the second function argument
	 * @param s
	 *            the third function argument
	 * @return the function result
	 */
	R apply(T t, U u, S s);

	default <V> TriFunction<T, U, S, V> andThen(
			Function<? super R, ? extends V> after) {
		Objects.requireNonNull(after);
		return (T t, U u, S s) -> after.apply(apply(t, u, s));
	}
}
