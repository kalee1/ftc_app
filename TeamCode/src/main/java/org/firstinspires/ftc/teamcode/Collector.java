package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Collector {

    public CRServo theServo = null;
    Telemetry telemetry;

    public void init(HardwareMap hwmap, Telemetry telem)
    {
        telemetry = telem;

        try
        {

            theServo = hwmap.crservo.get( "Collector" );
            theServo.setDirection(CRServo.Direction.FORWARD);

        } catch (Exception p_exeception) {

            theServo = null;
        }

       // telemetry.addData("msg1", "" + theServo.getPower());
    }
    public void intake()
    {

        theServo.setPower(1.0);
    }
    public void eject()

    {
        theServo.setPower(-1.0);
    }
    public void stop()

    {
        theServo.setPower(0.0);
    }
}


