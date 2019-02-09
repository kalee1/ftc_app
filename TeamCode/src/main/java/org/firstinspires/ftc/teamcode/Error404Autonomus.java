package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcontroller.internal.FtcOpModeRegister;
import org.firstinspires.ftc.robotcore.external.Telemetry;

@Autonomous(name="Mother Autonomus", group="Zeta")

/**
 * An opmode that holds a state machine that contains the autonomous robot commands. Has three child
 * classes that contain specific distances and headings for different autonomous drive paths.
 *
 * @author Andrew, Error 404: Team Name Not Found
 * @see OpMode
 * */
public class Error404Autonomus extends OpMode
{
    /* Declare OpMode members. */
    RuckusBot robot = new RuckusBot("MecanumChassis"); // use the class created to define a Testbot's hardware
    /** An int varibale that is used to record the current case the case machine is in. */
    int state = -1;  // used to represent the current state in the state machine
    /** An int variable that is used to record the current motor position at the beginning of a move. */
    int initialPosition = 0;  // used to grab the position of a robot at the beginning of a move

    /** Used to simulate joystick commands. Drives the robot forward. */
    double forward = MecanumChassis.FORWARD;
    /** Used to simulate joystick commands. Drives the robot backward. */
    double backward = MecanumChassis.BACKWARD;
    /** Used to simulate joystick commands. Strafes the robot right. */
    double right = MecanumChassis.RIGHT;
    /** Used to simulate joystick commands. Strafes the robot left. */
    double left = MecanumChassis.LEFT;
    /** Used to simulate joystick commands. Strafes the robot forward-right diagonal. */
    double frDiagonal = MecanumChassis.FORWARD_RIGHT_DIAGONAL;
    /** Used to simulate joystick commands. Strafes the robot forward-left diagonal. */
    double flDiagonal = MecanumChassis.FORWARD_LEFT_DIAGONAL;
    /** Used to simulate joystick commands. Strafes the robot backward-right diagonal. */
    double rrDiagonal = MecanumChassis.REVERSE_RIGHT_DIAGONAL;
    /** Used to simulate joystick commands. Strafes the robot backward-left diagonal. */
    double rlDiagonal = MecanumChassis.REVERSE_LEFT_DIAGONAL;



    /** A move variable that holds differing distances that change based on where the robot starts on the field.
     * The distance to the left mineral location.*/
    protected double mineralDriveDistanceL;
    /** A move variable that holds differing distances that change based on where the robot starts on the field.
     * The distance to the right mineral location.*/
    protected double mineralDriveDistanceR;
    /** A move variable that holds differing distances that change based on where the robot starts on the field.
     * The distance to the center mineral location.*/
    protected double mineralDriveDistanceC;
    /** An enum that stores three variable options for driving to the gold mineral: left, right, and center. */
    protected double[] mineralDriveDistance;
    /** A move variable that holds differing distances that change based on where the robot starts on the field.
     * The distance required to drive to the gold mineral. Assigned either left, right, or center
     * depending on which one is selected from the enum. */
    protected double mineralDriveDistanceFinal;

    /** A turn variable that holds differing angles that change based on where the robot is on the field.
     * The angle needed to turn to face the depo from the left mineral position.*/
    protected double faceDepoHeadingL;
    /** A turn variable that holds differing angles that change based on where the robot is on the field.
     * The angle needed to turn to face the depo from the right mineral position.*/
    protected double faceDepoHeadingR;
    /** A turn variable that holds differing angles that change based on where the robot is on the field.
     * The angle needed to turn to face the depo from the center mineral position.*/
    protected double faceDepoHeadingC;
    /** An enum that stores three variable options for turning to face the depo: left, right, and center. */
    protected double[] faceDepoHeading;
    /** A turn variable that holds differing angles that change based on where the robot starts on the field.
     * The angle required to turn to the alliance depo. Assigned either left, right, or center
     * depending on which one is selected from the enum. */
    protected double faceDepoHeadingFinal;

