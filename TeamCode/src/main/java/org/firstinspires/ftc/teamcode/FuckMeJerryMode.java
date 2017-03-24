package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by DragosTrett on 21.02.2017.
 */
@TeleOp(name="Fuck Me Jerry", group="Iterative Opmode")
@Disabled
public class FuckMeJerryMode extends LinearOpMode {

    DcMotor motor;
    double motorSpeed;

    public void myInit(){
        motor = hardwareMap.dcMotor.get("motor");
    }

    public void startMotor(DcMotor motor, double motorSpeed){
        motor.setPower(0);

        for(motorSpeed = 0; motorSpeed <= 1; motorSpeed += 0.01){
            motor.setPower(motorSpeed);
            sleep(20);
        }
    }

    public void runOpMode(){
        myInit();
        waitForStart();
        startMotor(motor,motorSpeed);
        while(true){
            try {
                motor.setPower(motorSpeed);
            }catch(Exception e){
                telemetry.addData("exceptie",e.getClass().toString());
            }
        }
    }
}
