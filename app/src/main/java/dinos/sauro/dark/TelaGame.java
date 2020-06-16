package dinos.sauro.dark;

import java.util.ArrayList;
import java.util.Random;

import dinos.sauro.dark.AndGraph.*;

public class TelaGame extends AGScene {
    //UM PARA CADA TIPO DE OBSTACULO
    ArrayList<AGSprite> vetObstaculos1 = null;
    ArrayList<AGSprite> vetObstaculos2 = null;
    ArrayList<AGSprite> vetObstaculos4 = null;
    AGSprite dino= null;
    AGSprite passaro=null;
    AGLayer backGround=null;
    AGLayer solo= null;
    AGLayer blackFundo= null;
    AGTimer timerPulo= null;
    AGTimer delayPulos= null;
    AGTimer delayObstaculo= null;
    int spriteObstaculo;
    //DEFINE A VELOCIDADE DO SOLO E DOS OBSTACULOS
    int velocidade=-15;
    Random gerador = new Random();

    //ATUALIZA A POSIÇÃO E CHECA COLISAO
    private  void atualizaObastaculoColisao(){
        for (AGSprite obstaculo: vetObstaculos1){
            obstaculo.vrPosition.setX(obstaculo.vrPosition.getX() +
                 velocidade    );
            if(dino.collide(obstaculo)){
                return;
            }
        }
        for (AGSprite obstaculo: vetObstaculos2) {
            obstaculo.vrPosition.setX(obstaculo.vrPosition.getX() +
                    velocidade);
            if (dino.collide(obstaculo)) {
                return;
            }
        }
        for (AGSprite obstaculo: vetObstaculos4) {
            obstaculo.vrPosition.setX(obstaculo.vrPosition.getX() +
                    velocidade);
            if (dino.collide(obstaculo)) {
                return;
            }
        }
    }
    private void criaObstaculo(){
        spriteObstaculo= gerador.nextInt(2);
        if (spriteObstaculo==0) {
            AGSprite novoSprite = null;
        /*    for (AGSprite obstaculo : vetObstaculos1) {
                if (obstaculo.vrPosition.getX() < -obstaculo.getSpriteWidth() / 2 ) {
                    novoSprite = obstaculo;
                    delayObstaculo.restart();
                    novoSprite.vrPosition.setX(AGScreenManager.iScreenWidth + obstaculo.getSpriteWidth() / 2);
                    /*FORLUMA QUE INVENTEI PARA O DINO FICAR SOBRE O TECIDO BLACK E NO LUGAR CERTO SOBRE  O SOLO/ RESPONSIVO/ 20%  É ONDE O SOLO SE ENCONTRA
                    novoSprite.vrPosition.setY((obstaculo.getSpriteHeight()) + (AGScreenManager.iScreenHeight * 10 / 100));
                    novoSprite.moveTo(4000 ,new AGVector2D(0,novoSprite.vrPosition.getY()));
                    break;
                }
            }
*/


            if (novoSprite == null) {
                //CARREGAR SPRITE E LAYERS
                novoSprite = createSprite(R.mipmap.spr_1_obstaculo, 1, 1);
                novoSprite.vrPosition.setX(AGScreenManager.iScreenWidth + dino.getSpriteWidth() / 2);
                //FORLUMA QUE INVENTEI PARA O DINO FICAR SOBRE O TECIDO BLACK
                //E NO LUGAR CERTO SOBRE  O SOLO/ RESPONSIVO
                //20%  É ONDE O SOLO SE ENCONTRA
                novoSprite.vrPosition.setY((novoSprite.getSpriteHeight()) + (AGScreenManager.iScreenHeight * 10 / 100));
                novoSprite.setScreenPercent(16, 32);
                novoSprite.addAnimation(1, false, 0, 0);
                novoSprite.moveTo(4000 ,new AGVector2D(0,novoSprite.vrPosition.getY()));
            }

        }
        /*
        if (spriteObstaculo==1) {
            AGSprite novoSprite = null;
            for (AGSprite obstaculo : vetObstaculos2) {
                if (obstaculo.vrPosition.getX() < -obstaculo.getSpriteWidth() / 2) {
                    novoSprite = obstaculo;
                    delayObstaculo.restart();
                    obstaculo.vrPosition.setX(AGScreenManager.iScreenWidth + obstaculo.getSpriteWidth() / 2);
                    //FORLUMA QUE INVENTEI PARA O DINO FICAR SOBRE O TECIDO BLACK
                    //E NO LUGAR CERTO SOBRE  O SOLO/ RESPONSIVO
                    //20%  É ONDE O SOLO SE ENCONTRA
                    obstaculo.vrPosition.setY((obstaculo.getSpriteHeight()) + (AGScreenManager.iScreenHeight * 10 / 100));
                    break;
                }
            }
            if (novoSprite == null) {
                //CARREGAR SPRITE E LAYERS
                novoSprite = createSprite(R.mipmap.spr_2_obstaculos, 1, 1);
                novoSprite.vrPosition.setX(AGScreenManager.iScreenWidth + dino.getSpriteWidth() / 2);
                //FORLUMA QUE INVENTEI PARA O DINO FICAR SOBRE O TECIDO BLACK
                //E NO LUGAR CERTO SOBRE  O SOLO/ RESPONSIVO
                //20%  É ONDE O SOLO SE ENCONTRA
                novoSprite.vrPosition.setY((novoSprite.getSpriteHeight()) + (AGScreenManager.iScreenHeight * 10 / 100));
                novoSprite.setScreenPercent(16, 32);
                novoSprite.addAnimation(1, false, 0, 0);
                novoSprite.moveTo(4000 ,new AGVector2D(0,novoSprite.vrPosition.getY()));
            }

        }
        if (spriteObstaculo==2) {
            AGSprite novoSprite = null;
            for (AGSprite obstaculo : vetObstaculos4) {
                if (obstaculo.vrPosition.getX() < -obstaculo.getSpriteWidth() / 2 ) {
                    novoSprite = obstaculo;
                    delayObstaculo.restart();
                    obstaculo.vrPosition.setX(AGScreenManager.iScreenWidth + obstaculo.getSpriteWidth() / 2);
                    //FORLUMA QUE INVENTEI PARA O DINO FICAR SOBRE O TECIDO BLACK
                    //E NO LUGAR CERTO SOBRE  O SOLO/ RESPONSIVO
                    //20%  É ONDE O SOLO SE ENCONTRA
                    obstaculo.vrPosition.setY((obstaculo.getSpriteHeight()) + (AGScreenManager.iScreenHeight * 10 / 100));
                    break;
                }
            }
            if (novoSprite == null) {
                //CARREGAR SPRITE E LAYERS
                novoSprite = createSprite(R.mipmap.spr_4_obstaculos, 1, 1);
                novoSprite.vrPosition.setX(AGScreenManager.iScreenWidth + dino.getSpriteWidth() / 2);
                //FORLUMA QUE INVENTEI PARA O DINO FICAR SOBRE O TECIDO BLACK
                //E NO LUGAR CERTO SOBRE  O SOLO/ RESPONSIVO
                //20%  É ONDE O SOLO SE ENCONTRA
                novoSprite.vrPosition.setY((novoSprite.getSpriteHeight()) + (AGScreenManager.iScreenHeight * 10 / 100));
                novoSprite.setScreenPercent(16, 32);
                novoSprite.addAnimation(1, false, 0, 0);
                novoSprite.moveTo(4000 ,new AGVector2D(0,novoSprite.vrPosition.getY()));
            }

        }

            */
    }



