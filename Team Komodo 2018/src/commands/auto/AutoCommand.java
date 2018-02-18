package commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import robotMain.Robot;
import subsystems.AutoController;

public abstract class AutoCommand extends Command {
	public boolean running;
	protected AutoController controller= Robot.driveSystem.getAutoController();
	
	protected AutoCommand() {
	}
	
	protected AutoCommand(int priority) {
	}
}
