package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;


public class Gen2_Hang
    {

    DcMotor Hang = null;

    public void init(HardwareMap hwmap, Telemetry telem)
        {
            try
                {
                Hang = hwmap.dcMotor.get("Hang");
                }
            catch (Exception p_exeception)
                {
                Hang = null;
                telem.addData("Hang Not Found", "");
                }
        }


    public enum Lift
        {
            LANDERDOWN(-5000);

        public final double targetEncoder;

        Lift(double targetEncoder)
            {
                this.targetEncoder = targetEncoder;
            }
        }

        public void hangTeleop(boolean dpaddown, boolean dpadup)
            {
                if (dpadup)
                    {
                    Hang.setPower(1);
                    }
                else if (dpaddown)
                    {
                    Hang.setPower(-1);
                    }
                else
                    {
                    Hang.setPower(0.0);
                    }
            }

        public void hangAuto()
            {
               if(Hang.getCurrentPosition() >= Lift.LANDERDOWN.targetEncoder)
                    {
                    Hang.setPower(-0.5);
                    }
                else
                    {
                    Hang.setPower(0.0);
                    }
            }

}
