package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name="TestBot: Auto with State Machine", group="TestBot")

public class TestBotAutoSquare extends OpMode
{
    /* Declare OpMode members. */
    TestBot robot = new TestBot(); // use the class created to define a Testbot's hardware
    int state = 0;  // used to represent the current state in the state machine
    int initialPosition = 0;  // used to grab the position of a robot at the beginning of a move

//////////////////////////

    void driveStraight(double power, double distance)
    {
        robot.leftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        robot.rightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        initialPosition = robot.leftDrive.getCurrentPosition();
        robot.leftDrive.setPower(power);
        robot.rightDrive.setPower(power);

        if((robot.leftDrive.getCurrentPosition() - initialPosition) > distance )
        {
            robot.leftDrive.setPower(0.0);
            robot.rightDrive.setPower(0.0);
        }
    }

//////////////////////////

    void turnCorner(double power, double distance)
    {
        robot.leftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        robot.rightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        initialPosition = robot.leftDrive.getCurrentPosition();
        robot.leftDrive.setPower(power);
        robot.rightDrive.setPower(power);

        if((robot.leftDrive.getCurrentPosition() - initialPosition) > distance)
        {
            robot.leftDrive.setPower(0.0);
            robot.rightDrive.setPower(0.0);
        }
    }



    /**
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //

        // Set all motors to use RUN_USING_ENCODERS if encoders are installed.
        robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /**
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
    }

    /**
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {

        switch (state)
        {
            // Drive forward 500 ticks and then turn for 50 ticks
            case 0:
                driveStraight(0.5, 500);
                state++;
                break;
            case 1:
                turnCorner(0.25, 50);
                state++;
                break;

////////////////////////////////////
            // Drive forward 500 ticks and then turn for 50 ticks
            case 2:
                driveStraight(.5, 500);
                state++;
                break;
            case 3:
                turnCorner(0.25, 50);
                break;

/////////////////////////////////////
            // Drive forward for 500 ticks and then turn for 50 ticks
            case 4:
                driveStraight(0.5, 500);
                state++;
                break;
            case 5:
                turnCorner(0.25, 50);
                state++;
                break;

////////////////////////////////////
            //Drive forward for 500 ticks and then turn for 50 ticks
            case 6:
                driveStraight(0.5, 500);
                state++;
                break;
            case 7:
                turnCorner(0.25, 50);
                state++;
                break;

////////////////////////////////
            // Wait 2 seconds and then spin around for 2 seconds
            case 16:
                if (getRuntime() > 2)
                {
                    while (getRuntime() < 5 )
                    {
                        robot.leftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
                        robot.rightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
                        robot.leftDrive.setPower(1);
                        robot.rightDrive.setPower(1);
                        state++;
                    }
                }
                robot.leftDrive.setPower(0.0);
                robot.rightDrive.setPower(0.0);
                break;
            default:
                break;

        }
    }

    /**
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}
