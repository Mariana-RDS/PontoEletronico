package br.com.vespertinedev.pontoEletronico.Controllers;

import java.sql.SQLException;
import java.util.List;

import br.com.vespertinedev.pontoEletronico.Entities.FuncionarioEntity;
import br.com.vespertinedev.pontoEletronico.Entities.SetorEntity;
import br.com.vespertinedev.pontoEletronico.Repositories.FacadeRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class FuncionarioController {

    @GetMapping("/funcionario")
    public  List<FuncionarioEntity> readAll() throws SQLException{
        return FacadeRepository.getCurrentInstance().readAllFuncionarios();
    }

    @GetMapping("/setores")
    public  List<SetorEntity> readAllSetores() throws SQLException{
        return FacadeRepository.getCurrentInstance().readSetor();
    }

    @GetMapping("/funcionario/{id}")
    public FuncionarioEntity read(@PathVariable int id) throws SQLException{
        return FacadeRepository.getCurrentInstance().readFuncionario(id);
    }

    @PostMapping("/funcionario")
    public String create(@RequestBody FuncionarioEntity f){
        try{
            FacadeRepository.getCurrentInstance().create(f);
            return "Cadastro realizado com sucesso!";
        }catch(SQLException e){
            e.printStackTrace();
            return "Não foi possível realizar o cadastro";
        }
    }

    @PutMapping("/funcionario/{id}")
    public void update(@RequestBody FuncionarioEntity f) throws SQLException{
        FacadeRepository.getCurrentInstance().update(f);
    }

    @DeleteMapping("/funcionario/{id}")
    public void delete(@PathVariable int id) throws SQLException{
        FacadeRepository.getCurrentInstance().deleteFuncionario(id);
    }



}
