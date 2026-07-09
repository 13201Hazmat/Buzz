package org.firstinspires.ftc.teamcode.subsystems;

import static com.pedropathing.ivy.commands.Commands.infinite;
import static com.pedropathing.ivy.commands.Commands.instant;

import com.pedropathing.ivy.Command;

import dev.nextftc.control.feedback.PIDCoefficients;
import dev.nextftc.control.feedback.PIDController;
import dev.nextftc.control.feedforward.ElevatorFeedforward;
import dev.nextftc.control.feedforward.GravityFeedforwardParameters;
import dev.nextftc.hardware.RobotController;
import dev.nextftc.hardware.actuators.NextMotor;
import dev.nextftc.robot.Mechanism;

public class Lift implements Mechanism {
    NextMotor l = new NextMotor(RobotController.controlHub(), 0 /*, depends on motor*/);
    NextMotor r = new NextMotor(RobotController.controlHub(), 0);

    PIDCoefficients pid = new PIDCoefficients(0, 0, 0);
    PIDCoefficients anglePid = new PIDCoefficients(0, 0, 0);
    GravityFeedforwardParameters ff = new GravityFeedforwardParameters(0, 0, 0, 0);
    PIDController slides = new PIDController(pid);
    PIDController anglePID = new PIDController(anglePid);
    ElevatorFeedforward elevator = new ElevatorFeedforward(ff);

    private final double diffyRadius = 1.0; // inches

    private LiftState liftState;

    public enum LiftState {
        HIGH,
        LOW,
        HOME
    }

    public enum BucketState {
        SCORE,
        REST
    }

    private BucketState bucketState = BucketState.REST;

    private final double SCORE_ANGLE = 0;
    private final double REST_ANGLE = 0;

    private double targetAngle(BucketState state) {
        switch (state) {
            case SCORE:
                return SCORE_ANGLE;
            case REST:
            default:
                return REST_ANGLE;
        }
    }

    private boolean isDiffy;

    public Lift() {
        this.liftState = LiftState.HOME;
        this.isDiffy = true;
    }

    public Command setPosition(LiftState state) {
        return instant(() -> liftState = state);
    }

    public Command setBucketAngle(BucketState state) {
        return instant(() -> bucketState = state);
    }
    private double targetPosition(LiftState state) {
        switch (state) {
            case HIGH:
                return 1000;
            case LOW:
                return 500;
            case HOME:
            default:
                return 0;
        }
    }

    public void periodic() {
            double liftTarget = targetPosition(liftState);
            double liftMeasured = (l.getEncoderPosition().getMagnitude() + r.getEncoderPosition().getMagnitude()) / 2.0;

            double liftPID = slides.calculateFromReference(liftTarget, liftMeasured);
            double liftFF = elevator.calculate(0.0, 0.0);
            double liftPower = liftPID + liftFF;

            double diffyPower = 0.0;
            if (isDiffy) {
                double angleMeasured = ((l.getEncoderPosition().getMagnitude() - r.getEncoderPosition().getMagnitude()) / 2.0) / diffyRadius;
                diffyPower = anglePID.calculateFromReference(targetAngle(bucketState), angleMeasured);
            }

            l.setThrottle(liftPower + diffyPower);
            r.setThrottle(liftPower - diffyPower);
    }
}