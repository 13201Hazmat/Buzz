package org.firstinspires.ftc.teamcode.subsystems.vision;

import com.pedropathing.geometry.Pose;

public class LimelightTarget {

    private final Pose pose;
    private final double distance;
    private final double tx;
    private final double ty;

    public LimelightTarget(
            Pose pose,
            double distance,
            double tx,
            double ty
    ) {
        this.pose = pose;
        this.distance = distance;
        this.tx = tx;
        this.ty = ty;
    }

    public Pose getPose() {
        return pose;
    }

    public double getDistance() {
        return distance;
    }

    public double getTx() {
        return tx;
    }

    public double getTy() {
        return ty;
    }
}