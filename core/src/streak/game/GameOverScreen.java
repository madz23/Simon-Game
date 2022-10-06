package streak.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class GameOverScreen implements Screen {

    private Skin skin;
    private Game game;

    private Table table;
    private Stage stage;

    public GameOverScreen(Game game, Skin skin) {
        this.skin = skin;
        this.game = game;

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        table = new Table(this.skin);
//        table.setDebug(true);
        table.setFillParent(true);

        table.add(new Label("Game Over", skin, "title-white"));


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
