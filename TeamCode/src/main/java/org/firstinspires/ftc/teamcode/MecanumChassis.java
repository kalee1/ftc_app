package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * @author Error 404: Team Name Not Found
 * @see Chassis
 */
public class MecanumChassis extends Chassis
{

    private DcMotor rFrontMotor = null;
    private DcMotor rRearMotor = null;
    private DcMotor lFrontMotor = null;
    private DcMotor lRearMotor = null;

    static final double FORWARD = 0.0;
    static final double BACKWARD = 180.0;
    static final double RIGHT = 90.0;
    static final double LEFT = 270.0;

    static final double FORWARD_RIGHT_DIAGONAL = 45.0;
    static final double FORWARD_LEFT_DIAGONAL = -45.0;
    static final double BACKWARD_RIGHT_DIAGONAL = 135.0;
    static final double BACKWARD_LEFT_DIAGONAL = -135.0;

    final double COUNTS_PER_MOTOR_REV = 2240;
    final double DRIVE_GEAR_REDUCTION = 1.7333;
    final double WHEEL_DIAMETER_INCHES = 4.0;
    final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.14159);

    int initialPosition;

    /**
     * Look for a specified set of motors in the config file. If the motors are found, give them a
     * specified direction. If a motor is not found, ignore the error and set the motor to equal null.
     *
     * @param hwMap  The hardware map that the code will use to find and classify the objects it sees.
     */
    @Override
    public void init(HardwareMap hwMap, Telemetry telem)
    {
        try
        {
            rFrontMotor = hwMap.dcMotor.get("rightFront");
            rFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rFrontMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        }
        catch (Exception p_exeception)
        {
            rFrontMotor = null;
        }
        try
        {
            lFrontMotor = hwMap.dcMotor.get("leftFront");
            lFrontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            lFrontMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        }
        catch (Exception p_exeception)
        {
            lFrontMotor = null;
        }
        try
        {
            rRearMotor = hwMap.dcMotor.get("rightRear");
            rRearMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rRearMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        }
        catch (Exception p_exeception)
        {
            rRearMotor = null;
        }
        try
        {
            lRearMotor = hwMap.dcMotor.get("leftRear");
            lRearMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            lRearMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        }
        catch (Exception p_exeception)
        {
            rRearMotor = null;
        }

        super.init(hwMap, telem);
    }

    /**
     * This method takes the command values from the x- and y-axes of the left and right joysticks
     * on the gamepad and converts them to motor speed commands. The code then makes sure that the
     * command values don't exceed a magnitude of a selected limit.
     *
     * The code making sure the speed commands don't exceed a value of 1 was sourced from:
     * http://www.chiefdelphi.com/media/papers/download/2906
     *
     * @param leftStickX  The x-axis of the left joystick
     * @param leftStickY  The y-axis of the left joystick
     * @param rightStickX  The x-axis of the right joystick
     * @param rightStickY  The y-axis of the right joystick
     * @param powerLimit  The max power allowed to the motors
     */
    @Override
    public void joystickDrive(double leftStickX, double leftStickY, double rightStickX, double rightStickY, double powerLimit)
    {
        /*
            These are the calculations need to make a simple mecaccnum drive.
              - The left joystick controls moving straight forward/backward and straight sideways.
              - The right joystick control turning.
        */
        double rightFront = (-leftStickY+rightStickX+leftStickX);
        double leftFront = (leftStickY+rightStickX+leftStickX);
        double rightRear=  (-leftStickY+rightStickX-leftStickX);
        double leftRear = (leftStickY+rightStickX-leftStickX);


        //Find the largest command value given and assign it to max.
        double max = Math.abs(leftFront);
        if(Math.abs(rightFront) > max) {max = Math.abs(rightFront);}
        if(Math.abs(leftRear) > max) {max = Math.abs(leftRear);}
        if(Math.abs(rightRear) > max) {max = Math.abs(rightRear);}

        powerLimit = Range.clip(powerLimit, .2, 1);
        if(max == 0)
        {
            max = 1;
        }

        // If max is greater than the power limit, divide all command values by max to ensure that all command
        // values stay below the magnitude of the power limit.
        if(max > powerLimit)
        {
            leftFront = leftFront / max * powerLimit;
            rightFront = rightFront / max * powerLimit;
            leftRear = leftRear / max * powerLimit;
            rightRear = rightRear / max *powerLimit;
        }

        //Give the motors the final power values -- sourced from the calculations above.
        rFrontMotor.setPower(rightFront);
        lFrontMotor.setPower(leftFront);
        rRearMotor.setPower(rightRear);
        lRearMotor.setPower(leftRear);
    }


    /**
     * The drive method moves the four drive motors on the robot and will move the robot forward,
     * backward, left, right, or at a 45 degree angle in any direction.
     *
     * @param distance  How far the robot will drive.
     * @param power  How fast the robot will drive.
     * @param direction  In which direction the robot will drive (forward, backward, left, right,
     *                   or 45 degrees in any direction).
     * @param time  The max time this move can take. A time-out feature: if the move stalls for some
     *              reason, the timer will catch it.
     * @return  A boolean that tells us whether or not the robot is moving.
     */
    @Override
    public boolean drive(double power, double direction, double distance, double time)
    {
        distance = COUNTS_PER_INCH * distance;

        if(!moving)
        {
            resetStartTime();
            moving = true;
            initialPosition = lFrontMotor.getCurrentPosition();
        }

        double stickX = power * Math.sin(Math.toRadians(direction));
        double stickY = power * Math.cos(Math.toRadians(direction));
        joystickDrive(stickX, stickY,0.0,0.0, power);

        if(((lFrontMotor.getCurrentPosition() - initialPosition) > distance) || (getRuntime() > time))
        {
            stopMotors();
            moving = false;
        }
        telemetry.addData("1) target position: ", distance);
        telemetry.addData("2) Current position: )", lFrontMotor.getCurrentPosition());
        telemetry.addData("3) timeout time: ", time);
        telemetry.addData("4) runtime: ", getRuntime());
        telemetry.addData("5) direction: ", direction);
        telemetry.update();
        return !moving;
     }

    /**
     * The pointTurn method turns the robot left or right depeinging on whether the power input is
     * positive or negative.
     *
     * @param heading  The direction in which the robot will move.
     * @param power  The power at which the robot will move.
     */
    @Override
    public boolean pointTurn(double power, double time, double heading)
    {
        // Haven't figured out how to incorporate heading yet...

        if(!moving)
        {
            resetStartTime();
            moving = true;
        }

        joystickDrive(0.0, 0.0,power,0.0, power);

        if(getRuntime() > time)
        {
            stopMotors();
            moving = false;
        }
        return !moving;
    }

    /**
     * Stop all four drive motors by setting their power to zero.
     */
    @Override
    public void stopMotors()
    {
        setPower(0.0);
        moving = false;
    }

    /**
     * setMode sets all four drive motors to a specified mode. There are three mode choices:
     * 1) RUN_USING_ENCODERS,
     * 2) RUN_WITHOUT_ENCODERS, and
     * 3) RUN_TO_POSITION.
     *
     * @param mode The type of mode the motors will will run with.
     */
    private void setMode(DcMotor.RunMode mode)
    {
        if(rFrontMotor != null){rFrontMotor.setMode(mode);}
        if(lFrontMotor != null){lFrontMotor.setMode(mode);}
        if(rRearMotor != null) {rRearMotor.setMode(mode);}
        if(lRearMotor != null){lRearMotor.setMode(mode);}
    }

    /**
     * setDirection sets all four drive motors to a specified direction. There are two direction choices:
     * 1) FORWARD and
     * 2) REVERSE.
     *
     * @param direction  The direction the motors will use.
     */
    private void setDirection(DcMotorSimple.Direction direction)
    {
        if(rFrontMotor != null){rFrontMotor.setDirection(direction);}
        if(lFrontMotor != null){lFrontMotor.setDirection(direction);}
        if(rRearMotor != null) {rRearMotor.setDirection(direction);}
        if(lRearMotor != null){lRearMotor.setDirection(direction);}
    }

    /**
     * setPower set all four drive motors to a specified power.
     *
     * @param power  The power the motors will run at.
     */
    private void setPower(double power)
    {
        if(rFrontMotor != null) {rFrontMotor.setPower(power);}
        if(lFrontMotor != null) {lFrontMotor.setPower(power);}
        if(rRearMotor != null) {rRearMotor.setPower(power);}
        if(lRearMotor != null) {lRearMotor.setPower(power);}

    }

}
