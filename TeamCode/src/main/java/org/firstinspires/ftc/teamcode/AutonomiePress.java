package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
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

    private void pressLeft(){
        leftMotor.setPower(-1);
        sleep(300);
    }

     private void pressRight(){
        rightMotor.setPower(1);
        sleep(300);
    }

    public void myInit() {

        colorSensor = hardwareMap.colorSensor.get("color_sensor");
        colorSensor.enableLed(false);

        colorServo = hardwareMap.servo.get("color_servo");

        colorServo.setPosition(0.1);
    }

    public final static double COLOR_SERVO_LEFT = 0.01;
    public final static double COLOR_SERVO_RIGHT = 0.99;


    public boolean inhim;

    public void beginColorCheck() throws InterruptedException {
        inhim = true;
        colorServo.setPosition(COLOR_SERVO_LEFT);
        sleep(200);

        if (!isTeamColor()) {
           // pressServo.setPosition(PRESS_SERVO_LEFT);
            pressLeft();
        }

        sleep(500);

        colorServo.setPosition(COLOR_SERVO_RIGHT);
        sleep(200);
        if (!isTeamColor()) {
            //pressServo.setPosition(PRESS_SERVO_RIGHT);
            pressRight();
        }
        inhim = false;
    }

    @Override
    public void runOpMode() {

        myInit();

        waitForStart();

        while (opModeIsActive())  {
            //myLoop();
            telemetry.update();
        }
    }



    /*public void myLoop() {
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
    }*/
}
