package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="TouchSensorTeleop", group="Teleop")
public class TouchSensor extends OpMode
{

    protected com.qualcomm.robotcore.hardware.TouchSensor Shoulderfront = null;
    protected com.qualcomm.robotcore.hardware.TouchSensor Shoulderback = null;
    protected com.qualcomm.robotcore.hardware.TouchSensor Elbowfront = null;
    protected com.qualcomm.robotcore.hardware.TouchSensor Elbowback = null;

    @Override
    public void init()
    {

        try
        {
            Shoulderfront = hardwareMap.touchSensor.get("Shoulderfront");
            telemetry.addData("", "" + Shoulderfront.getValue());
            telemetry.update();
        } catch (Exception p_exeception)
        {

            telemetry.addData("SHOULDER-FRONT", "NOT FOUND");
            Shoulderfront = null;
        }

       /* try
        {
            Shoulderback = hardwareMap.touchSensor.get("Shoulderback");
            telemetry.addData("", "" + Shoulderback.getValue());
            telemetry.update();
        } catch (Exception p_exeception)
        {

            telemetry.addData("SHOULDER-BACK", "NOT FOUND");
            telemetry.update();
            Shoulderback = null;
        }

        try
        {
            Elbowfront = hardwareMap.touchSensor.get("Elbowfront");
            telemetry.addData("", "" + Elbowfront.getValue());
            telemetry.update();
        } catch (Exception p_exeception)
        {

            telemetry.addData("ELBOW-FRONT", "NOT FOUND");
            telemetry.update();
            Elbowfront = null;
        }

        try
        {
            Elbowback = hardwareMap.touchSensor.get("Elbowback");
            telemetry.addData("", "" + Elbowback.getValue());
            telemetry.update();
        } catch (Exception p_exeception)
        {

            telemetry.addData("Elbow-Back", "NOT FOUND");
            telemetry.update();
            Elbowback = null;
        }
        */

    }
    @Override
    public void loop()
    {
        telemetry.addData("", "" + Shoulderfront.getValue());
        telemetry.update();

        if (Shoulderfront.isPressed())
        {
            //Shoulder.setPower(.5);
            telemetry.addData("ShoulderFront", "has been pressed");
            telemetry.update();
        }

       /* if (Shoulderback.isPressed())
        {
            //Shoulder.setPower(-.5);
            telemetry.addData("ShoulderBack", "has been pressed");
            telemetry.update();
        }

        if (Elbowfront.isPressed())
        {
            //Elbow.setPower(.5);
            telemetry.addData("ElbowFront", "has been pressed");
            telemetry.update();
        }
        if (Elbowback.isPressed())
        {
            //Elbow.setPower(-.5);
            telemetry.addData("ElbowBack", "has been pressed");
            telemetry.update();

        }
        */

    }
    @Override
    public void stop()
    {

    }
}