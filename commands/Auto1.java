package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.RobotClaw;

public class Auto1 extends SequentialCommandGroup{
    public Auto1(DriveTrain Robot, ArmSubsystem Evo, RobotClaw Claw){
        addCommands(
            new ArmGoto(Evo, 0.2
            ), // poner la garra en el nivel bajo
            new ToggleClaw(Claw), // soltar el cono
            new ArmGoto(Evo, 0.2),
            new DriveDistance(-1.89, 0.1,0.6, Robot), //balace auto
            //new DriveDistance(-3.4, 0.1,0.6, Robot),
            new ArmGoto(Evo, 0.25)
        );
    }
}
