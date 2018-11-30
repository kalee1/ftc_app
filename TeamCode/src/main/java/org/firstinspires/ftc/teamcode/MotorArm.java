package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class MotorArm
{

    DcMotor Elbow = null;
    DcMotor Shoulder = null;
    protected TouchSensor Shoulderfront = null;
    protected TouchSensor Shoulderback = null;
    protected TouchSensor Elbowfront = null;
    protected TouchSensor Elbowback = null;

    double Pos1 = 0;
    double Pos2 = 0;

    // Constructor for Class
    public MotorArm()
    {
    }

    public void Arminit(HardwareMap hwmap, Telemetry telem)
    {

        Elbow = hwmap.dcMotor.get("Elbow");
        Shoulder = hwmap.dcMotor.get("Shoulder");

        Elbow.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Shoulder.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        try
        {
            Shoulderfront = hwmap.touchSensor.get("Shoulderfront");
            telem.addData("", "" + Shoulderfront.getValue());
            telem.update();
        }   catch (Exception p_exeception) {

            telem.addData("SHOULDER-FRONT", "NOT FOUND");
            Shoulderfront = null;
        }

        try
        {
            Shoulderback = hwmap.touchSensor.get("Shoulderback");
            telem.addData("", "" + Shoulderback.getValue());
            telem.update();
        }   catch (Exception p_exeception) {

            telem.addData("SHOULDER-BACK", "NOT FOUND");
            telem.update();
            Shoulderback = null;
        }

        try
        {
            Elbowfront = hwmap.touchSensor.get("Elbowfront");
            telem.addData("", "" + Elbowfront.getValue());
            telem.update();
        }   catch (Exception p_exeception) {

            telem.addData("ELBOW-FRONT", "NOT FOUND");
            telem.update();
            Elbowfront = null;
        }

        try
        {
            Elbowback = hwmap.touchSensor.get("Elbowback");
            telem.addData("", "" + Elbowback.getValue());
            telem.update();
        }   catch (Exception p_exeception) {

            telem.addData("Elbow-Back", "NOT FOUND");
            telem.update();
            Elbowback = null;
        }
    }

    public void ArmDrive( double RightStickY, double LeftStickY, Telemetry telem)
    {

        if (RightStickY > 0)
        {
            //Elbow.setPower(.15);
        }
        else if (RightStickY < 0)
        {
            //Elbow.setPower(-.15);
        }
        else
        {
            //Elbow.setPower(0.0);
        }

        Shoulder.setPower(LeftStickY * .6);

        if (Shoulderfront.isPressed())
        {
            Shoulder.setPower(.5);
            telem.addData("ShoulderFront", "has been pressed");
            telem.update();
        }

        if (Shoulderback.isPressed())
        {
            Shoulder.setPower(-.5);
            telem.addData("ShoulderBack", "has been pressed");
            telem.update();
        }

        if (Elbowfront.isPressed())
        {
            //Elbow.setPower(.5);
            telem.addData("ElbowFront", "has been pressed");
            telem.update();
        }
        if (Elbowback.isPressed())
        {
            //Elbow.setPower(-.5);
            telem.addData("ElbowBack", "has been pressed");
            telem.update();

        }



    }

}

