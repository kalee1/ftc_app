package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Autonomous(name="Mother Autonomus", group="Zeta")

public class Error404Autonomus extends OpMode
{
    /* Declare OpMode members. */
    RuckusBot robot = new RuckusBot("MecanumChassis"); // use the class created to define a Testbot's hardware
    int state = 0;  // used to represent the current state in the state machine
    int initialPosition = 0;  // used to grab the position of a robot at the beginning of a move

    double forward = MecanumChassis.FORWARD;
    double backward = MecanumChassis.BACKWARD;
    double right = MecanumChassis.RIGHT;
    double left = MecanumChassis.LEFT;

    double frDiagonal = MecanumChassis.FORWARD_RIGHT_DIAGONAL;
    double flDiagonal = MecanumChassis.FORWARD_LEFT_DIAGONAL;
    double rrDiagonal = MecanumChassis.REVERSE_RIGHT_DIAGONAL;
    double rlDiagonal = MecanumChassis.REVERSE_LEFT_DIAGONAL;

    protected double mineralDriveDistance;
    protected double mineralSlideDistance;
    protected double depoTurnHeading;
    protected double depoDriveDistance;
    protected double craterDriveDistance;
    protected double craterTurnHeading;
    protected double craterSlideDistance;
    protected double enterCraterDistance;
    protected double headingReset;

    double gain = 0.01;

    /*
     * Code to run ONCE when the driver hits INIT
     */
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
    @Override
    public void start()
    {
        resetStartTime();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop()
    {
        switch (state)
        {
            case 0:
                if(robot.drive(.4, backward, gain, mineralDriveDistance, 6))
                {
                    resetStartTime();
                    state = 1;
                }
                break;

            case 1:
                if(robot.drive(.4, right, gain, mineralSlideDistance, 6))
                {
                    resetStartTime();
                    state = 2;
                }
                break;

            case 2:
                if(robot.pointTurn(.2, depoTurnHeading, 6))
                {
                    resetStartTime();
                    state = 3;
                }
                break;

            case 3:
                if(robot.drive(.5, backward, gain, depoDriveDistance, 6))
                {
                    robot.markDeploy();
                    resetStartTime();
                    state = 4;
                }
                break;

            case 4:
                if(getRuntime() > 3)
                {
                    robot.markRetract();
                    resetStartTime();
                    state = 5;

                }
                break;

            case 5:
                if(robot.pointTurn(.2, headingReset, 6))
                {
                    resetStartTime();
                    state = 6;
                }
                break;

            case 6:
                if(robot.drive(.4, forward, gain, craterDriveDistance, 6))
                {
                    state = 7;
                    resetStartTime();
                }
                break;

            case 7:
                if(robot.pointTurn(.2, craterTurnHeading, 6))
                {
                    state = 8;
                    resetStartTime();
                }
                break;

            case 8:
                if(robot.drive(.4, right, gain, craterSlideDistance, 6))
                {
                    state = 9;
                    resetStartTime();
                }
                break;

            case 9:
                if(robot.drive(.5, forward, gain, enterCraterDistance, 6))
                {
                    state = 10;
                    resetStartTime();
                }
                break;


            case 11:
                if(robot.goldPosition().equals("right"))
                {
                    //knock off right mineral

                    resetStartTime();
                    state = 12;
                }
                else if(robot.goldPosition().equals("left"))
                {
                    //knock off left mineral

                    resetStartTime();
                    state = 12;
                }
                else if(robot.goldPosition().equals("center"))
                {
                    //knock off center mineral

                    resetStartTime();
                    state = 12;
                }
                break;

            default:
                break;
        }
        telemetry.addData("1)", "state: " + state );

    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop()
    {
        robot.stopMotors();
    }
}
