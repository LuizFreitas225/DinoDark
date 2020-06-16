/********************************************
 Class: AGScene
 Description: used to represent a scene
 Author: Silvano Maneck Malfatti
 Date: 05/11/2013
 ********************************************/

//Engine Package
package dinos.sauro.dark.AndGraph;

//Used Packages
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class AGLayer
{
    //Class attributes
    public boolean autoRender = true;
    private AGVector2D offset = null;
    private AGVector2D speed = null;
    private AGFrameIndex[] vetBlocks = null;
    private int tileImageCode = 0;
    private int imageCode = 0;
    private boolean visible = false;
    private GL10 vrOpenGL = null;
    private FloatBuffer[] vetTextures = null;
    private float[] vetCoords = null;
    private float fCoordX1 = 0;
    private float fCoordY1 = 0;
    private float fCoordX2 = 0;
    private float fCoordY2 = 0;
    private int iTotalBlocksWidth = 0;
    private int iTotalBlocksHeight = 0;
    private int iFrameWidth = 0;
    private int iFrameHeight = 0;
    private int iImageWidth = 0;
    private int iImageHeight = 0;
    private int iTotalLinTile = 0;
    private int iTotalColTile = 0;

    //DESENHO DA LAYER
    private int xBlock = 0;
    private int yBlock = 0;
    private double xPosition = 0.0f;
    private double offsetX = 0.0;
    private double offsetY = 0.0;
    private double layerSizeX = 0.0;
    private double layerSizeY = 0.0;

    /***********************************************************
     *Name: JGLayer
     *Description: constructor
     *Parameters: JGGameManager, JGVector2D, JGVector2D
     *Return: none
     ************************************************************/
    public AGLayer(int imageCode, int frameWidth, int frameHeight, int layerWidth, int layerHeight, GL10 vrOpenGL)
    {
        this.vrOpenGL = vrOpenGL;

        this.imageCode = imageCode;

        iTotalBlocksWidth = layerWidth;
        iTotalBlocksHeight = layerHeight;

        speed = new AGVector2D();
        offset = new AGVector2D();
        visible = true;
        autoRender = true;

        vetBlocks = new AGFrameIndex[(iTotalBlocksWidth * iTotalBlocksHeight)];

        tileImageCode = AGTextureManager.loadTexture(vrOpenGL, imageCode);

        iImageWidth = 	AGTextureManager.getTextureData(imageCode).iWidth;
        iImageHeight = 	AGTextureManager.getTextureData(imageCode).iHeight;
        iFrameWidth = frameWidth;
        iFrameHeight = frameHeight;

        generateFrames();
    }

    /*******************************************
     * Name: setScreenPercent()
     * Description: usedo to calcule the size of sprite over a screen percent
     * Parameters: int, int
     * Returns: none
     ******************************************/
    public void setScreenPercent(int pWidth, int pHeight)
    {
        iFrameWidth = (AGScreenManager.iScreenWidth * pWidth) / 100;
        iFrameHeight = (AGScreenManager.iScreenHeight * pHeight) / 100;
    }

    /***********************************************************
     *Name: getTotalBlocks
     *Description: returns the total of blocks in the layer
     *Parameters: none
     *Return: int
     ************************************************************/
    public int getTotalBlocks()
    {
        return vetBlocks.length;
    }

    /***********************************************************
     *Name: getTileImageCode
     *Description: returns code of tileimage
     *Parameters: none
     *Return: int
     ************************************************************/
    public int getTileImageCode()
    {
        return tileImageCode;
    }

    /*******************************************
     * Name: reloadTileImageTexture()
     * Description: reload a texture image id
     * Parameters: GL10
     * Returns: none
     ******************************************/
    public void reloadTileImageTexture(GL10 pOpenGL)
    {
        vrOpenGL = pOpenGL;
        tileImageCode = AGTextureManager.loadTexture(vrOpenGL, imageCode);
    }

    /***********************************************************
     *Name: getOffset
     *Description: returns the offset of the layer
     *Parameters: none
     *Return: JGVector2D
     ************************************************************/
    public AGVector2D getOffset()
    {
        return offset;
    }

    /***********************************************************
     *Name: setFrameIndex
     *Description:sets the frame index by a specific position
     *Parameters: int, int
     *Return: none
     ************************************************************/
    public void setFrameIndex(int index, int frameIndex)
    {
        vetBlocks[index] = new AGFrameIndex();
        vetBlocks[index].setFrameIndex(frameIndex);
    }

    /***********************************************************
     *Name: getFrameIndexByPosition
     *Description:returns the frame to the position index
     *Parameters: int
     *Return: int
     ************************************************************/
    public int getFrameIndexByPosition(int position)
    {
        return vetBlocks[position].getFrameIndex();
    }

    /***********************************************************
     *Name: setVisible
     *Description: configures the visibility of the layer
     *Parameters: boolean
     *Return: void
     ************************************************************/
    public void setVisible(boolean pVisivel)
    {
        visible = pVisivel;
    }

    /***********************************************************
     *Name: getVisible
     *Description: getter of visibility of the layer
     *Parameters: none
     *Return: boolean
     ************************************************************/
    boolean getVisible()
    {
        return visible;
    }

    /***********************************************************
     *Name: setOffset
     *Description: set the layer offset
     *Parameters: JGVector2D
     *Return: void
     ************************************************************/
    public void setOffset(AGVector2D offset)
    {
        this.offset = offset;
    }

    /***********************************************************
     *Name: setSpeed
     *Description: set the layer speed
     *Parameters: JGVector2D
     *Return: void
     ************************************************************/
    public void setSpeed(AGVector2D speed)
    {
        this.speed = speed;
    }

    /***********************************************************
     *Name: scrollLayer
     *Description: scroll the layer with the current speed
     *Parameters: none
     *Return: void
     ************************************************************/
    public void scrollLayer()
    {
        offset.setX(offset.getX() + speed.getX());
        offset.setY(offset.getY() + speed.getY());
    }

    /***********************************************************
     *Name: generateFrames
     *Description: generate the texture coord frames
     *Parameters: none
     *Return: void
     ************************************************************/
    private void generateFrames()
    {
        iTotalLinTile = iImageHeight / iFrameHeight;
        iTotalColTile = iImageWidth / iFrameWidth;

        vetCoords = new float[8];
        vetTextures = new FloatBuffer[iTotalLinTile * iTotalColTile];

        for (int iIndex = 0; iIndex < vetTextures.length; iIndex++)
        {
            //Calc the frame coords
            fCoordX1 = (iIndex % iTotalColTile) * (1.0f / iTotalColTile);
            fCoordY1 = (iIndex / iTotalColTile) * (1.0f / iTotalLinTile);
            fCoordX2 = fCoordX1 + 1.0f / iTotalColTile;
            fCoordY2 = fCoordY1 + 1.0f / iTotalLinTile;

            //Normal mirror
            vetCoords[0] = fCoordX1;
            vetCoords[1] = fCoordY2;
            vetCoords[2] = fCoordX1;
            vetCoords[3] = fCoordY1;
            vetCoords[4] = fCoordX2;
            vetCoords[5] = fCoordY2;
            vetCoords[6] = fCoordX2;
            vetCoords[7] = fCoordY1;
            vetTextures[iIndex] = AGNioBuffer.generateNioBuffer(vetCoords);
        }
    }

    /***********************************************************
     *Name: drawBlock
     *Description: method used to draw a specific Block of layer
     *Parameters: int, int, int
     *Return: void
     ************************************************************/
    private void drawBlock(int frameIndex, int x, int y)
    {
        vrOpenGL.glTexCoordPointer(2, GL10.GL_FLOAT, 0, vetTextures[frameIndex]);

        //Sign texture and call OpenGL draw methods
        vrOpenGL.glBindTexture(GL10.GL_TEXTURE_2D, tileImageCode);
        vrOpenGL.glLoadIdentity();
        vrOpenGL.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        vrOpenGL.glTranslatef(x,  y, 0);
        vrOpenGL.glScalef(iFrameWidth, iFrameHeight, 1.0f);
        vrOpenGL.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
    }

    /***********************************************************
     *Name: render()
     *Description: create a layer based in a color image
     *Parameters: none
     *Return: void
     ************************************************************/
    public void  render()
    {
        //Retorna se a layer n�o estiver vis�vel
        if (!visible)
        {
            return;
        }

        if (vetBlocks == null)
        {
            return;
        }

        scrollLayer();

        xBlock = 0;
        yBlock = 0;
        xPosition = 0.0f;
        offsetX = offset.getX();
        offsetY = offset.getY();
        layerSizeX = iTotalBlocksWidth;
        layerSizeY = iTotalBlocksHeight;

        //Calcula o inicio da layer em x caso offset seja menor que zero
        if (offsetX > 0)
        {
            int mult = (int)Math.ceil(Math.abs(offsetX) / iFrameWidth);
            xBlock = ((int)layerSizeX - ((mult % (int)layerSizeX))) == (int)layerSizeX ? 0 : ((int)layerSizeX - ((mult % (int)layerSizeX)));
            offsetX -= mult * iFrameWidth;
        }
        //Guarda o inicio do offset e o brick inicial em X
        xPosition = offsetX;

        //Calcula o inicio da layer em y caso offset seja menor que zero
        if (offsetY > 0)
        {
            int mult = (int)Math.ceil(Math.abs(offsetY) / iFrameHeight );
            yBlock = ((int)layerSizeY - ((mult % (int)layerSizeY))) == (int)layerSizeY ? 0 : ((int)layerSizeY - ((mult % (int)layerSizeY)));
            offsetY -= mult * iFrameHeight;
        }

        //Desenha todos os bricks da layer
        for(int iStartX = xBlock; offsetY < AGScreenManager.iScreenHeight;
            yBlock = (yBlock+1)%(int)layerSizeY,
                    offsetY += iFrameHeight,
                    xBlock = iStartX,offsetX = (int)xPosition)
        {
            for( ; offsetX < AGScreenManager.iScreenWidth;
                 xBlock = (xBlock+1)%(int)layerSizeX, offsetX += iFrameWidth)
            {
                int blockIndex = xBlock + (yBlock * (int)layerSizeX);

                if (vetBlocks[blockIndex] != null)
                {
                    drawBlock(vetBlocks[blockIndex].getFrameIndex(), (int) offsetX, (int) offsetY);
                }
            }
        }
    }

    /*******************************************
     * Name: release
     * Description: free resources
     * Parameters: none
     * Returns: none
     ******************************************/
    public void release()
    {
        AGTextureManager.release(tileImageCode);
        vetBlocks = null;
        offset = null;
        speed = null;
        tileImageCode = 0;
        vrOpenGL = null;
        vetTextures = null;
        vetCoords = null;
    }

}
