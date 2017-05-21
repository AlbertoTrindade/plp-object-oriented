package loo1.plp.orientadaObjetos1.declaracao.procedimento;

import loo1.plp.expressions2.memory.VariavelJaDeclaradaException;
import loo1.plp.expressions2.memory.VariavelNaoDeclaradaException;
import loo1.plp.orientadaObjetos1.excecao.declaracao.ClasseNaoDeclaradaException;
import loo1.plp.orientadaObjetos1.memoria.AmbienteCompilacaoOO1;
import loo1.plp.orientadaObjetos1.memoria.AmbienteExecucaoOO1;
import loo1.plp.orientadaObjetos1.util.Tipo;
/**
 * Classe que representa uma declara��o de par�metros.
 */
public class DecParametro {
    /**
     * Parametro declarado.
     */
    private Parametro parametro;
    /**
     * Tipo do parametro declarado.
     */
    private Tipo tipo;
    /**
     * Construtor.
     * @param parametro Parametro declarado.
     * @param tipo Tipo do parametro declarado.
     */
    public DecParametro(Parametro parametro, Tipo tipo){
        this.parametro = parametro;
        this.tipo = tipo;
    }
    /**
     * Obt�m o parametro declarado.
     * @return o parametro.
     */
    public Parametro getParametro() {
        return parametro;
    }
    /**
     * Obt�m o tipo do parametro declarado.
     * @return o tipo do parametro declarado.
     */
    public Tipo getTipo() {
        return tipo;
    }
    /**
     * Cria um mapeamento do identificador para o valor da express�o
     * desta declara��o no AmbienteExecucao
     * @param ambiente o ambiente que contem o mapeamento entre identificadores
     *  e valores.
     * @return o ambiente modificado pela inicializa��o da vari�vel.
     */
    public AmbienteExecucaoOO1 elabora(AmbienteExecucaoOO1 ambiente) {
        return ambiente;
    }
    /**
     * Verifica se a declara��o est� bem tipada, ou seja, se a
     * express�o de inicializa��o est� bem tipada.
     * @param ambiente o ambiente que contem o mapeamento entre identificadores
     *  e seus tipos.
     * @return <code>true</code> se os tipos da declara��o s�o v�lidos;
     *          <code>false</code> caso contrario.
     */
    public boolean checaTipo(AmbienteCompilacaoOO1 ambiente)throws ClasseNaoDeclaradaException {
        return tipo.eValido(ambiente) && 
        	   (!(parametro instanceof ParametroOpcional) || tipo.equals(((ParametroOpcional) parametro).getValorPadrao().getTipo(ambiente)));
    }

    /**
     * Cria um mapeamento do identificador para o tipo do parametro
     * desta declara��o no AmbienteCompilacao
     *
     * @param ambiente o ambiente que contem o mapeamento entre identificador
     *  e seu tipo.
     * @return o ambiente modificado pela declara��o do parametro.
     */
    public AmbienteCompilacaoOO1 declaraParametro(AmbienteCompilacaoOO1 ambiente)
        throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException, ClasseNaoDeclaradaException {
    	if (parametro instanceof ParametroOpcional) {
        	ParametroOpcional parametroOpcional = (ParametroOpcional) parametro;
        	parametroOpcional.getValorPadrao().checaTipo(ambiente);
        }
    	
        ambiente.map(parametro.getId(), tipo);
        return ambiente;
    }
}
