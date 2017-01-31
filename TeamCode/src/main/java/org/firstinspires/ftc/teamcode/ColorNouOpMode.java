package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by timi and Gaspi on 31.01.2017.
 */

@TeleOp(name="Color Sensor Bun OpMode", group="Iterative Opmode")
public class ColorNouOpMode extends OpMode{

    ColorSensor colorSensor;

    Servo servo;

    DcMotor leftMotor;
    DcMotor rightMotor;

    private boolean isRed(){
        if(colorSensor.red() > colorSensor.blue()){
            return true;
        }
        return false;
    }

    public void init() {
        colorSensor = hardwareMap.colorSensor.get("color_sensor");
        colorSensor.enableLed(false);

        servo = hardwareMap.servo.get("servo");

        leftMotor  = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");

        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void loop() {
        telemetry.addData("Red", colorSensor.red());
        telemetry.addData("blue", colorSensor.blue());
        telemetry.addData("green", colorSensor.green());
        telemetry.addData("alpha", colorSensor.alpha());
        telemetry.addData("hue", colorSensor.argb());

        leftMotor.setPower(gamepad1.left_stick_y);
        rightMotor.setPower(gamepad1.right_stick_y);

        if(gamepad1.b) {

            if (!isRed()) {
                servo.setPosition(0);
            } else {
                servo.setPosition(0.75);
            }
        }
    }
}
