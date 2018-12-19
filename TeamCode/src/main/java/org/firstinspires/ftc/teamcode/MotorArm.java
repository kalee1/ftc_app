package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class MotorArm
{
    DcMotor elbow = null;
    DcMotor shoulder = null;
    protected TouchSensor shoulderFront = null;
    protected TouchSensor elbowFront = null;
    protected TouchSensor elbowRear = null;

    TestVuforia vuTest = new TestVuforia();

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
            telem.addData("Elbow Not Found", "");
        }
        try
        {
            shoulder = hwmap.dcMotor.get("shoulder");
            shoulder.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
        catch (Exception p_exeception)
        {
            shoulder = null;
            telem.addData("Shoulder Not Found", "");
        }
        try
        {
            shoulderFront = hwmap.touchSensor.get("shoulderFront");
        }
        catch (Exception p_exeception)
        {
            shoulderFront = null;
            telem.addData("ShoulderFront Not Found", "");
        }
        try
        {
            elbowFront = hwmap.touchSensor.get("elbowFront");
        }
        catch (Exception p_exeception)
        {
            elbowFront = null;
            telem.addData("ElbowFront Not Found", "");
        }
        try
        {
            elbowRear = hwmap.touchSensor.get("elbowRear");
        }
        catch (Exception p_exeception)
        {
            elbowRear = null;
            telem.addData("ElbowRear Not Found", "");
        }


    }

    public void armDrive( double RightStickY, double LeftStickY)
    {
//        if (shoulderFront.isPressed())
//        {
//            shoulder.setPower(-0.2);
//        }
//        else
//        {
//            elbow.setPower(RightStickY * .5);
//            shoulder.setPower(LeftStickY * .6);
//        }
//
//        if (elbowFront.isPressed())
//        {
//            elbow.setPower(-0.2);
//        }
//        else if(elbowRear.isPressed())
//        {
//            elbow.setPower(0.2);
//        }
//        else
//        {
//            elbow.setPower(RightStickY * .5);
//            shoulder.setPower(LeftStickY * .6);
//        }
    }
}