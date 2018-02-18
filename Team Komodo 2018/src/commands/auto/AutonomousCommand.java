// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import robotMain.Robot;

/**
 *
 */
public class AutonomousCommand extends Command {
	private DifferentialDrive robotDrive;
	
	private int driveCounter;
	private int stopValue;
	
    public AutonomousCommand() {
        requires(Robot.driveSystem);
        
        robotDrive = Robot.driveSystem.getDrive();
        
        // TODO Use encoders instead of this bs
        // Probably use some function in the DriveSubsystem to move x feet 
        driveCounter = 0;
        stopValue = 50;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	//robotDrive.tankDrive(-0.3, -0.3);
    	driveCounter++;
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	if (driveCounter > stopValue)
    		return true;
    	else
    		return false;
        //return (driveCounter > stopValue) ? true : false;
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
