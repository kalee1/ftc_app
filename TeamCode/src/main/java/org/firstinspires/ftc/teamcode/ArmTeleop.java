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

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ArmTeleop{

    public Servo Swivel;
    public Servo Elbow;
    public Servo Shoulder;

    double gamma = .25; //was .25 ---- Sleep pos = .25   gamma is swivel
    double alpha = .31;  //was .33 ---- sleep pos = .31  alpha is shoulder
    double theta = .14; //was .18 ---- sleep pos = .14   theta is elbow

    public ArmTeleop(){
    }

    public void init(HardwareMap hwmap) {

        try {

            Elbow = hwmap. servo .get( "Elbow" );
            //Elbow.setPosition(theta);

        } catch (Exception p_exeception) {

            Elbow = null;
        }
        try {

            Shoulder = hwmap. servo .get( "Shoulder" );
            //Shoulder.setPosition(alpha);

        } catch (Exception p_exeception) {

            Shoulder = null;
        }
        try {

            Swivel = hwmap. servo .get( "Swivel" );
            //Swivel.setPosition(gamma);

        } catch (Exception p_exeception) {

            Swivel = null;
        }


        this.armSleep(true);  //put servos into sleep positions

        Shoulder.scaleRange(0.1, 0.27);


    }

    public void armPosition(double right_stick_x,double right_stick_y, double left_stick_y)
    {
        //Swivel
        //gamma = gamma + right_stick_x / 1000;
        //Swivel.setPosition(gamma);

        //Shoulder
        //alpha = alpha + right_stick_y / 1000;
        alpha = alpha + right_stick_y / 100;
        Shoulder.setPosition(alpha);

        //Elbow
        //theta = theta + left_stick_y / 1000;
        //Elbow.setPosition(theta);//change to neg
    }

    public void armSleep( boolean sleepButton)
    {
        if (sleepButton == true)
        {
            Shoulder.setPosition(.31);
            Swivel.setPosition(gamma);
            Elbow.setPosition(theta);
        }
    }
}