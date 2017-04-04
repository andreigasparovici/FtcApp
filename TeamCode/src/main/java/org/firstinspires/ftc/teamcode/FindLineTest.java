package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * Created by DragosTrett on 24.03.2017.
 */
@Autonomous(name = "FindLineTest", group = "Test")
public class FindLineTest extends LinearOpMode {
    public static final String LEFT_MOTOR = "left_motor";
    public static final String RIGHT_MOTOR = "right_motor";
    TouchSensor s1,s2,s3;
    double motor_universal_power=0.5;
    DcMotor leftMotor, rightMotor, brushMotor, trebuchetmotor;
    int[] a=new int[9];
    public void readLine(){
        if (s1.isPressed())
            a[1]=1;
        else
            a[1]=0;
        if (s2.isPressed())
            a[2]=1;
        else
            a[2]=0;
        if (s3.isPressed())
            a[3]=1;
        else
            a[3]=0;
    }
    private boolean foundLine(){
        return a[1] == 0 || a[2] == 0 || a[3] == 0;
    }
    public void runOpMode() throws InterruptedException {
        leftMotor  = hardwareMap.dcMotor.get(LEFT_MOTOR);
        rightMotor = hardwareMap.dcMotor.get(RIGHT_MOTOR);
        waitForStart();
        while (opModeIsActive()) {

        }
    }
}
