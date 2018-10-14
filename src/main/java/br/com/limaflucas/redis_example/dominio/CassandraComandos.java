package br.com.limaflucas.redis_example.dominio;

import br.com.limaflucas.redis_example.dao.CassandraDAO;

public class CassandraComandos<T> extends CassandraDAO<T> {

    public CassandraComandos(Class<T> classe) {
        super(classe);
    }
}
