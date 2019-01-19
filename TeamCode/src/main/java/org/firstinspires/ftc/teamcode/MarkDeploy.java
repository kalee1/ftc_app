package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Contains the hardware and methods to run the mechanism that deploys the team marker during autonomous.
 *
 * @author Andrew, Error 404: Team Name Not Found
 * */
public class MarkDeploy
{

    /** A continuous rotation servo called flag. */
    public CRServo flag;
//    Servo flag;

    /** Initilzing the hardware used in this class.
     *
     * @param hwMap  An instance of HardwareMap that lets hardware be initialized.
     * @param telem  An instance of Telemetry that lets telemetry statements be used in this class.
     * */
    public void init(HardwareMap hwMap, Telemetry telem)
    {
        try
        {
            flag = hwMap.crservo.get( "MarkDeploy" );
//            flag = hwMap.servo.get("MarkDeploy");
          flag.setDirection(CRServo.Direction.FORWARD);
//            flag.setDirection(Servo.Direction.FORWARD);
//            flag.setPosition(.5);
        }
        catch (Exception p_exception)
        {
            flag = null;
        }
    }

    /** Deploys the team marker. */
    public void deploy()
    {
        flag.setPower(1.0);
//        flag.setPosition(.5);
    }

    /** Retracts the mechanism that deploys the team marker. */
    public void retract()
    {
        flag.setPower(0.0);
//        flag.setPosition(.3);
    }


}
