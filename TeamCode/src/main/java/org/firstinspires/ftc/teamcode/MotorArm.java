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
 */
public class MotorArm
{
    Telemetry telemetry;
    boolean moving = false;

    /**
     * A double that is the number of nanoseconds per second.
     */
    double NANOSECONDS_PER_SECOND = TimeUnit.SECONDS.toNanos(1);
    /**
     * A long variable that is the internal start time.
     */
    private long startTime = 0; // in nanoseconds

    /**
     * The elbow DC Motor.
     */
    private DcMotor elbow = null;
    /**
     * The shoulder DC Motor.
     */
    private DcMotor shoulder = null;

    int timesrun = 0;

    /**
     * A touch sensor that, if pressed, stops the arm.
     */
    protected TouchSensor chassisTouch = null;
    /**
     * A touch sensor that, if pressed, stops the forearm from folding into itself.
     */
    protected TouchSensor elbowFront = null;
    /**
     * A touch sensor that, if pressed, stops the forearm from folding into itself.
     */
    protected TouchSensor elbowBack = null;

    /**
     * Initializes all the motors and sensors on the arm using try-catches. The try-catch statements
     * prevents the code from crashing if the wanted device is missing and instead sends a message
     * to the phone to notify the driver of the missing device.
     *
     * @param hwmap An instance of the FIRST-provided HarwareMap.
     * @param telem An instance of Telemetry that allows the use of telemetry in this class.
     */
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
        catch (Exception p_exeception)
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

    }

    /**
     * Sends the joystick commands to the motors, allowing the drivers to move the arm. Reverses the
     * arm's movement if it hits one of the three limit switches. This prevents the arm from driving
     * into itself and breaking.
     *
     * @param RightStickY The y-axis of the right stick on the gamepad. Controls the elbow motor.
     * @param LeftStickY  The y-axis of the left stick on the gamepad. Controls the shoulder motor.
     */
    public void armDrive(double RightStickY, double LeftStickY)
    {
        double elbowGain = 0.6;
        double shoulderGain = 0.7;

        //elbow limits
        if (elbow != null)
        {
            if (elbowBack != null && elbowBack.getValue() != 1)
            {
                elbow.setPower(-0.5);
            }
            else if (elbowFront != null && elbowFront.isPressed())
            {
                elbow.setPower(0.5);
            }
            else
            {
                elbow.setPower(RightStickY);
            }
            //telemetry.addData("elbow power: ", elbow.getPower());
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
//            telemetry.addData("shoulder power: ", shoulder.getPower());
        }

//        telemetry.addData("elbow back: ", elbowBack);
//        telemetry.addData("elbow front: ", elbowFront);
        telemetry.addData("elbow: ", elbow.getCurrentPosition());
//        telemetry.addData("chassis touch: ", chassisTouch);
        telemetry.addData("shoulder: ", shoulder.getCurrentPosition());
//        telemetry.addData("elbowBack: ", elbowBack.getValue());
//        telemetry.addData("elbowFront: ", elbowFront.getValue());
//        telemetry.addData("chassisTouch", chassisTouch.getValue());

    }

    public enum ArmPositions
    {
        ARM_HOME(0,0),
        LANDER_EXTEND(9000, -4000),
        CRATER_EXTEND(7000, -5000),
        DRIVING_EXTEND(4600,-3400),
        MINERAL_COLLECT(0, 0);

        public final int elbow;   // in kilograms
        public final int shoulder; // in meters

        ArmPositions(int theElbow, int theShoulder)
        {
            this.elbow = theElbow;
            this.shoulder = theShoulder;
        }
    }

    public boolean craterExtend()
    {
//        if (shoulder.getCurrentPosition() >= ArmPositions.CRATER_EXTEND.shoulder &&
//            elbow.getCurrentPosition() <= ArmPositions.CRATER_EXTEND.elbow)
//        {
//            shoulder.setPower(-0.2);
//            elbow.setPower(0.2);
//        }
//        else if (shoulder.getCurrentPosition() <= ArmPositions.CRATER_EXTEND.shoulder &&
//                 elbow.getCurrentPosition() >= ArmPositions.CRATER_EXTEND.elbow)
//        {
//            shoulder.setPower(0.2);
//            elbow.setPower(-0.2);
//        }

        return goTo(ArmPositions.CRATER_EXTEND, 0.2);
    }

    public boolean armHome()
    {
//        if (shoulder.getCurrentPosition() <= ArmPositions.ARM_HOME.shoulder &&
//            elbow.getCurrentPosition() >= ArmPositions.ARM_HOME.elbow)
//        {
//            shoulder.setPower(0.25);
//            elbow.setPower(-0.25);
//        }
//        else if (shoulder.getCurrentPosition() >= ArmPositions.ARM_HOME.shoulder &&
//                 elbow.getCurrentPosition() <= ArmPositions.ARM_HOME.elbow)
//        {
//            shoulder.setPower(-0.25);
//            elbow.setPower(0.25);
//        }
//        else
//        {
//            shoulder.setPower(0.0);
//            elbow.setPower(0.0);
//        }
        return goTo(ArmPositions.ARM_HOME, 0.25);
    }

    public boolean landerExtend()
    {
//        if (shoulder.getCurrentPosition() <= ArmPositions.LANDER_EXTEND.shoulder &&
//            elbow.getCurrentPosition() >= ArmPositions.LANDER_EXTEND.elbow)
//        {
//            shoulder.setPower(-0.8);
//            elbow.setPower(0.9);
//        }
//        else
//        {
//            shoulder.setPower(0.0);
//            elbow.setPower(0.0);
//        }
        return goTo(ArmPositions.LANDER_EXTEND, 0.8);
    }

    public boolean drivingExtend()
    {
//        shoulder.setTargetPosition(ArmPositions.DRIVING_EXTEND.shoulder);
//        elbow.setTargetPosition(ArmPositions.DRIVING_EXTEND.elbow);
//
//
//        if (shoulder.getCurrentPosition() <= ArmPositions.DRIVING_EXTEND.shoulder &&
//            elbow.getCurrentPosition() >= ArmPositions.DRIVING_EXTEND.elbow)
//        {
//            shoulder.setPower(-0.8);
//            elbow.setPower(0.9);
//        }
//        else
//        {
//            shoulder.setPower(0.0);
//            elbow.setPower(0.0);
//        }
        return goTo(ArmPositions.DRIVING_EXTEND, 0.8);
    }

    public boolean goldCollect(boolean elbowSecond)
    {
//        if (shoulder.getCurrentPosition() >= ArmPositions.MINERAL_COLLECT.shoulder)
//        {
//            shoulder.setPower(-0.7);
//        }
//        else
//        {
//            shoulder.setPower(0.0);
//        }
//
//        if (elbowSecond)
//        {
//            if (elbow.getCurrentPosition() <= ArmPositions.MINERAL_COLLECT.elbow &&
//                shoulder.getCurrentPosition() <= ArmPositions.MINERAL_COLLECT.shoulder)
//            {
//                elbow.setPower(0.7);
//            }
//            else
//            {
//                elbow.setPower(0.0);
//            }
//        }
//        else
//        {
//            if (elbow.getCurrentPosition() <= ArmPositions.MINERAL_COLLECT.elbow)
//            {
//                elbow.setPower(0.7);
//            }
//            else
//            {
//                elbow.setPower(0.0);
//            }
//        }
        return goTo(ArmPositions.MINERAL_COLLECT, 0.7);
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


    /**
     * Stop all arm movement.
     */
    public void stop()
    {
        shoulder.setPower(0.0);
        elbow.setPower(0.0);
    }

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

    /** Moves the arm towards the named position.
     *
     * @param position  An enumerated value that specifies where the arm should move towards.
     * @param power     The speed with which the arm should move.
     * */
    public boolean goTo( ArmPositions position, double power )
    {
        double elbowPower;
        double shoulderPower;
        double shoulderPos = shoulder.getCurrentPosition();
        double elbowPos = elbow.getCurrentPosition();

        // Move the arm only if it is far enough away from the desired position to be a worthwhile move.
        // Allow for +/- 10 encoder tick dead band around the desired position.

        // Move the shoulder
        if (shoulderPos < (position.shoulder-15) )
        {
            shoulderPower = power;
        }
        else if (shoulderPos > (position.shoulder+15) )
        {
            shoulderPower = -power;
        }
        else
        {
            shoulderPower = 0.0;
        }

        // Move the elbow
        if (elbowPos < (position.elbow-15) )
        {
            elbowPower = power;
        }
        else if (elbowPos > (position.elbow+15) )
        {
            elbowPower = -power;
        }
        else
        {
            elbowPower = 0.0;
        }

        moving = (shoulderPower != 0.0) || (elbowPower != 0.0);

        armDrive(shoulderPower, elbowPower);

        return !moving;
    }

}