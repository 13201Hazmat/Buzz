package org.firstinspires.ftc.teamcode.vision;

import com.pedropathing.geometry.Pose;
import com.pedropathing.math.Vector;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;

import dev.nextftc.hardware.RobotController;

public class Vision {
    private Limelight3A limelight;
    public Vision(HardwareMap hardwareMap) {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(0);
        limelight.start();
    }
    private final LLResult visionResult = limelight.getLatestResult();

    public LLResult getVisionResult(){
        LLResult result = limelight.getLatestResult();
        if(result == null || result.isValid()){
            return null;
        }
        return result;
    }

    public Limelight3A getLimelight() {
        return limelight;
    }
    public Pose getFinalPose(double targetX, double targetY, Pose currentPose) {

        if (currentPose == null) {
            return null;
        }

        double cameraPitch = 0.0;
        double ballRadius = 1.4;
        double cameraHeight = 10.375;

        double cameraOffsetY = 5.0;
        double cameraOffsetX = 0.0;

        double cameraToBallAngle = Math.toRadians(cameraPitch + targetY);

        double heightDifference = cameraHeight - ballRadius;

        // Checks for impossible math
        if (Math.abs(Math.tan(cameraToBallAngle)) < 0.0001) {
            return null;
        }

        double forwardDistance = heightDifference / Math.tan(cameraToBallAngle);
        double lateralDistance = forwardDistance * Math.tan(Math.toRadians(targetX));

        forwardDistance += cameraOffsetY;
        lateralDistance += cameraOffsetX;

        // TODO FIX THIS WITH RELATION TO PEDRO COORDS
        Vector robotToBallVector = new Vector(new Pose(-forwardDistance, lateralDistance));

        robotToBallVector.rotateVector(currentPose.getHeading());

        double fieldX = currentPose.getX() + robotToBallVector.getXComponent();

        double fieldY = currentPose.getY() + robotToBallVector.getYComponent();

        return new Pose(fieldX, fieldY, currentPose.getHeading());
    }
}