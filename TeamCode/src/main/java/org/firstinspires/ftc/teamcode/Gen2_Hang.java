package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ListIterator;


public class Gen2_Hang
    {

    DcMotor hang = null;

    Telemetry telem;

    public void init(HardwareMap hwmap, Telemetry telemetry)
    {
        telem = telemetry;
        try
        {
            hang = hwmap.dcMotor.get("Hang");
            hang.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
        catch (Exception p_exeception)
        {
            hang = null;
            telem.addData("Hang Not Found", "");
        }
    }


    public enum Lift
    {
        LANDERHANG(0),
        LANDERPREP(-18500);

        public final double targetEncoder;

        Lift(double targetEncoder)
            {
                this.targetEncoder = targetEncoder;
            }
    }

   public void landerPrep()
   {
       if (hang != null)
       {
           if (hang.getCurrentPosition() < Lift.LANDERPREP.targetEncoder)
           {
               hang.setPower(.3);
           }
       }
   }
    public void landerHang()
    {
        if (hang != null)
        {
            if (hang.getCurrentPosition() < Lift.LANDERPREP.targetEncoder)
            {
                hang.setPower(.4);
            }
        }
    }
    public void hangControl(boolean dpadDown, boolean dpadUp)
    {
        if (hang != null)
        {
            if (dpadUp)
            {
                hang.setPower(-.2);
            }
            else if (dpadDown)
            {
                hang.setPower(.8);
            }
            else
            {
                hang.setPower(0.0);
            }
            telem.addData("Hang Encoder Pos", "" + hang.getCurrentPosition());
        }

    }
}
