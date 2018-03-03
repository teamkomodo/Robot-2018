package subsystems;

import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import robotMain.RobotMap;

public class ManipulatorRotatorSystem extends Subsystem {
private PWMSpeedController rotatorController;
	
	public ManipulatorRotatorSystem() {
		if (RobotMap.isCompetitionBot) {
			rotatorController = new Talon(RobotMap.rotatorControllerPort);
		}
		else {
			rotatorController = new Victor(RobotMap.rotatorControllerPort);
		}
	}

	@Override
	protected void initDefaultCommand() {
        //setDefaultCommand(new TeleopManipulatorCommand());
	}
  
    @Override
    public void periodic() {
    }
    
    public PWMSpeedController getManipulatorController() {
    	return rotatorController;
    }

}
