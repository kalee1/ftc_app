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

   public void landerPrep()
   {
       if (hang.getCurrentPosition() < Lift.LANDERPREP.targetEncoder)
       {
           hang.setPower(.3);
       }
   }
    public void landerHang()
    {
        if (hang.getCurrentPosition() < Lift.LANDERPREP.targetEncoder)
        {
            hang.setPower(.4);
        }
    }
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
            telemetry.addData("Up: ", up);
            telemetry.addData("Down: ", down);
            telemetry.addData("Hang Power: ", power );
        }
    }


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

    public void stopHangMotor()
    {
        if(hang != null)
        {
            hang.setPower(0.0);
        }
    }

}