    /** A move variable that holds differing distances that change based on where the robot starts on the field.
     * The distance required to slide around the minerals from the left mineral position. */
    protected double mineralSlideDistanceL;
    /** A move variable that holds differing distances that change based on where the robot starts on the field.
     * The distance required to slide around the minerals from the right mineral position. */
    protected double mineralSlideDistanceR;
    /** A move variable that holds differing distances that change based on where the robot starts on the field.
     * The distance required to slide around the minerals from the center mineral position. */
    protected double mineralSlideDistanceC;
    /** An enum that stores three variable options for sliding around the minerals: left, right, and center. */
    protected double[] mineralSlideDistance;
    /** A move variable that holds differing distances that change based on where the robot starts on the field.
     * The distance required to slide around the minerals. Assigned either left, right, or center
     * depending on which one is selected from the enum. */
    protected double mineralSlideDistanceFinal;

    /** A turn variable that holds differing target headings that change based on where the robot starts on the field.
     * The angle required to turn towards the depo. */
    protected double depoTurnHeadingL;
    protected double depoTurnHeadingR;
    protected double depoTurnHeadingC;
    protected double[] depoTurnHeading;
    protected double depoTurnHeadingFinal;
    protected double backup;
    /** A move variable that holds differing distances that change based on where the robot starts on the field.
     * The distance required to slide into the field wall to line up on the depo. */
    protected double depoSlideDistance;

    /** An enum that stores three variable options for driving to the depo: left, right, and center. */
    protected double[] depoDriveDistance;
    /** A move variable that holds differing distances that change based on where the robot starts on the field.
     * The distance required to drive to the alliance depo from the left position. */
    protected double depoDriveDistanceL;
    /** A move variable that holds differing distances that change based on where the robot starts on the field.
     * The distance required to drive to the alliance depo from the right position. */
    protected double depoDriveDistanceR;
    /** A move variable that holds differing distances that change based on where the robot starts on the field.
     * The distance required to drive to the alliance depo from the center position. */
    protected double depoDriveDistanceC;
    /** A move variable that holds differing distances that change based on where the robot starts on the field.
     * The distance required to drive to the alliance depo. Assigned either left, right, or center
     * depending on which one is selected from the enum. */
    protected double depoDriveDistanceFinal;

    /** A turn variable that holds the differing angles that change based on where the robot starts on the field.
     * The angle required to turn 45 degrees to the depo in preparation for deploying the team marker. */
    protected double markerTurnHeading;
    /** A move variable that holds the differing distances that change based on where the robot starts on the field.
     * The distance required to slide into the depo to deploy the marker. */
    protected double markerSlideDistance;
    /** A turn variable that holds the differing angles that change based on where the robot starts on the field.
     * The angle required to turn to face the crater. */
    protected double faceCraterHeading;

    /** A move variable that holds differing distances that change based on where the robot starts on the field.
     * The distance required to drive to the crater from the depo. */
    protected double craterDriveDistance;
    /** A turn variable that holds differing target headings that change based on where the robot starts on the field.
     * The target heading required to face the crater. */
    protected double craterTurnHeading;
    /** A move variable that holds differing distances that change based on where the robot starts on the field.
     * The distance required to drive around the crater to enter the crater on a selected side (left or right). */
    protected double craterSlideDistance;
    /** A move variable that holds differing distances that change based on where the robot starts on the field.
     * The distance required to drive up to the edge of the crater -- not used. */
    protected double enterCraterDistance;
    /** A turn variable that corrects any error acquired over the first half of the run. */
    protected double headingReset;
    /** A string that holds the position of the gold mineral. */
    protected String goldPosition;
    /** The amount by which the PID drive algorithms will correct error. */
    double gain = 0.01;
    //Variables that represent which variable to use -- used when the gold mineral is located.
    /** An int that is used to decide which variable in the variable enums to use.*/
    final int LEFT = 0;
    /** An int that is used to decide which variable in the variable enums to use.*/
    final int RIGHT = 1;
    /** An int that is used to decide which variable in the variable enums to use.*/
    final int CENTER = 2;
    protected double[] direction;
    protected double directionL;
    protected double directionG;
    protected double directionFinal;
    protected double leftMineral;
    protected double rightMineral;
    protected double centerMineral;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    /** Calls the required init methods in the needed classes.
     * Initializes all the hardware and methods in the various class objects called. */
    @Override
    public void init()
    {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap, telemetry, true);
        mineralDriveDistance = new double[] {mineralDriveDistanceL, mineralDriveDistanceR, mineralDriveDistanceC};
        mineralSlideDistance = new double[] {mineralSlideDistanceL, mineralSlideDistanceR, mineralSlideDistanceC};
        faceDepoHeading = new double[] {faceDepoHeadingL, faceDepoHeadingR, faceDepoHeadingC};
        depoDriveDistance = new double[] {depoDriveDistanceL, depoDriveDistanceR, depoDriveDistanceC};
        depoTurnHeading = new double[] {depoTurnHeadingL, depoTurnHeadingR, depoTurnHeadingC};
        direction = new double[] {directionL,directionG};
        goldPosition = "NoPosition";




