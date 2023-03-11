package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveDistance extends CommandBase{

    private DriveTrain RobotDrive;
    private double goal;
    private double margen;
    
    public DriveDistance(double distance, double margen){
        addRequirements(RobotDrive);
        this.goal = distance;
        this.margen = margen;
    }

    @Override  
    public void initialize(){
        RobotDrive.TargetDistanceAuto(this.goal);

    }
    
    @Override
    public void execute(){
        RobotDrive.gotoAuto();
    }

    @Override 
    public boolean isFinished(){
        return RobotDrive.getDistanceError() < this.margen;
    }
}
