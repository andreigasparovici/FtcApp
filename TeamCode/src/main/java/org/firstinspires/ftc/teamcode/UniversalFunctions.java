package org.firstinspires.ftc.teamcode;

/**
 * Created by DragosTrett on 24.03.2017.
 */

public class UniversalFunctions extends Controler{

    public void gopls(int x, int y){
        rightMotor.setPower(-x);
        leftMotor.setPower(y);
    }
}
