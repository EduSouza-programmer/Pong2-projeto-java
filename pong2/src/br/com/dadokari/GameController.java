package br.com.dadokari;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.text.StyledEditorKit.BoldAction;

public class GameController {

	private boolean pause;

	private ESTADOS_GAME estadoAtuaGame;
	private ESTADOS_GAME proximoEstadoGame;
	private Tempo tempoParaRenderizarPause;

	// Gets and sets

	public boolean isPause() {
		return this.pause;
	}

	public void setPause(boolean value) {
		this.pause = value;
	}

	public ESTADOS_GAME getEstadoAtualGame() {
		return this.estadoAtuaGame;
	}

	public void mudeOEstadoGame(ESTADOS_GAME novoEstadoGame) {
		proximoEstadoGame = novoEstadoGame;
	}

	public GameController() {
		proximoEstadoGame = ESTADOS_GAME.INGAME;
		tempoParaRenderizarPause = new Tempo();
		pause = false;

	}

	public void tick() {

		estadoAtuaGame = proximoEstadoGame;

		switch (estadoAtuaGame) {
		case INGAME:
			// System.out.println(estadoAtuaGame);

			break;

		case ENDGAME:

			break;

		case PAUSE:

			break;

		case START:

			break;

		case PONTOPLAYER:

			break;

		case PONTOENEMY:

			break;

		default:

			break;

		}
	}

	public void render(Graphics g) {
		renderizarPause(g);

	}

	private void renderizarPause(Graphics g) {

		if (estadoAtuaGame == ESTADOS_GAME.PAUSE) {

			g.setFont(new Font("arial", Font.BOLD, 20));
			g.setColor(Color.white);
			g.drawString("Pause", (Game.game.getLaguraTela() / 2) - 28, (Game.game.getAlturaTela() / 2) + 5);
			if(tempoParaRenderizarPause.acaoCadaTempoLoop(true, 0, 0.3f, 0.5f)) {
				g.setFont(new Font("arial", Font.BOLD, 20));
				g.setColor(Color.MAGENTA);
				g.drawString("Pause", (Game.game.getLaguraTela() / 2) - 28, (Game.game.getAlturaTela() / 2) + 5);
				
			}
		}

	}

}
