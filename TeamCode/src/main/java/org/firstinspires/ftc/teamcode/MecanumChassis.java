package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

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

    /**
     * Look for a specified set of motors in the config file. If the motors are found, give them a
     * specified direction. If a motor is not found, ignore the error and set the motor to equal null.
     *
     * @param hwMap  The hardware map that the code will use to find and classify the objects it sees.
     */
    @Override
    public void init(HardwareMap hwMap)
    {
        try {
            rFrontMotor = hwMap.dcMotor.get("rightFront");
            rFrontMotor.setDirection(DcMotorSimple.Direction.FORWARD);
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
     * setMode sets all four drive motors to a specified mode. There are three mode choices:
     * 1) RUN_USING_ENCODERS,
     * 2) RUN_WITHOUT_ENCODERS, and
     * 3) RUN_TO_POSITION.
     *
     * @param mode The type of mode the motors will will run with.
     */
    public void setMode(DcMotor.RunMode mode)
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
    public void setDirection(DcMotorSimple.Direction direction)
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
    public void setPower(double power)
    {
        if(rFrontMotor != null) {rFrontMotor.setPower(power);}
        if(lFrontMotor != null) {lFrontMotor.setPower(power);}
        if(rRearMotor != null) {rRearMotor.setPower(power);}
        if(lRearMotor != null) {lRearMotor.setPower(power);}

    }


    /**
     * The drive method moves the four drive motors on the robot and will move the robot forward or
     * backward depending on whenther it recieved a positive or negative power value.
     *
     * @param distance  How far the robot will drive.
     * @param power  How fast the robot will drive.
     */
    @Override
    public void drive(double distance, double power)
    {
        if(power>0)
        {
            setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            setDirection(DcMotorSimple.Direction.FORWARD);
            setPower(power);
        }
        else if(power<0)
        {
            setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            setDirection(DcMotorSimple.Direction.REVERSE);
            setPower(power);
        }
        else if(power==0){
            stopMotors();
        }
    }

    @Override
    public void pointTurn(double heading, double power)
    {
        //If the power input is positive, turn right
        if(power>0)
        {
            setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            if(rFrontMotor != null){rFrontMotor.setDirection(DcMotorSimple.Direction.FORWARD);}
            if(lFrontMotor != null){lFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);}
            if(rRearMotor != null) {rRearMotor.setDirection(DcMotorSimple.Direction.FORWARD);}
            if(lRearMotor != null){lRearMotor.setDirection(DcMotorSimple.Direction.REVERSE);}
            setPower(power);
        }
        //If the power input is negative, turn left.
        else if(power<0)
        {
            setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            if(rFrontMotor != null){rFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);}
            if(lFrontMotor != null){lFrontMotor.setDirection(DcMotorSimple.Direction.FORWARD);}
            if(rRearMotor != null) {rRearMotor.setDirection(DcMotorSimple.Direction.REVERSE);}
            if(lRearMotor != null){lRearMotor.setDirection(DcMotorSimple.Direction.FORWARD);}
            setPower(power);
        }
        //If the power input is null, stop the motors.
        else if(power==0){
            stopMotors();
        }
    }

    /**
     * The strafe method moves the robot left or right depending on whether the power input is positive or negative.
     *
     * @param power  How fast the robot will move.
     * @param heading  At what heading the robot will move.
     */
    public void strafe(double power, double heading)
    {
        //If the power input is positive, strafe right
        if(power>0)
        {
            setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            if(rFrontMotor != null){rFrontMotor.setDirection(DcMotorSimple.Direction.FORWARD);}
            if(lFrontMotor != null){lFrontMotor.setDirection(DcMotorSimple.Direction.FORWARD);}
            if(rRearMotor != null) {rRearMotor.setDirection(DcMotorSimple.Direction.REVERSE);}
            if(lRearMotor != null){lRearMotor.setDirection(DcMotorSimple.Direction.REVERSE);}
            setPower(power);
        }
        //If the power input is negative, strafe left.
        else if(power<0)
        {
            setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            if(rFrontMotor != null){rFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);}
            if(lFrontMotor != null){lFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);}
            if(rRearMotor != null) {rRearMotor.setDirection(DcMotorSimple.Direction.FORWARD);}
            if(lRearMotor != null){lRearMotor.setDirection(DcMotorSimple.Direction.FORWARD);}
            setPower(power);
        }
        //If the power input is null, stop the motors.
        else if(power==0){
            stopMotors();
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
        double rightFront = (leftStickY+rightStickX+leftStickX); //these are the calculations need to make a simple
        double leftFront = (leftStickY-rightStickX-leftStickX);  // mecaccnum drive. The left joystick controls moving
        double rightRear=  (leftStickY+rightStickX-leftStickX);  //straight forward/backward and straight sideways. The
        double leftRear = (leftStickY-rightStickX+leftStickX);   //right joystick controls turning.


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
     * Stop all four drive motors by setting their power to zero.
     */
    @Override
    public void stopMotors()
    {
        setPower(0.0);
    }

}
