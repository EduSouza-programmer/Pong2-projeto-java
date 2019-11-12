package br.com.dadokari;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Player extends Entidade {

	public Player(float posX, float posY, int largCorpo, int altCorpo, int cor, float velocidadeDoObjeto) {
		super(posX, posY, largCorpo, altCorpo, cor, velocidadeDoObjeto);
		tempoParaPiscarScore = new Tempo();
		// TODO Auto-generated constructor stub

	}

	private Tempo tempoParaPiscarScore;

	private boolean up;
	private boolean down;

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean up) {
		this.down = up;
	}

	@Override
	public void tick() {

		this.pontoScorePlayer();
		controlesCimaBaixo();
		this.colisaoObjeto();
		this.getEntidadeController().tick();

		// System.out.println(this.getPosicaoEixoX() + this.getLaguraCorpo());
		// System.out.println(Game.game.getLaguraTela() - this.getLaguraCorpo());
	}

	@Override
	public void render(Graphics g) {

		super.render(g);

		if (this.getEntidadeController().getEstadoAtualEntidade() == ESTADOS_ENTIDADE.CHUTANDO && this.podeChutar()) {
			g.setColor(Color.MAGENTA);
			g.fillRect((int) this.getPosicaoEixoX() + 5, (int) (this.getPosicaoEixoY() + 10), 5, 10);
		}

		if (Game.gameController.getEstadoAtualGame() != ESTADOS_GAME.PONTOPLAYER) {
			g.setFont(new Font("arial", Font.BOLD, 10));
			g.setColor(Color.white);
			g.drawString(String.valueOf(this.getScore()), (int) (this.getPosicaoEixoX() - 20), 11);

		}else if (Game.gameController.getEstadoAtualGame() == ESTADOS_GAME.PONTOPLAYER) {
			
			if (tempoParaPiscarScore.acaoCadaTempoLoop(false, 5, 0.1f, 0.4f)) {
				g.setFont(new Font("arial", Font.BOLD, 10));
				g.setColor(Color.red);
				g.drawString(String.valueOf(this.getScore()), (int) (this.getPosicaoEixoX() - 20), 11);

				if (tempoParaPiscarScore.getAcaoCadaTempoLoopVar() == 5) {
					Game.gameController.mudeOEstadoGame(ESTADOS_GAME.INGAME);
					tempoParaPiscarScore.setAcaoCadaTempoLoopVar(0);

				}
			}

		}

	}

	private void controlesCimaBaixo() {

		if (Game.gameController.getEstadoAtualGame() != ESTADOS_GAME.PAUSE) {
			if (up) {

				this.setPosicaoEixoY((this.getPosicaoEixoY() - getVelocidadeDoObjeto()));
			} else if (down) {
				this.setPosicaoEixoY((this.getPosicaoEixoY() + getVelocidadeDoObjeto()));
			}
		}

	}

	public void pontoScorePlayer() {

		if (Game.ball.getPosicaoEixoX() > Game.game.getLaguraTela()) {

			Game.gameController.mudeOEstadoGame(ESTADOS_GAME.PONTOPLAYER);
			Game.ball.setPosicaoEixoX(Game.game.getLaguraTela() / 2 - Game.ball.getLaguraCorpo());
			Game.ball.anguloBall();
			this.setScore(this.getScore() + 1);
			

		}
	}

	/*
	 * private void colisaoPlayer() {
	 * 
	 * if (this.getPosicaoEixoY() + this.getAlturaCorpo() >
	 * Game.game.getAlturaTela()) {
	 * 
	 * this.setPosicaoEixoY(Game.game.getAlturaTela() - this.getAlturaCorpo());
	 * 
	 * }else if (this.getPosicaoEixoX() <=0) { this.setPosicaoEixoX(0); }
	 * 
	 * }
	 */

}
