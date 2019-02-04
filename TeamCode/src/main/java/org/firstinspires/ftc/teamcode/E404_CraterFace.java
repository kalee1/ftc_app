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
        mineralDriveDistanceL = 23; //done
        mineralDriveDistanceR = 23; //done
        mineralDriveDistanceC = 19; //done

        faceDepoHeadingL = -20; //done
        faceDepoHeadingR = 10; //done
        faceDepoHeadingC = 0; //done

        mineralSlideDistanceL = 0;
        mineralSlideDistanceR = 0;
        mineralSlideDistanceC = 0;

        //Case 13
        depoTurnHeadingL = -20;
        depoTurnHeadingR = 10;
        depoTurnHeadingC = 0;

        depoSlideDistance = 0;
        directionL = right;
        directionG = right;

        depoDriveDistanceL = 0; //all same
        depoDriveDistanceR = 0;
        depoDriveDistanceC = 0;

        markerTurnHeading = 0;
        markerSlideDistance = 0;
        faceCraterHeading = 0;

        headingReset = 0;
        craterDriveDistance = 0;
        craterTurnHeading = 0;
        craterSlideDistance = 0;
        enterCraterDistance = 0;

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
