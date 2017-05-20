package loo1.plp.orientadaObjetos1.excecao.declaracao;

import loo1.plp.orientadaObjetos1.declaracao.procedimento.Parametro;

public class ParametroRequeridoAposOpcionalException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ParametroRequeridoAposOpcionalException(Parametro parametro){
		super("Não é possível especificar o parâmetro requerido " + parametro + " após a especificação de parâmetros opcionais.");
	}
}
