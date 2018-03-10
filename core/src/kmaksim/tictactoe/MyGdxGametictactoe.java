package kmaksim.tictactoe;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;

import kmaksim.tictactoe.assets.AssetDescriptors;
import kmaksim.tictactoe.common.Game_Manager;
import kmaksim.tictactoe.screens.loading.LoadingScreen;

public class MyGdxGametictactoe extends Game {

	private AssetManager assetManager;
	private SpriteBatch batch;

	
	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		assetManager = new AssetManager();
		assetManager.getLogger().setLevel(Logger.DEBUG);

		batch = new SpriteBatch();

		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		setScreen(new LoadingScreen(this));
	}

	@Override
	public void dispose () {
		assetManager.dispose();
		batch.dispose();
		System.exit(1);
	}

	public AssetManager getAssetManager() {
		return assetManager;
	}

	public SpriteBatch getBatch() {
		return batch;
	}


}
