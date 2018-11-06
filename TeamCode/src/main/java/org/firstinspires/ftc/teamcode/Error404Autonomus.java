package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Autonomous(name="Test Autonomus w/Mecanum Chassis", group="Autonomus")

public class Error404Autonomus extends OpMode
{
    /* Declare OpMode members. */
    RuckusBot robot = new RuckusBot("MecanumChassis"); // use the class created to define a Testbot's hardware
    int state = 0;  // used to represent the current state in the state machine
    int initialPosition = 0;  // used to grab the position of a robot at the beginning of a move

    double forward = MecanumChassis.FORWARD;
    double backward = MecanumChassis.FORWARD;
    double right = MecanumChassis.RIGHT;
    double left = MecanumChassis.LEFT;

    double frDiagonal = MecanumChassis.BACKWARD_RIGHT_DIAGONAL;
    double rlDiagonal = MecanumChassis.FORWARD_LEFT_DIAGONAL;
    double brDiagonal = MecanumChassis.BACKWARD_RIGHT_DIAGONAL;
    double blDiagonal = MecanumChassis.BACKWARD_LEFT_DIAGONAL;

    protected double mineralDriveDistance;
    protected double mineralSlideDistance;
    protected double depoTurnHeading;
    protected double depoDriveDistance;
    protected double craterDriveDistance;

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
            // Drive to depo
            case 0:
                if(robot.drive(.2, forward, 60, 1.5))
                {
                    state = 1;
                }
                break;

            // turn to face crater
            case 1:
                if(robot.pointTurn(.2, 45, 3,true))
                {
                    state = 2;
                }
                break;

            // Drive to crater
            case 2:
                if(robot.drive(.2, forward, 120, 4))
                {
                    state = 3;
                }
                break;

            case 3:
                if(robot.theEyeOfSauron.tensorFlowMineralDetection().equals("center"))
                {
                    state = 4;
                }
                break;

//            // Drive to depo
//            case 3:
//                if(robot.drive(.5, forward, depoDriveDistance,2))
//                {
//                    state = 4;
//                }
//                break;
//
//            // Drive to crater
//            case 4:
//                if(robot.drive(.5, backward, craterDriveDistance,5))
//                {
//                    state = 5;
//                }
//                break;

            default:
                break;
        }
        telemetry.addData("State: ", state );

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
