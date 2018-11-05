package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name="Error404 ArmTest", group="Teleop")
public class ArmTest extends OpMode{


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



    public ArmTest(){
    }

    public void init()
    {


        left_stick_y = gamepad2.left_stick_y;
        right_stick_y = gamepad2.right_stick_y;
        left_stick_x = gamepad2.left_stick_x;
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

    public void loop()
    {



        left_stick_y = gamepad2.left_stick_y;
        right_stick_y = gamepad2.right_stick_y;
        left_stick_x = gamepad2.left_stick_x;


        telemetry.addData("msg3", "joystick control" + left_stick_y);
        telemetry.addData("msg4", "servo values" + Shoulder.getPosition());
        telemetry.update();

        //Shoulder
        if (shoulderControl == true)
        {
            shoulderTarTime = System.currentTimeMillis() + 30;//was 63

            if (-left_stick_y < 0)
            {
                alpha += increment;
                Shoulder.setPosition(alpha);
                telemetry.addData("Shoulder positive increment", ""+ left_stick_y);
            }

            else if (-left_stick_y > 0)
            {
                alpha -= increment;
                Shoulder.setPosition(alpha);
                telemetry.addData("Shoulder negative increment", ""+ left_stick_y);
            }

        }

        if (System.currentTimeMillis() > shoulderTarTime)
        {
            shoulderControl = true;
        }
        else
            {
                shoulderControl = false;
            }

            //Swivel
        if (swivelControl == true)
        {
            swivelTarTime = System.currentTimeMillis() + 30;//was 63

            if (gamepad2.right_stick_x < 0)
            {
                gamma += increment;
                Swivel.setPosition(gamma);
                telemetry.addData("Swivel positive increment", ""+ gamepad2.right_stick_x);

            }

            else if (gamepad2.right_stick_x > 0)
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

        //Arm is supposed to fold into sleep position
        //Do Not Delete - In Progress!!!
        /*
        if (sleepButton == true)
        {
            Shoulder.setPosition(shoulderSleep);
            Swivel.setPosition(swivelSleep);
            Elbow.setPosition(elbowSleep);
            telemetry.addData("", "sleep pos method , in loop");
        }
        */

    }



    public void stop()
    {
    }
}