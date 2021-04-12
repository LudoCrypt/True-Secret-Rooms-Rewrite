package net.ludocrypt.truerooms.util;

public class TripplePair<A, B, C> {
	private A first;
	private B second;
	private C third;

	public TripplePair(A first, B second, C third) {
		this.first = first;
		this.second = second;
		this.third = third;
	}

	public A getFirst() {
		return first;
	}

	public B getSecond() {
		return second;
	}

	public C getThird() {
		return third;
	}

}
