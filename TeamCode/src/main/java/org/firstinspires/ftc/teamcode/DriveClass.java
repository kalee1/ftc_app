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

public class DriveClass extends OpMode {

    HardwareClass            mechdrive   = new HardwareClass();

    double left;
    double right;
    double strafeR;  //holds desired power level for right motor
    double strafeL;  //holds desired power level for left motor
    double diagR;
    double diagL;

    public DriveClass(){
    }

    public void Strafe() {
        //This section allows the robot to strafe with mecanum wheels
        strafeL = gamepad1.left_stick_x;  //get left stick value, hold it for future use
        telemetry.addData("strafeL = ", strafeL);
        strafeR = -gamepad1.right_stick_x; //get the right stick value, hold it for future use

        //sets the power for each motor, some values are inverted power due to controller negative values
        mechdrive.rightBack.setPower(-strafeR);
        mechdrive.rightFront.setPower(strafeR);
        mechdrive.leftBack .setPower(strafeL);
        mechdrive.leftFront .setPower(-strafeL);

    }

    public void TankDrive() {
        //Drive Forward - The joystick goes negative when pushed forwards, invert these values to move the robot forward instead of reverse.
        left = -gamepad1.left_stick_y;
        right = gamepad1.right_stick_y;//This stick is negative because the wheel values are inverted on the right side

        mechdrive.rightBack.setPower(-right);
        mechdrive.rightFront.setPower(-right);
        mechdrive.leftBack.setPower(-left);
        mechdrive.leftFront.setPower(-left);

    }

    public void DiagStrafe() {
        diagL = gamepad1.left_trigger;
        diagR = -gamepad1.right_trigger;

        mechdrive.rightFront.setPower(diagL);
        mechdrive.leftBack .setPower(diagL);

        mechdrive.rightBack.setPower(diagR);
        mechdrive.leftFront .setPower(diagR);

    }

    @Override
    public void init() {
    }
    @Override
    public void loop() {
    }
    @Override
    public void stop() {
        mechdrive.rightBack.setPower(0);
        mechdrive.rightFront.setPower(0);
        mechdrive.leftBack.setPower(0);
        mechdrive.leftFront.setPower(0);
    }
}
