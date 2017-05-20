package loo1.plp.orientadaObjetos1.declaracao.procedimento;

import loo1.plp.orientadaObjetos1.expressao.leftExpression.Id;

public abstract class Parametro {

	private Id id;
	
	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}
	
	@Override
	public Parametro clone() {
		return this;
	}
}
