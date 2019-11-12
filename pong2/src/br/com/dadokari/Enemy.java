package br.com.dadokari;

import java.awt.Graphics;

public class Enemy extends Entidade {

	public int tempo;

	public Enemy(float posX, float posY, int largCorpo, int altCorpo, int cor, float velocidadeDoObjeto) {
		super(posX, posY, largCorpo, altCorpo, cor, velocidadeDoObjeto);
		// TODO Auto-generated constructor stub
	}

	public void tick() {

		//enemyIA();
		this.colisaoObjeto();
		this.pontoScoreEnemy();

	}

	public void render(Graphics g) {
		super.render(g);
	}

	private void enemyIA() {

		if (Game.gameController.getEstadoAtualGame()== ESTADOS_GAME.INGAME) {
			int espaçamentoDoEixo = 6;
			this.setPosicaoEixoY(
					(Game.ball.getPosicaoEixoY() - this.getPosicaoEixoY() - espaçamentoDoEixo) + this.getPosicaoEixoY());

		}
		
	}

	public void pontoScoreEnemy() {

		if (Game.ball.getPosicaoEixoX() < 0) {
			// System.out.println("ponto para o Enemy");
			Game.ball.setPosicaoEixoX(Game.game.getLaguraTela() / 2 - Game.ball.getLaguraCorpo());
			Game.ball.anguloBall();
			this.setScore(this.getScore() + 1);

		}
	}

}
