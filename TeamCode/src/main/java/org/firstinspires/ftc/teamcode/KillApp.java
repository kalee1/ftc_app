package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="crash", group="crash")
public class KillApp extends OpMode
{
    @Override
    public void init()
    {

    }

    @Override
    public void start()
    {
        new Thread()
        {
            public void run()
            {
                throw new RuntimeException("Robot controller crash!");
            }
        }
        .start();
    }

    @Override
    public void loop()
    {

    }
    
}
