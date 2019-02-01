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
        //Case 8
        mineralDriveDistanceL = 31; //done
        mineralDriveDistanceR = 31; //done
        mineralDriveDistanceC = 45; //done

        //Case 10
        faceDepoHeadingL = 135; //done
        faceDepoHeadingR = 225; //done
        faceDepoHeadingC = 225; //done

        //Case 11
        mineralSlideDistanceL = 21; //done
        mineralSlideDistanceR = 18; //done
        mineralSlideDistanceC = 25; //done
        directionL = right;
        directionG = left;
//        depoSlideDistance = 16;

        //Case 12
        depoDriveDistanceL = 32; //done
        depoDriveDistanceR = 32; //done
        depoDriveDistanceC = 10; //done


        //Case 13
        depoTurnHeadingL = 225;
        depoTurnHeadingR = 225;
        depoTurnHeadingC = 225;



        //Case 15
        faceCraterHeading = 225;

        markerTurnHeading = 180;
        markerSlideDistance = 7;

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
