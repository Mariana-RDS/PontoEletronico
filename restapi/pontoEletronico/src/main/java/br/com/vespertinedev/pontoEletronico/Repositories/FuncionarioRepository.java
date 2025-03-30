package br.com.vespertinedev.pontoEletronico.Repositories;

import br.com.vespertinedev.pontoEletronico.Entities.FuncionarioEntity;
import br.com.vespertinedev.pontoEletronico.Entities.SetorEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


public class FuncionarioRepository implements GenericRepository<FuncionarioEntity, Integer>{

    public FuncionarioRepository(){}

    @Override
    public void create(FuncionarioEntity z) throws SQLException{
        String sql = "insert into funcionario(nome, email, setor_id) values(?,?,?)";

        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

        pstm.setString(1, z.getNome());
        pstm.setString(2, z.getEmail());
        pstm.setLong(3, z.getSetor().getId());
        pstm.executeUpdate();
    }

    @Override
    public void update(FuncionarioEntity z){
        String sql = "update funcionario set nome=?, email=?, setor_id=? where id=?";

        try{

            PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
            
            pstm.setString(1, z.getNome());
            pstm.setString(2, z.getEmail());
            pstm.setLong(3, z.getSetor().getId());
            pstm.setLong(4, z.getId());
            pstm.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }


    @Override
    public FuncionarioEntity read(Integer key){
        String sql = "SELECT f.*, s.nome as setor_nome FROM funcionario f " +
                "JOIN setor s ON f.setor_id = s.id WHERE f.id = ?";

        try{
            PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

            pstm.setInt(1, key);

            ResultSet result = pstm.executeQuery();

            FuncionarioEntity f = null;

            if(result.next()){
                f = new FuncionarioEntity();
                f.setId((long) key);
                f.setNome(result.getString("nome"));
                f.setEmail(result.getString("email"));
                
                Long setorId = result.getLong("setor_id");
                String setorNome = result.getString("setor_nome");
                SetorEntity setor = new SetorEntity(setorId, setorNome);
                f.setSetor(setor);
            }
            return f;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(Integer key){
        String sql = "delete from funcionario where id=?";

        try{

            PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

            pstm.setInt(1, key);
            pstm.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }


    public List<FuncionarioEntity> readAll(){
        String sql = "SELECT f.*, s.nome as setor_nome FROM funcionario f " +
                "JOIN setor s ON f.setor_id = s.id";

        List<FuncionarioEntity> funcionarioEntity = new ArrayList<>();

        try{
            PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

            ResultSet result = pstm.executeQuery();

            FuncionarioEntity f = null;

            while(result.next()){
                f = new FuncionarioEntity();
                f.setId(result.getLong("id"));
                f.setNome(result.getString("nome"));
                f.setEmail(result.getString("email"));
                
                Long setorId = result.getLong("setor_id");
                String setorNome = result.getString("setor_nome");
                SetorEntity setor = new SetorEntity(setorId, setorNome);
                f.setSetor(setor);

                funcionarioEntity.add(f);
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        return funcionarioEntity;
    }




}
