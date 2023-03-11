package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.AutoConst;
import frc.robot.Constants.DriveConstants;


public class DriveTrain extends SubsystemBase{
    private final CANSparkMax RightD1 = new CANSparkMax(DriveConstants.CANid_chassis_l, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax RightD2 = new CANSparkMax(DriveConstants.CANid_chassis_2, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax LeftD1 = new CANSparkMax(DriveConstants.CANid_chassis_3, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax LeftD2 = new CANSparkMax(DriveConstants.CANid_chassis_4, CANSparkMaxLowLevel.MotorType.kBrushless);
    

    RelativeEncoder R_encoder;
    RelativeEncoder L_encoder;

    private final DifferentialDrive myDrive;
    AHRS NAVX;
    DifferentialDriveOdometry m_Odometry;


    private double speed = 1;

    //auto
    
    private final PIDController R_PID = new PIDController(
        AutoConst.kPauto,
        AutoConst.kIauto,
        AutoConst.kDauto
        );
    
    private final PIDController L_PID = new PIDController(
            AutoConst.kPauto,
            AutoConst.kIauto,
            AutoConst.kDauto
            );
    private double goalAuto;

    public DriveTrain(){
        RightD1.restoreFactoryDefaults();
        RightD2.restoreFactoryDefaults();
        LeftD1.restoreFactoryDefaults();
        LeftD2.restoreFactoryDefaults();

        RightD2.follow(RightD1);
        LeftD2.follow(LeftD1);

        RightD1.setInverted(true);
        
        R_encoder = RightD1.getEncoder();
        L_encoder = RightD1.getEncoder();
        myDrive = new DifferentialDrive(LeftD1 , RightD1 );

        try {
            NAVX = new AHRS(SPI.Port.kMXP);
        } catch (Exception e) {
            DriverStation.reportError("NAVX ERROR"+e.getMessage(), true);
        }
        m_Odometry = new DifferentialDriveOdometry(NAVX.getRotation2d(), metersL_encoder(), metersR_encoder());
        
        //auto 
        R_PID.setSetpoint(0);
        L_PID.setSetpoint(0);
    }

    public void curvatureDrive(double fwd, double rt){
        myDrive.curvatureDrive(fwd * this.speed, (rt * this.speed), true);
    }
    public void max(double max){
        this.speed = max;
    }

    @Override
    public void periodic(){
        m_Odometry.update( this.NAVX.getRotation2d(), metersL_encoder(), metersR_encoder());
    }
    

    public void Telemetry(){
        SmartDashboard.putNumber(   "IMU_Yaw", NAVX.getYaw());
        SmartDashboard.putNumber("R Encoder", metersR_encoder());
        SmartDashboard.putNumber("L Encoder", metersL_encoder());

    }
    public double metersR_encoder(){
        return DriveConstants.Revoluionspermeter * R_encoder.getPosition();
    }
    public double metersL_encoder(){
        return DriveConstants.Revoluionspermeter * L_encoder.getPosition();
    }


    //AUTO

    public void TargetDistanceAuto(double distance){
        this.goalAuto = distance;

        L_PID.setSetpoint(distance);
        R_PID.setSetpoint(distance);
    }

    public void gotoAuto(){
        double l_powerauto = L_PID.calculate(metersL_encoder());
        double r_powerauto = R_PID.calculate(metersR_encoder());

        myDrive.tankDrive(l_powerauto, r_powerauto);
    }

    public double getDistanceError(){
        return this.goalAuto - metersL_encoder();
    }
}   