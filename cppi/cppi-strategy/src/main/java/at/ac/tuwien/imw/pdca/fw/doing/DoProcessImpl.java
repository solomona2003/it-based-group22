package at.ac.tuwien.imw.pdca.fw.doing;

import at.ac.tuwien.imw.pdca.fw.DoProcess;

public class DoProcessImpl extends DoProcess
{
	private final DoRulesImpl doRules;

	public DoProcessImpl( final DoRulesImpl doRules )
	{
		this.doRules = doRules;
	}

	@Override
	public void operate()
	{
		this.doRules.applyDoRules();
	}
}
