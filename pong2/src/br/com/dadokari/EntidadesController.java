package br.com.dadokari;

public class EntidadesController {

	private ESTADOS_ENTIDADE estadoAtualEntidade;
	private ESTADOS_ENTIDADE proximoEstadoEntidade;

	public ESTADOS_ENTIDADE getEstadoAtualEntidade() {

		return this.estadoAtualEntidade;
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

		}

	}

}
