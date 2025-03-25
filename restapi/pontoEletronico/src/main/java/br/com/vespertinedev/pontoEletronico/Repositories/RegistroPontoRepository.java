package br.com.vespertinedev.pontoEletronico.Repositories;

import br.com.vespertinedev.pontoEletronico.Entities.RegistroPontoEntity;
import br.com.vespertinedev.pontoEletronico.Entities.FuncionarioEntity;
import br.com.vespertinedev.pontoEletronico.Entities.SetorEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import java.util.ArrayList;
import java.util.List;

public final class RegistroPontoRepository implements GenericRepository<RegistroPontoEntity, Integer>{

    public RegistroPontoRepository(){}

    public void create(RegistroPontoEntity ponto) throws SQLException {
        String sql = "INSERT INTO RegistroPonto (data, hora, tipo, funcionario_id) VALUES (?, ?, ?, ?)";

        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);

        pstm.setDate(1, java.sql.Date.valueOf(ponto.getData()));
        pstm.setTime(2, java.sql.Time.valueOf(ponto.getHora()));
        pstm.setInt(3, ponto.getTipo());
        pstm.setLong(4, ponto.getFuncionario().getId());

        pstm.executeUpdate();
    }

    public List<RegistroPontoEntity> getPontosByFuncionario(int funcionarioId) throws SQLException {
        String sql = """
            SELECT rp.data, rp.hora, rp.tipo, 
                    f.id AS funcionario_id, f.nome, f.email, 
                    s.id AS setor_id, s.nome AS setor_nome
            FROM RegistroPonto rp
            JOIN Funcionario f ON rp.funcionario_id = f.id
            JOIN Setor s ON f.setor_id = s.id
            WHERE rp.funcionario_id = ?
        """;

        PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
        pstm.setInt(1, funcionarioId);

        ResultSet result = pstm.executeQuery();

        List<RegistroPontoEntity> registros = new ArrayList<>();

        while (result.next()) {
            SetorEntity setor = new SetorEntity(result.getLong("setor_id"), result.getString("setor_nome"));

            FuncionarioEntity funcionario = new FuncionarioEntity(
                    result.getLong("funcionario_id"),
                    result.getString("nome"),
                    result.getString("email"),
                    setor
            );

            RegistroPontoEntity ponto = new RegistroPontoEntity(
                    result.getDate("data").toLocalDate(),
                    result.getTime("hora").toLocalTime(),
                    result.getInt("tipo"),
                    funcionario
            );
            registros.add(ponto);
        }

        return registros;
    }

    @Override
    public void update(RegistroPontoEntity ponto) throws SQLException {
        throw new UnsupportedOperationException("");
    }

    @Override
    public void delete(Integer k) throws SQLException {
        throw new UnsupportedOperationException("");
    }

    @Override
    public RegistroPontoEntity read(Integer k) throws SQLException {
        throw new UnsupportedOperationException("");
    }
}
