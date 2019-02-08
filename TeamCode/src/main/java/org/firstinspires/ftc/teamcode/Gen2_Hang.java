package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ListIterator;


public class Gen2_Hang
{

    DcMotor hang = null;

    Telemetry telemetry;

    enum HangDirection {IN, OUT}

    boolean moving = false;
    double initialPosition;


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

    public void start()
    {
        if(hang != null)
        {
//            hang.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            hang.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//            hang.setPower(0.0);
        }
    }


    public enum Hang
    {
        LAND(-18500),
        LIFT(0);

        public final double distance;

        Hang(double distance)
            {
                this.distance = distance;
            }
    }

    public void hangTeleop(boolean dpadDown,boolean dpadUp,double power)
    {
        if (hang != null)
        {
            if (dpadDown)
            {
                hang.setPower(power);
            }
            else if (dpadUp)
            {
                hang.setPower(-power);
            }
            else
            {
                hang.setPower(0.0);
            }
            telemetry.addData("Hang Encoder Pos: ", hang.getCurrentPosition());
            telemetry.addData("Up: ", dpadUp);
            telemetry.addData("Down: ", dpadDown);
            telemetry.addData("Hang Power: ", power );
        }
    }


    public boolean landerHang(double power, HangDirection direction)
    {
        if(!moving)
        {
            initialPosition = hang.getCurrentPosition();

            moving = true;
        }

        if(direction.equals(HangDirection.IN))
        {

            hangTeleop(true, false, power);
        }
        else if(direction.equals(HangDirection.OUT))
        {
            hangTeleop(false, true, power);
        }

        if(Math.abs(hang.getCurrentPosition() - initialPosition) >= Hang.LAND.distance)
        {
            stopHangMotor();
            moving = false;
        }

        return !moving;
    }

    public void stopHangMotor()
    {
        if(hang != null)
        {
            hang.setPower(0.0);
        }
    }

}
