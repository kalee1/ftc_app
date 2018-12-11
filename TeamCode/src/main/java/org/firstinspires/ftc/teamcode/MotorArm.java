package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.TouchSensor;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.EventListener;


public class MotorArm
{
    Telemetry telemetry;

    private DcMotor elbow = null;
//    private DcMotorEx ElbowEx = null;
    private DcMotor shoulder = null;

//    protected TouchSensor Shoulderfront = null;

//    int targetEncoder = 0;


    protected TouchSensor shoulderFront = null;
    protected TouchSensor elbowFront = null;
    protected TouchSensor elbowRear = null;

    // Constructor for Class
    public MotorArm()
    {
    }

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
            shoulder.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
        try
        {
            shoulderFront = hwmap.touchSensor.get("shoulderFront");
        }
        catch (Exception p_exeception)
        {
            shoulderFront = null;
        }
        try
        {
            elbowFront = hwmap.touchSensor.get("elbowFront");
        }
        catch (Exception p_exeception)
        {
            elbowFront = null;
        }
        try
        {
            elbowRear = hwmap.touchSensor.get("elbowRear");
        }
        catch (Exception p_exeception)
        {
            elbowRear = null;
        }
    }

    public void armDrive( double RightStickY, double LeftStickY, Telemetry telem)
    {
        if (shoulderFront.isPressed())
        {
            shoulder.setPower(-0.2);
        }
        else
        {
            elbow.setPower(RightStickY * .5);
            shoulder.setPower(LeftStickY * .6);
        }

        if (elbowFront.isPressed())
        {
            elbow.setPower(-0.2);
        }
        else if(elbowRear.isPressed())
        {
            elbow.setPower(0.2);
        }
        else
        {
            elbow.setPower(RightStickY * .5);
            shoulder.setPower(LeftStickY * .6);
        }
    }
}