    private void sobePulo(){

        dino.moveTo(400,new AGVector2D( dino.vrPosition.getX() ,
                AGScreenManager.iScreenHeight /2 + AGScreenManager.iScreenHeight/4 ));
        dino.setCurrentAnimation(1);
        timerPulo.restart();
    }

    private void descePulo ()
    {
        if(timerPulo.isTimeEnded()) {
            if (dino.vrPosition.getY() > (dino.getSpriteHeight() / 2) + (AGScreenManager.iScreenHeight * 20 / 100) - 40) {
                dino.moveTo(200, new AGVector2D(dino.vrPosition.getX(), dino.getSpriteHeight() / 6 + AGScreenManager.iScreenHeight * 20 / 100));

            }
        }
      //MUDAR O ANIAMÇÃO CONFOME O DINO VOLTA A SUA POSICAO ORIGINAL
        if(dino.vrPosition.getY() <=(dino.getSpriteHeight()/6)  + ( AGScreenManager.iScreenHeight * 20 /100)   ){
            dino.setCurrentAnimation(0);
    }

    }




    @Override
    public void init() {
        //CARREGAR SPRITE E LAYERS
        passaro = createSprite(R.mipmap.passaro, 1, 1);
        passaro.vrPosition.setX(AGScreenManager.iScreenWidth +  passaro.getSpriteWidth() / 2);
        //FORLUMA QUE INVENTEI PARA O DINO FICAR SOBRE O TECIDO BLACK
        //E NO LUGAR CERTO SOBRE  O SOLO/ RESPONSIVO
        //20%  É ONDE O SOLO SE ENCONTRA
        passaro.vrPosition.setY(( passaro.getSpriteHeight()) + (AGScreenManager.iScreenHeight * 30 / 100));
        passaro.setScreenPercent(16, 32);
        passaro.addAnimation(1, false, 0, 0);
        passaro.moveTo(4000 ,new AGVector2D(0, passaro.vrPosition.getY()));


        dino= createSprite(R.mipmap.spr_dino_claro,6,1);
        //15% DA TELA SEÁ A POSIÇÃO DO DINO
        dino.vrPosition.setX( AGScreenManager.iScreenWidth *15/ 100);
        //FORLUMA QUE INVENTEI PARA O DINO FICAR SOBRE O TECIDO BLACK
        //E NO LUGAR CERTO SOBRE  O SOLO/ RESPONSIVO
        dino.vrPosition.setY((dino.getSpriteHeight()/6)  + ( AGScreenManager.iScreenHeight * 20 /100)   );
        dino.setScreenPercent(9,18);
        dino.addAnimation(13, true,2,3);
        dino.addAnimation(1, true,0,1);











        backGround= createLayer(R.mipmap.spr_retangular,1024,512,1,1);
        backGround.setScreenPercent(100,100);
        backGround.setFrameIndex(0,0);
        backGround.setSpeed(new AGVector2D( -3 ,0));
     //SOLO OCOPANDO A QUARTA LINHA DA INTERFACE
        solo = createLayer(R.mipmap.spr_solo,128,16,2,20);
        for (int index =6; index<8; index++){
            //if (index != 13 && index != 17) {
            solo.setFrameIndex(index, 0);
            //}
        }

        solo.setScreenPercent(50,5);
        solo.setSpeed(new AGVector2D(velocidade,0));

     //TECIDO BLACK OCUPANDO AS 3 PRIMEIRAS LINHAS DA INTERFACE / MESMA PRPOPORCÃO DO SOLO
        blackFundo = createLayer(R.mipmap.spr_black,128,16,2,20);
        for (int index =0; index<6; index++){
            //if (index != 13 && index != 17) {
            blackFundo.setFrameIndex(index, 0);
            //}
        }

        blackFundo.setScreenPercent(50,5);
        blackFundo.setSpeed(new AGVector2D(-2,0));

       timerPulo = new AGTimer(400);
       delayObstaculo= new AGTimer(2000);


    }


    @Override
    public void restart() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void loop() {
        timerPulo.update();
        delayObstaculo.update();
        descePulo();
        //PULAR QUANDO TELA FOR CLICADA
        if (AGInputManager.vrTouchEvents.screenClicked())
        {
            if(dino.vrPosition.getY()<= dino.getSpriteHeight()/6  + ( AGScreenManager.iScreenHeight * 20 /100)) {
            sobePulo();
        }
        }

        //VOLTANDO PARA SUA ANIMAÇAO DE CORRER APOS VOLTAR PARA O SOLO
        if (dino.vrPosition.getX() <= (dino.getSpriteHeight()/6)  + ( AGScreenManager.iScreenHeight * 20 /100)   ){
             dino.setCurrentAnimation(0);
        }

     //   if(delayObstaculo.isTimeEnded()){
            criaObstaculo();
     //   }

      //  atualizaObastaculoColisao();
    }
}
