package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Blue Center", group="Field Position")

public class E404_BlueCenter extends Error404Autonomus
{
    @Override
    public void init()
    {
        // values are based on my incredible guesstimating including the use of a scaled version of the field and some math.

//        mineralDriveDistance = 15;
//        mineralSlideDistance = 45;
//        depoTurnHeading = 125;
//        depoDriveDistance = 35;
        headingReset = 125;
        craterDriveDistance = 65;
        craterTurnHeading = 180;
        craterSlideDistance = 15;
        enterCraterDistance = 30;

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
