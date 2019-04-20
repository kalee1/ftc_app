package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name="Error404 Mecanum Teleop", group="Teleop")
/**
 * The class which will handle the driver controlled period of the match. Uses a robot chassis with
 * mecanum wheels.
 *
 * @author Andrew, Error 404: Team Name Not Found
 * @see OpMode
 */
public class Error404MecanumTeleop extends OpMode
{
    /* Declare OpMode members. */
    /** The robot type. */
    RuckusBot robot = new RuckusBot("MecanumChassis"); // use the class created to define a Testbot's hardware
    /** The collector. */
    Collector theCollect = new Collector();
    /** The FTC Dashboard (displays telemetry to a web interface.  */
    FtcDashboard dashboard = FtcDashboard.getInstance();
    /** A telemetry object that uses the ftc dahsbaord. */
    Telemetry dashboardTelemetry = dashboard.getTelemetry();
    /*
     * Code to run ONCE when the driver hits INIT
     */
    /** Calls the init methods for needed classes. */
    @Override
    public void init()
    {
        // Initialize the hardware variables.
        // The init() method of the hardware class does all the work here
        //
        robot.init(hardwareMap, telemetry, false);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver. Your Mecanum Robot is Ready for Your Command.");    //

    }

    /** Not used for anything right now, but runs once when the start button is pressed, but before
     * the loop method starts. */
    @Override
    public void start()
    {

    }

    /** Contains the actual movement commands of the class. Runs repeatedly after the driver hits play and until the stop button is
     *  pressed.*/
    @Override
    public void loop()
    {
        /* Arm Control */
        /** The y-axis of the right joystick on the gamepad. Used for mineral arm control. */
        double RightStickY = gamepad2.right_stick_y;
        /** The y-axis of the left joystick on the gamepad. Used for mineral arm control. */
        double LeftStickY = gamepad2.left_stick_y;

        /* Chassis Control */
        /** The x-axis of the left joystick on the gamepad. Used for chassis control*/
        double lStickX = -gamepad1.left_stick_x;
        /** The x-axis of the right joystick on the gamepad. Used for chassis control*/
        double rStickX = -gamepad1.right_stick_x;
        /** The y-axis of the left joystick on the gamepad. Used for chassis control*/
        double lStickY = gamepad1.left_stick_y;
        /** The y-axis of the right joystick on the gamepad. Used for chassis control*/
        double rStickY = gamepad1.right_stick_y;
        /** The truth value of the dpad down button. Ussed for hanger control.*/
        boolean dpadDown = gamepad1.dpad_down;
        /** The truth value of the dpad up button. Used for hanger control.*/
        boolean dpadUp = gamepad1.dpad_up;
        /** The return value of the button a on gamepad1. Used for lander hang presets*/
        boolean buttonB = gamepad1.b;
        /** A double that is used to hold a power value. */
        double power;

//        telemetry.addData("1: leftX", lStickX);
//        telemetry.addData("2: leftY", lStickY);

        // Robot chassis controls
        if(gamepad1.a)
        {
            robot.reset(0.5, 10);
        }
        else if(gamepad1.x)
        {
            robot.pointTurn(.5, -90, 10);
        }
        else if(gamepad1.b)
        {
            robot.pointTurn(.5, 90, 10);
        }
        else
        {
            robot.joystickDrive(lStickX, lStickY, rStickX, rStickY, afterburners());

        }

        // Robot lander hang method
        if (dpadDown || dpadUp)
        {
            power = 1;
            if (gamepad1.left_bumper)
            {
                power = 0.1;
            }
        }
        else
        {
            power = 0;
        }
        robot.hangControl(dpadDown, dpadUp, power);

        // Arm control method
        if (gamepad2.b)
        {
            robot.armLanderExtend();
        }
        else if (gamepad2.a)
        {
            robot.armHome();
        }
        else if (gamepad2.y)
        {
            robot.armCraterExtend();
        }
        else if (gamepad2.x)
        {
            robot.armDrivingExtend();
        }
        else
        {
            robot.armDrive(RightStickY, LeftStickY);
        }

        // Right intake controls
        if (gamepad2.right_bumper)
        {
            robot.intakeR();
        }
        else if (gamepad2.right_trigger > .1)
        {
            robot.ejectR();
        }
        else
        {
            robot.collectorStopR();
        }

        // Left intake controls
        if (gamepad2.left_bumper)
        {
            robot.intakeL();
        }
        else if (gamepad2.left_trigger > .1)
        {
            robot.ejectL();
        }
        else
        {
            robot.collectorStopL();
        }

        // Auxiliary Swivel
        // Allows the secondary gamepad (which run the mineral arm) to turn the chassis at a very slow
        // speed to aid in lining up on minerals.
        if (gamepad1.right_trigger > .1)
        {
            robot.joystickDrive(0.0, 0.0, -1, 0.0, .18);
        }
        //If the left trigger on the secondary gamepad is pressed, rotate the chassis left at .15 power.
        else if (gamepad1.left_trigger > .1)
        {
            robot.joystickDrive(0.0, 0.0, 1, 0.0, .18);
        }
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

        // If the right bumper on the primary gamepad is pressed, set the maximum drive power to the fast limit

        if(gamepad1.right_bumper)
        {
            powerLimit = 1;
        }
        // If the left trigger on the primary gamepad is not pressed, set the maximum drive power to the normal limit
        else
        {
            powerLimit = .4;
        }
        return powerLimit;
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    /** Runs once after the driver hits the stop button on teh drivers station phone.
     * Stops the loop method and stops the drive motors. */
    @Override
    public void stop()
    {
        robot.stopMotors();
    }
}

