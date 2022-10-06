package streak.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;

/**
 * @author: madz
 * This will hold some methods for the buttons to make this easier
 */

public class StreakButton {

    private final float DELAYSECONDS = .3f;

    private Button button;
    private Button hiddenButton;
    private Sound sound;

    private Stack buttonStack;

    private boolean done;

    /**
     * @param skin: The skin object to be used
     * @param buttonNum: which button this is, this will help us pick which pattern in the skin to use
     * @param soundPath: where the folder for the sound is, we know which file it is by buttonNum
     */
    public StreakButton(Skin skin, int buttonNum, String soundPath) {
        sound = Gdx.audio.newSound(Gdx.files.internal(soundPath + "note-" + buttonNum + ".wav"));

        button = new Button(skin, "button-" + buttonNum);
        hiddenButton = new Button(skin, "button-" + buttonNum + "-pressed");

        buttonStack = new Stack();
        buttonStack.add(hiddenButton);
        buttonStack.add(button);

        button.setTransform(true);
        done = false;
    }

    public void play() { sound.play(); }

    public Button getButton() { return button; }

    public Stack getButtonStack() { return buttonStack; }

    public boolean isDone() { return done; }

    public void fakePress() {
        // make the button look pressed
        done = false;
        button.setVisible(false);

        //play the sound
        sound.play();

        // put it back after 1 second:
        // https://stackoverflow.com/questions/21781161/how-can-i-do-something-every-second-libgdx
        Timer.schedule(new Timer.Task(){
                           @Override
                           public void run() { button.setVisible(true); }
                       }
                , DELAYSECONDS       //    (delay)
                , 1     //    (seconds)
                , 0
        );
        done = true;
    }
}
