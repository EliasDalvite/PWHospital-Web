/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.Medicamento;
import br.edu.ifsul.modelo.Receituario;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Elias Dalvite
 */
@Stateful
public class ReceituarioDAO <TIPO> extends DAOGenerico<Receituario> implements Serializable {
    public ReceituarioDAO(){
        super();
        classePersistente = Receituario.class;
        // definir as ordens possiveis
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("nome", "Nome", "like"));
        // definir a ordem inicial
        ordematual = listaOrdem.get(1);
        // inicializar o conversor das ordens
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);
    }
    
    @Override
    public Receituario getObjectByID(Object id) throws Exception {
        Receituario obj = em.find(Receituario.class, id);
        // uso para evitar o erro de lazy inicialization exception
        obj.getMedicamentos().size();
        return obj;
    } 
}
