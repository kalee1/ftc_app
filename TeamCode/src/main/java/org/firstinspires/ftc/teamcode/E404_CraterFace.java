package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Crater Face", group="Field Position")

public class E404_CraterFace extends Error404Autonomus
{
    @Override
    public void init()
    {
        // values are based on my incredible guesstimating including the use of a scaled version of the field and some math.

        mineralDriveDistance = 18;
        mineralSlideDistance = 44;
        depoTurnHeading = 135;
        depoDriveDistance = 47;
        headingReset = 135;
        craterDriveDistance = 45;
        craterTurnHeading = 175;
        craterSlideDistance = 65;
        enterCraterDistance = 32;

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
