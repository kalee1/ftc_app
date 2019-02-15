package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="camera TF test", group="Teleop")

public class CameraVision extends OpMode
{
    RuckusBot robot = new RuckusBot("MecanumChassis"); // use the class created to define a Testbot's hardware

    public void init()
    {
        robot.init(hardwareMap, telemetry, true);
        telemetry.addData("ready ", "to go");


    }

    public void loop()
    {
        telemetry.addData("gold position",robot.goldPosition());

    }
    public void stop()
    {
        robot.tfodShutdown();
    }
}
