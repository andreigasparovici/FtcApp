package org.firstinspires.ftc.teamcode.utils;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by timi on 08.02.2017.
 */

public class PerieHandler {

    static private DcMotor perieMotor;
    enum State {
        GATHER, REPEL, OFF
    }
    static private State currentDirection = State.OFF;
    static private boolean lastA, lastY;

    static public void init(DcMotor motor) {
        perieMotor = motor;
    }

    static public void update(boolean aPress, boolean yPress) {
        if (lastA && !aPress) {
            if (currentDirection == State.REPEL)
                currentDirection = State.OFF;
            else
                currentDirection = State.REPEL;
        }
        if (lastY && !yPress) {
            if (currentDirection == State.GATHER)
                currentDirection = State.OFF;
            else
                currentDirection = State.GATHER;
        }
        switch (currentDirection) {
            case OFF: perieMotor.setPower(0); break;
            case REPEL: perieMotor.setPower(1); break;
            case GATHER: perieMotor.setPower(-1); break;
        }
        lastA = aPress;
        lastY = yPress;
    }
}
