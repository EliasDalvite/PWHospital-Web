/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

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
    }
}
