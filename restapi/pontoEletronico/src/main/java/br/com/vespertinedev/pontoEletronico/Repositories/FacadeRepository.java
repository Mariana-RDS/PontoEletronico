package br.com.vespertinedev.pontoEletronico.Repositories;


import br.com.vespertinedev.pontoEletronico.Entities.FuncionarioEntity;
import br.com.vespertinedev.pontoEletronico.Entities.RegistroPontoEntity;
import br.com.vespertinedev.pontoEletronico.Entities.SetorEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;


public class FacadeRepository {

    private static FacadeRepository myself = null;

    private GenericRepository<FuncionarioEntity, Integer> rFuncionario = null;
    private GenericRepository<RegistroPontoEntity, Integer> rRegistroPonto = null;
    private GenericRepository<SetorEntity, Integer> rSetor = null;

    private FacadeRepository(){
        this.rRegistroPonto = new RegistroPontoRepository();
        this.rFuncionario = new FuncionarioRepository();
        this.rSetor = new SetorRepository();
    }

    public static FacadeRepository getCurrentInstance(){
        if(myself == null){
            myself = new FacadeRepository();
        }
        return myself;
    }


    public void create(FuncionarioEntity f) throws SQLException{
        this.rFuncionario.create(f);
    }
    public void update(FuncionarioEntity f) throws SQLException{
        this.rFuncionario.update(f);
    }
    public FuncionarioEntity readFuncionario(int id) throws SQLException{
        return this.rFuncionario.read(id);
    }
    public void deleteFuncionario(int id) throws SQLException{
        this.rFuncionario.delete(id);
    }
    public List<FuncionarioEntity> readAllFuncionarios() throws SQLException {
        List<FuncionarioEntity> funcionarios = ((FuncionarioRepository) this.rFuncionario).readAll();
        return (funcionarios != null) ? funcionarios : new ArrayList<>();
    }

    public List<SetorEntity> readSetor() throws SQLException {
        return ((SetorRepository) this.rSetor).readAll();
    }


    public void createRegistroPonto(RegistroPontoEntity ponto) throws SQLException {
        this.rRegistroPonto.create(ponto);
    }

    public List<RegistroPontoEntity> getPontosByFuncionario(int funcionarioId) throws SQLException {
        return ((RegistroPontoRepository) this.rRegistroPonto).getPontosByFuncionario(funcionarioId);
    }

}
