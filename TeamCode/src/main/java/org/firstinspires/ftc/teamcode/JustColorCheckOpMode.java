package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.DigitalChannelController;


/**
 * Created by alex on 01.02.2017.
 */
@TeleOp(name="Color check OpMode", group="Iterative Opmode")

public class JustColorCheckOpMode extends OpMode{

        ColorSensor colorSensor;

        static final int LED_CHANNEL = 2;

        public void init() {

            colorSensor = hardwareMap.colorSensor.get("color_sensor");
            colorSensor.enableLed(false);


        }

        public void loop() {
            telemetry.addData("Red", colorSensor.red());
            telemetry.addData("blue", colorSensor.blue());
            telemetry.addData("green", colorSensor.green());
            telemetry.addData("alpha", colorSensor.alpha());
            telemetry.addData("hue", colorSensor.argb());
        }
}
