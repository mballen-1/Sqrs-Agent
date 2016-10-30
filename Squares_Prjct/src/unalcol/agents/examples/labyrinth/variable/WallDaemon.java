package unalcol.agents.examples.labyrinth.variable;
import unalcol.agents.Action;
import unalcol.agents.AgentProgram;
import unalcol.agents.Percept;
import unalcol.random.raw.RawGenerator;

public class WallDaemon implements AgentProgram{
	protected double probability;
	public WallDaemon( double p ){
		probability = p;
	}
	
	@Override
	public Action compute(Percept p) {
		if( RawGenerator.next(this) < probability ){
			return new Action("change_walls");
		}
		return new Action("no_op");
	}

	@Override
	public void init() {
	}
}