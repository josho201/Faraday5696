package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.RobotClaw;

public class Auto1 extends SequentialCommandGroup{
    public Auto1(DriveTrain Robot, ArmSubsystem Evo, RobotClaw Claw){
        addCommands(
            new ArmGoto(Evo, 0.12), // poner la garra en el nivel bajo
            new ToggleClaw(Claw), // soltar el cono
            
            new DriveDistance(-5, 0.2, Robot), //ir de reversa 5 metros
            new DriveDistance(-2.519, 0.2, Robot) //ir a 2.519 metros del inicio
        );
    }
}
