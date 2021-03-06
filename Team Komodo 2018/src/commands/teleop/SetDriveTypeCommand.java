package commands.teleop;

import subsystems.DriveType;

import edu.wpi.first.wpilibj.command.Command;
import robotMain.Robot;

public class SetDriveTypeCommand extends Command {
	private DriveType type;
	
    public SetDriveTypeCommand(DriveType t) {
        requires(Robot.driveSystem);
        
        type = t;
        System.out.println("Setting drive type: "+type);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	System.out.println("Setting Drive Type: " + type.toString());
    	Robot.driveSystem.setDriveType(type);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
