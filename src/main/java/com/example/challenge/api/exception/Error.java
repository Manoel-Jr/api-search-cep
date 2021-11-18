package com.example.challenge.api.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Error {

    private List<String> errors = new ArrayList<>();
    private String dataHora;
    private int statusCode;

    public Error() {
    }

    public Error(List<String> errors) {
        super();
        this.errors = errors;
    }

    public String getDataHora() {
        LocalDateTime dataAndHorario = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        dataHora = dateTimeFormatter.format(dataAndHorario);
        return dataHora;
    }

}
