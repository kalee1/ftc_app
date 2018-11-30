package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Depo Face", group="Field Position")

public class E404_DepoFace extends Error404Autonomus
{
    @Override
    public void init()
    {
        // values are based on my incredible guesstimating including the use of a scaled version of the field and some math.

        mineralDriveDistance = 55.0;
        mineralSlideDistance = 0.0;
        depoTurnHeading = 45.0; // ~45 degrees
        depoDriveDistance = 0.0;
        headingReset = 45;
        craterDriveDistance = 65;
        craterTurnHeading = 70;
        craterSlideDistance = 15;
        enterCraterDistance = 45;
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
