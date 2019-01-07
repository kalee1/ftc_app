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
    public CRServo one;
    public CRServo two;
    public CRServo three;
    public CRServo four;
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
            telem.addData("servo position", flag.getPower());
        }
        catch (Exception p_exception)
        {
            flag = null;
        }

        try
        {
            one = hwMap.crservo.get( "one" );
//            flag = hwMap.servo.get("MarkDeploy");
            one.setDirection(CRServo.Direction.FORWARD);
//            flag.setDirection(Servo.Direction.FORWARD);
//            flag.setPosition(.5);
        }
        catch (Exception p_exception)
        {
            one = null;
        }
        try
        {
            two = hwMap.crservo.get( "two" );
//            flag = hwMap.servo.get("MarkDeploy");
            two.setDirection(CRServo.Direction.FORWARD);
//            flag.setDirection(Servo.Direction.FORWARD);
//            flag.setPosition(.5);
        }
        catch (Exception p_exception)
        {
            two = null;
        }
        try
        {
            three = hwMap.crservo.get( "three" );
//            flag = hwMap.servo.get("MarkDeploy");
            three.setDirection(CRServo.Direction.FORWARD);
//            flag.setDirection(Servo.Direction.FORWARD);
//            flag.setPosition(.5);
        }
        catch (Exception p_exception)
        {
            three = null;
        }
        try
        {
            four = hwMap.crservo.get( "four" );
//            flag = hwMap.servo.get("MarkDeploy");
            four.setDirection(CRServo.Direction.FORWARD);
//            flag.setDirection(Servo.Direction.FORWARD);
//            flag.setPosition(.5);
        }
        catch (Exception p_exception)
        {
            four = null;
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

    public void activate()
    {
        one.setPower(1.0);
        two.setPower(1.0);
        three.setPower(1.0);
        four.setPower(1.0);
    }


}
