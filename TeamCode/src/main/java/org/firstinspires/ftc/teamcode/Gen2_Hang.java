package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ListIterator;

/**
 * Contains the code for initializing the hanger hardware and for running the hanger in teleop and
 * autonomous.
 *
 * @author  Ben, Error 404: Team Name Not Found
 * */
public class Gen2_Hang
{

    /** The DC Motor used to hang. */
    DcMotor hang = null;

    /** A telemetry object. Allows for telemetry calls to be made in this class. */
    Telemetry telemetry;

    /** An enum that stores the two possible directions the hanger can go: IN or OUT */
    enum HangDirection {IN, OUT}
    /** A boolean that is used to determine whether or not the robot is moving. */
    boolean moving = false;
    /** A double that is the starting position of the motor. */
    double initialPosition;


    /** Initializes the hardware used in the hanger mechanism.
     *
     * @param hwmap  An instance of the hardware map. Used to initialize the hang motor.
     * @param telem  An instance of Telemtry that allows for telemetry to be initialized and
     *               therefore used in this class.
     * */
    public void init(HardwareMap hwmap, Telemetry telem)
    {
        telemetry = telem;
        try
        {
            hang = hwmap.dcMotor.get("hang");
//            hang.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            hang.setTargetPosition(hang.getCurrentPosition());
//            hang.setPower(.8);
            hang.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            hang.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            hang.setPower(0.0);

        }
        catch (Exception p_exeception)
        {
            hang = null;
            telem.addData("hang Not Found", "");
        }


    }

    /** Called when the start button on the controller is pressed. Runs once. */
    public void start()
    {
        if(hang != null)
        {
//            hang.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            hang.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//            hang.setPower(0.0);
        }
    }


    /**  */
    public enum Lift
    {
        LANDERHANG(0),
        LANDERPREP(-2400);

        public final double targetEncoder;

        Lift(double targetEncoder)
            {
                this.targetEncoder = targetEncoder;
            }
    }

    /** Sends the hanger out to a preset position in preparation for hanging.*/
    public void landerPrep()
    {
        if (hang.getCurrentPosition() < Lift.LANDERPREP.targetEncoder)
        {
            hang.setPower(.3);
        }
    }
    /** Pulls the hanger in to a preset position to lift the robot off the ground. */
    public void landerHang()
    {
        if (hang.getCurrentPosition() < Lift.LANDERPREP.targetEncoder)
        {
            hang.setPower(.4);
        }
    }
    /** A generic drive method for controlling the hanger.
     *
     * @param down  A truth value of whether or not up is the correct direction.
     * @param up  A truth value of whether or not down is the correct direction.
     * @param power  The power at which the hanger will run.
     * */
    public void hangControl(boolean down, boolean up, double power)
    {
        power = Math.abs(power);

        if(hang != null)
        {
            if (down)
            {
                hang.setPower(-power);
            }
            else if (up)
            {
                hang.setPower(power);
            }
            else
            {
                hang.setPower(0.0);
            }
            telemetry.addData("Hang Encoder Pos: ", hang.getCurrentPosition());
//            telemetry.addData("Up: ", up);
//            telemetry.addData("Down: ", down);
//            telemetry.addData("Hang Power: ", power );
        }
    }


    /** Drives the hanger for a set number of encoder ticks. Used in autonomous.
     *
     * @param power  The power at which the hanger will run.
     * @param distance  The number of encoders the hanger will run.
     * @param direction  The direction the hanger will run in (there are two choices: IN or OUT).
     * @return A boolean that is whether or not the robot is moving.
     * */
    public boolean hangDrive(double power, double distance,  HangDirection direction)
    {
        if(!moving)
        {
            initialPosition = hang.getCurrentPosition();

            moving = true;
        }

        if(direction.equals(HangDirection.IN))
        {

            hangControl(true, false, power);
        }
        else if(direction.equals(HangDirection.OUT))
        {
            hangControl(false, true, power);
        }

        if(Math.abs(hang.getCurrentPosition() - initialPosition) >= distance)
        {
            stopHangMotor();
            moving = false;
        }

        return !moving;
    }

    /** Stops the hang motor. */
    public void stopHangMotor()
    {
        if(hang != null)
        {
            hang.setPower(0.0);
        }
    }

}
