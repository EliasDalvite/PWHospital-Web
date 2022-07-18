/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.modelo.Paciente;
import br.edu.ifsul.dao.PacienteDAO;
import br.edu.ifsul.util.Util;
import br.edu.ifsul.util.UtilRelatorios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Elias Dalvite
 */
@Named(value = "controlePaciente")
@ViewScoped
public class ControlePaciente implements Serializable {
    
    @EJB
    private PacienteDAO<Paciente> dao;
    private Paciente objeto;
    
    public ControlePaciente() {

    }
    
    public void imprimePacientes(){
        HashMap parametros = new HashMap();
        UtilRelatorios.imprimeRelatorio("relatorioPacientes", parametros, dao.getListaTodos());
    }
    
    public void imprimePaciente(Object id){
        try {
            objeto = dao.getObjectByID(id);
            List<Paciente> lista = new ArrayList<>();
            lista.add(objeto);
            HashMap parametros = new HashMap();
            UtilRelatorios.imprimeRelatorio("relatorioPacientes", parametros, lista);
    
        } catch (Exception e){
            Util.mensagemInformacao("Erro ao imprimir: " + Util.getMensagemErro(e));
        }
    }

    public String listar(){
        return "/privado/paciente/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Paciente();
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

    public PacienteDAO<Paciente> getDao() {
        return dao;
    }

    public void setDao(PacienteDAO<Paciente> dao) {
        this.dao = dao;
    }

    public Paciente getObjeto() {
        return objeto;
    }

    public void setObjeto(Paciente objeto) {
        this.objeto = objeto;
    }
}
