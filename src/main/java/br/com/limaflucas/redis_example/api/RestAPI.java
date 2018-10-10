package br.com.limaflucas.redis_example.api;

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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Saldo getSaldo(@PathVariable int id) {
        Map<String, String> hashMap = new HashMap<>();

        if(jedis.exists(String.format("id:%d", id))) {
            hashMap = jedis.hgetAll(String.format("id:%d", id));
            Saldo saldo = new Saldo(id, Float.parseFloat(hashMap.get("valor")));
            return saldo;
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public boolean insSaldo(@RequestBody Saldo saldo) {
        Map<String, String> hashMap = new HashMap<>();
        String resposta = "";

        if(!jedis.exists(String.format("id:%d", saldo.getId()))) {
            hashMap.put("valor", Float.toString(saldo.getValor()));
            resposta = jedis.hmset(String.format("id:%d", saldo.getId()), hashMap);
        }

        return (resposta.equals("OK") ? true : false);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean delSaldo(@PathVariable int id) {
        Long resposta = 0L;

        resposta = jedis.del(String.format("id:%d", id));

        return (resposta == 0L ? false : true);
    }
}
