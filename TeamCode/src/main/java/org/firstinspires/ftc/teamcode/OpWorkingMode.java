package org.firstinspires.ftc.teamcode;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.Date;

/**
 * Created by andrei on 26.01.2017.
 */

@TeleOp(name="OpWorkingMode", group="Iterative Opmode")
public class OpWorkingMode extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor leftMotor = null;
    private DcMotor rightMotor = null;

    private DcMotor perieMotor = null;

    private boolean isRotatingForward = false;
    private boolean isRotatingBackward = false;

    private int leftDirection = 1;
    private int rightDirection = -1;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        leftMotor  = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");

        perieMotor = hardwareMap.dcMotor.get("motor_perie");
    }
    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {
        telemetry.addData("Status", "Running: " + runtime.toString());

        float x = gamepad1.right_stick_x;
        float y = -gamepad1.right_stick_y;
        if (x != 0 || y != 0)
        {
            if (x <= 0 && y > 0) // cadranul 2
            {
                rightMotor.setPower(0.5);
                double angle = Math.atan2(y, x) - 3.0/2.0*Math.PI;
                //leftMotor.setPower(angle*0.5*2/Math.PI);
                leftMotor.setPower(Math.cos(angle) * -0.5);
            }
            else if (x > 0 && y >= 0)
            {
                leftMotor.setPower(-0.5);
                double angle = Math.atan2(y, x);
                rightMotor.setPower(Math.sin(angle) * 0.5);
                //Log.e("TELLME", x + " " + y + " " + angle + " " + Math.cos(angle)*-0.5 + " " + new Date().getSeconds());
            }
            else if (x < 0 && y < 0)
            {
                rightMotor.setPower(-0.5);
                double angle = (Math.atan2(y, x) - Math.PI);
                leftMotor.setPower(Math.cos(angle)*0.5);
            }
            else if (x >= 0 && y <= 0)
            {
                leftMotor.setPower(-0.5);
                double angle = Math.atan2(y, x) - Math.PI/2;
                rightMotor.setPower(Math.sin(angle)*0.5);
            }
        }
        else
        {
            leftMotor.setPower(0);
            rightMotor.setPower(0);
        }



        if(isRotatingForward){
            if(gamepad1.a){
                perieMotor.setPower(0);
                isRotatingForward = false;
            }
        } else {
            if(gamepad1.a){
                perieMotor.setPower(.5);
                isRotatingForward = true;
            }
        }

        if(isRotatingBackward){
            if(gamepad1.y){
                perieMotor.setPower(0);
                isRotatingBackward = false;
            }
        } else {
            if(gamepad1.y){
                perieMotor.setPower(-.5);
                isRotatingBackward = true;
            }
        }
    }

    @Override
    public void stop() {

    }

}
