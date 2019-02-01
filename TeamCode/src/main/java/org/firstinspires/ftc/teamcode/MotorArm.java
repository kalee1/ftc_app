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
import java.util.concurrent.TimeUnit;


/**
 * The class responsible for the mineral collection and deployment arm on the robot. Contains all
 * the harware and methods for the arm.
 *
 * @author Ben, Error 404: Team Name Not Found
 * */
public class MotorArm
{
    /** A telemetry object that is used to enable the calling of telemetry in this class. */
    Telemetry telemetry;
    /** A boolean that represents whether or not the arm is currently moving. */
    boolean moving = false;
    /** An int that holds the starting position of the elbow motor. */
    int elbowStart;
    /** An int that holds the starting position of the shoulder arm. */
    int shoulderStart;
    /** An int that holds the distance required to extend the elbow joint. */
    int elbowExtend;
    /** An int that holds the distance required to extend the shoulder joint. */
    int shoulderExtend;


    /** A double that is the number of nanoseconds per second. */
    double NANOSECONDS_PER_SECOND = TimeUnit.SECONDS.toNanos(1);
    /** A long variable that is the internal start time. */
    private long startTime = 0; // in nanoseconds

    /** A dc Motor that moves the elbow joint on the arm.*/
    private DcMotor elbow = null;
    /** A dc motor that moves the shoulder joint on the arm.*/
    private DcMotor shoulder = null;

    /** An int that holds a target position -- not used. */
    int targetEncoder;
    /** An int that holds a target position -- not used. */
    int targetEncoder2;
    /** An int that holds the number of times a method has been run. */
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
        telemetry = telem;

