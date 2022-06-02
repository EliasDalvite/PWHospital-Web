/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.modelo.Exame;
import br.edu.ifsul.dao.ExameDAO;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Elias Dalvite
 */
@Named(value = "controleExame")
@ViewScoped
public class ControleExame implements Serializable {
    
    @EJB
    private ExameDAO<Exame> dao;
    private Exame objeto;
    
    public ControleExame() {

    }

    public String listar(){
        return "/privado/exame/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Exame();
    }
    
    public void alterar(Object id){
        try {
            objeto = dao.getObjectByID(id);
        } catch (Exception e){
            Util.mensagemInformacao("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }
     
    public void excluir(Object id){
        try {
            objeto = dao.getObjectByID(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e){
            Util.mensagemInformacao("Erro ao remover objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public void salvar(){
        try {
            if (objeto.getId() == null){
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
        } catch (Exception e){
            Util.mensagemInformacao("Erro ao salvar objeto: " + Util.getMensagemErro(e));
        }
    }

    public ExameDAO<Exame> getDao() {
        return dao;
    }

    public void setDao(ExameDAO<Exame> dao) {
        this.dao = dao;
    }

    public Exame getObjeto() {
        return objeto;
    }

    public void setObjeto(Exame objeto) {
        this.objeto = objeto;
    }
}
