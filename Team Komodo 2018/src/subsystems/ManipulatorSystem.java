package subsystems;

import commands.teleop.TeleopManipulatorCommand;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import robotMain.RobotMap;

public class ManipulatorSystem extends Subsystem {
	private Victor manipulatorVictors;
	
	public ManipulatorSystem() {
		manipulatorVictors = new Victor(RobotMap.manipulatorVictorsPort);
	}

	@Override
	protected void initDefaultCommand() {
        //setDefaultCommand(new TeleopManipulatorCommand());
	}
  
    @Override
    public void periodic() {
    }
    
    public Victor getManipulatorVictors() {
    	return manipulatorVictors;
    }

}
