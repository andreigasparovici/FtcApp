package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cCompassSensor;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by DragosTrett on 25.03.2017.
 */
@TeleOp(name = "Autonomie_win", group = "final")

public class Autonomie_win extends LinearOpMode {
    ModernRoboticsI2cGyro gyro;
    double heading = 0;
    DcMotor leftMotor, rightMotor, trebuchetmotor;
    public static final String LEFT_MOTOR = "left_motor";
    public static final String RIGHT_MOTOR = "right_motor";
    public static final String COLOR_SERVO_NAME = "color_servo";
    public static final String TREBUCHET_MOTOR = "catapult_motor";
    public final static double COLOR_SERVO_RIGHT = 0.99;
    public final static double COLOR_SERVO_MIDDLE = 0.5;

    ColorSensor colorSensor;

    Servo colorServo;

    public void mapTheBitch(){

        leftMotor  = hardwareMap.dcMotor.get(LEFT_MOTOR);
        rightMotor = hardwareMap.dcMotor.get(RIGHT_MOTOR);
        trebuchetmotor = hardwareMap.dcMotor.get(TREBUCHET_MOTOR);
        colorSensor = hardwareMap.colorSensor.get("color_sensor");
        colorSensor.enableLed(false);
        colorServo = hardwareMap.servo.get(COLOR_SERVO_NAME);
        colorServo.setPosition(COLOR_SERVO_RIGHT);
        gyro = (ModernRoboticsI2cGyro)hardwareMap.gyroSensor.get("gyro");
    }



    public void mfront(){
        leftMotor.setPower(1);
        rightMotor.setPower(-1);
    }

    public void mback(){
        leftMotor.setPower(-1);
        rightMotor.setPower(1);
    }

    public void mstop(){
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }

    public void beacon(){
        colorServo.setPosition(COLOR_SERVO_RIGHT);
        if(colorSensor.red() < colorSensor.blue()){
            colorServo.setPosition(COLOR_SERVO_MIDDLE);
            rightMotor.setPower(1);
            sleep(200);
            rightMotor.setPower(-1);
            sleep(200);
            rightMotor.setPower(0);
        }

        else{
            colorServo.setPosition(COLOR_SERVO_MIDDLE);
            leftMotor.setPower(-1);
            sleep(200);
            leftMotor.setPower(1);
            sleep(200);
            leftMotor.setPower(0);
        }
    }

    @Override
    public void runOpMode(){

        mapTheBitch();
        gyro.calibrate();
        while(gyro.getHeading() != 0) {
            telemetry.addData("gyro ", "calibrat");
            telemetry.update();
        }

        waitForStart();
        while(heading<90){
            rightMotor.setPower(-0.3);
            leftMotor.setPower(-0.3);
            telemetry.addData("gyro" , heading);
            telemetry.update();
            heading = gyro.getHeading();
        }
    }
}
