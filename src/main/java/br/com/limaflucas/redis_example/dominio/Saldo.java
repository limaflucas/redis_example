package br.com.limaflucas.redis_example.dominio;

import br.com.limaflucas.redis_example.dao.anotacoes.ChaveRedis;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Saldo {

    @ChaveRedis
    @JsonSerialize(using = ToStringSerializer.class)
    private int id;
    @JsonSerialize(using = ToStringSerializer.class)
    private Float valor;
}
