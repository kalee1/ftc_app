package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;


public class ArmLift
    {

    DcMotor Lift = null;
    DcMotor Drop = null;

    public void init(HardwareMap hwmap, Telemetry telem)
        {
            try
                {
                Lift = hwmap.dcMotor.get("armlift");
                }
            catch (Exception p_exeception)
                {
                Lift = null;
                telem.addData("Lift Not Found", "");
                }
            try
                {
                Drop = hwmap.dcMotor.get("armdrop");
                }
            catch (Exception p_exeception)
                {
                Drop = null;
                telem.addData("Armdrop Not Found", "");
                }
        }

    public void armpull(HardwareMap hwmap, Telemetry telem)
        {

        }
    }
