package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Armvuforia extends OpMode
    {
    TestVuforia vuTest = new TestVuforia();

    // Constructor for Class
    public Armvuforia()
        {
        }

    @Override
    public void init()
        {

        }

    @Override
    public void loop()
        {
            if (vuTest.NavTarget == "Back-Space")
                {
                //Elbow.setpower(1);
                telemetry.addData("", "" + vuTest.navTarget);
                }
            else
                {
                //Elbow.setpower(0.0);
                }
        }

    @Override
    public void stop()
        {
        }
    }