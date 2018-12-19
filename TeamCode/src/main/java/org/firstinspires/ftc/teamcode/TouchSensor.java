package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TouchSensor
    {

    protected com.qualcomm.robotcore.hardware.TouchSensor shoulderFront = null;
    protected com.qualcomm.robotcore.hardware.TouchSensor Shoulderback = null;
    protected com.qualcomm.robotcore.hardware.TouchSensor Elbowfront = null;
    protected com.qualcomm.robotcore.hardware.TouchSensor Elbowback = null;

    public void init(HardwareMap hwmap,Telemetry telem)
        {

            try
                {
                shoulderFront = hwmap.touchSensor.get("shoulderFront");
                //telem.addData("", "" + shoulderFront.getValue());
                //telem.update();
                }
            catch (Exception p_exeception)
                {

                //telem.addData("SHOULDER-FRONT", "NOT FOUND");
                shoulderFront = null;
                }

       /* try
        {
            Shoulderback = hardwareMap.touchSensor.get("Shoulderback");
            //telem.addData("", "" + Shoulderback.getValue());
            //telem.update();
        } catch (Exception p_exeception)
        {

            //telem.addData("SHOULDER-BACK", "NOT FOUND");
            //telem.update();
            Shoulderback = null;
        }

        try
        {
            Elbowfront = hardwareMap.touchSensor.get("Elbowfront");
            //telem.addData("", "" + Elbowfront.getValue());
            //telem.update();
        } catch (Exception p_exeception)
        {

            //telem.addData("ELBOW-FRONT", "NOT FOUND");
            //telem.update();
            Elbowfront = null;
        }

        try
        {
            Elbowback = hardwareMap.touchSensor.get("Elbowback");
            //telem.addData("", "" + Elbowback.getValue());
            //telem.update();
        } catch (Exception p_exeception)
        {

            //telem.addData("Elbow-Back", "NOT FOUND");
            //telem.update();
            Elbowback = null;
        }
        */

        }

    public void touchloop()
        {
            //telem.addData("", "" + shoulderFront.getValue());
            //telem.update();

            if (shoulderFront.isPressed())
                {
                //Shoulder.setPower(.5);
                //telem.addData("shoulderFront", "has been pressed");
                //telem.update();
                }

       /* if (Shoulderback.isPressed())
        {
            //Shoulder.setPower(-.5);
            //telem.addData("ShoulderBack", "has been pressed");
            //telem.update();
        }

        if (Elbowfront.isPressed())
        {
            //Elbow.setPower(.5);
            //telem.addData("ElbowFront", "has been pressed");
            //telem.update();
        }
        if (Elbowback.isPressed())
        {
            //Elbow.setPower(-.5);
            //telem.addData("ElbowBack", "has been pressed");
            //telem.update();

        }
        */

        }
    }