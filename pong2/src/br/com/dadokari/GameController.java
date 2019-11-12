package br.com.dadokari;

public class GameController {

	private int pause;

	private ESTADOS_GAME estadoAtuaGame;
	private ESTADOS_GAME proximoEstadoGame;

	// Gets and sets

	public int getPause() {
		return this.pause;
	}

	public void setPause(int value) {
		this.pause = value;
	}

	public ESTADOS_GAME getEstadoAtualGame() {
		return this.estadoAtuaGame;
	}

	public void setEstadoAtualGame(ESTADOS_GAME value) {
		this.estadoAtuaGame = value;
	}

	public ESTADOS_GAME getProximoEstadoGame() {
		return this.proximoEstadoGame;
	}

	public void setProximoEstadoGame(ESTADOS_GAME value) {
		this.proximoEstadoGame = value;
	}

	public void mudeOEstadoGame(ESTADOS_GAME novoEstadoGame) {
		proximoEstadoGame = novoEstadoGame;
	}

	public GameController() {
		proximoEstadoGame = ESTADOS_GAME.INGAME;
		pause = 0;

	}

	public void tick() {

		
		isPause();
		estadoAtuaGame = proximoEstadoGame;

		switch (estadoAtuaGame) {
		case INGAME:
			//System.out.println(estadoAtuaGame);
			
			break;

		case ENDGAME:

			break;

		case PAUSE:
			
			break;

		case START:
			;

			break;

		}
	}

	public void isPause() {

		if (pause == 1) {

			mudeOEstadoGame(ESTADOS_GAME.PAUSE);
			//System.out.println(pause);

		} else if (pause == 0) {

			mudeOEstadoGame(ESTADOS_GAME.INGAME);
			//System.out.println(pause);

		}

	}

	public void controllerPause(int value) {
		pause += 1;
		if (pause >= 2) {
			pause = 0;
		}
	}

}
