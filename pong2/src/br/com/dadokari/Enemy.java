package br.com.dadokari;

import java.awt.Color;
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
    
	@Override
	public void render(Graphics g) {

		g.setColor(new Color(192,217,217));
		g.fillRect((int) this.getPosicaoEixoX(), (int) this.getPosicaoEixoY(), this.getLaguraCorpo(), this.getAlturaCorpo());
		g.setColor(Color.RED);
		g.fillRect((int)this.getPosicaoEixoX() + 6, (int)this.getPosicaoEixoY() + 4, this.getLaguraCorpo()/2, this.getAlturaCorpo());
	}

	private void enemyIA() {

		if (Game.gameController.getEstadoAtualGame() != ESTADOS_GAME.PAUSE) {

			int espaçamentoDoEixo = 6;

			this.setPosicaoEixoY((Game.ball.getPosicaoEixoY() - this.getPosicaoEixoY() - espaçamentoDoEixo)
					+ this.getPosicaoEixoY());

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
