package org.firstinspires.ftc.teamcode.vision;


import com.pedropathing.geometry.Pose;
import com.pedropathing.math.Vector;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

import java.util.ArrayList;
import java.util.List;

public class Vision {
    Limelight3A limelight;

    public Limelight3A getLimelight(){
        return limelight;
    }

    public Vision(HardwareMap hardwareMap){
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(0);
        limelight.start();
    }

    public void start() {
        pipelineSwitch(0);
    }

    public void pipelineSwitch(int index) {
        limelight.pipelineSwitch(index);
    }
    public int getPipeline() {
        return limelight.getStatus().getPipelineIndex();
    }

    private LLResult cachedResult;

    public void update() {
        cachedResult = limelight.getLatestResult();
    }

    public Pose getRobotPose() {
        //Creating a 3d array to store the distances of each block for comparison
        LLResult result = limelight.getLatestResult();
        Pose pose = null;
        if (result != null) {
            if (result.isValid()) {
                List<LLResultTypes.FiducialResult> fiducialResults = result.getFiducialResults();
                for (LLResultTypes.FiducialResult fr : fiducialResults) {
                    Pose3D robotPoseWeirdM = fr.getRobotPoseFieldSpace();
                    pose = new Pose(-robotPoseWeirdM.getPosition().x * 39.37,
                            -robotPoseWeirdM.getPosition().y * 39.37,
                            Math.toRadians(robotPoseWeirdM.getOrientation().getYaw() - 180));
                    averagingList.add(pose);
                }
            }
        }
        return pose;
    }
    private final List<Pose> averagingList = new ArrayList<>();
    public List<DetectedBall> searchForBalls(Pose curPose) {
        return searchForBalls(curPose, false, false);
    }
    public List<DetectedBall> searchForBalls(Pose curPose, boolean limitZone, boolean farZone) {
        List<DetectedBall> detectedBalls = new ArrayList<>();
        LLResult result = limelight.getLatestResult();
        if (result.isValid()) {
            List<LLResultTypes.DetectorResult> detections = result.getDetectorResults();
            for (LLResultTypes.DetectorResult detection : detections) {
                double tx = detection.getTargetXDegrees(); // Where it is (left-right)
                double ty = detection.getTargetYDegrees(); // Where it is (up-down)
                String className = detection.getClassName(); // What was detected

                long timePhotoWasTaken = result.getControlHubTimeStampNanos();
                Pose ballPose = getFinalPose(tx, ty, curPose);

                if (Math.abs(ballPose.getX()) > 90 || Math.abs(ballPose.getY()) > 90) {
                    continue;
                }
                if (limitZone && ballPose.getX() > -4) {
                    continue;
                }
                detectedBalls.add(new DetectedBall(ballPose, timePhotoWasTaken));
            }
        }
        return detectedBalls;
    }

    public Pose getFinalPose(double targetX, double targetY, Pose curpose) {
        return getFinalPose(targetX, targetY, curpose, new Vector(), 0, 0);
    }

    public Pose getFinalPose(double targetX, double targetY, Pose curpose, Vector robotVelocity, double headingVelocity, double latency) {
        //todo: double check these numbers
        double cameraPitch = 0; // zero facing straight forward 90 facing straight up
        double ballRadius = 2.5; // radius of the ball in inches
        double cameraHeight = 10.375; // distance from the camera to the ground in inches
        double cameraOffsetY = 5; // distance Y to center of the chassis
        double cameraOffsetX = 0; // distance X to center of the chassis

        double cameraToBallAngle = Math.toRadians(cameraPitch + targetY);
        double heightDifference = cameraHeight - ballRadius;

        double forwardDistance = heightDifference / Math.tan(cameraToBallAngle); // Y is forward backward
        double lateralDistance = forwardDistance * Math.tan(Math.toRadians(targetX));

        forwardDistance += cameraOffsetY;
        lateralDistance += cameraOffsetX;

        Vector robotToBallVector = new Vector(new Pose(- forwardDistance, lateralDistance));
        robotToBallVector.rotateVector(curpose.getHeading());
        double x = robotToBallVector.getXComponent();
        double y = robotToBallVector.getYComponent();

        double newX = curpose.getX() + x;
        double newY = curpose.getY() + y;
        return new Pose(newX, newY);
    }

    public static class DetectedBall {
        public Pose ballPose;
        public long timePhotoWasTaken;
        public Vector velocity;
        DetectedBall(Pose ballPose, long timePhotoWasTaken){
            this.ballPose = ballPose;
            this.timePhotoWasTaken = timePhotoWasTaken;
        }
        @Override
        public String toString() {
            return ballPose.toString();
        }
    }

}