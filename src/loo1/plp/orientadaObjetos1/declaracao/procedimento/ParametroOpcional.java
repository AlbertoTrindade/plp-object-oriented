package loo1.plp.orientadaObjetos1.declaracao.procedimento;

import loo1.plp.orientadaObjetos1.expressao.Expressao;
import loo1.plp.orientadaObjetos1.expressao.leftExpression.Id;

public class ParametroOpcional extends Parametro {
	
	private Expressao valorPadrao;
	
	public ParametroOpcional(Id parametroId, Expressao valorPadrao) {
		setParametroId(parametroId);
		this.valorPadrao = valorPadrao;
	}
	
	public Expressao getValorPadrao() {
		return valorPadrao;
	}

	public void setValorPadrao(Expressao valorPadrao) {
		this.valorPadrao = valorPadrao;
	}
	
	@Override
	public String toString() {
		return getParametroId() + " = " + valorPadrao;
	}
}
