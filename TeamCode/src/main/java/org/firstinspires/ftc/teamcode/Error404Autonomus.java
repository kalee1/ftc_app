package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Autonomous(name="Test Autonomus w/Mecanum Chassis", group="Autonomus")

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
    double rlDiagonal = MecanumChassis.FORWARD_LEFT_DIAGONAL;
    double brDiagonal = MecanumChassis.BACKWARD_RIGHT_DIAGONAL;
    double blDiagonal = MecanumChassis.BACKWARD_LEFT_DIAGONAL;

    protected double mineralDriveDistance;
    protected double mineralSlideDistance;
    protected double depoTurnHeading;
    protected double depoDriveDistance;
    protected double craterDriveDistance;
    protected double craterTurnHeading;
    protected double craterSlideDistance;
    protected double enterCraterDistance;
    protected double headingReset;

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
        telemetry.addData("Say", "Let's Rock and Roll");    //

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
                if(robot.drive(.2, backward, mineralDriveDistance, 6))
                {
                    state = 1;
                    resetStartTime();
                }
                break;

            case 1:
                if(robot.drive(.3, right, mineralSlideDistance, 6))
                {
                    state = 2;
                    resetStartTime();
                }
                break;

            case 2:
                if(robot.pointTurn(.1, depoTurnHeading, 6, true))
                {
                    state = 3;
                    resetStartTime();
                }
                break;

            case 3:
                if(robot.drive(.3, backward, depoDriveDistance, 6))
                {
                    state = 4;
                    resetStartTime();
                }
                break;

            case 4:
                if(robot.pointTurn(.1, headingReset, 6, true))
                {
                    state = 5;
                    resetStartTime();
                }
                break;

            case 5:
                if(robot.drive(.4, forward, craterDriveDistance, 6))
                {
                    state = 6;
                    resetStartTime();
                }
                break;

            case 6:
                if(robot.pointTurn(.1, craterTurnHeading, 6, true))
                {
                    state = 7;
                    resetStartTime();
                }
                break;

            case 7:
                if(robot.drive(.4, right, craterSlideDistance, 6))
                {
                    state = 8;
                    resetStartTime();
                }
                break;

            case 8:
                if(robot.drive(.4, forward, enterCraterDistance, 6))
                {
                    state = 9;
                    resetStartTime();
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
