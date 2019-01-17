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
    int state = 2;  // used to represent the current state in the state machine
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
     * The distance from the start position to the on-field minerals.*/
    protected double mineralDriveDistance;
    /** A move variable that holds differing distances that change based on where the robot starts on the field.
     * The distance required to slide around the minerals. */
    protected double mineralSlideDistance;
    /** A turn variable that holds differing target headings that change based on where the robot starts on the field.
     * The target heading that faces the robot towards the alliance depo. */
    protected double depoTurnHeading;
    /** A move variable that holds differing distances that change based on where the robot starts on the field.
     * The distance required to drive to the alliance depo. */
    protected double depoDriveDistance;
    /** A move variable that holds differing distances that change based on where the robot starts on the field.
     * The distance required to drive to the crater. */
    protected double craterDriveDistance;
    /** A turn variable that holds differing target headings that change based on where the robot starts on the field.
     * The target heading required to face the crater. */
    protected double craterTurnHeading;
    /** A move variable that holds differing distances that change based on where the robot starts on the field.
     * The distance required to drive around the crater to enter on a selected side. */
    protected double craterSlideDistance;
    /** A move variable that holds differing distances that change based on where the robot starts on the field.
     * The distance reqiured to drive halfway into the crater. */
    protected double enterCraterDistance;
    /** A turn variable that corrects any error acquired over the first half of the run. */
    protected double headingReset;
    /** A string that holds the position of the gold mineral. */
    protected String goldPosition;
    /** The amount by which the PID drive algorithms will correct error. */
    double gain = 0.01;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    /** Calls the required init methods in the needed classes. */
    @Override
    public void init()
    {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap, telemetry);

        // Send telemetry message to signify robot waiting;
//        telemetry.addData("Say", "Let's Rock and Roll");
//        telemetry.addData("initial heading: ", initialPosition);

    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    /** Runs through once after the driver hits start.
     * Resets the internal timer to 0. */
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
//            case 0:
//              set gyro
//              lower robot

//            case 1:
//                //gyro correct

            /*Look for the three minerals and locate the position of the gold mineral. Set the
                 goldPosistion variable to the value of the gold position and set state equal to
                 the correct case value..*/
            //find gold mineral position
            case 2:
                goldPosition = robot.goldPosition();

                if(goldPosition.equals("left") || goldPosition.equals("right") || goldPosition.equals("center"))
                {
                    state = 3;
                }
                if (getRuntime() > 5)
                {
                    goldPosition = "center";
                    state = 3;
                }
//                telemetry.addData("gold position", goldPosition);
                if (state != 2)
                {
                    resetStartTime();
                    robot.tfodShutdown();
                }
                break;

                //drive out from under the lander
            case 3:
                if(goldPosition.equals("left"))
                {
                    if(robot.drive(.2, forward, gain, 7, 6))
                    {
                        resetStartTime();
                        state = 4;
                    }
                }
                else if(goldPosition.equals("right"))
                {
                    if(robot.drive(.2, forward, gain, 7, 6))
                    {
                        resetStartTime();
                        state = 5;
                    }
                }
                else if(goldPosition.equals("center"))
                {
                    if(robot.drive(.2, forward, gain, 7, 6))
                    {
                        resetStartTime();
                        state = 6;
                    }
                }
                telemetry.addData("gold position", goldPosition);
                break;


                //Swivel to face the left mineral and drive forward
            case 4:
                if(robot.pointTurn(.2, 20, 4))
                {
                    resetStartTime();
                    state = 7;
                }
                break;

                //swivel to face right mineral and drive forward
            case 5:
                if(robot.pointTurn(.2, -20, 4))
                {
                    resetStartTime();
                    state = 7;
                }
                break;

                //swivel to face center mineral and drive forward
            case 6:
                if(robot.pointTurn(.2, 0, 4))
                {
                    resetStartTime();
                    state = 7;
                }
                break;

                //knock off gold mineral
            case 7:
                // extend arm
                robot.theArm.ArmDeploy();
                state = 8;
                break;

                //drive forward
            case 8:
                if(robot.drive(.2, forward, gain, 10, 6))
                {
                    resetStartTime();
                    state = 9;
                }
                break;

                //face depo
            case 9:
                if(robot.pointTurn(.2, 0, 4))
                {
                    resetStartTime();
                    state = 10;
                }
                break;

