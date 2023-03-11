// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.RobotClaw;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;

public final class Autos {
  /** Example static factory for an autonomous command. */
  public static CommandBase exampleAuto(ExampleSubsystem subsystem) {
    return Commands.sequence(subsystem.exampleMethodCommand(), new ExampleCommand(subsystem));
  }
/* 
  public static CommandBase AUTO1(DriveTrain Robotdrive, ArmSubsystem EVO, RobotClaw garra){
    
    return Commands.sequence(
      EVO.setGoal(0.15, false), // mover la evo a poner por delante, tunear
      Robotdrive.TargetDistanceAuto(-5),// moverse hacia atras 5.08 metros (200 in) para salir del area de equipo
      Robotdrive.TargetDistanceAuto(0)
    )
  }*/
  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
