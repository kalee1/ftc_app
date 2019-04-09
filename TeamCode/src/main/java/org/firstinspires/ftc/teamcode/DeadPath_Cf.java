package org.firstinspires.ftc.teamcode;

//@Autonomous(name="Crater Face", group="Zombie")

/** Contains the distance and heading values for the CraterFace autonomous drive path.
 * Starts on the gold side of the lander and drives from the lander to the alliance depo, then from
 * the depo to the crater, and enters the crater from the right side.
 *
 * @author Andrew, Error 404: Team Name Not Found
 * @see DeadAuto
 * */
public class DeadPath_Cf extends DeadAuto
{
    /**
     * Initializes drive and heading values for autonomous. */
    @Override
    public void init()
    {
        //case 4
        leftMineral = -59;
        //case 8
        rightMineral = -118;
        //case 6 & 11
        centerMineral = -90;
        //case 12
        mineralDriveDistanceL = 25; //done
        mineralDriveDistanceR = 25; //done
        mineralDriveDistanceC = 21; //done
        //case 13
        backup = 10;
        //case 14
        faceDepoHeadingL = -90; //done
        faceDepoHeadingR = -90; //done
        faceDepoHeadingC = -90; //done
        //case 15
        mineralSlideDistanceL = 10;
        mineralSlideDistanceR = 0;
        mineralSlideDistanceC = 5;
        //case 15
        directionL = right;
        directionG = right;
        //case 16
        depoDriveDistanceL = 0; //all same
        depoDriveDistanceR = 0;
        depoDriveDistanceC = 0;
        //case 17
        depoTurnHeadingL = -90;
        depoTurnHeadingR = -90;
        depoTurnHeadingC = -90;
        //case 18
        markerSlideDistance = 0;
        //case 20
        faceCraterHeading = -90;
        //case 21
        craterDriveDistance = 0;
        //case 22
        craterTurnHeading = -90;
        //case 23
        craterSlideDistance = 0;

        //not used
        enterCraterDistance = 0;
        depoSlideDistance = 0;
        markerTurnHeading = -90;
        headingReset = -90;


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
