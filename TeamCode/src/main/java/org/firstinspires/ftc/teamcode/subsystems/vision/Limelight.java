package org.firstinspires.ftc.teamcode.subsystems.vision;

import com.pedropathing.follower.Follower;

import dev.nextftc.hardware.webcams.NextLimelight;
import dev.nextftc.robot.Mechanism;

public class Limelight implements Mechanism {

    private static final int PIPELINE = 0;
    private static final int REFRESH_RATE = 100;

    private final NextLimelight limelight;
    private final Follower follower;

    public Limelight(Follower follower) {
        this.follower = follower;

        limelight = new NextLimelight("limelight");
        limelight.startReading(PIPELINE, REFRESH_RATE);
    }

    public boolean hasTarget() {
        return limelight.getLatestResult() != null &&
                limelight.getLatestResult().isValid();
    }

    public double getTx() {
        return limelight.getTX();
    }

    public double getTy() {
        return limelight.getTY();
    }

    public LimelightTarget getTarget() {

        if (!hasTarget()) {
            return null;
        }

        return LimelightMath.calculateTarget(
                follower.getPose(),
                getTx(),
                getTy()
        );
    }

    public double getDistance() {

        LimelightTarget target = getTarget();

        if (target == null) {
            return -1;
        }

        return target.getDistance();
    }

    public void stop() {
        limelight.stop();
    }

    public void start() {
        limelight.startReading(PIPELINE, REFRESH_RATE);
    }

    public void setPipeline(int pipeline) {
        limelight.setPipeline(pipeline);
    }

}