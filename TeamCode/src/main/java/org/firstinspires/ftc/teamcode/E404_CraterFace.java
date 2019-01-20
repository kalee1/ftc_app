package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Crater Face", group="Field Position")
/** Contains the distance and heading values for the CraterFace autonomous drive path.
 * Starts on the gold side of the lander and drives from the lander to the alliance depo, then from
 * the depo to the crater, and enters the crater from the right side.
 *
 * @author Andrew, Error 404: Team Name Not Found
 * @see Error404Autonomus
 * */
public class E404_CraterFace extends Error404Autonomus
{
    /**
     * Initializes drive and heading values for autonomous. */
    @Override
    public void init()
    {
        mineralDriveDistanceL = 13; //done
        mineralDriveDistanceR = 12; //done
        mineralDriveDistanceC = 10; //done

        faceDepoHeadingL = 0; //done
        faceDepoHeadingR = 0; //done
        faceDepoHeadingC = 0; //done

        mineralSlideDistanceL = 35;
        mineralSlideDistanceR = 46;
        mineralSlideDistanceC = 45;

        depoTurnHeading = 20;
        depoSlideDistance = 0;

        depoDriveDistanceL = 60; //all same
        depoDriveDistanceR = 60;
        depoDriveDistanceC = 60;

        markerTurnHeading = depoTurnHeading;
        markerSlideDistance = 0;
        faceCraterHeading = depoTurnHeading;

        headingReset = 135;
        craterDriveDistance = 60;
        craterTurnHeading = 220;
        craterSlideDistance = 65;
        enterCraterDistance = 32;

        super.init();
    }

    /** Calls the parent start method. */
    @Override public void start()
    {
        super.start();
    }

    /** Calls the parent loop method. */
    @Override public void loop()
    {
        super.loop();
    }
}
