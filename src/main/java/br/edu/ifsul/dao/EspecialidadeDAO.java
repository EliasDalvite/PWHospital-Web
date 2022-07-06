/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.Especialidade;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Elias Dalvite
 */
@Stateful
public class EspecialidadeDAO <TIPO> extends DAOGenerico<Especialidade> implements Serializable {
    public EspecialidadeDAO(){
        super();
        classePersistente = Especialidade.class;
        // definir as ordens possiveis
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("descricao", "Descricao", "like"));
        // definir a ordem inicial
        ordematual = listaOrdem.get(1);
        // inicializar o conversor das ordens
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);
    }
}
