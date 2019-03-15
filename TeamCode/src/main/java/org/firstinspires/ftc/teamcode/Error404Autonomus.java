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
    /** An int varibale that is used to record the current case the case machine is in and what case
     *  the case machine will start in. */
    int state = -1;
    /** An int variable that is used to record the current motor position at the beginning of a move. */
    int initialPosition = 0;

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
     * The angle required to turn towards the depo from the left mineral position. */
    protected double depoTurnHeadingL;
    /** A turn variable that holds differing target headings that change based on where the robot starts on the field.
     * The angle required to turn towards the depo from the right mineral position. */
    protected double depoTurnHeadingR;
    /** A turn variable that holds differing target headings that change based on where the robot starts on the field.
     * The angle required to turn towards the depo from the center mineral position. */
    protected double depoTurnHeadingC;
    /** An enum that stores the differing target headings needed to face the depo from any of the three mineral positions. */
    protected double[] depoTurnHeading;
    /** The angle required to turn to face the depo from the sampling field. Assigned either left,
     * right, or center, depending on which mineral the robot sampled. */
    protected double depoTurnHeadingFinal;
    /** A move variable that holds the drive distance needed to back up from a mineral after pushing
     * it across the field (so as not to snag on it). */
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
    /** An enum that stores two direction options for strafing into the wall. */
    protected double[] direction;
    /** The strafe direction used when the robot samples the left mineral. */
    protected double directionL;
    /** The strafe direction used when the robot samples the center or right minerals. */
    protected double directionG;
    /** The strafe direction used to strafe into the wall after sampling. Assigned either left or
     * right depending on which mineral was sampled.*/
    protected double directionFinal;
    /** The angle required to turn to the left mineral. */
    protected double leftMineral;
    /** The angle required to turn to the right mineral. */
    protected double rightMineral;
    /** The angle required to turn to the center mineral. */
    protected double centerMineral;

    protected double mineralTurnDistance;
    protected int fail = 0;

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
            // Land
            case -1:
                if (robot.hangDrive(.9, 9100, Gen2_Hang.HangDirection.OUT) || getRuntime() > 4.5)
                {
                    robot.stopMotors();
                    resetStartTime();
                    state = 0;
                }
                break;

            // Correct robot heading
            case 0:
                if (robot.reset(.1, 2))
                {
                    resetStartTime();
                    state = 1;
                }
                break;

            // Back out of latch
            case 1:
                if (robot.drive(.2, backward, gain, 2, 6))
                {
                    resetStartTime();
                    state = 2;
                }
                break;

                //check the pitch of the robot to ensure robot is not stuck
            case 2:
                //robot is not stuck - continue with program
                if(robot.goodPitch())
                {
                    resetStartTime();
                    state = 3;
                }
                //robot is stuck - execute fail-safe
                else
                {
                    //if robot has executed the fail-safe 3 times and is still stuck, stop executing
                    if(fail >= 3)
                    {
                        resetStartTime();
                        robot.stopMotors();
                        state = 999;
                    }
                    //stop the motors and enter fail-safe
                    else
                    {
                        resetStartTime();
                        robot.stopMotors();
                        state = 66;
                    }
                }
                break;

                //fail-safe step 1:
                //   drive forward to original landing position
            case 66:
                if(robot.drive(.2, forward, gain, 2, 4))
                {
                    resetStartTime();
                    state = 67;
                }
                break;

                //fail-safe step 2:
                //   extend hook for one second or 1000 encoders -- will extend the hanger all the
                //   way out until it stalls up against the top of the hanger case -- then reset to
                //   case 1 and reattempt detaching from lander
            case 67:
                if(robot.hangDrive(.9, 1000, Gen2_Hang.HangDirection.OUT) || getRuntime() > 1)
                {
                    resetStartTime();
                    fail++;
                    state = 1;
                }
                break;

            // Slide away from lander
            case 3:
                if (robot.drive(.3, right, gain, 6, 6))
                {
                    resetStartTime();
                    state = 4;
                }
                break;

             // Drive forward the same amount the robot drove back in case 1
            case 4:
                if (robot.drive(.3, forward, gain, 4, 2))
                {
                    resetStartTime();
                    state = 5;
                }
                break;

            case 5:
                if(robot.pointTurn(.2, -75, 3))
                {
                    resetStartTime();
                    state = 6;
                }
                break;

            case 6:
                String goldLocation = robot.goldPosition();
                Boolean found = false;
                if(goldLocation.equals("left"))
                {
                    found = true;
                    mineralDriveDistanceFinal = mineralDriveDistance[LEFT];
                    mineralSlideDistanceFinal = mineralSlideDistance[LEFT];
                    faceDepoHeadingFinal = faceDepoHeading[LEFT];
                    depoDriveDistanceFinal = depoDriveDistance[LEFT];
                    depoTurnHeadingFinal = depoTurnHeading[LEFT];
                    directionFinal = direction[LEFT];
                    goldPosition = "left";
                    mineralTurnDistance = leftMineral;
                    robot.tfodShutdown();
                    resetStartTime();
                    state = 11;

                }
                else if(goldLocation.equals("center"))
                {
                    found = true;
                    mineralDriveDistanceFinal = mineralDriveDistance[CENTER];
                    mineralSlideDistanceFinal = mineralSlideDistance[CENTER];
                    faceDepoHeadingFinal = faceDepoHeading[CENTER];
                    depoDriveDistanceFinal = depoDriveDistance[CENTER];
                    depoTurnHeadingFinal = depoTurnHeading[CENTER];
                    directionFinal = direction[RIGHT];
                    goldPosition = "center";
                    mineralTurnDistance = centerMineral;
                    robot.tfodShutdown();
                    resetStartTime();
                    state = 11;
                }
                else if (goldLocation.equals("right"))
                {
                    found = true;
                    mineralDriveDistanceFinal = mineralDriveDistance[RIGHT];
                    mineralSlideDistanceFinal = mineralSlideDistance[RIGHT];
                    faceDepoHeadingFinal = faceDepoHeading[RIGHT];
                    depoDriveDistanceFinal = depoDriveDistance[RIGHT];
                    depoTurnHeadingFinal = depoTurnHeading[RIGHT];
                    directionFinal = direction[RIGHT];
                    goldPosition = "right";
                    mineralTurnDistance = rightMineral;
                    robot.tfodShutdown();
                    resetStartTime();
                    state = 11;
                }

                if(getRuntime() > 4 && !found)
                {
                    mineralDriveDistanceFinal = mineralDriveDistance[CENTER];
                    mineralSlideDistanceFinal = mineralSlideDistance[CENTER];
                    faceDepoHeadingFinal = faceDepoHeading[CENTER];
                    depoDriveDistanceFinal = depoDriveDistance[CENTER];
                    depoTurnHeadingFinal = depoTurnHeading[CENTER];
                    directionFinal = direction[RIGHT];
                    goldPosition = "center";
                    mineralTurnDistance = centerMineral;
                    robot.tfodShutdown();
                    resetStartTime();
                    state = 11;
                }
                break;

            // Turn to correct mineral
            case 11:
                if(robot.pointTurn(.2, mineralTurnDistance, 3))
                {
                    resetStartTime();
                    state = 12;
                }
                break;

            // Drive forward, knocking off the mineral
            case 12:
                if (robot.drive(.3, forward, gain, mineralDriveDistanceFinal, 4))
                {
                    resetStartTime();
                    state = 13;
                }
                break;

            // Drive backward, so as to not snag on the pushed mineral
            case 13:
                if (robot.drive(.4, backward, gain, backup, 4))
                {
                    resetStartTime();
                    state = 14;
                }
                break;

            // Turn to face the depo
            case 14:
                if (robot.pointTurn(.2, faceDepoHeadingFinal, 4))
                {
                    resetStartTime();
                    state = 15;
                }
                break;

            // Strafe into the field wall to straighten robot
            case 15:
                if (robot.drive(.4, directionFinal, gain, mineralSlideDistanceFinal, 4))
                {
                    resetStartTime();
                    state = 16;
                }
                break;

            // Drive into depo
            case 16:
                if (robot.drive(.5, backward, gain, depoDriveDistanceFinal, 6))
                {
                    resetStartTime();
                    state = 17;
                }
                break;

            // Turn into depo
            case 17:
                if (robot.pointTurn(.2, depoTurnHeadingFinal, 4))
                {
                    resetStartTime();
                    state = 18;
                }
                break;

            // Slide into left wall to straighten and deploy team marker
            case 18:
                if (robot.drive(.4, left, gain, markerSlideDistance, 4))
                {
                    resetStartTime();
                    robot.markDeploy();
                    state = 19;
                }
                break;

            // Wait 1.5 seconds for the team marker to fall
            case 19:
                if (getRuntime() > 1.5)
                {
                    resetStartTime();
                    state = 20;
                }
                break;

            // Turn to face the crater
            case 20:
                if (robot.pointTurn(.2, faceCraterHeading,4))
                {
                    resetStartTime();
                    robot.markRetract();
                    state = 21;
                }
                break;

            // Drive two-thirds of the way to the crater
            case 21:
                if (robot.drive(.8, forward, gain, craterDriveDistance, 6))
                {
                    resetStartTime();
                    state = 22;
                }
                break;

            // Turn to face the crater.
            case 22:
                if (robot.pointTurn(.2, craterTurnHeading, 6))
                {
                    state = 23;
                    resetStartTime();
                }
                break;

            // Strafe right, around the crater.
            case 23:
                if (robot.drive(.4, right, gain, craterSlideDistance, 6))
                {
                    state = 25;
                    resetStartTime();
                }
                break;

//            case 24:
//                if (robot.drive(.5, forward, gain, enterCraterDistance, 5))
//                {
//                    resetStartTime();
//                    state = 25;
//                }
//                break;

            // extend arm out over the crater
            // (gets parking points and readies arm for teleop)
            case 25:
                if (robot.armCraterExtend())
                {
                    resetStartTime();
                    state = 26;
                }
                break;


            default:
                break;
        }
        telemetry.addData("state: ", state);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    /** Runs through once after the stop button on the driver station phone is pressed.
     * Stops all motors and interrupts the loop method */
    @Override
    public void stop()
    {
        // Stop the drive motors.
        robot.stopMotors();
    }
}
