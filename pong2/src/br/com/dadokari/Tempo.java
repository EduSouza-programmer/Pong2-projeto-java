package br.com.dadokari;

public class Tempo {

	// para o metodo criarTempo
	private int criarTempoVar;
	private double criarTempoVarMET;

	// para o metodo AcaoCadaTempoLoop
	private int acaoCadaTempoLoopVar;
	private double acaoCadaTempoLoopVarMET;

	// para o metodo acaoCadaTempo
	private int acaoCadaTempoVar;
	private double acaoCadaTempoVarMET;

	private boolean chamaTempoDelay;

	// get and set
	public int getCriarTempoVar() {
		return this.criarTempoVar;
	}

	public void setCriatTempoVar(int value) {
		this.criarTempoVar = value;

	}

	public int getAcaoCadaTempoLoopVar() {
		return this.acaoCadaTempoLoopVar;
	}

	public void setAcaoCadaTempoLoopVar(int value) {
		this.acaoCadaTempoLoopVar = value;
	}

	public int getAcaoCadaTempoVar() {
		return this.acaoCadaTempoVar;
	}

	public void setAcaoCadaTempoVar(int value) {
		this.acaoCadaTempoVar = value;
	}

	public Tempo() {
		criarTempoVar = 0;
		criarTempoVarMET = System.currentTimeMillis();
		acaoCadaTempoLoopVar = 0;
		acaoCadaTempoLoopVarMET = System.currentTimeMillis();
		acaoCadaTempoVar = 0;
		acaoCadaTempoVarMET = System.currentTimeMillis();
		chamaTempoDelay = false;

	}

	public void tick() {

	}

	public int criarTempo(int tempoEmSegundosRequerido) {

		if (System.currentTimeMillis() - criarTempoVarMET >= tempoEmSegundosRequerido) {
			criarTempoVar += 1;
			criarTempoVarMET = System.currentTimeMillis();

		}

		return criarTempoVar;

	}

	public boolean acaoCadaTempoLoop(boolean isLoop, int contador, float tempoParaAtivarEmSegundos,
			float tempoParaFicarAtivoEmSegs) {

		tempoParaAtivarEmSegundos *= 1000;
		tempoParaFicarAtivoEmSegs *= 1000;

		if (chamaTempoDelay == false) {
			if (contador == 0 && isLoop == true) {
				if (System.currentTimeMillis() - acaoCadaTempoLoopVarMET >= tempoParaAtivarEmSegundos) {

					acaoCadaTempoLoopVarMET = System.currentTimeMillis();
					chamaTempoDelay = true;

				}
			}
		}
		if (chamaTempoDelay == false) {
			if (contador > acaoCadaTempoLoopVar && isLoop == false) {
				if (System.currentTimeMillis() - acaoCadaTempoLoopVarMET >= tempoParaAtivarEmSegundos) {

					acaoCadaTempoLoopVar += 1;
					acaoCadaTempoLoopVarMET = System.currentTimeMillis();
					chamaTempoDelay = true;

				}
			}
		} else if (chamaTempoDelay) {

			if (System.currentTimeMillis() - acaoCadaTempoLoopVarMET >= tempoParaFicarAtivoEmSegs) {

				acaoCadaTempoLoopVarMET = System.currentTimeMillis();
				chamaTempoDelay = false;

			}

			return true;
		} else if (acaoCadaTempoLoopVar == contador) {

			acaoCadaTempoLoopVar = contador;

		}

		return false;

	}

}
