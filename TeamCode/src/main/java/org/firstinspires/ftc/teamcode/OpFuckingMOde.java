package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by andrei on 26.01.2017.
 */

@TeleOp(name="OpFuckingMode", group="Iterative Opmode")
public class OpFuckingMOde extends OpMode {
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

        if(gamepad1.dpad_up) {
            leftMotor.setPower(1);
            rightMotor.setPower(-1);
        }
        else if (gamepad1.dpad_down)
        {
            leftMotor.setPower(-1);
            rightMotor.setPower(1);
        }
        else if(gamepad1.dpad_right)
        {
            leftMotor.setPower(-1);
            rightMotor.setPower(0);
        }
        else if(gamepad1.dpad_left)
        {
            leftMotor.setPower(0);
            rightMotor.setPower(1);
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
