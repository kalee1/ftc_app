package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Red Center", group="Field Position")

public class E404_RedCenter extends Error404Autonomus
{
    @Override
    public void init()
    {
        // values are based on my incredible guesstimating including the use of a scaled version of the field and some math.

        mineralDriveDistance = 27.6;
        mineralSlideDistance = 21.21;
        depoTurnHeading = 45.0; // ~45 degrees
        depoDriveDistance = 60.8;
        craterDriveDistance = 121.6;

        super.init();
    }

    @Override public void start()
    {
        super.start();
    }


    @Override public void loop()
    {
        super.loop();
    }
}
