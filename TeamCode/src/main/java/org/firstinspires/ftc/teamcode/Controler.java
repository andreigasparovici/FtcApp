
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
/**
 * Created by DragosTrett on 23.03.2017.
 */
@TeleOp(name = "Controler", group = "Opmode")

public class Controler extends OpMode{

    DcMotor leftMotor, rightMotor, brushMotor, trebuchetmotor;

    public static final String LEFT_MOTOR = "left_motor";
    public static final String RIGHT_MOTOR = "right_motor";
    public static final String BRUSH_MOTOR = "brush_motor";
    public static final String TREBUCHET_MOTOR = "catapult_motor";

    private void mapDevices() {
        leftMotor = hardwareMap.dcMotor.get(LEFT_MOTOR);
        rightMotor = hardwareMap.dcMotor.get(RIGHT_MOTOR);
        brushMotor = hardwareMap.dcMotor.get(BRUSH_MOTOR);
        trebuchetmotor = hardwareMap.dcMotor.get(TREBUCHET_MOTOR);
    }

    @Override
    public void init(){
        mapDevices();
    }

    @Override
    public void loop(){

        rightMotor.setPower(-gamepad1.right_stick_y);
        leftMotor.setPower(gamepad1.left_stick_y);
        if(gamepad1.y){
            brushMotor.setPower(1);
        }
        if(gamepad1.a){
            brushMotor.setPower(-1);
        }
        if(gamepad1.b){
            brushMotor.setPower(0);
            //trebuchetmotor.setPower(0);
        }
        if(gamepad1.x){
            trebuchetmotor.setPower(1);
        }
        if(gamepad1.right_bumper){
            trebuchetmotor.setPower(0);
        }
    }
}
