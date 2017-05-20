package loo1.plp.orientadaObjetos1.declaracao.procedimento;

import loo1.plp.orientadaObjetos1.expressao.leftExpression.Id;

public class ParametroRequerido extends Parametro {
	
	public ParametroRequerido(Id parametroId) {
		setParametroId(parametroId);
	}

	@Override
	public String toString() {
		return getParametroId().toString();
	}
}
