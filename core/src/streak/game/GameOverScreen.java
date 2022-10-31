package streak.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.Random;

public class GameOverScreen implements Screen {

    private Skin skin;
    private Game game;

    private Table table;
    private Stage stage;

    private TextButton okButton;
    private TextButton tryAgainButton;

    public GameOverScreen(final Game game, Skin skin, final GameScreen gameScreen) {
        this.skin = skin;
        this.game = game;

        Skin tempSkin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        table = new Table(this.skin);
//        table.setDebug(true);
        table.setFillParent(true);

        okButton = new TextButton("Exit", tempSkin);
        tryAgainButton = new TextButton("Try Again", tempSkin);

        okButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        tryAgainButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game, gameScreen.getConfig(), gameScreen.getTries(), gameScreen.getBestScore()));
            }
        });

        table.add(new Label("Game Over", skin, "title-white"));
        table.row();
        table.add(new Label(gameScreen.getTries() + "/3 tries", skin, "title-white"));
        table.row();
        table.add(new Label("Your best score was: " + gameScreen.getBestScore(), skin, "title-white"));
        table.row();

        if (gameScreen.getTries() < 3) {
            table.add(tryAgainButton);
        } else { table.add(okButton); }


        stage.addActor(table);

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
        stage.dispose();
    }
}
