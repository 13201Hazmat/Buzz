package org.firstinspires.ftc.teamcode.subsystems;

import static dev.nextftc.units.Units.RotationsPerMinute;


import dev.nextftc.hardware.RobotController;
import dev.nextftc.hardware.actuators.NextMotor;
import dev.nextftc.robot.Mechanism;
import dev.nextftc.units.measuretypes.AngularVelocity;

public class Putter implements Mechanism {
    private final NextMotor putterMotor = new NextMotor(RobotController.controlHub(), 0);

    enum PutterState {
        ON,
        OFF
    }

    private PutterState putterState;
    private final AngularVelocity ON_SPEED = RotationsPerMinute.of(2000);

    private final AngularVelocity OFF_SPEED = RotationsPerMinute.of(0);
    public Putter(){
        putterState = PutterState.OFF;

        putterMotor.getVelocityConstants().setKP(0.0);
        putterMotor.getVelocityConstants().setKI(0.0);
        putterMotor.getVelocityConstants().setKD(0.0);

        putterMotor.getVelocityConstants().setKS(0.0);
        putterMotor.getVelocityConstants().setKV(0.0);
        putterMotor.getVelocityConstants().setKA(0.0);

    }

    public void setPutterState(PutterState putterState){
        this.putterState = putterState;
    }

    public AngularVelocity getTargetVelocity(PutterState putterState){
        if(putterState == PutterState.ON){
            return ON_SPEED;
        }
        return OFF_SPEED;
    }

    @Override
    public void periodic() {
        putterMotor.setVelocitySetpoint(getTargetVelocity(putterState));
    }
}
