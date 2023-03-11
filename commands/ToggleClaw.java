package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.RobotClaw;

public class ToggleClaw extends CommandBase{
    
    RobotClaw garra;
    public ToggleClaw(RobotClaw Claw){
        garra = Claw;
    }

    @Override
    public void initialize(){
        garra.toggle();
    }

    @Override 
    public boolean isFinished(){
        return true;
    }
}
