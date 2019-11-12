package br.com.dadokari;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball extends Entidade {

	private float direcaoBallX;
	private float direcaoBallY;

	public Ball(float posX, float posY, int largCorpo, int altCorpo, int cor, float velocidadeDoObjeto) {
		super(posX, posY, largCorpo, altCorpo, cor, velocidadeDoObjeto);
		anguloBall();

		// TODO Auto-generated constructor stub
	}

	public void tick() {
		direcaoBall();
		this.colisaoObjeto();

	}

	public void render(Graphics g) {
		super.render(g);
	}

	private void direcaoBall() {

		if (Game.gameController.getEstadoAtualGame() == ESTADOS_GAME.INGAME) {
			this.setPosicaoEixoX(this.getPosicaoEixoX() + (this.getVelocidadeDoObjeto() * direcaoBallX));
			this.setPosicaoEixoY(this.getPosicaoEixoY() + (this.getVelocidadeDoObjeto() * direcaoBallY));

		}

	}

	@Override
	public void colisaoObjeto() {
		// TODO Auto-generated method stub

		if (this.getPosicaoEixoY() + this.getAlturaCorpo() + (this.getVelocidadeDoObjeto() * direcaoBallY) > Game.game
				.getAlturaTela()) {
			direcaoBallY *= -1;

		} else if (this.getPosicaoEixoY() + this.getAlturaCorpo() + (this.getVelocidadeDoObjeto() * direcaoBallY) < 0) {
			direcaoBallY *= -1;

		}
		/*
		 * if (this.getPosicaoEixoX() + this.getLaguraCorpo() +
		 * (this.getVelocidadeDoObjeto() * direcaoBallX) > Game.game .getLaguraTela()) {
		 * direcaoBallX *= -1;
		 * 
		 * } else if (this.getPosicaoEixoX() + (this.getVelocidadeDoObjeto() *
		 * direcaoBallX) < 0) { direcaoBallX *= -1; }
		 */

		this.setColisaoCorpo(
				new Rectangle((int) (this.getPosicaoEixoX() + (this.getVelocidadeDoObjeto() * direcaoBallX)),
						(int) (this.getPosicaoEixoY() + (this.getVelocidadeDoObjeto() * direcaoBallY)),
						this.getLaguraCorpo(), this.getAlturaCorpo()));

		if (this.getColisaoCorpo().intersects(Game.player.getColisaoCorpo())) {
			anguloBall();
			if (this.getPosicaoEixoX() < Game.player.getPosicaoEixoX() + Game.player.getLaguraCorpo()) {
				direcaoBallX *= -1;
			}

		} else if (this.getColisaoCorpo().intersects(Game.enemy.getColisaoCorpo())) {

			anguloBall();
			if (this.getPosicaoEixoX() > Game.enemy.getPosicaoEixoX() + Game.enemy.getLaguraCorpo()) {
				direcaoBallX *= -1;
			}

		}

	}

	public void anguloBall() {
		int angulo = new Random().nextInt((90 - 35)) + 5 + 180;
		direcaoBallX = (float) Math.cos(Math.toRadians(angulo));
		direcaoBallY = (float) Math.sin(Math.toRadians(angulo));

	}
	/*
	 * private void colisaoBall() {
	 * 
	 * if (this.getPosicaoEixoY()+
	 * this.getLaguraCorpo()+(this.getVelocidadeDoObjeto() * direcaoBallY)>
	 * Game.game.getAlturaTela()) { direcaoBallY *=-1;
	 * 
	 * 
	 * }else if (this.getPosicaoEixoY() +(this.getVelocidadeDoObjeto() *
	 * direcaoBallY)< 0) { direcaoBallY *=-1;
	 * 
	 * } if ( this.getPosicaoEixoX() + this.getLaguraCorpo()+
	 * (this.getVelocidadeDoObjeto() * direcaoBallX) > Game.game.getLaguraTela()) {
	 * direcaoBallX *=-1;
	 * 
	 * }else if ( this.getPosicaoEixoX() + (this.getVelocidadeDoObjeto() *
	 * direcaoBallX)< 0 ) { direcaoBallX *=-1; }
	 * 
	 * }
	 */

}
