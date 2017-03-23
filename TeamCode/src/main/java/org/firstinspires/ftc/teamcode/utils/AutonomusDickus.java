package org.firstinspires.ftc.teamcode.utils;


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


/**
 * Created by DragosTrett on 23.03.2017.
 */


@Autonomous(name="AutonomusDickus", group="Iterative Opmode")
public class AutonomusDickus extends LinearOpMode {

    private void delay(int x){
        sleep(x);
    }

    //Maping the bitch
    OpticalDistanceSensor odsSensor;
    int timp_90grade=400, distanta_min_pork=10;

    DcMotor leftMotor, rightMotor, brushMotor;

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

    public final static String[] TOUCH_SENSOR = new String[]{"s1","s2","s3"};
    public final static double COLOR_SERVO_LEFT = 0.01;
    public final static double COLOR_SERVO_RIGHT = 0.99;
    public final static double COLOR_SERVO_INITIAL = 0.12;

    private void mapDevices(){
        leftMotor  = hardwareMap.dcMotor.get(LEFT_MOTOR);
        rightMotor = hardwareMap.dcMotor.get(RIGHT_MOTOR);
        brushMotor = hardwareMap.dcMotor.get(BRUSH_MOTOR);

        RANGE1 = hardwareMap.i2cDevice.get(RANGE_SENSOR_NAME);
        RANGE1Reader = new I2cDeviceSynchImpl(RANGE1, RANGE1ADDRESS, false);
        RANGE1Reader.engage();

        s1 = hardwareMap.touchSensor.get(TOUCH_SENSOR[0]);
        s2 = hardwareMap.touchSensor.get(TOUCH_SENSOR[1]);
        s3 = hardwareMap.touchSensor.get(TOUCH_SENSOR[2]);

        colorSensor = hardwareMap.colorSensor.get(COLOR_SENSOR_NAME);
        colorSensor.enableLed(false);

        colorServo = hardwareMap.servo.get(COLOR_SERVO_NAME);

        colorServo.setPosition(COLOR_SERVO_INITIAL);

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

    private int returnTheDistanceBitch() {
        range1Cache = RANGE1Reader.read(RANGE1_REG_START, RANGE1_READ_LENGTH);
        return  range1Cache[0] & 0xFF;
    }

    private void pressTheBitchInTheFace(){
        //TODO press the button timi's code pls work
    }

    private void FoundTheLIneBitchJERRY(){

            //turn 90 dg
            rightMotor.setPower(-1);
            leftMotor.setPower(1);
            sleep(timp_90grade);
            rightMotor.setPower(0);
            leftMotor.setPower(0);

            //go forth until limit
            while (returnTheDistanceBitch()>distanta_min_pork){
                rightMotor.setPower(1);
                leftMotor.setPower(1);
                delay(10);
            }
            pressTheBitchInTheFace();
    }

    private void doTheMagicPlease(){
        while(opModeIsActive()){
            brushMotor.setPower(1);
            readLine();
            rightMotor.setPower(.5);
            leftMotor.setPower(.5);
            if(foundLine()){
                rightMotor.setPower(0);
                leftMotor.setPower(0);
                FoundTheLIneBitchJERRY();
            }

        }
    }

    @Override
    public void runOpMode() throws InterruptedException {
        mapDevices();
        waitForStart();
        doTheMagicPlease();
    }
}