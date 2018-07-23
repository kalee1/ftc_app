package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name="TestBot: Auto with State Machine", group="TestBot")
@Disabled
public class TestBotAuto extends OpMode
{
    /* Declare OpMode members. */
    TestBot robot = new TestBot(); // use the class created to define a Pushbot's hardware
    int state = 0;  // used to represent the current state in the state machine
    int initialPosition = 0;  // used to grab the position of a robot at the beginning of a move

    /*
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
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {

    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {

        switch (state)
        {
            // Drive Forward indefinitely
            case 0:
                robot.leftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
                robot.rightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
                initialPosition = robot.leftDrive.getCurrentPosition();
                robot.leftDrive.setPower(0.5);
                robot.rightDrive.setPower(0.5);
                state++;
                break;
            // Stop after set distance
            case 1:
                if ( (robot.leftDrive.getCurrentPosition() - initialPosition) > 100 )
                {
                    robot.leftDrive.setPower(0.0);
                    robot.rightDrive.setPower(0.0);
                    state++;
                }
                break;
            // Drive Backward indefinitely
            case 2:
                robot.leftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
                robot.rightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
                initialPosition = robot.leftDrive.getCurrentPosition();
                robot.leftDrive.setPower(0.5);
                robot.rightDrive.setPower(0.5);
                state++;
                break;
            // Stop After Set Distance
            case 3:
                if ( (robot.leftDrive.getCurrentPosition() - initialPosition) > 100 )
                {
                    robot.leftDrive.setPower(0.0);
                    robot.rightDrive.setPower(0.0);
                    state++;
                }
                break;
            default:
                break;

        }
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}
