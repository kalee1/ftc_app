package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Error404 Tank Teleop", group="Teleop")

/**
 * @author Error 404: Team Name Not Found
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
    @Override
    public void init()
    {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //

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
    public void loop()
    {
        double lStick = gamepad1.left_stick_y;
        double rStick = gamepad1.right_stick_y;


        double powerClip = .6;
        lStick = Range.clip(lStick, -powerClip, powerClip);
        rStick = Range.clip(rStick, -powerClip, powerClip);
        robot.joystickDrive(lStick, rStick);

 //       robot.theEyeOfSauron.goldMineralPosition();

    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
