package core.game_engine.input_commands;

public class MoveBackwardCommand implements Command {
    private MoveAble actor;
    public MoveBackwardCommand(MoveAble _actor){
        actor = _actor;
    }
    @Override
    public void execute() {
        actor.moveBackward();
    }
}
