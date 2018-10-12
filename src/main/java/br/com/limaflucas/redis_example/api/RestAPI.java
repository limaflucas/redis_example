package br.com.limaflucas.redis_example.api;

import br.com.limaflucas.redis_example.dominio.RedisComandos;
import br.com.limaflucas.redis_example.dominio.Saldo;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

@EnableAutoConfiguration
@RestController
@RequestMapping(value = "/saldo")
public class RestAPI {

    Jedis jedis = new Jedis("localhost");
    RedisComandos<Saldo> comandos = new RedisComandos<>(Saldo.class);

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Saldo getSaldo(@PathVariable int id) {
        return comandos.recuperar(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String insSaldo(@RequestBody Saldo saldo) {
        return comandos.criar(saldo).toString();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delSaldo(@PathVariable int id) {
        return comandos.remover(id).toString();
    }
}
