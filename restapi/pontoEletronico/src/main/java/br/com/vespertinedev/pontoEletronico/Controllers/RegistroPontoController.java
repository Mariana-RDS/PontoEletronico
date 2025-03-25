package br.com.vespertinedev.pontoEletronico.Controllers;

import java.sql.SQLException;
import java.util.List;

import br.com.vespertinedev.pontoEletronico.Entities.RegistroPontoEntity;
import br.com.vespertinedev.pontoEletronico.Repositories.FacadeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RegistroPontoController {

    @PostMapping("/registroPonto")
    public String create(@RequestBody RegistroPontoEntity ponto) throws SQLException{
        try{
            FacadeRepository.getCurrentInstance().createRegistroPonto(ponto);
            return "Ponto Registrado";
        }catch(SQLException e){
            e.printStackTrace();
            return "Ponto não registrado";
        }
    }

    @GetMapping("/registroPonto/{id}")
    public List<RegistroPontoEntity> readAll(@PathVariable int id) throws SQLException{
        return FacadeRepository.getCurrentInstance().getPontosByFuncionario(id);
    }

}
