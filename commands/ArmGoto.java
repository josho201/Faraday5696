package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;

public class ArmGoto extends CommandBase{
    
    ArmSubsystem Arm;
    private double goal;
    public ArmGoto(ArmSubsystem evo, double goal){
        this.Arm = evo;
        this.goal = goal;
    }

    @Override
    public void initialize(){
        Arm.setGoal(goal, false);
    }
    
    @Override
    public boolean isFinished(){
        return true;
    }
}
