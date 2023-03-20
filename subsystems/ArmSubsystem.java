package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstans;



public class ArmSubsystem extends SubsystemBase{


    // Variables para mover el brazo
    private final CANSparkMax EVO1 = new CANSparkMax(
        ArmConstans.EVOcanIDs[0], 
        CANSparkMaxLowLevel.MotorType.kBrushless
        );
    private final CANSparkMax EVO2 = new CANSparkMax(
        ArmConstans.EVOcanIDs[1], 
        CANSparkMaxLowLevel.MotorType.kBrushless
        );
    
    AnalogPotentiometer pot = new AnalogPotentiometer(0, 1, 0);
    private final PIDController EVOPID = new PIDController(ArmConstans.kP, ArmConstans.kI,ArmConstans.kD);     
    private double goalEVO;
    private double goalElevator;
    private boolean lifted;

    // Variables para el telescopico del brazo
    private final CANSparkMax telescopico = new CANSparkMax(
        ArmConstans.elevadorCANid,
        CANSparkMaxLowLevel.MotorType.kBrushed
    );
    Encoder elevatorEnc = new Encoder(ArmConstans.encoderElevador[0], ArmConstans.encoderElevador[1]); 
    private final PIDController elevatorPID = 
        new PIDController(
        ArmConstans.ekP,
        ArmConstans.ekI,
        ArmConstans.ekD
        );
    
    public ArmSubsystem(){

        EVOPID.setSetpoint(ArmConstans.iddle);        
        EVO1.restoreFactoryDefaults();
        EVO2.restoreFactoryDefaults();

        elevatorPID.setSetpoint(-0.88);
        telescopico.restoreFactoryDefaults();
        elevatorEnc.setDistancePerPulse(ArmConstans.relacionElevador* 1/2 * 4); // TODO: VEr relacion real, debe ser un pulso por cm
    }

    public void useOutput(){

        double position = getMeasurement();
        double output = EVOPID.calculate(position);

        EVO1.set(output);

        SmartDashboard.putNumber("Encoder", pot.get());
        SmartDashboard.putNumber("Goal",this.goalEVO);
        SmartDashboard.putNumber("Fuerza Evo", output);

        double elevatorPosition = elevatorEnc.getDistance()/1000;
        double outputElevator = elevatorPID.calculate(elevatorPosition);
        telescopico.set(-outputElevator);

        SmartDashboard.putNumber("Encoder elevador", elevatorPosition);
        SmartDashboard.putNumber("Fuerza elevador", outputElevator);

        
        
    }

    public double getMeasurement(){
      //  return EVOencoder.getDistance();
      return pot.get();
    }
    

    public void MODGoal(double mod){
        this.goalEVO = this.goalEVO + mod;
        setGoal(this.goalEVO, this.lifted);
    }

    public void MODGoalElevator(double mod){
        this.goalElevator += mod;
 
        setGoalElevator(this.goalElevator);
    }
    public void setGoalElevator(double mod){
        this.goalElevator = mod;

        this.elevatorPID.setSetpoint(this.goalElevator);
    }
    public void setGoal(double goal, boolean lifted){
        if (lifted){
            this.goalEVO = goal+ 0.07;
        } else {
            this.goalEVO = goal;
        }

        updateGoalEVO();
    }   

    public void updateGoalEVO(){
        EVOPID.setSetpoint(goalEVO);
    }
    // mover el elevador al fondo y la garra hasta adelante
    public void Agarrar(){
        
        elevatorPID.setSetpoint(-0.88);
        setGoal(.05 , this.lifted);
    }

    // poner en 2do nivel, elevador extendido y brazo atras 
    public void _2doNivel(){
        
        elevatorPID.setSetpoint(-0.88);
        setGoal(.34, this.lifted);

    }

    
    // levantar el brazo mientras se cruza la cancha, elevador a la mitad y garra a 45 grados
    public void Cruise (){
        setGoal(.28 
        , lifted);
        elevatorPID.setSetpoint(-.88);
    }

    public void lift(boolean bol){
        this.lifted = bol;
    }

    public void reset_encoder(){
        elevatorEnc.reset();
    }

    public double getPot(){
        return pot.get();
    }
}
