package loo1.plp.orientadaObjetos1.excecao.declaracao;

import loo1.plp.orientadaObjetos1.declaracao.procedimento.Parametro;

public class ParametroRequeridoAposOpcionalException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ParametroRequeridoAposOpcionalException(Parametro parametro){
		super("N�o � poss�vel especificar o par�metro requerido " + parametro + " ap�s a especifica��o de par�metros opcionais.");
	}
}
