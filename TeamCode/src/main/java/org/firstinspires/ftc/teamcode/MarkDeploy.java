package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class MarkDeploy
{
    public Servo Flag;

    public void init(HardwareMap hwMap)
    {
        try {
            Flag = hwMap.servo.get( "Elbow" );
            Flag.setDirection(Servo.Direction.FORWARD);
            Flag.setPosition(.5);
        } catch (Exception p_exception) {
            Flag = null;
        }
    }
    public void deploy()
    {
        Flag.setPosition(.4);
    }
    public void retract()
    {
        Flag.setPosition(.3);
    }
}