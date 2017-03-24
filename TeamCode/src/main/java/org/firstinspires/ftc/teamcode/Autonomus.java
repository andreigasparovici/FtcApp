
package org.firstinspires.ftc.teamcode;


import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDevice;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchImpl;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


/**
 * Created by DragosTrett on 23.03.2017.
 */


@Autonomous(name="Autonomus", group="Iterative Opmode")

public class Autonomus extends LinearOpMode {

    double motor_universal_power=0.5;

    private void delay(int x){
        sleep(x);
    }

    //Maping the bitch
    ModernRoboticsI2cRangeSensor rangeSensor;
    double distanta_min_pork=6;
    int timp_90grade=600;

    DcMotor leftMotor, rightMotor, brushMotor, trebuchetmotor;

    byte[] range1Cache;

    I2cAddr RANGE1ADDRESS = new I2cAddr(0x14);
    public static final int RANGE1_REG_START = 0x04;
    public static final int RANGE1_READ_LENGTH = 2;
    public I2cDevice RANGE1;
    public I2cDeviceSynch RANGE1Reader;

    TouchSensor s1,s2,s3;

    int[] a=new int[9];

    ColorSensor colorSensor;

    Servo colorServo;

    public static final String COLOR_SENSOR_NAME = "color_sensor";
    public static final String RANGE_SENSOR_NAME = "range_sensor";

    public static final String COLOR_SERVO_NAME = "color_servo";

    public static final String LEFT_MOTOR = "left_motor";
    public static final String RIGHT_MOTOR = "right_motor";
    public static final String BRUSH_MOTOR = "brush_motor";
    public static final String TREBUCHET_MOTOR = "catapult_motor";

    public final static String[] TOUCH_SENSOR = new String[]{"s1","s2","s3"};
    public final static double COLOR_SERVO_LEFT = 0.01;
    public final static double COLOR_SERVO_RIGHT = 0.99;
    public final static double COLOR_SERVO_CENTER = 0.12;

    private void mapDevices(){
        leftMotor  = hardwareMap.dcMotor.get(LEFT_MOTOR);
        rightMotor = hardwareMap.dcMotor.get(RIGHT_MOTOR);
        brushMotor = hardwareMap.dcMotor.get(BRUSH_MOTOR);
        trebuchetmotor = hardwareMap.dcMotor.get(TREBUCHET_MOTOR);

        RANGE1 = hardwareMap.i2cDevice.get(RANGE_SENSOR_NAME);
        RANGE1Reader = new I2cDeviceSynchImpl(RANGE1, RANGE1ADDRESS, false);
        RANGE1Reader.engage();

        s1 = hardwareMap.touchSensor.get(TOUCH_SENSOR[0]);
        s2 = hardwareMap.touchSensor.get(TOUCH_SENSOR[1]);
        s3 = hardwareMap.touchSensor.get(TOUCH_SENSOR[2]);

        colorSensor = hardwareMap.colorSensor.get(COLOR_SENSOR_NAME);
        colorSensor.enableLed(false);

        colorServo = hardwareMap.servo.get(COLOR_SERVO_NAME);

        colorServo.setPosition(COLOR_SERVO_RIGHT);

        rangeSensor = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "sensor_range");

    }

    public void readLine(){
        if (s1.isPressed())
            a[1]=1;
        else
            a[1]=0;
        if (s2.isPressed())
            a[2]=1;
        else
            a[2]=0;
        if (s3.isPressed())
            a[3]=1;
        else
            a[3]=0;
    }

    private boolean foundLine(){return a[1] == 0 || a[2] == 0 || a[3] == 0;}

    private double returnTheDistance() {
        //TODO test that bitch
        double x=rangeSensor.getDistance(DistanceUnit.CM);
        //telemetry.addData("cm", "%.2f cm", x);
        //telemetry.update();
        return x;
    }

    private void ApasaDreapta(){
        rightMotor.setPower(motor_universal_power);
        delay(100);
        rightMotor.setPower(0);

    }

    private void ApasaStanga(){
        leftMotor.setPower(motor_universal_power);
        delay(100);
        rightMotor.setPower(0);
    }

    private void pressTheBeacon(){
        //TODO press the button timi's code pls work
        colorServo.setPosition(COLOR_SERVO_RIGHT);
        if(colorSensor.red() > colorSensor.blue()){
            colorServo.setPosition(COLOR_SERVO_CENTER);
            delay(100);
            ApasaDreapta();
        }
        else {
            colorServo.setPosition(COLOR_SERVO_CENTER);
            delay(100);
            ApasaStanga();
        }
    }

    private void Launch(){
        //TODO align the robot to the vortex
        rightMotor.setPower(1);
        leftMotor.setPower(1);
        delay(timp_90grade*2);
        rightMotor.setPower(0);
        leftMotor.setPower(0);

        trebuchetmotor.setPower(1);
        delay(100);
        trebuchetmotor.setPower(0);
    }

    private void FoundTheLIne(){

        //turn 90 dg
        rightMotor.setPower(1);
        leftMotor.setPower(1);
        sleep(timp_90grade);
        rightMotor.setPower(0);
        leftMotor.setPower(0);

        //go forth until limit
        while (returnTheDistance()>distanta_min_pork){
            rightMotor.setPower(1);
            leftMotor.setPower(1);
            delay(10);
        }
        pressTheBeacon();
        Launch();
    }

    private void doTheMagicPlease(){
        while(opModeIsActive()){
            brushMotor.setPower(1);
            readLine();
            rightMotor.setPower(motor_universal_power);
            leftMotor.setPower(motor_universal_power);
            if(foundLine()){
                rightMotor.setPower(0);
                leftMotor.setPower(0);
                FoundTheLIne();
            }
            //TODO harcodeaza coae sa ajunga la linii

        }
    }

    @Override
    public void runOpMode() throws InterruptedException {
        mapDevices();
        waitForStart();
        doTheMagicPlease();
    }
}