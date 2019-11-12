package br.com.dadokari;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static Game game;
	// Janela
	public static JFrame frameJanela;
	private int laguraTela;
	private int alturaTela;
	private int scaleTela;
	private BufferedImage imageFundoDoJogo;

	// Loop Game (Update)
	private Thread thread;
	private boolean isRunning;

	// Entidades
	public static Player player;
	public static Ball ball;
	public static Enemy enemy;

	// Controller
	public static GameController gameController;

	// Tempo

	// Get's and Set's

	public int getLaguraTela() {

		return laguraTela;
	}

	public int getAlturaTela() {

		return alturaTela;
	}

	public Game() {

		laguraTela = 180;
		alturaTela = 100;
		scaleTela = 3;

		// Iniciando instaciais tempo

		// Iniciando os Controles

		gameController = new GameController();

		// Inicializar
		imageFundoDoJogo = new BufferedImage(laguraTela, alturaTela, BufferedImage.TYPE_INT_RGB);
		criarJanela();
		player = new Player(laguraTela / 4 - 20, alturaTela / 2 - 15, 5, 30, 200, 2f);
		enemy = new Enemy(laguraTela / 4 + 105, alturaTela / 2 - 15, 5, 30, 120, 0.0f);
		ball = new Ball(laguraTela / 2 - 5, alturaTela / 2 - 5, 5, 5, 255, 2f);
		this.addKeyListener(this);

	}

	public static void main(String[] args) {

		game = new Game();
		game.start();

	}

	private void criarJanela() {

		this.setPreferredSize(new Dimension(laguraTela * scaleTela, alturaTela * scaleTela));
		frameJanela = new JFrame("Pong");
		frameJanela.add(this);
		frameJanela.pack();
		frameJanela.setLocationRelativeTo(null);
		frameJanela.setResizable(false);
		frameJanela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameJanela.setVisible(true);

	}

	private synchronized void start() {

		isRunning = true;
		thread = new Thread(this);
		thread.start();

	}

	private synchronized void stop() {

		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void tick() {

		player.tick();
		enemy.tick();
		ball.tick();
		gameController.tick();

	}

	private void render() {

		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = imageFundoDoJogo.getGraphics();
		
		

		g.setColor(new Color(20));
		g.fillRect(0, 0, laguraTela, alturaTela);

		g.setColor(Color.DARK_GRAY);
		g.fillRect(laguraTela / 2 - 1, 0, 1, 100);

		g.setFont(new Font("arial", Font.BOLD, 10));
		g.setColor(Color.white);
		g.drawString(String.valueOf(enemy.getScore()), (int) (enemy.getPosicaoEixoX() + 20), 11);

		/*
		 * if (tempo.acaoCadaTempoLoop(false, 3, 0.5f, 1f)) { g.setFont(new
		 * Font("arial", Font.BOLD, 16)); g.setColor(Color.white); g.drawString("Ready",
		 * laguraTela / 2 - 25, alturaTela / 2);
		 * 
		 * }
		 */

		player.render(g);
		ball.render(g);
		enemy.render(g);
		gameController.render(g);
		
		g.dispose();
		g = bs.getDrawGraphics();

		g.drawImage(imageFundoDoJogo, 0, 0, laguraTela * scaleTela + 10, alturaTela * scaleTela + 10, null);

		bs.show();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		this.requestFocus();
		System.out.println("Começou");
		long tempoPassadoEmNanoSegundos = System.nanoTime();
		double ciclosDoFPS = 60.0;
		double ajusteCicloDoFPS = 1000000000 / ciclosDoFPS;
		double calculoCiclosPorSegundo = 0;

		int framesFPS = 0;
		double tempoPorSegundo = System.currentTimeMillis();

		while (isRunning) {

			long tempoAgoraEmNadoSegundos = System.nanoTime();
			calculoCiclosPorSegundo += (tempoAgoraEmNadoSegundos - tempoPassadoEmNanoSegundos) / ajusteCicloDoFPS;
			tempoPassadoEmNanoSegundos = tempoAgoraEmNadoSegundos;

			if (calculoCiclosPorSegundo > 0) {

				tick();
				render();
				framesFPS++;
				calculoCiclosPorSegundo--;

			}

			if (System.currentTimeMillis() - tempoPorSegundo >= 1000) {

				// System.out.println("FPS " + framesFPS);
				framesFPS = 0;
				tempoPorSegundo += 1000;

			}

		}
		stop();

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {

			if (player.getEntidadeController().getEstadoAtualEntidade() == ESTADOS_ENTIDADE.CHUTANDO) {

				player.setUIponts(player.getUIponts() - player.getCustoChute());
			}
			player.getEntidadeController().mudarEstado(ESTADOS_ENTIDADE.INDO_PARA_CIMA);

			player.setUp(true);

		} else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {

			player.getEntidadeController().mudarEstado(ESTADOS_ENTIDADE.INDO_PARA_BAIXO);

			if (player.getEntidadeController().getEstadoAtualEntidade() == ESTADOS_ENTIDADE.CHUTANDO) {

				player.setUIponts(player.getUIponts() - player.getCustoChute());
			}
			player.setDown(true);
		}
		if (e.getKeyCode() == KeyEvent.VK_P) {

			if (gameController.isPause() == false) {

				gameController.mudeOEstadoGame(ESTADOS_GAME.PAUSE);
				gameController.setPause(true);

			} else if (gameController.isPause()) {

				gameController.mudeOEstadoGame(ESTADOS_GAME.INGAME);
				gameController.setPause(false);
			}

		}

		if (e.getKeyCode() == KeyEvent.VK_K) {

			if (player.podeChutar()) {
				player.setUIponts(player.getUIponts() - player.getCustoChute());
				player.getEntidadeController().mudarEstado(ESTADOS_ENTIDADE.CHUTANDO);
			}

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			player.setUp(false);
			player.getEntidadeController().mudarEstado(ESTADOS_ENTIDADE.IDLE);
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {

			player.getEntidadeController().mudarEstado(ESTADOS_ENTIDADE.IDLE);

			player.setDown(false);
		}
		if (e.getKeyCode() == KeyEvent.VK_K) {
			player.getEntidadeController().mudarEstado(ESTADOS_ENTIDADE.IDLE);
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
