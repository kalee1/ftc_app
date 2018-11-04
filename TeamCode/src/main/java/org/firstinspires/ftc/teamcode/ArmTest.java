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


    double gamma = .50; //  gamma is swivel
    double alpha = .50;  // alpha is shoulder
    double theta = .52; //  theta is elbow - elbow is higher than others due to intake box catching on chassis
    double increment = .003;
    boolean B1;
    boolean sleepButton;
    double left_stick_y;
    double right_stick_y;
    double left_stick_x;
    double waitTime;
    boolean fireCode = true;


    public ArmTest(){
    }

    public void init()
    {


        left_stick_y = gamepad2.left_stick_y;
        right_stick_y = gamepad2.right_stick_y;
        left_stick_x = gamepad2.left_stick_x;



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

        telemetry.addData("msg 2", "elbow pos" + Elbow.getPosition());

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

        //Elbow.setPosition(.10);

        //DO NOT UNCOMMENT --- TESTING PHASE
        //prototype range clip code - *untested*
        /*
        alpha = alpha + right_stick_y / 200;

        if(Shoulder.getPosition() < alpha )
            Shoulder.setPosition(Range.clip(Shoulder.getPosition() + .01, .08, 0.31));
        else if(Shoulder.getPosition() > alpha)
            Shoulder.setPosition(Range.clip(Shoulder.getPosition() - .01, .08, 0.31));

        theta = theta + -left_stick_y / 200;

        if(Elbow.getPosition() < theta )
            Elbow.setPosition(Range.clip(Elbow.getPosition() + .01, .08, .15));
        else if(Elbow.getPosition() > theta)
            Elbow.setPosition(Range.clip(Elbow.getPosition() - .01, .08, .15));

        gamma = gamma + right_stick_x / 200;

        if(Swivel.getPosition() < gamma )
            Swivel.setPosition(Range.clip(Swivel.getPosition() + .25, .44, 0.24));
        else if(Swivel.getPosition() > gamma)
            Swivel.setPosition(Range.clip(Swivel.getPosition() - .25, .44, 0.24));

        */

        //This section allows the drivers to control the 3 arm servos with the gamepad 2 sticks.
        //problem with incrementing position too fast --- testing phase
        //not user friendly yet / not competition ready!

        /*
        //Swivel
        gamma = gamma + right_stick_x / 1000;// set to 1000 due to higher gear ratio
        Swivel.setPosition(gamma);

        //Shoulder
        alpha = alpha + right_stick_y / 100;
        Shoulder.setPosition(alpha);

        //Elbow
        theta = theta + left_stick_y / 100;
        Elbow.setPosition(theta);//change to neg
        */
        /*
        //This is a check to see if the Elbow can move based upon Shoulder position
        //DO NOT UNCOMMENT --- TESTING PHASE
        check1 = Shoulder.getPosition();

        if (check1 == .31)
        {
            check2 = .31;
        }

        if (check1 == check2)
        {
            Elbow.setPosition(.14);
        }
        */

        telemetry.addData("msg3", "joystick control" + left_stick_y);
        telemetry.addData("msg4", "servo values" + Shoulder.getPosition());
        telemetry.update();

        /*
        if (B1)
        {
            //Shoulder.setPosition(.505);
            telemetry.addData("msg5", "value" + Shoulder.getPosition());
        }
        */
        if (fireCode == true)
        {
            waitTime = System.currentTimeMillis() + 30;//was 63

            //Shoulder
            if (left_stick_y < 0)
            {
                alpha += increment;
                Shoulder.setPosition(alpha);

            }

            else if (left_stick_y > 0)
            {
                alpha -= increment;
                Shoulder.setPosition(alpha);
            }

            /*
            //Elbow
            if (right_stick_y < 0)
            {
                theta += increment;
                Elbow.setPosition(theta);

            }
            else if (right_stick_y > 0)
            {

                theta -= increment;
                Elbow.setPosition(theta);

            }


            //Swivel

            if (left_stick_y < 0)
            {
                gamma += increment;
                Swivel.setPosition(gamma);

            }
            else if (left_stick_y > 0)
            {

                gamma -= increment;
                Swivel.setPosition(gamma);
            }
            */

        }

        if (System.currentTimeMillis() > waitTime)
        {
            fireCode = true;
        }
        else
            {
                fireCode = false;
            }

    }



    public void stop()
    {
        sleepButton = gamepad2.b;

        if (sleepButton == true)
        {
            Shoulder.setPosition(alpha);
            Swivel.setPosition(gamma);
            //Elbow.setPosition(theta);
        }
    }
}