        // Send telemetry message to signify robot waiting;
//        telemetry.addData("Say", "Let's Rock and Roll");
//        telemetry.addData("initial heading: ", initialPosition);

    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    /** Runs through once after the driver hits start.
     * Resets the internal timer to 0.0 */
    @Override
    public void start()
    {
        robot.start();
        resetStartTime();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    /** Runs through code repeatedly after the driver hits play but before the driver hits stop.
     * This loop method contains a state machine that runs through the autonomous moves.
     * */
    @Override
    public void loop()
    {
        switch (state)
        {
            //land
            case -1:
                if(robot.hangDrive(.9, 18750, Gen2_Hang.HangDirection.OUT))
                {
                    resetStartTime();
                    state = 1;
                }
                break;

//            case 0:
//                if(robot.pointTurn(.1, 0, 2))
//                {
//                    resetStartTime();
//                    state = 1;
//                }
//                break;

                //back out of latch
            case 1:
                if(robot.drive(.3, backward, gain, 2, 6))
                {
                    resetStartTime();
                    state = 2;
                }
                break;

                //slide away from lander
            case 2:
                if(robot.drive(.3, right, gain, 6, 6))
                {
                    resetStartTime();
                    state = 3;
                }
                break;

            case 3:
                if(robot.drive(.3, forward, gain, 4, 2))
                {
                    resetStartTime();
                    state = 4;
                }
                break;

                // turn to left
            case 4:
                if(robot.pointTurn(.2, leftMineral , 3))
                {
                    resetStartTime();
                    state = 5;
                }
                break;
            case 5:
                if(robot.goldPosition().equals("center"))
                {
                    mineralDriveDistanceFinal = mineralDriveDistance[LEFT];
                    mineralSlideDistanceFinal = mineralSlideDistance[LEFT];
                    faceDepoHeadingFinal = faceDepoHeading[LEFT];
                    depoDriveDistanceFinal = depoDriveDistance[LEFT];
                    depoTurnHeadingFinal = depoTurnHeading[LEFT];
                    directionFinal = direction[LEFT];
                    goldPosition = "left";
                    robot.tfodShutdown();
                    resetStartTime();
                    state = 12;
                }
                else if(getRuntime() > 2.5)
                {
                    resetStartTime();
                    state = 6;
                }
                break;



                //turn to center
            case 6:
                if(robot.pointTurn(.2, centerMineral, 3))
                {
                    resetStartTime();
                    state = 7;
                }
                break;
            case 7:
                if(robot.goldPosition().equals("center"))
                {
                    mineralDriveDistanceFinal = mineralDriveDistance[CENTER];
                    mineralSlideDistanceFinal = mineralSlideDistance[CENTER];
                    faceDepoHeadingFinal = faceDepoHeading[CENTER];
                    depoDriveDistanceFinal = depoDriveDistance[CENTER];
                    depoTurnHeadingFinal = depoTurnHeading[CENTER];
                    directionFinal = direction[RIGHT];
                    goldPosition = "center";
                    robot.tfodShutdown();
                    resetStartTime();
                    state = 12;
                }
                else if(getRuntime() > 2.5)
                {
                    resetStartTime();
                    state = 8;
                }
                break;



                //turn to right
            case 8:
                if(robot.pointTurn(.2, rightMineral, 3))
                {

                    resetStartTime();
                    state = 9;
                }
                break;
            case 9:
                if(robot.goldPosition().equals("center"))
                {
                    mineralDriveDistanceFinal = mineralDriveDistance[RIGHT];
                    mineralSlideDistanceFinal = mineralSlideDistance[RIGHT];
                    faceDepoHeadingFinal = faceDepoHeading[RIGHT];
                    depoDriveDistanceFinal = depoDriveDistance[RIGHT];
                    depoTurnHeadingFinal = depoTurnHeading[RIGHT];
                    directionFinal = direction[RIGHT];
                    goldPosition = "right";
                    robot.tfodShutdown();
                    resetStartTime();
                    state = 12;
                }
                else if(getRuntime() > 2.5)
                {
                    resetStartTime();
                    state = 10;
                }
                break;


            case 10:
                if(goldPosition.equals("NoPosition"))
                {
                    goldPosition = "center";
                    mineralDriveDistanceFinal = mineralDriveDistance[CENTER];
                    mineralSlideDistanceFinal = mineralSlideDistance[CENTER];
                    faceDepoHeadingFinal = faceDepoHeading[CENTER];
                    depoDriveDistanceFinal = depoDriveDistance[CENTER];
                    depoTurnHeadingFinal = depoTurnHeading[CENTER];
                    directionFinal = direction[RIGHT];
                    resetStartTime();
                    state = 11;
                }
                else
                {
                    resetStartTime();
                    state = 12;
                }
                break;

            case 11:
                if(robot.pointTurn(.3, centerMineral, 3))
                {
                    resetStartTime();
                    state = 12;
                }
                break;

            case 12:
                if(robot.drive(.3, forward, gain, mineralDriveDistanceFinal, 4))
                {
                    resetStartTime();
                    state = 13;
                }
                break;

            case 13:
                if(robot.drive(.4, backward, gain, backup, 4))
                {
                    resetStartTime();
                    state = 14;
                }
                break;

            case 14:
                if (robot.pointTurn(.3, faceDepoHeadingFinal, 4))
                {
                    resetStartTime();
                    state = 15;
                }
                break;

            case 15:
                if(robot.drive(.4, directionFinal, gain, mineralSlideDistanceFinal, 4))
                {
                    resetStartTime();
                    state = 16;
                }
                break;

            case 16:
                if(robot.drive(.5, backward, gain, depoDriveDistanceFinal, 6))
                {
                    resetStartTime();
                    state = 17;
                }
                break;

            case 17:
                if(robot.pointTurn(.3, depoTurnHeadingFinal, 4))
                {
                    resetStartTime();
                    state = 18;
                }
                break;

            case 18:
                if(robot.drive(.4, left, gain, markerSlideDistance, 4))
                {
                    resetStartTime();
                    robot.markDeploy();
                    state = 19;
                }
                break;

            case 19:
                if(getRuntime() > 1.5)
                {
                    resetStartTime();
                    state = 20;
                }
                break;

            case 20:
                if(robot.pointTurn(.3, faceCraterHeading,4))
                {
                    resetStartTime();
                    robot.markRetract();
                    state = 21;
                }
                break;

            case 21:
                if(robot.drive(.8, forward, gain, craterDriveDistance, 6))
                {
                    resetStartTime();
                    state = 22;
                }
                break;

            //Turn to face the crater.
            case 22:
                if(robot.pointTurn(.4, craterTurnHeading, 6))
                {
                    state = 23;
                    resetStartTime();
                }
                break;

            //Strafe right, around the crater.
            case 23:
                if(robot.drive(.6, right, gain, craterSlideDistance, 6))
                {
                    state = 25;
                    resetStartTime();
                }
                break;

//            case 24:
//                if(robot.drive(.5, forward, gain, enterCraterDistance, 5))
//                {
//                    resetStartTime();
//                    state = 25;
//                }
//                break;


            case 25:
                if(robot.armDeploy(-5000, 7000, false))
                {
                    resetStartTime();
                    state = 26;
                }
                break;


            default:
                break;
        }
//        telemetry.addData("state", state);

        //Post the current state value to the driver station phone.
//        telemetry.addData("1)", "state: " + state );
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    /** Runs through once after the stop button on the driver station phone is pressed.
     * Stops all motors and interrupts the loop method */
    @Override
    public void stop()
    {
        //stop the drive motors.
        robot.stopMotors();
    }
}
