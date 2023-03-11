// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.RobotClaw;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private final DriveTrain RobotDrive = new DriveTrain();
  private final ArmSubsystem EVO = new ArmSubsystem();
  private final RobotClaw robotClaw = new RobotClaw();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
   new CommandXboxController(OperatorConstants.DriverControllerPort);

  private final CommandXboxController m_ARMController =
   new CommandXboxController(OperatorConstants.ArmControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    RobotDrive.setDefaultCommand(Commands.run(() ->{ 
          RobotDrive.curvatureDrive(
              -m_driverController.getLeftY(),
              -m_driverController.getRightX()*.5);
              RobotDrive.Telemetry();
            },  
            RobotDrive));

    EVO.setDefaultCommand(Commands.run(() -> {
     EVO.useOutput();
    }, EVO));

  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */


  private void configureBindings() {

    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    //new Trigger(m_exampleSubsystem::exampleCondition)
      //  .onTrue(new ExampleCommand(m_exampleSubsystem));
  
  // SLOW

  m_driverController.rightBumper()
  .onTrue(Commands.runOnce(() -> RobotDrive.max(0.2)))
  .onFalse(Commands.runOnce(()-> RobotDrive.max(1)));

  // apagar y encender compresor
   m_ARMController.rightBumper().onTrue(
    Commands.runOnce(()->{
      robotClaw.CompressorToggle();
    }, robotClaw));
   
    // cerrar y abrir garra
   m_ARMController.a().onTrue(
    Commands.runOnce(() -> {
      robotClaw.toggle();
    }, robotClaw));
    // iddle 
    
    m_ARMController.y().onTrue(
      Commands.runOnce(
        ()->{
          EVO.Agarrar();
        }, EVO)
     )
     
     ;
    // poner en primer nivel
      m_ARMController.b().onTrue(
    Commands.runOnce(
      ()->{
        EVO._2doNivel();;
      }, EVO)
   );

   // crucero
   m_ARMController.x().onTrue(
    Commands.runOnce(
      ()->{
        EVO.Cruise();
      }, EVO)
   );
   

   m_ARMController.pov(180).whileTrue(Commands.runOnce(()->{
    EVO.MODGoal(0.02);
   }, EVO));
   m_ARMController.pov(0).whileTrue(Commands.runOnce(()->{
    EVO.MODGoal(-0.02);
   }, EVO));

   m_ARMController.pov(90).whileTrue(Commands.runOnce(()->{
    EVO.MODGoalElevator(0.06);
   }, EVO));
   m_ARMController.pov(270).whileTrue(Commands.runOnce(()->{
    EVO.MODGoalElevator(-0.06);
   }, EVO));


  }

  
  public void disablePIDSubsystems(){
    //EVO.disable();
  }
  
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

  
  public Command getAutonomousCommand() {
    
    return null;
  }

  public void INIT(){
    EVO.reset_encoder();
  }
}
