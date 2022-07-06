/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.modelo.Consulta;
import br.edu.ifsul.dao.ConsultaDAO;
import br.edu.ifsul.dao.ExameDAO;
import br.edu.ifsul.dao.MedicoDAO;
import br.edu.ifsul.dao.PacienteDAO;
import br.edu.ifsul.modelo.Exame;
import br.edu.ifsul.modelo.Medico;
import br.edu.ifsul.modelo.Paciente;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Elias Dalvite
 */
@Named(value = "controleConsulta")
@ViewScoped
public class ControleConsulta implements Serializable {
    
    @EJB
    private ConsultaDAO<Consulta> dao;
    private Consulta objeto;
    @EJB
    private MedicoDAO<Medico> daoMedico; 
    @EJB
    private PacienteDAO<Paciente> daoPaciente;
    @EJB
    private ExameDAO<Exame> daoExame;
    
    private Exame exame;
    private Boolean novoExame;
    
    public ControleConsulta() {

    }
    
    public void novoExame() {
        setNovoExame((Boolean) true);
        setExame(new Exame());
    }

    public void alterarExame(int index) {
        setExame(objeto.getExames().get(index));
        setNovoExame((Boolean) false);
    }

    public void salvarExame() {
        if (getNovoExame()) {
            objeto.adicionarExame(getExame());
        }
        Util.mensagemInformacao("Exame adicionado ou atualizado com sucesso");
    }
    
    public void removerExame(int index) {
        objeto.removerExame(index);
        Util.mensagemInformacao("Exame removido com sucesso!");
    }

    public String listar(){
        return "/privado/consulta/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Consulta();
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

    public ConsultaDAO<Consulta> getDao() {
        return dao;
    }

    public void setDao(ConsultaDAO<Consulta> dao) {
        this.dao = dao;
    }

    public Consulta getObjeto() {
        return objeto;
    }

    public void setObjeto(Consulta objeto) {
        this.objeto = objeto;
    }

    public MedicoDAO<Medico> getDaoMedico() {
        return daoMedico;
    }

    public void setDaoMedico(MedicoDAO<Medico> daoMedico) {
        this.daoMedico = daoMedico;
    }

    public PacienteDAO<Paciente> getDaoPaciente() {
        return daoPaciente;
    }

    public void setDaoPaciente(PacienteDAO<Paciente> daoPaciente) {
        this.daoPaciente = daoPaciente;
    }

    public ExameDAO<Exame> getDaoExame() {
        return daoExame;
    }

    public void setDaoExame(ExameDAO<Exame> daoExame) {
        this.daoExame = daoExame;
    }

    public Exame getExame() {
        return exame;
    }

    public void setExame(Exame exame) {
        this.exame = exame;
    }

    public Boolean getNovoExame() {
        return novoExame;
    }

    public void setNovoExame(Boolean novoExame) {
        this.novoExame = novoExame;
    }
}
