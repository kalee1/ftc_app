package org.firstinspires.ftc.teamcode;
import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
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

    int targetEncoder = 500;
    int targetEncoder2 = 300;
    int timesrun = 0;


    /** A touch sensor that, if pressed, stops the arm. */
    protected TouchSensor chassisTouch = null;
    /** A touch sensor that, if pressed, stops the forearm from folding into itself. */
    protected TouchSensor elbowFront = null;
    /** A touch sensor that, if pressed, stops the forearm from folding into itself. */
    protected TouchSensor elbowBack = null;

    /**
     * Initializes all the motors and sensors on the arm using try-catches. The try-catch statements
     * prevents the code from crashing if the wanted device is missing and instead sends a message
     * to the phone to notify the driver of the missing device.
     *
     * @param hwmap  An instance of the FIRST-provided HarwareMap.
     * @param telem  An instance of Telemetry that allows the use of telemetry in this class.
     * */
    public void init(HardwareMap hwmap, Telemetry telem)
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
        }


        try
        {
            elbowBack = hwmap.touchSensor.get("elbowBack");
        }
        catch (Exception p_exception)
        {
            elbowBack = null;
            telemetry.addData("elbowBack not found", "");
        }
        try
        {
            elbowFront = hwmap.touchSensor.get("elbowFront");
        }
        catch (Exception p_exception)
        {
            elbowFront = null;
            telemetry.addData("elbowFront not found", "");
        }
        try
        {
            chassisTouch = hwmap.touchSensor.get("chassisTouch");
        }
        catch (Exception p_exception)
        {
            chassisTouch = null;
            telemetry.addData("chassisTouch not found", "");
        }

        telemetry = telem;
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
        double elbowGain = 0.6;
        double shoulderGain = 0.7;

        if(elbowBack != null)
        {
            if (elbowBack.getValue() != 1)
            {
                if (elbow != null)
                {
                    elbow.setPower(-0.5);
                }
            }
        }
        if (elbowBack != null)
        {
            if (elbowFront.isPressed())
            {
                shoulder.setPower(LeftStickY * shoulderGain);
                elbow.setPower(0.5);
            }
        }
        if (chassisTouch.isPressed())
        {
            shoulder.setPower(-0.5);
            elbow.setPower(RightStickY * elbowGain);
        }
        else
        {
            shoulder.setPower(LeftStickY * shoulderGain);
            elbow.setPower(RightStickY * elbowGain);
        }

        telemetry.addData("elbowBack: ", elbowBack.getValue());
        telemetry.addData("elbowFront: ", elbowFront.getValue());
        telemetry.addData("chassisTouch", chassisTouch.getValue());

    }
    public void ArmDeploy()
    {
        elbow.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        shoulder.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        elbow.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        shoulder.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        if (timesrun >= 1)
            {
            targetEncoder = shoulder.getCurrentPosition() + targetEncoder;
            targetEncoder2 = elbow.getCurrentPosition() + targetEncoder2;
            timesrun = 1;

            if (shoulder.getCurrentPosition() != targetEncoder)
                {
                shoulder.setPower(4);
                else
                    {
                    shoulder.setPower(0);
                    }

                if (elbow.getCurrentPosition() != targetEncoder2)
                    {
                    elbow.setTargetPosition(targetEncoder2);
                    elbow.setPower(-4);
                    }
                }
            }
    }
}