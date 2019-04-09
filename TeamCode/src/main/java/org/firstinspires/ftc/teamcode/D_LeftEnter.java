package org.firstinspires.ftc.teamcode;

import java.io.File;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


@Autonomous(name="D-LeftEnter", group="Zombie")
public class D_LeftEnter extends E404_Autonomous
{
   public void init()
   {
       whichFile = "D_LeftEnter";
       testFile = new File("/storage/9016-4EF8/DepoLeftEnter.csv");
       super.init();
   }
}
