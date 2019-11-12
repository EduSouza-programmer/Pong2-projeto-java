package br.com.dadokari;

public class EntidadesController {

	private ESTADOS_ENTIDADE estadoAtualEntidade;
	private ESTADOS_ENTIDADE proximoEstadoEntidade;

	public ESTADOS_ENTIDADE getEstadoAtualEntidade() {
		return this.estadoAtualEntidade;
	}

	public void setEstadoAtualEntidade(ESTADOS_ENTIDADE value) {
		this.estadoAtualEntidade = value;
	}

	public ESTADOS_ENTIDADE getProximoEstadoEntidade() {
		return this.proximoEstadoEntidade;
	}

	public void setProximoEstadoEntidade(ESTADOS_ENTIDADE value) {
		this.proximoEstadoEntidade = value;
	}

	public EntidadesController() {
		proximoEstadoEntidade = ESTADOS_ENTIDADE.ATIVO;
		

	}

	public void mudarEstado(ESTADOS_ENTIDADE novoEstadoEntidade) {
		proximoEstadoEntidade = novoEstadoEntidade;
	}

	public void tick() {
		
		estadoAtualEntidade = proximoEstadoEntidade;

		switch (estadoAtualEntidade) {
		case CHUTANDO:

			break;
		case CHUTE_MEIO:

			break;
		case IDLE:

			break;
		case INDO_PARA_BAIXO:

			break;
		case INDO_PARA_CIMA:

			break;

		case ATIVO:
			
			break;
		case PONTO:
			//System.out.println(estadoAtualEntidade);

			break;

		}

	}

}
