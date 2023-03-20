package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;

public class ArmGoto extends CommandBase{
    
    ArmSubsystem Arm;
    private double goal;
    public ArmGoto(ArmSubsystem evo, double goal){
        
        this.Arm = evo;
        this.goal = goal;

        addRequirements(Arm);
    }

    @Override
    public void initialize(){
        Arm.setGoal(goal, false);
        
        Arm.updateGoalEVO();  
    }
    
    @Override
    public void execute(){
        Arm.useOutput();
    }

    @Override
    public boolean isFinished(){
        if(Math.abs(Arm.getPot()-goal) < 0.05){
            return true;
        } else{
            return false;
        }
    }
}
