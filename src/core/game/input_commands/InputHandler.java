package core.game.input_commands;

public class InputHandler {
    private Command leftCommand, rightCommand, forwardCommand;
    public InputHandler(Command left, Command right, Command forward){ //constructor
        //defining each command
        leftCommand = left;
        rightCommand = right;
        forwardCommand = forward;
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
}
