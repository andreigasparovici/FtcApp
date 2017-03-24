package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by DragosTrett on 24.03.2017.
 */
@Autonomous(name = "AutonomieInspectie", group = "test")
public class AUtonomieInspectie extends LinearOpMode{
    DcMotor leftMotor, rightMotor;
    public static final String LEFT_MOTOR = "left_motor";
    public static final String RIGHT_MOTOR = "right_motor";

    public void runOpMode(){
        leftMotor  = hardwareMap.dcMotor.get(LEFT_MOTOR);
        rightMotor = hardwareMap.dcMotor.get(RIGHT_MOTOR);
        rightMotor.setPower(1);
        leftMotor.setPower(-1);
        sleep(2000);
        leftMotor.setPower(0);
        rightMotor.setPower(0);

    }
}
