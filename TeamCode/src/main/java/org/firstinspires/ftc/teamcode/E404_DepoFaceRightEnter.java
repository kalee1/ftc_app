package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="DepoFace (Right Enter)", group="Field Position")

/** Contains the distance and heading values for the DepoFaceRightEnter autonomous drive path.
 * Starts on the silver side and drives from the lander to the alliance depo, then from the depo to
 * the crater, and enters the crater from the right side.
 *
 * @author Andrew, Error 404: Team Name Not Found
 * @see Error404Autonomus
 * */
public class E404_DepoFaceRightEnter extends Error404Autonomus
{
    /**
     * Initializes drive and heading values for autonomous. */
    @Override public void init()
    {
        mineralDriveDistanceL = 30; //done
        mineralDriveDistanceR = 31; //done
        mineralDriveDistanceC = 45; //done

        faceDepoHeadingL = 135; //done
        faceDepoHeadingR = 225; //done
        faceDepoHeadingC = 225; //done

        directionL = right;
        directionG = left;
//        depoSlideDistance = 16;

        mineralSlideDistanceL = 16; //done
        mineralSlideDistanceR = 15; //done
        mineralSlideDistanceC = 28; //done

        depoDriveDistanceL = 32; //done
        depoDriveDistanceR = 30; //done
        depoDriveDistanceC = 17; //done



        depoTurnHeadingL = 225;
        depoTurnHeadingR = 225;
        depoTurnHeadingC = 225;




        faceCraterHeading = 45;
        markerTurnHeading = 180;
        markerSlideDistance = 7;

        headingReset = 45;
        craterDriveDistance = 48;
        craterTurnHeading = 260;
        craterSlideDistance = 60;
        enterCraterDistance = 15;
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
