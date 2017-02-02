package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannelController;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchImpl;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by timi on 31.01.2017.
 */


@TeleOp(name = "Autonomie press", group = "Autonomous Opmode")
public class AutonomiePress extends LinearOpMode {

    ColorSensor colorSensor;

    Servo colorServo;
    Servo pressServo;

    DcMotor leftMotor;
    DcMotor rightMotor;

    private boolean isRed() {
        // TODO Find a better way to do this
        return colorSensor.red() > colorSensor.blue();
    }

    public enum TeamColor {
        RED, BLUE
    }

    public TeamColor teamColor = TeamColor.RED;

    private boolean isTeamColor() {
        return (teamColor != TeamColor.RED) ^ isRed();
    }

    public void myInit() {

        colorSensor = hardwareMap.colorSensor.get("color_sensor");
        colorSensor.enableLed(false);

        colorServo = hardwareMap.servo.get("color_servo");
        pressServo = hardwareMap.servo.get("press_servo");

        /*leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");

        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);*/

        colorServo.setPosition(0.12);
        pressServo.setPosition(0.12);
    }

    public final static double PRESS_SERVO_LEFT = 0.12;
    public final static double PRESS_SERVO_RIGHT = 0.67;
    public final static double COLOR_SERVO_LEFT = 0.01;
    public final static double COLOR_SERVO_RIGHT = 0.99;



    public boolean inhim;

    public void beginColorCheck() throws InterruptedException {
        inhim = true;
        colorServo.setPosition(COLOR_SERVO_LEFT);
        sleep(200);

        if (!isTeamColor()) {
            pressServo.setPosition(PRESS_SERVO_LEFT);
        }

        sleep(500);

        colorServo.setPosition(COLOR_SERVO_RIGHT);
        sleep(200);
        if (!isTeamColor()) {
            pressServo.setPosition(PRESS_SERVO_RIGHT);
        }
        inhim = false;
    }

    @Override
    public void runOpMode() {

        myInit();

        waitForStart();

        while (opModeIsActive())  {
            myLoop();
            telemetry.update();
        }
    }



    public void myLoop() {
        telemetry.addData("Red", colorSensor.red());
        telemetry.addData("blue", colorSensor.blue());
        telemetry.addData("green", colorSensor.green());
        telemetry.addData("alpha", colorSensor.alpha());
        telemetry.addData("hue", colorSensor.argb());

        //leftMotor.setPower(gamepad1.left_stick_y);
        //rightMotor.setPower(gamepad1.right_stick_y);

        if (gamepad1.b) {
            if (!inhim) {
                try {
                    beginColorCheck();
                } catch (InterruptedException e) {
                    telemetry.addData("Cought Exception", "fuuu");
                    e.printStackTrace();
                }
            }
        }
    }
}
