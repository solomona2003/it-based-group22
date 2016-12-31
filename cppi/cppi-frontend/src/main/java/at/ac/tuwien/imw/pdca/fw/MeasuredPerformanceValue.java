package at.ac.tuwien.imw.pdca.fw;


/**
 * This is the result of performance measure
 * 
 * @author ivanstojkovic
 *
 * @param <T>
 */
public abstract class MeasuredPerformanceValue<T> {
	
	protected T value;
	
	public T getValue() {
		return value;
	}

	public MeasuredPerformanceValue(T value) {
		super();
		this.value = value;
	}
}
