package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Error404 Mecanum Teleop", group="Teleop")
/**
 * The class which will handle the driver controlled period of the match.
 *
 * @author Error 404: Team Name Not Found
 * @see OpMode
 */
public class Error404MecanumTeleop extends OpMode
{

    public Servo Swivel;
    public Servo Elbow;
    public Servo Shoulder;
    public CRServo Intake;
    double elbowSleep = .52;
    double shoulderSleep = .50;
    double swivelSleep = .50;
    double gamma = swivelSleep; //  gamma is swivel
    double alpha = shoulderSleep;  // alpha is shoulder
    double theta = elbowSleep; //  theta is elbow - elbow is higher than others due to intake box catching on chassis
    double increment = .005;
    boolean B1;
    boolean sleepButton;
    double left_stick_y;
    double right_stick_y;
    double left_stick_x;
    double shoulderTarTime;
    double swivelTarTime;
    double elbowTarTime;
    boolean shoulderControl = true;
    boolean swivelControl = true;
    boolean elbowControl = true;

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

        //left_stick_y = gamepad2.left_stick_y;
        //right_stick_y = gamepad2.right_stick_y;
        //left_stick_x = gamepad2.left_stick_x;
        sleepButton = gamepad2.dpad_up;



        B1 = true;

        try {

            Elbow = hardwareMap. servo .get( "Elbow" );
            Elbow.setDirection(Servo.Direction.FORWARD);
            Elbow.setPosition(theta);


        } catch (Exception p_exeception) {

            Elbow = null;
        }
        try {

            Shoulder = hardwareMap. servo .get( "Shoulder" );
            Shoulder.setPosition(alpha);

        } catch (Exception p_exeception) {

            Shoulder = null;
        }
        try {

            Swivel = hardwareMap.servo.get( "Swivel" );
            Swivel.setPosition(gamma);

        } catch (Exception p_exception) {

            Swivel = null;
        }
        try
        {

            Intake = hardwareMap.crservo.get( "Collector" );
            Intake.setDirection(CRServo.Direction.FORWARD);

        } catch (Exception p_exeception) {

            Intake = null;
        }

        telemetry.addData("Arm Test Init", "elbow position" + Elbow.getPosition());

        //this.armSleep(true);  //put servos into sleep positions

        //Shoulder.scaleRange(0.08, 0.31);
        //Elbow.scaleRange(0.20,0.04 );
        //Swivel.scaleRange(0.24, 0.44);

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
    public void loop() {

        /*
        double right_stick_x = gamepad2.right_stick_x;
        double right_stick_y = gamepad2.right_stick_y;
        double left_stick_y = gamepad2.left_stick_y;
        */
        //boolean sleepButton = gamepad2.b;

        telemetry.addData("Testing", "Entering armMove");
        //robot.armMove(right_stick_x, right_stick_y, left_stick_y);
        //robot.armSleep(sleepButton);

        double lStickX = -gamepad1.left_stick_x;
        double rStickX = -gamepad1.right_stick_x;
        double lStickY = gamepad1.left_stick_y;
        double rStickY = gamepad1.right_stick_y;

        telemetry.addData("1: leftX", lStickX);
        telemetry.addData("2: leftY", lStickY);
        telemetry.addData("3: rightX", rStickX);

        robot.joystickDrive(lStickX, lStickY, rStickX, rStickY, 1);

        /*
        if (gamepad2.right_bumper)
        {
            robot.armIntake();
        }
        else if (gamepad2.left_bumper)
        {
            robot.armEject();
        }
        else
        {
            robot.armStop();
        }
        */
        //robot.theEyeOfSauron.goldMineralPosition();



/*
        left_stick_y = gamepad2.left_stick_y;
        right_stick_y = gamepad2.right_stick_y;
        left_stick_x = gamepad2.left_stick_x;
*/

        //telemetry.addData("msg3", "joystick control" + left_stick_y);
        telemetry.addData("msg4", "servo values" + Shoulder.getPosition());
        telemetry.update();

        //Swivel
        if (swivelControl == true)
        {
            swivelTarTime = System.currentTimeMillis() + 60;//was 30

            if (gamepad2.dpad_left == true)
            {
                gamma += increment;
                Swivel.setPosition(gamma);
                telemetry.addData("Swivel positive increment", "" + gamepad2.right_stick_x);

            }

            else if (gamepad2.dpad_right == true)
            {
                gamma -= increment;
                Swivel.setPosition(gamma);
                telemetry.addData("Swivel positive increment", ""+ gamepad2.right_stick_x);
            }

        }

        if (System.currentTimeMillis() > swivelTarTime)
        {
            swivelControl = true;
        }
        else
        {
            swivelControl = false;
        }

        //Shoulder
        telemetry.addData("before shoulder", " before shoulder");

        if (shoulderControl == true)
        {
            shoulderTarTime = System.currentTimeMillis() + 30; //was 63
            telemetry.addData("in shoulder", "in shoulder");
            telemetry.update();

            if (-gamepad2.left_stick_y < 0)
            {
                alpha += increment;
                Shoulder.setPosition(alpha);
                telemetry.addData("Shoulder positive increment", "" + gamepad2.dpad_left);
                telemetry.update();
            }

            else if (-gamepad2.left_stick_y > 0)
            {
                alpha -= increment;
                Shoulder.setPosition(alpha);
                telemetry.addData("Shoulder negative increment", "-" + gamepad2.dpad_right);
                telemetry.update();
            }

        }

        if (System.currentTimeMillis() > shoulderTarTime)
        {
            shoulderControl = true;
            telemetry.addData("", "shouldercontrol = true" + shoulderControl);
            telemetry.update();
        }
        else
        {
            telemetry.addData("", "shouldercontrol = false" + shoulderControl);
            shoulderControl = false;
            telemetry.update();
        }

        telemetry.addData("", "" + shoulderTarTime);
        telemetry.addData("after shoulder", " after shoulder");

        //Elbow

        if (elbowControl == true)
        {
            elbowTarTime = System.currentTimeMillis() + 30;//was 63

            if (gamepad2.right_stick_y < 0)
            {
                theta += increment;
                Elbow.setPosition(theta);
                telemetry.addData("Elbow positive increment", ""+ gamepad2.right_stick_y);
            }

            else if (gamepad2.right_stick_y > 0)
            {
                theta -= increment;
                Elbow.setPosition(theta);
                telemetry.addData("Elbow negative increment", ""+ gamepad2.right_stick_y);
            }

        }

        if (System.currentTimeMillis() > elbowTarTime)
        {
            elbowControl = true;
        }
        else
        {
            elbowControl = false;
        }

        //Collector

        if (gamepad2.right_bumper)
        {
            Intake.setPower(1.0);
        }
        else if (gamepad2.left_bumper)
        {
            Intake.setPower(-1.0);
        }
        else
        {
            Intake.setPower(0.0);
        }


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
