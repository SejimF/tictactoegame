package kmaksim.tictactoe.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by Maksim1 on 03.02.2018.
 */

public class AssetDescriptors{

    public static final AssetDescriptor<Skin> UI_SKIN =
            new AssetDescriptor<Skin>(AssetsPaths.UI_SKIN, Skin.class);

    public static final AssetDescriptor<TextureAtlas> GAMEPLAY =
            new AssetDescriptor<TextureAtlas>(AssetsPaths.GAMEPLAY_ATLASS, TextureAtlas.class);

    public static final AssetDescriptor<BitmapFont> FONT =
            new AssetDescriptor<BitmapFont>(AssetsPaths.FONT, BitmapFont.class);

    public static final AssetDescriptor<Music> THEME_MUSIC =
            new AssetDescriptor<Music>(AssetsPaths.THEME_MUSIC, Music.class);

    public static final AssetDescriptor<Sound> X_SOUND =
            new AssetDescriptor<Sound>(AssetsPaths.X_SOUND, Sound.class);

    public static final AssetDescriptor<Sound> O_SOUND =
            new AssetDescriptor<Sound>(AssetsPaths.O_SOUND, Sound.class);

    public static final AssetDescriptor<Sound> CLICK =
            new AssetDescriptor<Sound>(AssetsPaths.CLICK, Sound.class);

    public static final AssetDescriptor<Music> WIN =
            new AssetDescriptor<Music>(AssetsPaths.WIN, Music.class);

    public static final AssetDescriptor<Music> DRAW =
            new AssetDescriptor<Music>(AssetsPaths.DRAW, Music.class);


    private AssetDescriptors(){}
}
