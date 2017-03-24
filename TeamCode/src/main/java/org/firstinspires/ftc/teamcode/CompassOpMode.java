package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CompassSensor;

/**
 * Created by alex on 16.03.2017.
 */
@TeleOp(name = "Compass MOde", group = "Sensor")
@Disabled
public class CompassOpMode extends OpMode {
    CompassSensor compassSensor;

    public void init(){
        compassSensor = hardwareMap.compassSensor.get("compass");
    }

    public void loop(){

        telemetry.addData("Direction", compassSensor.getDirection());
        telemetry.update();

    }
}
