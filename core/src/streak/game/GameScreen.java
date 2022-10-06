package streak.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.Timer;


/** @author: madz
 *  Welcome menu for the streak game
 */

public class GameScreen implements Screen {
    private Game game;

    private final Skin skin;
    private Label title;
    private Label subTitle;
    private Table table;

    private StreakButton button1;
    private StreakButton button2;
    private StreakButton button3;
    private StreakButton button4;

    private Label scoreLabel;
    private Label turnLabel;

    private boolean playerTurn;
    private boolean success;
    private int score;
    private Sequence sequence;

    private float waitTime = .5f;

    private final int buttonDim = 150;

    private Stage stage;

    private String soundPath;

    Sound successSound;

    private int currentIndex = 0;

    public GameScreen(Game game, String skinPath, String soundPath) {
        this.game = game;

        this.soundPath = soundPath;

        successSound = Gdx.audio.newSound(Gdx.files.internal(soundPath + "success.wav"));

        // set up basic skin and stage
        skin = new Skin((Gdx.files.internal(skinPath)));
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        // table for all of the widgets

        table = new Table(skin);
        table.setFillParent(true);

        title = new Label("Simon Says", skin, "title-white");
        table.add(title);

        subTitle = new Label("A game to test your memory.", skin, "sub-title-white");
        table.row();
        table.add(subTitle);

        scoreLabel = new Label("Score: 0", skin, "sub-title-white");
//        table.add(scoreLabel).left().padTop(100);
        table.row();

        Table buttonTable = new Table();

        // Buttons-- note that they are a custom class and not the normal button. SimonButton
        // contains the actual button, this just allows for less duplicate code.
        button1 = new StreakButton(skin, 1, soundPath);
        button2 = new StreakButton(skin, 2, soundPath);
        button3 = new StreakButton(skin, 3, soundPath);
        button4 = new StreakButton(skin, 4, soundPath);

        button1.getButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                checkInput(1);
            }
        });

        button2.getButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                checkInput(2);
            }
        });

        button3.getButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                checkInput(3);
            }
        });

        button4.getButton().addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                checkInput(4);
            }
        });

        // separate button table so they are in a square and the other widgets don't offset them
        buttonTable.add(button1.getButtonStack()).width(buttonDim).height(buttonDim);
        buttonTable.add(button2.getButtonStack()).width(buttonDim).height(buttonDim);
        buttonTable.row();
        buttonTable.add(button4.getButtonStack()).width(buttonDim).height(buttonDim);
        buttonTable.add(button3.getButtonStack()).width(buttonDim).height(buttonDim);

        table.row();
        table.add(buttonTable).width(buttonDim);

        stage.addActor(table);
        stage.addActor(scoreLabel);

        // set up gameplay variables
        playerTurn = false;
        success = true;
        score = 0;
        sequence = new Sequence();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // normal Libgdx stuff to draw the screen

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();


        // gameplay
        if (success) {
            if (!playerTurn) {
                currentIndex = 0;
                sequence.resetAndAdd();
                playSequence(sequence);
                playerTurn = true;
                System.out.println(sequence);
            }

        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        scoreLabel.setText("");
    }

    private void checkEndOfSequence() {
        if (currentIndex >= sequence.getLength()) {
            score++;
            scoreLabel.setText("Score: " + score);
            successSound.play();

            System.out.println(score);
            playerTurn = false;
        }
    }

    private void checkInput(int input) {
        if (playerTurn && (input == sequence.get(currentIndex)) && success) {
//            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            playFromInt(input);
            System.out.println("Correct input of " + input);
            currentIndex++;
            checkEndOfSequence();
        }
        else if (input != sequence.get(currentIndex)) {
            gameOver();
        }
    }

    private void gameOver() {
        success = false;
        Sound fail = Gdx.audio.newSound(Gdx.files.internal(soundPath + "failure.wav"));
        fail.play();
        System.out.println("GAME OVER");
        game.setScreen(new GameOverScreen(game, skin));
    }
    /**
     * @param sequence: should contain random sequence of 1, 2, 3, 4
     */
    private void playSequence(final Sequence sequence) {

        Timer.schedule(new Timer.Task() {
                           @Override
                           public void run() {
                               playFromInt(sequence.next());
                               System.out.println("playing");
                           }
                       }
                , waitTime + 1      //    (delay)
                , waitTime     //    (seconds)
                , sequence.getLength() - 1
        );
        sequence.reset();
    }

    /**
     * @param note: int, function to help play the sequence
     */
    private void playFromInt(int note) {
        switch (note) {
            case 1:
                button1.fakePress();
                break;
            case 2:
                button2.fakePress();
                break;
            case 3:
                button3.fakePress();
                break;
            case 4:
                button4.fakePress();
                break;
        }

    }
}


