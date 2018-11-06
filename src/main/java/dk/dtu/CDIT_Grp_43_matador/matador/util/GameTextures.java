package dk.dtu.CDIT_Grp_43_matador.matador.util;

import java.awt.image.BufferedImage;

public class GameTextures {

    private static final int imageCropWidth = 100, imageCropHeight = 100;

    public static BufferedImage[] tileTextures;
    public static BufferedImage gameBackground;

    public static void createGameBoardTextures() {

        // Loading in textures for res

        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("monopolyGameTileSheet.png"));
        gameBackground = ImageLoader.loadImage("paintGameBoard.png");

        // Creating game textures

        tileTextures = new BufferedImage[12];
        tileTextures[0] = sheet.crop(0,0,imageCropWidth,imageCropWidth);


    }


}
