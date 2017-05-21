package loo1.plp.orientadaObjetos1.comando;

import loo1.plp.expressions2.memory.VariavelJaDeclaradaException;
import loo1.plp.expressions2.memory.VariavelNaoDeclaradaException;
import loo1.plp.orientadaObjetos1.declaracao.procedimento.ListaDeclaracaoParametro;
import loo1.plp.orientadaObjetos1.declaracao.procedimento.Parametro;
import loo1.plp.orientadaObjetos1.declaracao.procedimento.ParametroOpcional;
import loo1.plp.orientadaObjetos1.excecao.declaracao.ClasseJaDeclaradaException;
import loo1.plp.orientadaObjetos1.excecao.declaracao.ClasseNaoDeclaradaException;
import loo1.plp.orientadaObjetos1.excecao.declaracao.ObjetoJaDeclaradoException;
import loo1.plp.orientadaObjetos1.excecao.declaracao.ObjetoNaoDeclaradoException;
import loo1.plp.orientadaObjetos1.excecao.declaracao.ProcedimentoJaDeclaradoException;
import loo1.plp.orientadaObjetos1.excecao.declaracao.ProcedimentoNaoDeclaradoException;
import loo1.plp.orientadaObjetos1.excecao.execucao.EntradaInvalidaException;
import loo1.plp.orientadaObjetos1.expressao.ListaExpressao;
import loo1.plp.orientadaObjetos1.expressao.valor.Valor;
import loo1.plp.orientadaObjetos1.memoria.AmbienteCompilacaoOO1;
import loo1.plp.orientadaObjetos1.memoria.AmbienteExecucaoOO1;
import loo1.plp.orientadaObjetos1.memoria.colecao.ListaValor;
import loo1.plp.orientadaObjetos1.util.ListaTipo;

/**
 * Classe que representa uma chamada de um procedimento.
 */
public class ChamadaProcedimento implements Comando {

    /**
     * ï¿½ o procedimento
     */
    private Procedimento procedimento;

    /**
     * Sï¿½o os parametros do procedimento
     */
    private ListaExpressao parametrosReais;

    /**
     * Valores que serao atribuï¿½dos aos parametros reais
     */
    private ListaValor valoresParametros;

    /**
     * Contrutor Default.
     * @param procedimento ï¿½ o procedimento
     * @param parametrosReais sao os parï¿½metros do procedimento
     * @param valoresParametros sao os valores dos parametros
     */
    public ChamadaProcedimento(Procedimento procedimento, ListaExpressao parametrosReais,
                               ListaValor valoresParametros){
        this.procedimento = procedimento;
        this.parametrosReais = parametrosReais;
        this.valoresParametros = valoresParametros;
    }

    /**
     * Contrutor Default.
     * @param procedimento ï¿½ o procedimento
     * @param parametrosReais sao os parï¿½metros do procedimento
     */
    public ChamadaProcedimento(Procedimento procedimento, ListaExpressao parametrosReais){
        this.procedimento = procedimento;
        this.parametrosReais = parametrosReais;
        this.valoresParametros = null;
    }
    /**
     * Executa este comando.
     * @param ambiente o ambiente que contem o mapeamento entre identificadores
     *  e valores.
     * @return o ambiente modificado pela execuï¿½ï¿½o do comando.
     */
    public AmbienteExecucaoOO1 executar(AmbienteExecucaoOO1 ambiente)
        throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
               ProcedimentoNaoDeclaradoException, ProcedimentoJaDeclaradoException,
               ObjetoNaoDeclaradoException, ObjetoJaDeclaradoException,
               ClasseNaoDeclaradaException, ClasseJaDeclaradaException, EntradaInvalidaException{

        ambiente.incrementa();
        ambiente = bindParameters(ambiente, procedimento.getParametrosFormais());
        ambiente = procedimento.getComando().executar(ambiente);
        ambiente.restaura();
        return ambiente;
    }

    /**
     * insere no contexto o resultado da associacao entre cada parametro formal
     * e seu correspondente parametro atual
     * @throws ClasseNaoDeclaradaException 
     */
    private AmbienteExecucaoOO1 bindParameters (AmbienteExecucaoOO1 ambiente, ListaDeclaracaoParametro parametrosFormais)
        throws VariavelJaDeclaradaException, VariavelNaoDeclaradaException,
               ObjetoNaoDeclaradoException, ClasseNaoDeclaradaException{
        ListaValor listaValor = this.valoresParametros;
        if(listaValor == null) {
            listaValor = parametrosReais.avaliar(ambiente);
        }
        while (parametrosFormais != null && parametrosFormais.length() > 0){
        	Valor valorParametro;
        	Parametro parametroFormal = parametrosFormais.getHead().getParametro();
        	
        	if (listaValor.length() > 0) {
        		valorParametro = listaValor.getHead();
        		listaValor = (ListaValor) listaValor.getTail();
        	}
        	else {
        		valorParametro = ((ParametroOpcional) parametroFormal).getValorPadrao().avaliar(ambiente);
        	}
        	
            ambiente.map(parametroFormal.getId(), valorParametro);
            parametrosFormais = ((ListaDeclaracaoParametro) parametrosFormais.getTail());
            
        }
        return ambiente;
    }

    /**
     * Realiza a verificacao de tipos desta chamada de procedimento, onde
     * os tipos dos parametros formais devem ser iguais aos tipos dos
     * parametros reais na ordem em que se apresentam.
     *
     * @param ambiente o ambiente que contem o mapeamento entre identificadores
     *  e tipos.
     *
     * @return <code>true</code> se a chamada de procedimeno estï¿½ bem tipada;
     *          <code>false</code> caso contrario.
     */
     public boolean checaTipo(AmbienteCompilacaoOO1 ambiente)
        throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException,
               ProcedimentoNaoDeclaradoException,ClasseNaoDeclaradaException {
        boolean resposta;
        ambiente.incrementa();
        ListaDeclaracaoParametro parametrosFormais = procedimento.getParametrosFormais();
        ListaTipo listaTipo = parametrosReais.getTipos(ambiente);
        // a quantidade de parâmetros reais está entre a dos formais requeridos e todos os formais?
        if (procedimento.getAridadeRequerido() <= listaTipo.length() &&
        	listaTipo.length() <= parametrosFormais.length()) {
            // a funcao tem algum parametro?
            if(listaTipo.head() == null || parametrosFormais.getHead() == null) {
                resposta = true;
            }
            else {
                resposta = true;
                // tem parametros formais de tipos diferentes
                // de parametros reais na ordem em que se apresentam?
                while(listaTipo.head() != null && parametrosFormais != null) {
                    if( ! listaTipo.head().equals(parametrosFormais.getHead().getTipo())) {
                        resposta = false;
                        break;
                    }
                    listaTipo = listaTipo.tail();
                    parametrosFormais = ((ListaDeclaracaoParametro) parametrosFormais.getTail());
                }
            }
        }
        else {
            resposta = false;
        }
        ambiente.restaura();
        return resposta;
    }
}