//                //drive forward and deploy marker and mineral into depo
//            case 10:
//                if(robot.drive(.2, forward, gain, 20, 4))
//                {
//                    robot.eject();
//                    resetStartTime();
//                    state = 11;
//                }
//                break;
//
//                //stop collector after three seconds
//            case 11:
//                if(getRuntime() > 3)
//                {
//                    robot.collectorStop();
//                    resetStartTime();
//                    state = 12;
//                }
//                break;
//
//                //retract the mineral arm
//            case 12:
//                //retract arm
//                state = 13;
//                break;
//
//                //turn to face crater
//            case 13:
//                if(robot.pointTurn(.2, craterTurnHeading, 5))
//                {
//                    resetStartTime();
//                    state = 14;
//                }
//                break;
//
//            case 14:
//                if(robot.drive(.2, forward, gain, craterDriveDistance, 6))
//                {
//                    resetStartTime();
//                    state = 15;
//                }
//                break;






//                //Drive up to the minerals and knock off the right one.
//            case 2:
//                if(robot.drive(.4, backward, gain, mineralDriveDistance, 6))
//                {
//                    //knock off right mineral
//                    resetStartTime();
//                    state = 4;
//                }
//                break;
//
//                //Drive up to the minerals and knock off the center one.
//            case 3:
//                if(robot.drive(.4, backward, gain, mineralDriveDistance, 6))
//                {
//                    //knock off center mineral
//                    resetStartTime();
//                    state = 4;
//                }
//                break;
//
//                //Strafe right and around the minerals.
//            case 4:
//                if(robot.drive(.4, right, gain, mineralSlideDistance, 6))
//                {
//                    resetStartTime();
//                    state = 2;
//                }
//                break;
//
//                //Turn to face the depo.
//            case 5:
//                if(robot.pointTurn(.2, depoTurnHeading, 6))
//                {
//                    resetStartTime();
//                    state = 3;
//                }
//                break;
//
//                //Drive to the depo.
//            case 6:
//                if(robot.drive(.5, backward, gain, depoDriveDistance, 6))
//                {
//                    robot.markDeploy();
//                    resetStartTime();
//                    state = 4;
//                }
//                break;
//
//                //Deposit the team marker into the alliance depo.
//            case 7:
//                if(getRuntime() > 3)
//                {
//                    robot.markRetract();
//                    resetStartTime();
//                    state = 5;
//
//                }
//                break;
//
//                //Correct heading.
//            case 8:
//                if(robot.pointTurn(.2, headingReset, 6))
//                {
//                    resetStartTime();
//                    state = 6;
//                }
//                break;
//
//                //Drive towards crater.
//            case 9:
//                if(robot.drive(.4, forward, gain, craterDriveDistance, 6))
//                {
//                    state = 7;
//                    resetStartTime();
//                }
//                break;
//
//                //Turn to face the crater.
//            case 10:
//                if(robot.pointTurn(.2, craterTurnHeading, 6))
//                {
//                    state = 8;
//                    resetStartTime();
//                }
//                break;
//
//                //Strafe right, around the crater.
//            case 11:
//                if(robot.drive(.4, right, gain, craterSlideDistance, 6))
//                {
//                    state = 9;
//                    resetStartTime();
//                }
//                break;
//
//                //Drive into the crater.
//            case 12:
//                if(robot.drive(.5, forward, gain, enterCraterDistance, 6))
//                {
//                    state = 10;
//                    resetStartTime();
//                }
//                break;

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
