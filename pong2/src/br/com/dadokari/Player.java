package br.com.dadokari;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Player extends Entidade {

	public Player(float posX, float posY, int largCorpo, int altCorpo, int cor, float velocidadeDoObjeto) {
		super(posX, posY, largCorpo, altCorpo, cor, velocidadeDoObjeto);
		tempo = new Tempo();
		// TODO Auto-generated constructor stub

	}

	public Tempo tempo;

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
		if (this.getEntidadeController().getEstadoAtualEntidade() != ESTADOS_ENTIDADE.PONTO) {
			g.setFont(new Font("arial", Font.BOLD, 10));
			g.setColor(Color.white);
			g.drawString(String.valueOf(this.getScore()), (int) (this.getPosicaoEixoX() - 20), 11);
		} else if (this.getEntidadeController().getEstadoAtualEntidade() == ESTADOS_ENTIDADE.PONTO) {
			if (tempo.acaoCadaTempoLoop(false, 5, 0.1f, 0.4f)) {
				g.setFont(new Font("arial", Font.BOLD, 10));
				g.setColor(Color.white);
				g.drawString(String.valueOf(this.getScore()), (int) (this.getPosicaoEixoX() - 20), 11);
				if (tempo.getAcaoCadaTempoLoopVar() == 5) {
					this.getEntidadeController().mudarEstado(ESTADOS_ENTIDADE.ATIVO);
					tempo.setAcaoCadaTempoLoopVar(0);

				}
			}

		}

	}

	private void controlesCimaBaixo() {

		if (Game.gameController.getEstadoAtualGame() == ESTADOS_GAME.INGAME) {
			if (up) {

				this.setPosicaoEixoY((this.getPosicaoEixoY() - getVelocidadeDoObjeto()));
			} else if (down) {
				this.setPosicaoEixoY((this.getPosicaoEixoY() + getVelocidadeDoObjeto()));
			}
		}

	}

	public void pontoScorePlayer() {

		if (Game.ball.getPosicaoEixoX() > Game.game.getLaguraTela()) {
			this.getEntidadeController().mudarEstado(ESTADOS_ENTIDADE.PONTO);
			System.out.println("ponto do jogador");
			System.out.println(this.getEntidadeController().getEstadoAtualEntidade());
			Game.ball.setPosicaoEixoX(Game.game.getLaguraTela() / 2 - Game.ball.getLaguraCorpo());
			Game.ball.anguloBall();
			this.setScore(this.getScore() + 1);
			System.out.println(this.getEntidadeController().getEstadoAtualEntidade());

		}
	}

	private boolean podeAnimar() {

		if (this.getEntidadeController().getEstadoAtualEntidade() == ESTADOS_ENTIDADE.PONTO) {
			System.out.println("foi diferente");

			return true;

		}
		return false;

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
