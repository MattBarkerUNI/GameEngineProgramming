package core.game_engine.input_commands;

import processing.core.PApplet;

public class InputController {
    public InputHandler inputHandler;
    MoveLeftCommand moveLeftCommand;
    MoveRightCommand moveRightCommand;
    MoveForwardCommand moveForwardCommand;
    MoveBackwardCommand moveBackwardCommand;

    boolean left, right, forward, backward;
    public InputController(MoveAble _actor){ //implements the MoveAble
        //connecting the commands to the _actor
        moveLeftCommand = new MoveLeftCommand(_actor);
        moveRightCommand = new MoveRightCommand(_actor);
        moveForwardCommand = new MoveForwardCommand(_actor);
        moveBackwardCommand = new MoveBackwardCommand(_actor);

        inputHandler = new InputHandler(moveLeftCommand, moveRightCommand, moveForwardCommand, moveBackwardCommand);
    }
    public void keyHandler(char key, int keyCode, boolean active){
        //checking if the input is active based on the keys the user is pressing
        if(key == 'A' || key == 'a' || keyCode == PApplet.LEFT){ //accessible as statics through the PApplet class ||| do not need tp use parent or create instance
            left = active;
        }else if(key == 'D'|| key == 'd' || keyCode == PApplet.RIGHT){
            right = active;
        }else if(key == 'W' || key == 'w' || keyCode == PApplet.UP){
            forward = active;
        }else if(key == 'S' || key == 's' || keyCode == PApplet.DOWN){
            backward = active;
        }else if(!active && keyCode != 0){ //if no keys are active then the value returned is false
            left = false;
            right = false;
            forward = false;
            backward = false;
        }
    }
    public void checkInput(){
        //update this in the main draw of application
        if(left){
            inputHandler.moveLeft();
        }
        if(right){
            inputHandler.moveRight();
        }
        if(forward){
            inputHandler.moveForward();
        }
        if(backward){
            inputHandler.moveBackward();
        }
    }
}
