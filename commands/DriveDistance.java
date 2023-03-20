package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveDistance extends CommandBase{

    private DriveTrain RobotDrive;
    private double goal;
    private double margen;
    private double speed;
    
    
    public DriveDistance(double distance, double margen,double speed, DriveTrain robot){
        
        this.goal = distance;
        this.margen = margen;
        this.RobotDrive = robot;
        this.speed = speed;
        addRequirements(RobotDrive);
    }

    @Override  
    public void initialize(){
        RobotDrive.TargetDistanceAuto(this.goal);

    }
    
    @Override
    public void execute(){
        RobotDrive.gotoAuto(speed);
    }
    
    @Override
    public void end(boolean interrupted){

        RobotDrive.curvatureDrive(0, 0);
    }
    @Override 
    public boolean isFinished(){
        return Math.abs(RobotDrive.getDistanceError()) < this.margen;
    }
    
}
