package at.ac.tuwien.imw.pdca.fw;

/**
 *
 * @author ivanstojkovic
 *
 */
public abstract class ActProcess<T, K>
{

	public CorrectiveActRules correctiveActRules;

	public AdaptiveActRules adaptiveActRules;

	public abstract CorrectiveActOutput<T> act(Deviation<K> deviation);

}
