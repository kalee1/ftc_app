package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name="TestBot Autonomous", group="Autonomous")
public class TestBotAutonomous extends OpMode
{
    int state = 0;
    private DcMotor leftMotor = null;
    private DcMotor rightMotor = null;

    public void init()
    {
        rightMotor = hardwareMap.dcMotor.get("right");
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        leftMotor = hardwareMap.dcMotor.get("left");
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftMotor.setDirection(DcMotorSimple.Direction.FORWARD);

    }

    public void start()
    {
        resetStartTime();
    }
    public void loop()
    {
        switch (state)
        {
            //drive forward 10 encoders
            case 0:
                forwardDrive(.5);
                if(rightMotor.getCurrentPosition() > 10)
                {
                    stopMotors();
                    resetStartTime();
                    state = 1;
                }
                break;
                //turn for 30 encoders
            case 1:
                leftMotor.setPower(.5);
                rightMotor.setPower(-.5);
                if(rightMotor.getCurrentPosition() > 30)
                {
                    stopMotors();
                    resetStartTime();
                    state = 2;
                }
                break;
                //drive forward for 50 encoders
            case 2:
                forwardDrive(.5);
                if(rightMotor.getCurrentPosition() > 50)
                {
                    stopMotors();
                    resetStartTime();
                    state = 3;
                }
                break;
                //turn for 5 encoders.
            case 3:
                leftMotor.setPower(.5);
                rightMotor.setPower(-.5);
                if(rightMotor.getCurrentPosition() > 5)
                {
                    stopMotors();
                    resetStartTime();
                    state = 4;
                }
                break;
                //drive forward for 10 encoders
            case 4:
                forwardDrive(.5);
                if(rightMotor.getCurrentPosition() > 10)
                {
                    stopMotors();
                    resetStartTime();
                    state = 5;
                }
                break;
                //turn for 30 encoders
            case 5:
                leftMotor.setPower(.5);
                rightMotor.setPower(-.5);
                if(rightMotor.getCurrentPosition() > 30)
                {
                    stopMotors();
                    resetStartTime();
                    state = 6;
                }
                break;
                //drive forward for 5 encoders
            case 6:
                forwardDrive(.5);
                if(rightMotor.getCurrentPosition() > 5)
                {
                    stopMotors();
                    resetStartTime();
                    state = 7;
                }
                break;
                //turn for 120 encoders
            case 7:
                leftMotor.setPower(.5);
                rightMotor.setPower(-.5);
                if(rightMotor.getCurrentPosition() > 120)
                {
                    stopMotors();
                    resetStartTime();
                    state = 8;
                }
                break;

            default:
                break;
        }
    }

    public void stop()
    {
        stopMotors();
    }

    public void stopMotors()
    {
        leftMotor.setPower(0.0);
        rightMotor.setPower(0.0);
    }

    public void forwardDrive(double power)
    {
        leftMotor.setPower(power);
        rightMotor.setPower(power);
    }
}
