/********************************************
 Class: AGScene
 Description: used to represent a scene
 Author: Silvano Maneck Malfatti
 Date: 05/11/2013
 ********************************************/

//Engine Package
package dinos.sauro.dark.AndGraph;

//Used packages
import java.util.ArrayList;
import javax.microedition.khronos.opengles.GL10;

public abstract class AGScene
{
	//Attibutes
	private ArrayList<AGSprite> vrSprites = null;
	private ArrayList<AGLayer> vrLayers = null;
	protected AGGameManager vrGameManager = null;
	public boolean bSceneStarted = false;
	public float fRed = 0.0f;
	public float fGreen = 0.0f;
	public float fBlue = 0.0f;

	//CONSTS
	private int FRAME_SIZE = 1;

	private float[] coordSprites = new float[]{
			-FRAME_SIZE, -FRAME_SIZE,
			-FRAME_SIZE,  FRAME_SIZE,
			FRAME_SIZE, -FRAME_SIZE,
			FRAME_SIZE,  FRAME_SIZE};

	private float[] coordLayers = new float[]{
			0, 0,
			0,  FRAME_SIZE,
			FRAME_SIZE,0,
			FRAME_SIZE,  FRAME_SIZE};
	
	/*******************************************
	* Name: CAGScene()
	* Description: Scene construtor
	* Parameters: CAGameManager
	* Returns: none
	******************************************/
	public AGScene()
	{
		//Instancia o vetor de sprites
		vrSprites = new ArrayList<AGSprite>();

		//Instancia o vetor de layers
		vrLayers = new ArrayList<AGLayer>();
	}
	
	//abstract methods
	public abstract void init();
	public abstract void restart();
	public abstract void stop();
	public abstract void loop();
	
	/*******************************************
	* Name: createSprite()
	* Description: insert a new sprite into scene sprite list
	* Parameters: int, int, int, int, int
	* Returns: CAGSprite
	******************************************/
	public AGSprite createSprite(int pCodImage, int pFrameWidth, int pFrameHeight, int pImageWidth, int pImageHeight)
	{
		AGSprite vrNewSprite = new AGSprite(AGGameManager.vrOpenGL, pCodImage, pFrameWidth, pFrameHeight, pImageWidth, pImageHeight);
		vrSprites.add(vrNewSprite);
		return vrNewSprite;
	}
	
	/*******************************************
	* Name: createSprite()
	* Description: insert a new sprite into scene sprite list
	* Parameters: int, int, int
	* Returns: CAGSprite
	******************************************/
	public AGSprite createSprite(int pCodImage, int pTotalCol, int pTotalLin)
	{
		AGSprite vrNewSprite = new AGSprite(AGGameManager.vrOpenGL, pCodImage, pTotalCol, pTotalLin);
		vrSprites.add(vrNewSprite);
		return vrNewSprite;
	}

	/*******************************************
	 * Name: createLayer()
	 * Description: insert a new layer into scene sprite list
	 * Parameters: int, int, int, int, int
	 * Returns: AGLayer
	 ******************************************/
	public AGLayer createLayer(int pCodImage, int blockWidth, int blockHeight, int totalBlocksWidth, int totalBlocksHeight)
	{
		AGLayer vrNewLayer = new AGLayer(pCodImage, blockWidth, blockHeight,totalBlocksWidth, totalBlocksHeight,  AGGameManager.vrOpenGL);
		vrLayers.add(vrNewLayer);
		return vrNewLayer;
	}
	
	/*******************************************
	* Name: setBackgroundColor()
	* Description: sets background color for scene
	* Parameters: float, float, float
	* Returns: void
	******************************************/
	public void setSceneBackgroundColor(float pRed, float pGreen, float pBlue)
	{
		fRed = pRed;
		fGreen = pGreen;
		fBlue = pBlue;
		AGGameManager.vrOpenGL.glClearColor(fRed, fGreen, fBlue, 1.0f);
	}
	
	/*******************************************
	* Name: render()
	* Description: Render scene elements
	* Parameters: none
	* Returns: none
	******************************************/
	public void render()
	{
		//Send the vertex array to OpenGL
		vrGameManager.vrOpenGL.glVertexPointer(2, GL10.GL_FLOAT, 0, AGNioBuffer.generateNioBuffer(coordLayers));
		for (AGLayer vrLayerList: vrLayers)
		{
			if (vrLayerList.autoRender)
			{
				vrLayerList.render();
			}
		}

		vrGameManager.vrOpenGL.glVertexPointer(2, GL10.GL_FLOAT, 0, AGNioBuffer.generateNioBuffer(coordSprites));
		for (AGSprite vrSpriteList:vrSprites)
		{
			if (vrSpriteList.bAutoRender)
			{
				vrSpriteList.render();
			}
		}
	}
	
	/********************************************
	* Name: removeSprite()
	* Description: remove a sprite from scene
	* Parameters: AGSprite
	* Returns: none
	******************************************/
	public void removeSprite(AGSprite pSprite)
	{
		for (int iIndex=vrSprites.size() - 1; iIndex >=0; iIndex--)
		{
			if (pSprite == vrSprites.get(iIndex))
			{
				vrSprites.get(iIndex).release();
				vrSprites.remove(iIndex);
				return;
			}
		}
	}
	
	/*******************************************
	* Name: release()
	* Description: clear all scene elements
	* Parameters: none
	* Returns: none
	******************************************/
	public void release()
	{
		for (int iIndex=0; iIndex < vrSprites.size(); iIndex++)
		{
			vrSprites.get(iIndex).release();
		}
		vrSprites.clear();

		for (int iIndex=0; iIndex < vrLayers.size(); iIndex++)
		{
			vrLayers.get(iIndex).release();
		}
		vrLayers.clear();
	}
	
	/*******************************************
	* Name: releaseSpritesTextures()
	* Description: clear all sprites textures
	* Parameters: none
	* Returns: none
	******************************************/
	public void releaseSpritesTextures()
	{
		for (int iIndex=0; iIndex < vrSprites.size(); iIndex++)
		{
			AGTextureManager.release(vrSprites.get(iIndex).getTextureCode());
		}
	}

	/*******************************************
	 * Name: releaseLayersTextures()
	 * Description: clear all Layers textures
	 * Parameters: none
	 * Returns: none
	 ******************************************/
	public void releaseLayersTextures()
	{
		for (int iIndex=0; iIndex < vrLayers.size(); iIndex++)
		{
			AGTextureManager.release(vrLayers.get(iIndex).getTileImageCode());
		}
	}

	/*******************************************
	* Name: reloadSpritesTextures()
	* Description: reload all sprite textures
	* Parameters: none
	* Returns: none
	******************************************/
	public void reloadSpritesTextures()
	{
		for (int iIndex=0; iIndex < vrSprites.size(); iIndex++)
		{
			vrSprites.get(iIndex).reloadTexture(AGGameManager.vrOpenGL);
		}
	}

	/*******************************************
	 * Name: reloadSpritesTextures()
	 * Description: reload all sprite textures
	 * Parameters: none
	 * Returns: none
	 ******************************************/
	public void reloadLayersTextures()
	{
		for (int iIndex=0; iIndex < vrLayers.size(); iIndex++)
		{
			vrLayers.get(iIndex).reloadTileImageTexture(AGGameManager.vrOpenGL);
		}
	}
}
