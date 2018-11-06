package dk.dtu.CDIT_Grp_43_matador.matador.util;

import java.awt.image.BufferedImage;

public class gameTextures {

    private static final int imageCropWidth = 50, imageCropHeight = 50;

    public static BufferedImage[] tileTextures;
    public static BufferedImage gameBackground;

    public static void createGameBoardTextures() {

        // Loading in textures for res
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet3.png"));
        gameBackground = ImageLoader.loadImage("/textures/bg.png");

        // Creating game textures

        tileTextures = new BufferedImage[12];

        tileTextures[0] = sheet.crop(0, 0,imageCropWidth, imageCropWidth);


    }


}
