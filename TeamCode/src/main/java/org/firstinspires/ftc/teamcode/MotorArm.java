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
    Telemetry telemetry;
    boolean moving = false;
    int elbowHome;
    int shoulderHome;
    int elbowExtend;
    int shoulderExtend;


    /** A double that is the number of nanoseconds per second. */
    double NANOSECONDS_PER_SECOND = TimeUnit.SECONDS.toNanos(1);
    /** A long variable that is the internal start time. */
    private long startTime = 0; // in nanoseconds

    /** A dc Motor that moves the elbow joint on the arm.*/
    private DcMotor elbow = null;
//    private DcMotorEx ElbowEx = null;
    /** A dc motor that moves the shoulder joint on the arm.*/
    private DcMotor shoulder = null;

    //    protected TouchSensor Shoulderfront = null;
    int targetEncoder;
    int targetEncoder2;
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

            elbowHome = elbow.getCurrentPosition();
            shoulderHome = shoulder.getCurrentPosition();


            elbowExtend = 9165;
            shoulderExtend = -5630;
        }

    /**
     * Sends the joystick commands to the motors, allowing the drivers to move the arm. Reverses the
     * arm's movement if it hits one of the three limit switches. This prevents the arm from driving
     * into itself and breaking.
     *
     * @param RightStickY  The y-axis of the right stick on the gamepad. Controls the elbow motor.
     * @param LeftStickY  The y-axis of the left stick on the gamepad. Controls the shoulder motor.
     * */
    public void armDrive(double RightStickY, double LeftStickY)
        {
            double elbowGain = 0.6;
            double shoulderGain = 0.7;


            //elbow limits
            if(elbow != null)
                {
                if(elbowBack != null && elbowBack.getValue() != 1)
                    {
                    elbow.setPower(-0.5);
                    }
                else if(elbowFront != null && elbowFront.isPressed())
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
            if(shoulder != null) //check to make sure shoulder motor is good.
                {
                if(chassisTouch != null) //check to make sure the limit switch is good.
                    {
                    //if the limit switch is pressed, reverse the arm, otherwise run the arm as normal.
                    if(chassisTouch.isPressed())
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
            ARMHOME(0, 0),
            LANDEREXTEND(9165, -5630),
            CRATEREXTEND(420, 420),
            DRIVINGEXTEND(420, 420);

        public final double Elbow;   // in kilograms
        public final double Shoulder; // in meters

        ArmPositions(double Elbow, double Shoulder)
            {
                this.Elbow = Elbow;
                this.Shoulder = Shoulder;
            }
        }
    public void craterExtend()
        {
            if (shoulder.getCurrentPosition() <= ArmPositions.CRATEREXTEND.Shoulder &&
                    elbow.getCurrentPosition() >= ArmPositions.CRATEREXTEND.Elbow)
                {
                shoulder.setPower(-0.8);
                elbow.setPower(0.9);
                }
            else
                {
                shoulder.setPower(0.0);
                elbow.setPower(0.0);
                }
        }
    public void armHome()
        {
            if (shoulder.getCurrentPosition() <= ArmPositions.ARMHOME.Shoulder &&
                    elbow.getCurrentPosition() >= ArmPositions.ARMHOME.Elbow)
                {
                shoulder.setPower(-0.8);
                elbow.setPower(0.9);
                }
            else
                {
                shoulder.setPower(0.0);
                elbow.setPower(0.0);
                }
        }
    public void landerExtend()
        {
            if (shoulder.getCurrentPosition() <= ArmPositions.LANDEREXTEND.Shoulder &&
                    elbow.getCurrentPosition() >= ArmPositions.LANDEREXTEND.Elbow)
                {
                shoulder.setPower(-0.8);
                elbow.setPower(0.9);
                }
            else
                {
                shoulder.setPower(0.0);
                elbow.setPower(0.0);
                }
        }
            public void driveingExtend()
            {
            if (shoulder.getCurrentPosition() <= ArmPositions.DRIVINGEXTEND.Shoulder &&
                    elbow.getCurrentPosition() >= ArmPositions.DRIVINGEXTEND.Elbow)
                {
                shoulder.setPower(-0.8);
                elbow.setPower(0.9);
                }
            else
                {
                shoulder.setPower(0.0);
                elbow.setPower(0.0);
                }
            }






    public boolean armDeploy(int shoulderTarget, int elbowTarget, boolean elbowSecond)
        {
            double shoulderValue;
            double elbowValue;

            if( timesrun < 1 )
                {
                shoulderTarget = shoulder.getCurrentPosition() + shoulderTarget;
                elbowTarget = elbow.getCurrentPosition() + elbowTarget;
                }

            if( shoulder.getCurrentPosition() >= shoulderTarget )
                {
                shoulderValue = -0.7;
                }
            else
                {
                shoulderValue = 0.0;
                }

            if( elbowSecond )
                {
                if( elbow.getCurrentPosition() <= elbowTarget && shoulder.getCurrentPosition() <= shoulderTarget )
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
                if( elbow.getCurrentPosition() <= elbowTarget )
                    {
                    elbowValue = 0.7;
                    }
                else
                    {
                    elbowValue = 0.0;
                    }
                }

            armDrive(elbowValue, shoulderValue);

            if( (elbow.getCurrentPosition() >= elbowTarget) &&
                    (shoulder.getCurrentPosition() <= shoulderTarget) )
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


    public boolean ArmHome()
        {
            double shoulderValue;
            double elbowValue;

            if( timesrun < 1 )
                {
//            shoulderTarget = shoulder.getCurrentPosition() + shoulderTarget;
//            elbowTarget = elbow.getCurrentPosition() + elbowTarget;
                }

            if( shoulder.getCurrentPosition() < shoulderHome )
                {
                shoulderValue = 0.8;
                }
            else
                {
                shoulderValue = 0.0;
                }


            if( elbow.getCurrentPosition() > elbowHome )
                {
                elbowValue = -0.9;
                }
            else
                {
                elbowValue = 0.0;
                }

            armDrive(elbowValue, shoulderValue);

            if( (elbow.getCurrentPosition()    <= elbowHome) &&
                    (shoulder.getCurrentPosition() >= shoulderHome) )
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

    public boolean testAutoArm(double power, double direction, double gain, double distance, double time)
        {
            resetStartTime();
            if(!moving)
                {

                moving = true;
                }

            armDrive(3,3);

            if((Math.abs(shoulder.getCurrentPosition()) > distance) || (getRuntime() > time))
                {
                moving = false;
                }

            return !moving;
        }


    /**
     * Get the number of seconds this op mode has been running
     * <p>
     * This method has sub millisecond accuracy.
     * @return number of seconds this op mode has been running
     */
    public boolean armExtend()
        {
            double elbowTarget = elbowHome;
            double shoulderTarget = shoulderHome;
            double shoulderValue;
            double elbowValue;

            if( timesrun < 1 )
                {
//            shoulderTarget = shoulder.getCurrentPosition() + shoulderTarget;
//            elbowTarget = elbow.getCurrentPosition() + elbowTarget;
                }

            if( shoulder.getCurrentPosition() < shoulderExtend )
                {
                shoulderValue = -0.8;
                }
            else
                {
                shoulderValue = 0.0;
                }


            if( elbow.getCurrentPosition() > elbowExtend )
                {
                elbowValue = 0.9;
                }
            else
                {
                elbowValue = 0.0;
                }

            armDrive(elbowValue, shoulderValue);

            if( (elbow.getCurrentPosition()    >= elbowExtend) &&
                    (shoulder.getCurrentPosition() <= shoulderExtend) )
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
    }