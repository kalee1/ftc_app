package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;



public class MecanumChassis extends Chassis
{

    private DcMotor rFront = null;
    private DcMotor rRear = null;
    private DcMotor lFront = null;
    private DcMotor lRear = null;

    @Override
    public void init(HardwareMap hwMap)
    {
        rFront = hwMap.get(DcMotor.class, "rightFront");
        rFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rRear = hwMap.get(DcMotor.class, "rightRear");
        rRear.setDirection(DcMotorSimple.Direction.FORWARD);
        lFront = hwMap.get(DcMotor.class, "leftFront");
        lFront.setDirection(DcMotorSimple.Direction.FORWARD);
        lRear = hwMap.get(DcMotor.class, "leftRear");
        lRear.setDirection(DcMotorSimple.Direction.FORWARD);
    }



    public void forwardDrive(double distance, double power)
    {
    }

    public void reverseDrive(double distance, double power)
    {

    }

    public void pointTurnRight(double heading, double power)
    {

    }

    public void pointTurnLeft(double heading, double power)
    {

    }

    public void slideRight(double power)
    {

    }

    public void slideLeft(double power)
    {

    }

    public void joystickDrive(double xStick, double yStick)
    {
    }

    public void stop()
    {
        rFront.setPower(0.0);
        rRear.setPower(0.0);
        lFront.setPower(0.0);
        lRear.setPower(0.0);
    }

}
