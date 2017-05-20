package loo1.plp.orientadaObjetos1.declaracao.procedimento;

import loo1.plp.orientadaObjetos1.expressao.leftExpression.Id;

public class ParametroRequerido extends Parametro {
	
	public ParametroRequerido(Id id) {
		setId(id);
	}

	@Override
	public String toString() {
		return getId().toString();
	}
}
