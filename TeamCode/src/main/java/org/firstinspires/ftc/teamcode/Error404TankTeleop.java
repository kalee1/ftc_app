package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

//@TeleOp(name="Error404 Tank Teleop", group="Teleop")

/**
 * A teleop opmode that drive the robot like a tank (ie, not mecanum).
 *
 * @author Andrew, Error 404: Team Name Not Found
 * @see OpMode
 */
public class Error404TankTeleop extends OpMode
{

    /* Declare OpMode members. */
    RuckusBot robot = new RuckusBot("TankChassis"); // use the class created to define a Testbot's hardware
    int state = 0;  // used to represent the current state in the state machine
    int initialPosition = 0;  // used to grab the position of a robot at the beginning of a move

    /*
     * Code to run ONCE when the driver hits INIT
     */
    /** Calls the needed init methods for used classes. */
    @Override
    public void init()
    {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap, telemetry, false);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //

    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    /** Not used for anything right now, but runs once when the start button is pressed, but before
     * the loop method starts. */
    @Override
    public void start() {
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    /** Contains the actual movement commands of the class. Runs repeatedly until the stop button is
     *  pressed.*/
    @Override
    public void loop()
    {
        double lStickY = gamepad1.left_stick_y;
        double rStickY = gamepad1.right_stick_y;


        robot.joystickDrive(0.0, lStickY ,0.0, rStickY, 1);

        //       robot.theEyeOfSauron.goldMineralPosition();

    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    /** Runs once after the driver hits the stop button on teh drivers station phone.
     * Stops the loop method. */
    @Override
    public void stop()
    {

    }

}
