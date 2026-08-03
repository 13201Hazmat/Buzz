package org.firstinspires.ftc.teamcode.subsystems.vision;


import com.pedropathing.geometry.Pose;

public class LimelightMath {

    public static LimelightTarget calculateTarget(Pose robotPose, double tx, double ty) {

        double turnAngle = Math.toRadians(LimelightConstants.CAMERA_YAW - tx);

        double heading = robotPose.getHeading() + turnAngle;

        double distance = LimelightConstants.CAMERA_HEIGHT / Math.tan(Math.toRadians(LimelightConstants.CAMERA_PITCH + ty));

        double x = robotPose.getX() + distance * Math.cos(heading);

        double y = robotPose.getY() + distance * Math.sin(heading);

        Pose targetPose = new Pose(x, y, heading);

        return new LimelightTarget(targetPose, distance, tx, ty);
    }

}