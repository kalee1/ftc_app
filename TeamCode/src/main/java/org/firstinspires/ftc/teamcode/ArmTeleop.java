package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ArmTeleop{

    public Servo Swivel;
    public Servo Elbow;
    public Servo Shoulder;


    double gamma = .50; //  gamma is swivel
    double alpha = .50;  // alpha is shoulder
    double theta = .52; //  theta is elbow - elbow is higher than others due to intake box catching on chassis
    double increment = .003;
    boolean B1;

    Telemetry telemetry;

    public ArmTeleop(){
    }

    public void init(HardwareMap hwmap , Telemetry telem) {

        telemetry = telem;
        B1 = true;

        try {

            Elbow = hwmap. servo .get( "Elbow" );
            Elbow.setDirection(Servo.Direction.FORWARD);
            Elbow.setPosition(theta);


        } catch (Exception p_exeception) {

            Elbow = null;
        }
        try {

            Shoulder = hwmap. servo .get( "Shoulder" );
            Shoulder.setPosition(alpha);

        } catch (Exception p_exeception) {

            Shoulder = null;
        }
        try {

            Swivel = hwmap.servo.get( "Swivel" );
            Swivel.setPosition(gamma);

        } catch (Exception p_exception) {

            Swivel = null;
        }

        telemetry.addData("msg 2", "elbow pos" + Elbow.getPosition());

    }

    public void armPosition(double right_stick_x,double right_stick_y, double left_stick_y)
    {

        telemetry.addData("msg3", "joystick control" + left_stick_y);
        telemetry.addData("msg4", "servo values" + Shoulder.getPosition());
        telemetry.update();


        if (B1)
        {
            Shoulder.setPosition(.505);
            telemetry.addData("msg5", "value" + Shoulder.getPosition());
        }


        if (left_stick_y < 0)
            {
                //alpha += increment;
                //Shoulder.setPosition(alpha);
            }

        else if  (left_stick_y > 0)
            {
                alpha -= increment;
                Shoulder.setPosition(alpha);
            }
    }



    public void armSleep( boolean sleepButton)
    {
        if (sleepButton == true)
        {
            Shoulder.setPosition(alpha);
            Swivel.setPosition(gamma);
            //Elbow.setPosition(theta);
        }
    }
}