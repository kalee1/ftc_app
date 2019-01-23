package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

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

protected com.qualcomm.robotcore.hardware.TouchSensor ChassisTouch = null;
protected com.qualcomm.robotcore.hardware.TouchSensor ArmTouchForward = null;
protected TouchSensor ArmTouchBackward = null;

    @Override
    public void init()
    {
        // Initialize the hardware variables.
        // The init() method of the hardware class does all the work here
        //
        robot.init(hardwareMap, telemetry);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver. Your Mecanum Robot is Ready for Your Command.");    //

        try
            {
            ArmTouchBackward = hardwareMap.touchSensor.get("ArmTouchBackward");
            }
        catch (Exception p_exception)
            {
            ArmTouchBackward = null;
            telemetry.addData("ArmTouchB not found", "");
            }
        try
            {
            ArmTouchForward = hardwareMap.touchSensor.get("ArmTouchForward");
            }
        catch (Exception p_exception)
            {
            ArmTouchForward = null;
            telemetry.addData("ArmTouchF not found", "");
            }
        try
            {
            ChassisTouch = hardwareMap.touchSensor.get("ChassisTouch");
            }
        catch (Exception p_exception)
            {
            ChassisTouch = null;
            telemetry.addData("ChassisTouch not found", "");
            }
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
        /* Do Arm Control  */
        double RightStickY = gamepad2.right_stick_y;
        double LeftStickY = gamepad2.left_stick_y;

        if (ArmTouchForward.isPressed());
        {

        }

        /* Do Chassis Control */
        double lStickX = -gamepad1.left_stick_x;
        double rStickX = -gamepad1.right_stick_x;
        double lStickY = gamepad1.left_stick_y;
        double rStickY = gamepad1.right_stick_y;

        telemetry.addData("1: leftX", lStickX);
        telemetry.addData("2: leftY", lStickY);
//        telemetry.addData("3: rightX", rStickX);

        //robot.touchloop();

        robot.joystickDrive(lStickX, lStickY, rStickX, rStickY, afterburners());

        robot.armDrive(LeftStickY, RightStickY);

        /** If one of the specified specified bumpers are pressed, the collector methods Intake,
         * Eject or CollecotrStop are called. Intake tells the robot to move the collector servo
         * to spin at a given direction*/
        if (gamepad2.left_bumper == true)
        {
            robot.intake(telemetry);
        }

        else if (gamepad2.right_bumper == true)
        {
            robot.eject(telemetry);
        }
        else{
            robot.collectorStop();
        }

        if (gamepad2.x){

        robot.armHome();
        }
        else if (gamepad2.a){

        }
        else
    }

    /**
     * afterburners() allows the driver to increase the robot's top speed from the default of 0.3 to
     * 0.8 by holding down the left trigger on the gamepad. This is because it is easier to make small
     * precise movements (like lining up on a mineral) at a lower top speed, but it is also
     * useful to drive fast when crossing the field.
     *
     * @return  a double that is the maximum power
     */
    public double afterburners()
    {
        double powerLimit;

        if (gamepad1.left_trigger == 1)
        {
            powerLimit = .8;
        }
        else
        {
            powerLimit = .3;
        }
        return powerLimit;
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

