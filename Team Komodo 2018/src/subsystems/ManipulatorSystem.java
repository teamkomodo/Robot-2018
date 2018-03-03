package subsystems;

import commands.teleop.TeleopManipulatorCommand;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import robotMain.RobotMap;

public class ManipulatorSystem extends Subsystem {
	private PWMSpeedController manipulatorController;
	
	public ManipulatorSystem() {
		manipulatorController = new Victor(RobotMap.manipulatorControllerPort);
	}

	@Override
	protected void initDefaultCommand() {
        //setDefaultCommand(new TeleopManipulatorCommand());
	}
  
    @Override
    public void periodic() {
    }
    
    public PWMSpeedController getManipulatorController() {
    	return manipulatorController;
    }

}