        try
        {
            elbow = hwmap.dcMotor.get("elbow");
            elbow.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            elbow.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        catch (Exception p_exeception)
        {
            elbow = null;
            telemetry.addData("elbow not found", "");
        }
        try
        {
            shoulder = hwmap.dcMotor.get("shoulder");
            shoulder.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            shoulder.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        catch (Exception p_exeception)
        {
            shoulder = null;
            telemetry.addData("shoulder not found", "");
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

        elbowStart = elbow.getCurrentPosition();
        shoulderStart = shoulder.getCurrentPosition();

        elbowExtend = 9165;
        shoulderExtend = -5630;
    }

    /**
     * Sends the joystick commands to the motors, allowing the drivers to move the arm and reverses the
     * arm's movement if it hits one of the three limit switches. This prevents the arm from driving
     * into itself and breaking.
     *
     * @param RightStickY  The y-axis of the right stick on the gamepad. Controls the elbow motor.
     * @param LeftStickY  The y-axis of the left stick on the gamepad. Controls the shoulder motor.
     * */
    public void armDrive(double RightStickY, double LeftStickY)
    {
        double elbowGain = 0.7;
        double shoulderGain = 0.8;


        //elbow limits
        if (elbow != null)
        {
            if (elbowBack != null)
            {
                if (elbowBack.getValue() != 1)
                {
                    elbow.setPower(-0.5);
                }
                else
                {
                    elbow.setPower(RightStickY);
                }
            }
            if (elbowFront != null)
            {
                if (elbowFront.isPressed())
                {
                    elbow.setPower(0.5);
                }
                else
                {
                    elbow.setPower(RightStickY);
                }
            }
        }

        //shoulder limits
        if (shoulder != null) //check to make sure shoulder motor is good.
        {
            if (chassisTouch != null) //check to make sure the limit switch is good.
            {
                //if the limit switch is pressed, reverse the arm, otherwise run the arm as normal.
                if (chassisTouch.isPressed())
                {
                    shoulder.setPower(-0.7);
                }
                else
                {
                    shoulder.setPower(LeftStickY);
                }
            }
        }

        telemetry.addData("elbow: ", elbow.getCurrentPosition());
        telemetry.addData("shoulder: ", shoulder.getCurrentPosition());

    }


    /** Drives the arm to a target position and returns true when done.
     *
     * @param shoulderTarget  The target position for the shoulder motor.
     * @param elbowTarget  The target position for the elbow motor.
     * @param elbowSecond  A boolean that tells whether or not to move the elbow after the shoulder is done.
     * @return  A boolean that tells whether or not the arm is moving. */
    public boolean armDeploy(int shoulderTarget, int elbowTarget, boolean elbowSecond)
    {
        double shoulderValue;
        double elbowValue;

        if (timesrun < 1)
        {
            shoulderTarget = shoulder.getCurrentPosition() + shoulderTarget;
            elbowTarget = elbow.getCurrentPosition() + elbowTarget;
        }

        if (shoulder.getCurrentPosition() >= shoulderTarget)
        {
            shoulderValue = -0.7;
        }
        else
        {
            shoulderValue = 0.0;
        }

        if (elbowSecond)
        {
            if (elbow.getCurrentPosition() <= elbowTarget && shoulder.getCurrentPosition() <= shoulderTarget)
            {
                elbowValue = 0.7;
            }
            else
            {
                elbowValue = 0.0;
            }
        }
        else
        {
            if (elbow.getCurrentPosition() <= elbowTarget)
            {
                elbowValue = 0.7;
            }
            else
            {
                elbowValue = 0.0;
            }
        }

        armDrive(elbowValue, shoulderValue);

        if ((elbow.getCurrentPosition() >= elbowTarget) &&
            (shoulder.getCurrentPosition() <= shoulderTarget))
        {
            moving = false;
            timesrun = 0;
            elbow.setPower(0.0);
            shoulder.setPower(0.0);
        }
        else
        {
            moving = true;
            timesrun = 1;
        }

        return !moving;
    }


    /** Retracts the arm to the home position (folded up on the chassis) and returns true when done.
     *
     * @param elbowFirst  A boolean that tells whether or not to move the elbow before the shoulder.
     * @return  A boolean that tells whether or not the arm is moving.
     * */
    public boolean armRetract(boolean elbowFirst)
    {
        double elbowTarget = elbowStart;
        double shoulderTarget = shoulderStart;
        double shoulderValue;
        double elbowValue;

//        if (timesrun < 1)
//        {
//            shoulderTarget = shoulder.getCurrentPosition() + shoulderTarget;
//            elbowTarget = elbow.getCurrentPosition() + elbowTarget;
//        }

        if (elbowFirst)
        {

            if (shoulder.getCurrentPosition() < shoulderStart && elbow.getCurrentPosition() < elbowStart)
            {
                shoulderValue = 0.8;
            }
            else
            {
                shoulderValue = 0.0;
            }
        }
        else
        {
            if (shoulder.getCurrentPosition() < shoulderStart)
            {
                shoulderValue = 0.8;
            }
            else
            {
                shoulderValue = 0.0;
            }
        }


        if (elbow.getCurrentPosition() > elbowStart)
        {
            elbowValue = -0.9;
        }
        else
        {
            elbowValue = 0.0;
        }

        armDrive(elbowValue, shoulderValue);

        if ((elbow.getCurrentPosition()    <= elbowStart) &&
            (shoulder.getCurrentPosition() >= shoulderStart))
        {
            moving = false;
            timesrun = 0;
            elbow.setPower(0.0);
            shoulder.setPower(0.0);
        }
        else
        {
            moving = true;
            timesrun = 1;
        }

        return !moving;
    }



//    public boolean armExtend()
//    {
//        double elbowTarget = elbowStart;
//        double shoulderTarget = shoulderStart;
//        double shoulderValue;
//        double elbowValue;
//
//        if( timesrun < 1 )
//        {
////            shoulderTarget = shoulder.getCurrentPosition() + shoulderTarget;
////            elbowTarget = elbow.getCurrentPosition() + elbowTarget;
//        }
//
//        if( shoulder.getCurrentPosition() < shoulderExtend )
//        {
//            shoulderValue = -0.8;
//        }
//        else
//        {
//            shoulderValue = 0.0;
//        }
//
//
//        if( elbow.getCurrentPosition() > elbowExtend )
//        {
//            elbowValue = 0.9;
//        }
//        else
//        {
//            elbowValue = 0.0;
//        }
//
//        armDrive(elbowValue, shoulderValue);
//
//        if( (elbow.getCurrentPosition()    >= elbowExtend) &&
//                (shoulder.getCurrentPosition() <= shoulderExtend) )
//        {
//            moving = false;
//            timesrun = 0;
//            elbow.setPower(0.0);
//            shoulder.setPower(0.0);
//        }
//        else
//        {
//            moving = true;
//            timesrun = 1;
//        }
//
//        return !moving;
//    }

        /**
         * Get the number of seconds this op mode has been running
         * <p>
         * This method has sub millisecond accuracy.
         * @return number of seconds this op mode has been running
         */
    public double getRuntime()
    {
        return (System.nanoTime() - startTime) / NANOSECONDS_PER_SECOND;
    }

    /**
     * Reset the internal timer to zero.
     */
    public void resetStartTime()
    {
        startTime = System.nanoTime();
    }

    /**
     * Stop the motors by removing power.
     */
    public void stop()
    {
        if (elbow != null) { elbow.setPower(0.0); }
        if (shoulder != null) { shoulder.setPower(0.0); }
    }
}