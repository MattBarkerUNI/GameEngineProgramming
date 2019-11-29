package core.game.input_commands;

public class MoveLeftCommand implements Command {
    private MoveAble actor;
    public MoveLeftCommand(MoveAble _actor){
        actor = _actor;
    }
    @Override
    public void execute() {
        actor.moveLeft();
    }
}
