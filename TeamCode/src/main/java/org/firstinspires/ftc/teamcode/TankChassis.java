package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;



/**
 *
 * @author Error 404: Team Name Not Found
 * @see Chassis
 */

public class TankChassis extends Chassis
{
    int initialPosition;

    private DcMotor rFrontMotor = null;
    private DcMotor lFrontMotor = null;
    private DcMotor rRearMotor = null;
    private DcMotor lRearMotor = null;

    @Override
    public void init(HardwareMap hwMap)
    {
        try {
            rFrontMotor = hwMap.dcMotor.get("rightFront");
            rFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        } catch (Exception p_exeception) {
            rFrontMotor = null;
        }
        try {
            lFrontMotor = hwMap.dcMotor.get("leftFront");
            lFrontMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        } catch (Exception p_exeception) {
            lFrontMotor = null;
        }
        try {
            rRearMotor = hwMap.dcMotor.get("rightRear");
            rRearMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        } catch (Exception p_exeception) {
            rRearMotor = null;
        }
        try {
            lRearMotor = hwMap.dcMotor.get("leftRear");
            lFrontMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        } catch (Exception p_exeception) {
            rRearMotor = null;
        }

    }





    /**
     * This method takes the command values from the x- and y-axes of the left and right joysticks
     * on the gamepad and converts them to motor speed commands. The code then makes sure that the
     * command values don't exceed a magnitude of 1.
     *
     * The code making sure the speed commands don't exceed a value of 1 was sourced from:
     * http://www.chiefdelphi.com/media/papers/download/2906
     *
     * @param leftStickX  The x-axis of the left joystick
     * @param leftStickY  The y-axis of the left joystick
     * @param rightStickX  The x-axis of the right joystick
     * @param rightStickY  The y-axis of the right joystick
     */
    @Override
    public void joystickDrive(double leftStickX, double leftStickY, double rightStickX, double rightStickY)
    {
        // These are the calculations need to make a simple mecaccnum drive.
        // The left joystick controls moving straight forward/backward and straight sideways.
        // The right joystick control turning.
        double rightFront = (-leftStickY+rightStickX+leftStickX);
        double leftFront = (leftStickY+rightStickX+leftStickX);
        double rightRear=  (-leftStickY+rightStickX-leftStickX);
        double leftRear = (leftStickY+rightStickX-leftStickX);


        //Find the largest command value given and assign it to max.
        double max = Math.abs(leftFront);
        if(Math.abs(rightFront) > max) {max = Math.abs(rightFront);}
        if(Math.abs(leftRear) > max) {max = Math.abs(leftRear);}
        if(Math.abs(rightRear) > max) {max = Math.abs(rightRear);}

        // If max is greater than 1, divide all command values by max to ensure that all command
        // values stay below a magnitude of 1.
        if(max > 1)
        {
            leftFront/=max;
            rightFront/=max;
            leftRear/=max;
            rightRear/=max;
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
        if(!moving)
        {

            moving = true;
            initialPosition = lFrontMotor.getCurrentPosition();
        }

        double stickX = power * Math.sin(Math.toRadians(direction));
        double stickY = power * Math.cos(Math.toRadians(direction));
        joystickDrive(stickX, stickY,0.0,0.0);

        if((lFrontMotor.getCurrentPosition() - initialPosition) > distance)
        {
            stopMotors();
            moving = false;
        }
        return !moving;
    }

    @Override
    public void stopMotors()
    {
        double power = 0.0;
        if(rFrontMotor != null)
        {
            rFrontMotor.setPower(power);
        }

        if(lFrontMotor != null)
        {
            lFrontMotor.setPower(power);
        }

        if(rRearMotor != null)
        {
            rRearMotor.setPower(power);
        }

        if(lRearMotor != null)
        {
            lRearMotor.setPower(power);
        }
    }


}
