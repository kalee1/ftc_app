package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class MotorArm
{
    DcMotor Elbow = null;
    DcMotor Shoulder = null;

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
    }

    public void ArmDrive(double right_stick_y ,double left_stick_y , Telemetry telem)
    {

        telem.addData("Telemetry Test:", "Clone Wars Saved!!!!");

        if (right_stick_y > 0)
        {
            Elbow.setPower(.15);
        }
        else if (right_stick_y < 0)
        {
            Elbow.setPower(-.15);
        }
        else
        {
            Elbow.setPower(0.0);
        }

        Shoulder.setPower(left_stick_y * .6);

    }

}