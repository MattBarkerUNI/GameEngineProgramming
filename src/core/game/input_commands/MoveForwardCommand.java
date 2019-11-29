package core.game.input_commands;

public class MoveForwardCommand implements Command {
    private MoveAble actor;
    public MoveForwardCommand(MoveAble _actor){
        actor = _actor;
    }
    @Override
    public void execute() {
        actor.moveForward();
    }
}
