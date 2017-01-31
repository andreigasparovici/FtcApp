package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;

/**
 * Created by timi on 31.01.2017.
 */

@TeleOp(name="Color Sensor Bun OpMode", group="Iterative Opmode")
public class ColorNouOpMode extends OpMode{

        ColorSensor colorSensor;

        public void init() {
            colorSensor = hardwareMap.colorSensor.get("sensor_color");
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
