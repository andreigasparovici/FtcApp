package org.firstinspires.ftc.teamcode;

/**
 * Created by DragosTrett on 24.03.2017.
 */

public class UniversalFunctionsAutonomus extends Autonomus{

    public void gopls(int x, int y){
        rightMotor.setPower(-x);
        leftMotor.setPower(y);
    }
    public void stoppls(){
        rightMotor.setPower(0);
        leftMotor.setPower(0);
    }
}
