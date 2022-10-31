package streak.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.Random;

public class SelfID implements Screen {
    private Game game;
    private Stage stage;

    private Label helloLabel;

    private SelectBox<String> ageBox;
    private Label ageLabel;

    private SelectBox<String> educationBox;
    private Label educationLabel;

    private SelectBox<Integer> gameActivityBox;
    private Label gameActivityLabel;

    private SelectBox<Integer> memoryBox;
    private Label memoryLabel;

    public final Config[] options = {
            new Config("skins/classic/classic.json", "sounds/simon-classic/", 4, true),
            new Config("skins/grayscale/grayscale.json", "sounds/gray/", 4, true),
            new Config("skins/animals/animals.json", "sounds/animals/", 4, true)
    };

    public final Skin tempSkin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));

    private TextButton submitButton;

    public SelfID(final Game game) {
        this.game = game;
        stage = new Stage();
        Table table = new Table();
        table.setFillParent(true);

//        table.setDebug(true);

        submitButton = new TextButton("Submit", tempSkin);
        submitButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Random random = new Random();
                game.setScreen(new Tutorial(game, options[random.nextInt(options.length)]));
            }
        });

        helloLabel = new Label("Welcome to this Simon Says, a Game to Test Your Memory. You are\n" +
                "participating in an unofficial research study. Please fill out some information \n" +
                "before you begin.", tempSkin) ;

        ageBox = new SelectBox<String>(tempSkin);
        ageBox.setItems("Under 18", "18-21", "22-25", "26-30", "31-35", "36-40", "41-50", "Over 50");

        ageLabel = new Label("What age range do you fit in?", tempSkin);

        educationBox = new SelectBox<String>(tempSkin);
        educationBox.setItems("Some High School", "High School/GED", "Some College", "Associate's\n" +
                "Degree", "Bachelor's Degree", "Pursuing Master's Degree", "Master's Degree", "Doctorate and Beyond");

        educationLabel = new Label("What level of education do you fit in?", tempSkin);

        gameActivityBox = new SelectBox<Integer>(tempSkin);
        gameActivityBox.setItems(0, 1, 2, 3, 4, 5, 6, 7);

        gameActivityLabel = new Label("On average, how many days \n" +
                "do you play video games in a week?", tempSkin);

        memoryBox = new SelectBox<Integer>(tempSkin);
        memoryBox.setItems(0, 1, 2, 3, 4, 5, 6, 7);

        memoryLabel = new Label("On average, how many days " +
                "a week\n do you try to improve your memory \nthrough memory games?", tempSkin);


        Table headerTable = new Table(tempSkin);
        headerTable.add(helloLabel).center();

        Table formTable = new Table(tempSkin);
//        formTable.setDebug(true);

        table.row();

        formTable.add(ageLabel).left();
        formTable.add(ageBox).left();
        formTable.row();

        formTable.add(educationLabel).left();
        formTable.add(educationBox).left();
        formTable.row();

        formTable.add(gameActivityLabel).left();
        formTable.add(gameActivityBox).left();
        formTable.row();

        formTable.add(memoryLabel).left();
        formTable.add(memoryBox).left();
        formTable.row();

        Table footerTable = new Table(tempSkin);
        footerTable.add(submitButton).left();

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
