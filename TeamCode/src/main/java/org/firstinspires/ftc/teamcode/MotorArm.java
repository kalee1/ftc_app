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
    /** A telemetry object that allows for telemetry to be used in this class. */
    Telemetry telemetry;
    /** A boolean that signifies whether or not the arm is in motion. */
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

    /** An int that records the number of times a method has run. */
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
//        ARM_HOME(0,0),
//        LANDER_EXTEND(8000, -3500),
//        CRATER_EXTEND(7000, -5000),
//        DRIVING_EXTEND(5100,-5000),
//        MINERAL_COLLECT(0, 0);

        ARM_HOME(0,0),
        LANDER_EXTEND(8000, -4025),
        CRATER_EXTEND(8080, -7000),
        DRIVING_EXTEND(5800,-4060),
        MINERAL_COLLECT(0, 0);
        public final int elbow;   // in kilograms
        public final int shoulder; // in meters

        ArmPositions(int theElbow, int theShoulder)
        {
            this.elbow = theElbow;
            this.shoulder = theShoulder;
        }
    }

    /** Convenience method to drive the arm to the position where it is fully extended into the crater
     * in preparation for collecting minerals.
     * @return  A boolean that tells whether or not the arm is moving.
     *  */
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

        return goTo(ArmPositions.CRATER_EXTEND, 0.7, 0.6);
    }

    /** Convenience method to drive the arm to the home position where it is fully stowed.
     * @return  A boolean that tells whether or not the arm is moving.
     *  */
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
        return goTo(ArmPositions.ARM_HOME, 0.6, 0.9);
    }

    /** Convenience method to drive the arm to the position where it is up next to the lander in preparation
     *  for putting minerals into the lander cargro hold.
     * @return  A boolean that tells whether or not the arm is moving.
     *  */
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
        return goTo(ArmPositions.LANDER_EXTEND, 0.7, 0.7);
    }

    /** Convenience method to drive the arm to the position where it is partially extended to make it
     *  easier to drive around between the crater and the lander.
     * @return  A boolean that tells whether or not the arm is moving.
     *  */
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
        return goTo(ArmPositions.DRIVING_EXTEND, 0.2, 0.8);
    }

    /** Convenience method to drive the arm to the position where the shoulder is perpendicular to
     * the robot chassis and the elbow is slight forward for knocking off the gold mineral in
     * autonomous.
     * @return  A boolean that tells whether or not the arm is moving. */
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
        return goTo(ArmPositions.MINERAL_COLLECT, 0.3, 0.3);
    }

    /** Drives the arm to a target position and returns true when done.
     *
     * @param shoulderTarget  The target position for the shoulder motor.
     * @param elbowTarget  The target position for the elbow motor.
     * @param elbowSecond  A boolean that tells whether or not to move the elbow after the shoulder is done.
     * @return  A boolean that tells whether or not the arm is moving.
     * */
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

    /** Moves the arm towards the named position.
     *
     * @param position  An enumerated value that specifies where the arm should move towards.
     * @param elbPower     The speed with which the elbow should move.
     * @param shouldPower   The speed with which the shoulder should move.
     * @return  A boolean that tells whether or not the arm is moving.
     * */
    public boolean goTo( ArmPositions position, double elbPower , double shouldPower)
    {
        double elbowPower;
        double shoulderPower;
        double shoulderPos = shoulder.getCurrentPosition();
        double elbowPos = elbow.getCurrentPosition();

        // Move the shoulder only if it is far enough away from the desired position to be a worthwhile move.
        // Allow for +/- 25 encoder tick dead band around the desired position.  The deadband also
        // reduces the back and forth alternating around the desired position when the joint slightly over
        // shoots the target (and then overshoots the correction).
        if (shoulderPos < (position.shoulder-25) )
        {
            shoulderPower = shouldPower;
        }
        else if (shoulderPos > (position.shoulder+25) )
        {
            shoulderPower = -shouldPower;
        }
        else
        {
            shoulderPower = 0.0;
        }

        // Move the elbow only if it is far enough away from the desired position to be a worthwhile move.
        // Allow for +/- 65 encoder tick dead band around the desired position. The deadband also
        // reduces the back and forth alternating around the desired position when the joint slightly over
        // shoots the target (and then overshoots the correction).
        if (elbowPos < (position.elbow-65) )
        {
            elbowPower = elbPower;
        }
        else if (elbowPos > (position.elbow+65) )
        {
            elbowPower = -elbPower;
        }
        else
        {
            elbowPower = 0.0;
        }

        // The moving flag is true if either joint has a non-zero power
        moving = (shoulderPower != 0.0) || (elbowPower != 0.0);

        elbow.setPower(elbowPower);
        shoulder.setPower(shoulderPower);

        telemetry.addData( "Elbow target: ", position.elbow);
        telemetry.addData( "Shoulder target: ", position.shoulder);
        telemetry.addData("Elbow Power: ", elbowPower);
        telemetry.addData("Shoulder Power ", shoulderPower);

        return !moving;
    }

}