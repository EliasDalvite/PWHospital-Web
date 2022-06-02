/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Exame;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Elias Dalvite
 * @param <TIPO>
 */
@Stateful
public class ExameDAO <TIPO> extends DAOGenerico<Exame> implements Serializable {
    public ExameDAO(){
        super();
        classePersistente = Exame.class;
    }
}