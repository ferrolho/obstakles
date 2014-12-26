package game;

import states.MainMenuState;
import states.StateManager;
import utilities.FontManager;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Game extends ApplicationAdapter {

	public final static String TITLE = "Obstakles";
	public final static String CREDITS = "© 2014 Henrique Ferrolho";

	public static float GRAVITY;
	public static Vector2 screenDimension;

	ShapeRenderer shapeRenderer;
	SpriteBatch spriteBatch;
	FontManager fontManager;

	// Texture img;

	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		Gdx.input.setCatchBackKey(true);

		screenDimension = new Vector2();
		updateScreenDimension();
		GRAVITY = 0.002f * screenDimension.x;

		shapeRenderer = new ShapeRenderer();
		spriteBatch = new SpriteBatch();
		fontManager = new FontManager();
		// img = new Texture("badlogic.jpg");

		StateManager.changeState(new MainMenuState());
	}

	@Override
	public void resize(int width, int height) {
		updateScreenDimension();

		StateManager.resizeState(width, height);
	}

	@Override
	public void render() {
		StateManager.updateState();

		// sb.begin();
		// sb.draw(img, 0, 0);
		// sb.end();

		StateManager.renderState(shapeRenderer, spriteBatch);
	}

	@Override
	public void dispose() {
		spriteBatch.dispose();
		fontManager.dispose();
		StateManager.disposeState();
	}

	public static final void clearScreen(float red, float green, float blue,
			float alpha) {
		Gdx.gl.glClearColor(red, green, blue, alpha);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	private void updateScreenDimension() {
		screenDimension.x = Gdx.graphics.getWidth();
		screenDimension.y = Gdx.graphics.getHeight();
	}

}