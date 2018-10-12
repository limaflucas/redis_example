package br.com.limaflucas.redis_example.dominio;

import br.com.limaflucas.redis_example.dao.RedisDAO;

public class RedisComandos<T> extends RedisDAO<T> {

    public RedisComandos(Class classe) {
        super(classe);
    }
}
