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
        mineralDriveDistanceL = 45; //done
        mineralDriveDistanceR = 37; //done
        mineralDriveDistanceC = 52; //done
        backup = 6;

        //Case 10
        faceDepoHeadingL = 40; //done
        faceDepoHeadingR = 120; //done
        faceDepoHeadingC = 135; //done

        //Case 11
        mineralSlideDistanceL = 0; //done
        mineralSlideDistanceR = 0; //18
        mineralSlideDistanceC = 25; //done
        directionL = right;
        directionG = left;
//        depoSlideDistance = 16;

        //Case 12
        depoDriveDistanceL = 30; //done
        depoDriveDistanceR = 32; //done
        depoDriveDistanceC = 5; //done


        //Case 13
        depoTurnHeadingL = 135;
        depoTurnHeadingR = 135;
        depoTurnHeadingC = 135;



        //Case 15
        faceCraterHeading = 137;

        markerTurnHeading = 90;
        markerSlideDistance = 7;

        headingReset = 45;
        craterDriveDistance = 60;
        craterTurnHeading = 135;
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
