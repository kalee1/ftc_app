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
                if(robot.tankDrive(.3, Chassis.TankDirection.FORWARD, gain, 50, 30))
                {
                    resetStartTime();
                    state = 1;
                }
                break;

            case 1:
                if(robot.pointTurn(.2, Chassis.TurnDirection.RIGHT, -90, 6))
                {
                    resetStartTime();
                    state = 2;
                }
                break;

            case 2:
                if(robot.drive(.3, right, gain, 12, 6))
                {
                    resetStartTime();
                    state = 3;
                }
                break;

            case 3:
                if(robot.drive(.3, flDiagonal, gain, 12, 6))
                {
                    resetStartTime();
                    state = 4;
                }
                break;

//            case 1:
//                if(robot.mecanumDrive(.3, right, gain, mineralSlideDistance, 6))
//                {
//                    resetStartTime();
//                    state = 2;
//                }
//                break;
//
//            case 2:
//                if(robot.pointTurn(.1, depoTurnHeading, 6, true))
//                {
//                    resetStartTime();
//                    state = 3;
//                }
//                break;



//            case 0:
//                if(robot.mecanumDrive(.2, backward, gain, mineralDriveDistance, 6))
//                {
//                    resetStartTime();
//                    state = 1;
//                }
//                break;
//
//            case 1:
//                if(robot.mecanumDrive(.3, right, gain, mineralSlideDistance, 6))
//                {
//                    resetStartTime();
//                    state = 2;
//                }
//                break;
//
//            case 2:
//                if(robot.pointTurn(.1, depoTurnHeading, 6, true))
//                {
//                    resetStartTime();
//                    state = 3;
//                }
//                break;
//            case 3:
//                if(robot.mecanumDrive(.3, backward, gain, depoDriveDistance, 6))
//                {
//                    robot.markDeploy();
//                    resetStartTime();
//                    state = 4;
//                }
//                break;
//
//            case 4:
//                if(getRuntime() > 3)
//                {
//                    robot.markRetract();
//                    resetStartTime();
//                    state = 5;
//
//                }
//                break;
//
//            case 5:
//                if(robot.pointTurn(.1, headingReset, 6, true))
//                {
//                    resetStartTime();
//                    state = 6;
//                }
//                break;
//
//            case 6:
//                if(robot.mecanumDrive(.5, forward, gain, craterDriveDistance, 6))
//                {
//                    state = 7;
//                    resetStartTime();
//                }
//                break;
//
//            case 7:
//                if(robot.pointTurn(.1, craterTurnHeading, 6, true))
//                {
//                    state = 9;
//                    resetStartTime();
//                }
//                break;
//
////            case 8:
////                if(robot.mecanumDrive(.4, right, craterSlideDistance, 6))
////                {
////                    state = 9;
////                    resetStartTime();
////                }
////                break;
//
//            case 9:
//                if(robot.mecanumDrive(.5, forward, gain, enterCraterDistance, 6))
//                {
//                    state = 10;
//                    resetStartTime();
//                }
//                break;

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
