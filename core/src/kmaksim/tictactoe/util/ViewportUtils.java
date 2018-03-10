package kmaksim.tictactoe.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Maksim1 on 13.01.2018.
 */

public class ViewportUtils {

    private static final Logger log = new Logger(ViewportUtils.class.getName(), Logger.DEBUG);

    private static final int DEFAULT_CELL_SIZE = 1;

    public static void drawGrid(Viewport viewport, ShapeRenderer renderer) {
        drawGrid(viewport, renderer, DEFAULT_CELL_SIZE);
    }

    public static void drawGrid(Viewport viewport, ShapeRenderer renderer, int cellSize) {
        if (viewport == null) {
            throw new IllegalArgumentException("viewport required");
        }

        if (renderer == null) {
            throw new IllegalArgumentException("renderer required");
        }

        if (cellSize < DEFAULT_CELL_SIZE) {
            cellSize = DEFAULT_CELL_SIZE;
        }

        Color oldColor = new Color(renderer.getColor());

        int worldWidth = (int) viewport.getWorldWidth();
        int worldHeigth = (int) viewport.getWorldHeight();
        int doubleWorldWidth = worldWidth * 2;
        int doubleWorldHeigth = worldHeigth * 2;

        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.WHITE);

        //draw vertycal lines
        for (int x = -doubleWorldWidth; x < doubleWorldWidth; x += cellSize) {
            renderer.line(x, -doubleWorldHeigth, x, doubleWorldHeigth);
        }

        //draw horizontal lines
        for (int y = -doubleWorldHeigth; y < doubleWorldHeigth; y += cellSize) {
            renderer.line(-doubleWorldWidth, y, doubleWorldWidth, y);
        }

        //drawing X and Y lines

        renderer.setColor(Color.RED);
        renderer.line(0, -doubleWorldHeigth, 0, doubleWorldHeigth);
        renderer.line(-doubleWorldWidth, 0, doubleWorldWidth, 0);

        //draw border lines

        renderer.setColor(Color.GREEN);
        renderer.line(0, worldHeigth, worldWidth, worldHeigth);
        renderer.line(worldWidth, 0, worldWidth, worldHeigth);
        renderer.end();
        renderer.setColor(oldColor);
    }

    public static void debugPixelPerUnit(Viewport viewport) {
        if (viewport == null) {
            throw new IllegalArgumentException("Viewport required");
        }

        float screenWidth = viewport.getScreenWidth();
        float screenHeigth = viewport.getScreenHeight();

        float worldWidth = viewport.getWorldWidth();
        float worldHeigth = viewport.getWorldHeight();

        //PPU => pixels per world unit
        float xPPU = screenWidth / worldWidth;
        float yPPU = screenHeigth / worldHeigth;

        log.debug("xPPU: " + xPPU + " yPPU: " + yPPU);

    }

    private ViewportUtils() {

    }
}
