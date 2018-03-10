package kmaksim.tictactoe.desktop;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

/**
 * Created by Maksim1 on 24.01.2018.
 */

public class AssetPacker {

    private static final boolean DRAW_DEBAG_OUTLINE = false;

    private static final String RAW_ASSET_PATH = "desktop/assets-raw";
    private static final String ASSETS_PATH = "android/assets";

    public static void main(String[] args) {
        TexturePacker.Settings settings = new TexturePacker.Settings();
        settings.debug = DRAW_DEBAG_OUTLINE;
        settings.maxWidth = 2048;
        settings.maxHeight = 2048;


        TexturePacker.process(settings,
                RAW_ASSET_PATH + "/gameplay",
                ASSETS_PATH + "/gameplay",
                "gameplay"
        );

        TexturePacker.process(settings,
                RAW_ASSET_PATH + "/skin",
                ASSETS_PATH + "/ui",
                "uiskin"
        );
    }

}
