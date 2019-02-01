package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="DepoFace (Left Enter)", group="Field Position")
/** Contains the distance and heading values for the DepoFaceLeftEnter autonomous drive path.
 * Starts on the silver side and drives from the lander to the alliance depo, then from the depo to
 * the crater, and enters the crater from the left side.
 *
 * @author Andrew, Error 404: Team Name Not Found
 * @see Error404Autonomus
 * */
public class E404_DepoFaceLeftEnter extends Error404Autonomus
{
    /**
     * Initializes drive and heading values for autonomous. */
    @Override public void init()
    {
        mineralDriveDistanceL = 31; //done
        mineralDriveDistanceR = 31; //done
        mineralDriveDistanceC = 45; //done

        faceDepoHeadingL = 135; //done
        faceDepoHeadingR = 225; //done
        faceDepoHeadingC = 225; //done

        mineralSlideDistanceL = 35; //done
        mineralSlideDistanceR = 46; //done
        mineralSlideDistanceC = 45; //done

        depoTurnHeadingL = 135;
        depoTurnHeadingR = 135;
        depoTurnHeadingC = 135;

        directionL = right;
        directionG = left;
        depoSlideDistance = 14;

        depoDriveDistanceL = 52; //done
        depoDriveDistanceR = 45; //done
        depoDriveDistanceC = 55; //done

        markerTurnHeading = 180;
        markerSlideDistance = 7;
        faceCraterHeading = 220;

        headingReset = 45;
        craterDriveDistance = 60;
        craterTurnHeading = 220;
        craterSlideDistance = 0.0;
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
