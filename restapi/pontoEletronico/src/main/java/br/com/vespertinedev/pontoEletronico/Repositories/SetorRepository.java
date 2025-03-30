package br.com.vespertinedev.pontoEletronico.Repositories;


import br.com.vespertinedev.pontoEletronico.Entities.SetorEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SetorRepository implements GenericRepository<SetorEntity, Integer> {

    public SetorRepository(){}

    public List<SetorEntity> readAll(){
        String sql = "Select * from setor ";

        List<SetorEntity> setorEntity = new ArrayList<>();
        try(PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
            ResultSet result = pstm.executeQuery()){

            while(result.next()){
                SetorEntity setor = new SetorEntity();
                setor.setId(result.getLong("id"));
                setor.setNome(result.getString("nome"));
                setorEntity.add(setor);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return setorEntity;
    }

    @Override
    public void create(SetorEntity setor) throws SQLException {
        throw new UnsupportedOperationException("");
    }

    @Override
    public SetorEntity read(Integer k) throws SQLException {
        throw new UnsupportedOperationException("");
    }

    @Override
    public void update(SetorEntity setor) throws SQLException {
        throw new UnsupportedOperationException("");
    }

    @Override
    public void delete(Integer k) throws SQLException {
        throw new UnsupportedOperationException("");
    }

}

