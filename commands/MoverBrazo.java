package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;

public class MoverBrazo extends CommandBase{

    private ArmSubsystem Evo;
    double goal;
    
    public MoverBrazo(double donde, ArmSubsystem evo){
        this.Evo = evo;
        this.goal = donde;
        addRequirements(this.Evo);
    }

    @Override
    public void initialize(){
        
        Evo.setGoal(goal, false);
    }
}
