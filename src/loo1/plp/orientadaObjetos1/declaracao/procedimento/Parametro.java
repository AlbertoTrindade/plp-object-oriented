package loo1.plp.orientadaObjetos1.declaracao.procedimento;

import loo1.plp.orientadaObjetos1.expressao.leftExpression.Id;

public abstract class Parametro {

	private Id parametroId;
	
	public Id getParametroId() {
		return parametroId;
	}

	public void setParametroId(Id parametroId) {
		this.parametroId = parametroId;
	}
	
	@Override
	public Parametro clone() {
		return this;
	}
}
