package org.firstinspires.ftc.teamcode.opmodes.auto;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.skeletonarmy.marrow.prompts.Prompter;
import com.skeletonarmy.marrow.prompts.OptionPrompt;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.data.Alliance;

import dev.nextftc.robot.NextRobot;
import dev.nextftc.robot.opmode.NextAutonomous;
import dev.nextftc.robot.opmode.NextOpMode;
import dev.nextftc.robot.opmode.OpModeHook;

@NextAutonomous(name = "Auto", group = "Auto")
public class MarrowTest extends NextOpMode {

    private final Prompter prompter;
    private Alliance alliance;
    private Robot robot;

    public MarrowTest(@NonNull NextRobot robot, @NonNull OpModeHook... hooks) {
        super(robot, hooks);

        OpMode opModeAdapter = new OpMode() {
            @Override public void init() {}
            @Override public void loop() {}
        };

        opModeAdapter.telemetry = this.telemetry;
        opModeAdapter.gamepad1 = this.gamepad1;
        opModeAdapter.gamepad2 = this.gamepad2;
        opModeAdapter.hardwareMap = this.hardwareMap;

        prompter = new Prompter(opModeAdapter);

        robot = new Robot(Alliance.BLUE);

        prompter.prompt("alliance", new OptionPrompt<>("Select Alliance", Alliance.RED, Alliance.BLUE))
                .label("Alliance Color")
                .showSummary()
                .onComplete(this::onPromptsComplete);
    }

    private void onPromptsComplete() {
        alliance = prompter.get("alliance");
        robot.setAlliance(alliance);
        telemetry.addData("Selected Alliance", alliance);
        telemetry.update();
    }

    @Override
    public void disabledPeriodic() {
        prompter.run();
    }

    @Override
    public void start() {
        telemetry.addData("Running with Alliance", alliance);
    }
}