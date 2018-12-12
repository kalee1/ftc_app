package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Collector {

    public CRServo theServo = null;

    public void init(HardwareMap hwmap, Telemetry telem)
    {
        try
        {
            theServo = hwmap.crservo.get( "Collector" );
            theServo.setDirection(CRServo.Direction.FORWARD);

        } catch (Exception p_exeception) {

            theServo = null;
            telem.addData("Collector Not Found", "");
        }
    }

    public void intake(Telemetry telem)
    {
        theServo.setPower(1.0);
        telem.addData("Collector Direction = Forward", "");
    }

    public void eject(Telemetry telem)
    {
        theServo.setPower(-1.0);
        telem.addData("Collector Direction = Backward", "");
    }

    public void stop()
    {
        theServo.setPower(0.0);
    }
}


