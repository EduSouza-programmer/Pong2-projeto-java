package br.com.dadokari;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Entidade {

	private int larguraCorpo;
	private int alturaCorpo;
	private float posicaoEixoX;
	private float posicaoEixoY;
	private float velocidadeDoObjeto;
	private Color cor;
	private Rectangle colisaoCorpo;
	private int score;
	private EntidadesController entidadeController;

	// Get & Set

	public EntidadesController getEntidadeController() {
		return this.entidadeController;
	}

	public void setEntidadeController(EntidadesController value) {
		this.entidadeController = value;
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int value) {
		this.score = value;
	}

	public Rectangle getColisaoCorpo() {
		return this.colisaoCorpo;
	}

	public void setColisaoCorpo(Rectangle value) {
		this.colisaoCorpo = value;
	}

	public float getVelocidadeDoObjeto() {
		return this.velocidadeDoObjeto;
	}

	public void setVelocidadeDoObjeto(float value) {
		this.velocidadeDoObjeto = value;
	}

	public int getLaguraCorpo() {
		return this.larguraCorpo;
	}

	public void setLarguCorpo(int value) {
		this.larguraCorpo = value;

	}

	public int getAlturaCorpo() {
		return this.alturaCorpo;
	}

	public void setAlturaCorpo(int value) {
		this.alturaCorpo = value;
	}

	public float getPosicaoEixoX() {
		return this.posicaoEixoX;
	}

	public void setPosicaoEixoX(float value) {

		this.posicaoEixoX = value;
	}

	public float getPosicaoEixoY() {
		return this.posicaoEixoY;

	}

	public void setPosicaoEixoY(float value) {
		this.posicaoEixoY = value;
	}

	public Color getCor() {
		return this.cor;
	}

	public void setCor(Color value) {
		this.cor = value;
	}

	public Entidade(float posX, float posY, int largCorpo, int altCorpo, int cor, float velocidadeDoObjeto) {

		entidadeController = new EntidadesController();
		this.posicaoEixoX = posX;
		this.posicaoEixoY = posY;
		this.larguraCorpo = largCorpo;
		this.alturaCorpo = altCorpo;
		this.velocidadeDoObjeto = velocidadeDoObjeto;
		this.cor = new Color(cor);
		score = 0;

	}

	public Entidade(int largCorpo, int altCorpo, int cor) {
		entidadeController = new EntidadesController();
		this.larguraCorpo = largCorpo;
		this.alturaCorpo = altCorpo;
		this.cor = new Color(cor);

	}

	public void tick() {

	}

	public void render(Graphics g) {
		g.setColor(cor);
		g.fillRect((int) posicaoEixoX, (int) posicaoEixoY, larguraCorpo, alturaCorpo);

	}

	public void colisaoObjeto() {

		/*
		 * if (this.getPosicaoEixoY() + this.getAlturaCorpo() >
		 * Game.game.getAlturaTela()) {
		 * 
		 * this.setPosicaoEixoY(Game.game.getAlturaTela() - this.getAlturaCorpo());
		 * 
		 * } else if (this.getPosicaoEixoY() <= 0) { this.setPosicaoEixoY(0); }
		 */
		if (posicaoEixoY + alturaCorpo > Game.game.getAlturaTela()) {
			posicaoEixoY = Game.game.getAlturaTela() - alturaCorpo;

		} else if (posicaoEixoY <= 0) {
			posicaoEixoY = 0;
		}
		colisaoCorpo = new Rectangle((int) posicaoEixoX, (int) posicaoEixoY, larguraCorpo, alturaCorpo);

	}

}
