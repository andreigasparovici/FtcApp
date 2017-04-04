package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by DragosTrett on 25.03.2017.
 */
@Autonomous(name = "AutonomieR", group = "final")

public class AutonomieR extends LinearOpMode {
    DcMotor leftMotor, rightMotor,trebuchetmotor;
    int timp_90grade=600;
    public static final String LEFT_MOTOR = "left_motor";
    public static final String RIGHT_MOTOR = "right_motor";
    public static final String TREBUCHET_MOTOR = "catapult_motor";
    int x1 = 1076;
    int x2 = 1384;
    public void delay(int timp){sleep(timp);}

    public void MapDevices(){
        leftMotor  = hardwareMap.dcMotor.get(LEFT_MOTOR);
        rightMotor = hardwareMap.dcMotor.get(RIGHT_MOTOR);
        trebuchetmotor = hardwareMap.dcMotor.get(TREBUCHET_MOTOR);
    }

    public void goForth(){
        rightMotor.setPower(1);
        leftMotor.setPower(-1);
        delay(x1);
        rightMotor.setPower(0);
        leftMotor.setPower(0);
    }

    public void tunr180grade(){
        rightMotor.setPower(1);
        leftMotor.setPower(1);
        delay(timp_90grade*2);
        rightMotor.setPower(0);
        leftMotor.setPower(0);
    }

    public void Launchpls(){
        trebuchetmotor.setPower(1);
        delay(375);
        trebuchetmotor.setPower(0);
    }

    public void turnback(){
        rightMotor.setPower(-1);
        leftMotor.setPower(-1);
        delay(timp_90grade*2);
        rightMotor.setPower(0);
        leftMotor.setPower(0);
    }

    public void impingeBile(){
        rightMotor.setPower(1);
        leftMotor.setPower(-1);
        delay(x2);
        rightMotor.setPower(0);
        leftMotor.setPower(0);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        MapDevices();
        waitForStart();
        goForth();
        tunr180grade();
        Launchpls();
        turnback();
        impingeBile();
    }
}
