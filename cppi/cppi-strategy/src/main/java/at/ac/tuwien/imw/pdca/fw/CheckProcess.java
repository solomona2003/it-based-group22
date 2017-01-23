package at.ac.tuwien.imw.pdca.fw;

public abstract class CheckProcess<T>
{

	public CheckingRules checkingRules;

	public ObjectiveSetting<T> objectiveSetting;

	public MeasuredPerformanceValue<T> performanceValue;

	public abstract Deviation<T>
			getCheckResult(ObjectiveSetting<T> objective, MeasuredPerformanceValue<T> performanceMeasureValue);

	public void setPerformanceValue(final MeasuredPerformanceValue<T> performanceValue)
	{
		this.performanceValue = performanceValue;
	}
}
