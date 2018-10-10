package br.com.limaflucas.redis_example.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Saldo {

    private int id;
    private Float valor;
}
