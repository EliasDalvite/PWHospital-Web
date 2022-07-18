/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.Consulta;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Elias Dalvite
 */
@Stateful
public class ConsultaDAO <TIPO> extends DAOGenerico<Consulta> implements Serializable {
    public ConsultaDAO(){
        super();
        classePersistente = Consulta.class;
        // definir as ordens possiveis
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("paciente.nome", "Paciente", "like"));
        listaOrdem.add(new Ordem("medico.nome", "Médico", "like"));
        // definir a ordem inicial
        ordematual = listaOrdem.get(0);
        // inicializar o conversor das ordens
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);
    }
    
    @Override
    public Consulta getObjectByID(Object id) throws Exception {
        Consulta obj = em.find(Consulta.class, id);
        // uso para evitar o erro de lazy inicialization exception
        obj.getReceituarios().size();
        obj.getExames().size();
        return obj;
    }   
    
    
    
}
