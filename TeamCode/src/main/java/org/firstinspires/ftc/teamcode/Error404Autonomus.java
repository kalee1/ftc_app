package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

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

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init()
    {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

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
                telemetry.addData("1)", "Case 0 checking in");
                robot.drive(.25, forward, 0.0);
                if(getRuntime() > 1)
                {
                    telemetry.addData("2)", "Case 0 complete");
                    robot.stopMotors();
                    state = 2;
                    resetStartTime();
                }
                break;

//            case 1:
//                telemetry.addData("3)", "Case 1 checking in");
//                robot.pointTurn(1, 0.0);
//                if(getRuntime() > 1.5)
//                {
//                    telemetry.addData("4)", "Case 1 complete");
//                    robot.stopMotors();
//                    resetStartTime();
//                    state = 2;
//                }
//                break;
            case 2:
                telemetry.addData("3)", "Case 2 checking in");
                robot.drive(.25, frDiagonal, 0.0);
                if(getRuntime() > 2)
                {
                    telemetry.addData("5)", "Case 2 complete");
                    robot.stopMotors();
                    resetStartTime();
                    state = 4;
                }
                break;
//            case 3:
//                telemetry.addData("3)", "Case 2 checking in");
//                robot.pointTurn(.2, 0.0);
//                if(getRuntime() > 2)
//                {
//                    telemetry.addData("5)", "Case 2 complete");
//                    robot.stopMotors();
//                    resetStartTime();
//                    state = 4;
//                }
//                break;
            case 4:
                telemetry.addData("3)", "Case 2 checking in");
                robot.drive(1, right, 0.0);
                if(getRuntime() > 1.5)
                {
                    telemetry.addData("5)", "Case 2 complete");
                    robot.stopMotors();
                    resetStartTime();
                    state = 5;
                }
                break;
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
