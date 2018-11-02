package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Error404 Mecanum Teleop", group="Teleop")
/**
 * The class which will handle the driver controlled period of the match.
 *
 * @author Error 404: Team Name Not Found
 * @see OpMode
 */
public class Error404MecanumTeleop extends OpMode
{

    /* Declare OpMode members. */
    RuckusBot robot = new RuckusBot("MecanumChassis"); // use the class created to define a Testbot's hardware

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init()
    {
        // Initialize the hardware variables.
        // The init() method of the hardware class does all the work here
        //
        robot.init(hardwareMap, telemetry);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver. Your Mecanum Robot is Ready for Your Command.");    //

    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start()
    {

    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop()
    {
        telemetry.addData("msg1", "");


        double right_stick_x = gamepad2.right_stick_x;
        double right_stick_y = gamepad2.right_stick_y;
        double left_stick_y = gamepad2.left_stick_y;
        boolean sleepButton = gamepad2.b;

        telemetry.addData("Testing", "Entering armMove");
        robot.armMove(right_stick_x ,right_stick_y ,left_stick_y);
        robot.armSleep(sleepButton);

        double lStickX = -gamepad1.left_stick_x;
        double rStickX = -gamepad1.right_stick_x;
        double lStickY = gamepad1.left_stick_y;
        double rStickY = gamepad1.right_stick_y;

        telemetry.addData("1: leftX", lStickX);
        telemetry.addData("2: leftY", lStickY);
        telemetry.addData("3: rightX", rStickX);

        robot.joystickDrive(lStickX, lStickY, rStickX, rStickY, 1);

        //robot.theEyeOfSauron.goldMineralPosition();

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
