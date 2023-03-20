package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class RobotClaw extends SubsystemBase {
    private final Solenoid ClawSelenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 0); 
    
    Compressor pcmCompressor = new Compressor(0, PneumaticsModuleType.CTREPCM );
    
    public RobotClaw(){
        pcmCompressor.enableDigital();
    }
    public void toggle(){
        ClawSelenoid.toggle();
    }

    public void CompressorToggle(){
       if (pcmCompressor.isEnabled()) {
        pcmCompressor.disable();
       } else {
        pcmCompressor.enableDigital();
       }
    }
}
