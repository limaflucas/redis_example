package br.com.limaflucas.redis_example.dominio;

import br.com.limaflucas.redis_example.dao.anotacoes.ChaveRedis;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name  = "saldo")
public class Saldo {

    @PartitionKey
    @ChaveRedis
    @JsonSerialize(using = ToStringSerializer.class)
    private int id;
    @JsonSerialize(using = ToStringSerializer.class)
    private Float valor;
}
