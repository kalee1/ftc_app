/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Mecanum" , group = "REV" )
public class Mecanum extends OpMode {

    private double left;
    private double right;
    private double strafeR;  //holds desired power level for right motor
    private double strafeL;  //holds desired power level for left motor
    private double diagR;
    private double diagL;

    DcMotor rightBack;
    DcMotor leftBack;
    DcMotor rightFront;
    DcMotor leftFront;

    @Override
    public void init() {

        rightBack = hardwareMap. dcMotor .get( "rightBack" );
        leftBack = hardwareMap . dcMotor .get( "leftBack" );
        rightFront = hardwareMap . dcMotor .get( "rightFront" );
        leftFront =hardwareMap . dcMotor .get( "leftFront" );
    }
    @Override
    public void loop() {
        //This section allows the robot to strafe with mecanum wheels
        strafeL = gamepad1.left_stick_x;  //get left stick value, hold it for future use
        strafeR = -gamepad1.right_stick_x; //get the right stick value, hold it for future use

        //sets the power for each motor, some values are inverted power due to controller negative values
        rightBack.setPower(-strafeR);
        rightFront.setPower(strafeR);
        leftBack .setPower(strafeL);
        leftFront .setPower(-strafeL);


        //Drive Forward - The joystick goes negative when pushed forwards, invert these values to move the robot forward instead of reverse.
        left = -gamepad1.left_stick_y;
        right = gamepad1.right_stick_y;//This stick is negative because the wheel values are inverted on the right side

        rightBack.setPower(-right);
        rightFront.setPower(-right);
        leftBack.setPower(-left);
        leftFront.setPower(-left);

        diagL = gamepad1.left_trigger;
        diagR = -gamepad1.right_trigger;

        rightFront.setPower(diagL);
        leftBack .setPower(diagL);

        rightBack.setPower(diagR);
        leftFront .setPower(diagR);

        telemetry.addData("msg1"," " + left );


    }

    @Override
    public void stop() {

        //Stopping the robot
        rightBack.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        leftFront.setPower(0);
    }
}
