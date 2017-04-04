package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by DragosTrett on 24.03.2017.
 */
@Autonomous(name = "TestPressTheBeacon", group = "OpMode")
public class TestPressTheBeacon extends LinearOpMode{
    DcMotor leftMotor, rightMotor;
    public static final String LEFT_MOTOR = "left_motor";
    public static final String RIGHT_MOTOR = "right_motor";
    public static final String COLOR_SERVO_NAME = "color_servo";
    public static final String COLOR_SENSOR_NAME = "color_sensor";
    public final static double COLOR_SERVO_LEFT = 0.01;
    public final static double COLOR_SERVO_RIGHT = 0.99;
    public final static double COLOR_SERVO_MIDDLE = 0.5;
    ColorSensor colorSensor;

    Servo colorServo;

    public void ApasaDreapta(){
        colorServo.setPosition(COLOR_SERVO_MIDDLE);
        sleep(100);
        rightMotor.setPower(-1);
        sleep(500);
        rightMotor.setPower(0);
    }

    public void ApasaStanga(){
        colorServo.setPosition(COLOR_SERVO_MIDDLE);
        sleep(100);
        leftMotor.setPower(1);
        sleep(500);
        leftMotor.setPower(0);
    }

    @Override
    public void runOpMode(){
        leftMotor  = hardwareMap.dcMotor.get(LEFT_MOTOR);
        rightMotor = hardwareMap.dcMotor.get(RIGHT_MOTOR);
        colorSensor = hardwareMap.colorSensor.get(COLOR_SENSOR_NAME);
        colorSensor.enableLed(false);

        colorServo = hardwareMap.servo.get(COLOR_SERVO_NAME);

        colorServo.setPosition(COLOR_SERVO_LEFT);
        waitForStart();
        if(colorSensor.red() < colorSensor.blue()){
            ApasaStanga();
        }
        else ApasaDreapta();

    }
}
