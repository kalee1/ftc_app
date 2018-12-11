package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.TouchSensor;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.EventListener;


/**
 * The class responsible for the mineral collection and deployment arm on the robot. Contains all
 * the harware and methods for the arm.
 *
 * @author Ben, Error 404: Team Name Not Found
 * */
public class MotorArm
{
    Telemetry telemetry;

    /** A dc Motor that moves the elbow joint on the arm.*/
    private DcMotor elbow = null;
//    private DcMotorEx ElbowEx = null;
    /** A dc motor that moves the shoulder joint on the arm.*/
    private DcMotor shoulder = null;

//    protected TouchSensor Shoulderfront = null;

//    int targetEncoder = 0;


    /** A touch sensor that, if pressed, stops the arm. */
    protected TouchSensor shoulderFront = null;
    /** A touch sensor that, if pressed, stops the forearm from folding into itself. */
    protected TouchSensor elbowFront = null;
    /** A touch sensor that, if pressed, stops the forearm from folding into itself. */
    protected TouchSensor elbowRear = null;

    /**
     * Initializes all the motors and sensors on the arm using try-catches. The try-catch statements
     * prevents the code from crashing if the wanted device is missing and instead sends a message
     * to the phone to notify the driver of the missing device.
     *
     * @param hwmap  An instance of the FIRST-provided HarwareMap.
     * @param telemetry  An instance of Telemetry that allows the use of telemetry in this class.
     * */
    public void init(HardwareMap hwmap, Telemetry telemetry)
    {
        try
        {
            elbow = hwmap.dcMotor.get("elbow");
            elbow.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
        catch (Exception p_exeception)
        {
            elbow = null;
        }
        try
        {
            shoulder = hwmap.dcMotor.get("shoulder");
            shoulder.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
        catch (Exception p_exeception)
        {
            shoulder = null;
            shoulder.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
        try
        {
            shoulderFront = hwmap.touchSensor.get("shoulderFront");
        }
        catch (Exception p_exeception)
        {
            shoulderFront = null;
        }
        try
        {
            elbowFront = hwmap.touchSensor.get("elbowFront");
        }
        catch (Exception p_exeception)
        {
            elbowFront = null;
        }
        try
        {
            elbowRear = hwmap.touchSensor.get("elbowRear");
        }
        catch (Exception p_exeception)
        {
            elbowRear = null;
        }
    }

    /**
     * Sends the joystick commands to the motors, allowing the drivers to move the arm. Reverses the
     * arm's movement if it hits one of the three limit switches. This prevents the arm from driving
     * into itself and breaking.
     *
     * @param RightStickY  The y-axis of the right stick on the gamepad. Controls the elbow motor.
     * @param LeftStickY  The y-axis of the left stick on the gamepad. Controls the shoulder motor.
     * */
    public void armDrive( double RightStickY, double LeftStickY)
    {
        if (shoulderFront.isPressed())
        {
            shoulder.setPower(-0.2);
        }
        else
        {
            elbow.setPower(RightStickY * .5);
            shoulder.setPower(LeftStickY * .6);
        }

        if (elbowFront.isPressed())
        {
            elbow.setPower(-0.2);
        }
        else if(elbowRear.isPressed())
        {
            elbow.setPower(0.2);
        }
        else
        {
            elbow.setPower(RightStickY * .5);
            shoulder.setPower(LeftStickY * .6);
        }
    }
}