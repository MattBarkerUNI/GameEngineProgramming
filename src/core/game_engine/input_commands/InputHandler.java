package core.game_engine.input_commands;

public class InputHandler {
    private Command leftCommand, rightCommand, forwardCommand, backwardCommand;
    public InputHandler(Command left, Command right, Command forward, Command backward){ //constructor
        //defining each command
        leftCommand = left;
        rightCommand = right;
        forwardCommand = forward;
        backwardCommand = backward;
    }
    public void moveLeft(){
        leftCommand.execute();
    }

    public  void moveRight(){
        rightCommand.execute();
    }

    public void moveForward(){
        forwardCommand.execute();
    }

    public void moveBackward(){
        backwardCommand.execute();
    }
}
