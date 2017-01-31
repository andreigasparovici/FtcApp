package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

/**
 * Created by alex on 31.01.2017.
 */
@TeleOp(name="Distance sensor", group="Iterative Opmode")
public class DistanceSensorOpMode extends OpMode {

    OpticalDistanceSensor ods;

    public void init() {
        ods = hardwareMap.opticalDistanceSensor.get("optical_distance");
        ods.enableLed(true);
    }

    public void loop(){
        telemetry.addData("Light detected",ods.getLightDetected());
        telemetry.addData("Raw light detected",ods.getRawLightDetected());
    }
}
