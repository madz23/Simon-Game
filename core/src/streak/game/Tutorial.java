package streak.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.Random;

public class Tutorial implements Screen {
    private Game game;
    private Stage stage;

    private Label helloLabel;
    private Label infoLabel;

    private final String tutorialString = "You are about to play a memory game patterned after a famous toy.\n\n" +
            "The game will play a sequence, starting with a length of 1. You will then click the \n" +
            "respective buttons to repeat the sequence. Each time you successfully complete the \n" +
            "sequence, you will score a point, and it will increase in length by 1. You will \n" +
            "repeat this process until you repeat the sequence incorrectly. Each round you will \n" +
            "see the full sequence.\n\n" +
            "The game will have sound, so please be sure the sounds on your machine are enabled. \n\n" +
            "You get 3 tries.";

    public final Skin tempSkin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));

    private TextButton submitButton;

    public Tutorial(final Game game, final Config config) {
        this.game = game;
        stage = new Stage();
        Table table = new Table();
        table.setFillParent(true);

//        table.setDebug(true);

        Label tutorialLabel = new Label(tutorialString, tempSkin);

        submitButton = new TextButton("Play the Game", tempSkin);
        submitButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Random random = new Random();
                game.setScreen(new GameScreen(game, config, 0, 0));
            }
        });

        helloLabel = new Label("Tutorial", tempSkin) ;


        Table headerTable = new Table(tempSkin);
        headerTable.add(helloLabel);

        Table formTable = new Table(tempSkin);
        formTable.add(tutorialLabel);

//        formTable.setDebug(true);

        table.row();

        formTable.row();

        Table footerTable = new Table(tempSkin);
        footerTable.add(submitButton);

        table.add(headerTable);
        table.row();
        table.add(formTable);
        table.row();
        table.add(footerTable);

        stage.addActor(table);

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.input.setInputProcessor(stage);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
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

    }
}
