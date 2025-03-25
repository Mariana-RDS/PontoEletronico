package br.com.vespertinedev.pontoEletronico.Entities;

import java.time.LocalDate;
import java.time.LocalTime;

public class RegistroPontoEntity {

    private Long id;
    private LocalDate data;
    private LocalTime hora;
    private int tipo;
    private FuncionarioEntity funcionario;

    public RegistroPontoEntity(LocalDate data, LocalTime hora, int tipo, FuncionarioEntity funcionario){
        if(tipo != 1 && tipo != 2){
            throw new IllegalArgumentException("Tipo inválido!");
        }
        this.data = data;
        this.hora = hora;
        this.tipo = tipo;
        this.funcionario = funcionario;
    }

    public Long getId(){
        return id;
    } 

    public void setId(Long id){
        this.id = id;
    }

    public LocalDate getData(){
        return data;
    }

    public void setData(LocalDate data){
        this.data = data;
    }

    public LocalTime getHora(){
        return hora;
    }

    public void setHora(LocalTime hora){
        this.hora = hora;
    }

    public int getTipo(){
        return tipo;
    }

    public void setTipo(int tipo){
        this.tipo = tipo;
    }

    public FuncionarioEntity getFuncionario(){
        return funcionario;
    }

    public void setFuncionario(FuncionarioEntity funcionario){
        this.funcionario = funcionario;
    }

}
