package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class MarkDeploy
{

    public CRServo flag;
//    Servo flag;
    public void init(HardwareMap hwMap, Telemetry telem)
    {

        try
        {
            flag = hwMap.crservo.get( "MarkDeploy" );
//            flag = hwMap.servo.get("MarkDeploy");
          flag.setDirection(CRServo.Direction.FORWARD);
//            flag.setDirection(Servo.Direction.FORWARD);
//            flag.setPosition(.5);
            telem.addData("servo position", flag.getPower());
        }
        catch (Exception p_exception)
        {
            flag = null;
        }

    }

    public void deploy()
    {
        flag.setPower(1.0);
//        flag.setPosition(.5);
    }

    public void retract()
    {
        flag.setPower(0.0);
//        flag.setPosition(.3);
    }



}
