package org.firstinspires.ftc.teamcode.opmodes.auto;

import androidx.annotation.NonNull;

import com.skeletonarmy.marrow.prompts.Prompter;
import com.skeletonarmy.marrow.prompts.OptionPrompt;

import org.firstinspires.ftc.teamcode.data.Alliance;

import dev.nextftc.robot.NextRobot;
import dev.nextftc.robot.opmode.NextAutonomous;
import dev.nextftc.robot.opmode.NextOpMode;
import dev.nextftc.robot.opmode.OpModeHook;

@NextAutonomous(name = "Auto", group = "Auto")
public class MarrowTest extends NextOpMode {

    private final Prompter prompter = new Prompter(this);

    public MarrowTest(@NonNull NextRobot robot, @NonNull OpModeHook... hooks) {
        super(robot, hooks);
    }

    @Override
    public void onInit() {
        prompter.prompt("alliance", new OptionPrompt<>("Select Alliance", Alliance.RED, Alliance.BLUE))
                .label("Alliance Color")
                .showSummary()
                .onComplete(this::onPromptsComplete);
    }

    private void onPromptsComplete() {
        Alliance alliance = prompter.get("alliance");

        telemetry.addData("Selected Alliance", alliance);
        telemetry.update();
    }

    @Override
    public void disabledPeriodic() {
        prompter.run();
    }

    @Override
    public void onStart() {
        Alliance alliance = prompter.get("alliance");
    }
}