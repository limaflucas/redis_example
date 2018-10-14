package br.com.limaflucas.redis_example.dao;

import br.com.limaflucas.redis_example.dao.contrato.DAO;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;

public abstract class CassandraDAO<T> implements DAO<T> {

    Cluster cassCluster;
    Session cassSession;
    MappingManager gerenciador;
    Mapper<T> mapeador;

    public CassandraDAO(Class<T> classe) {
        cassCluster = Cluster.builder().addContactPoint("127.0.0.1").build();
        cassSession = cassCluster.connect("testes");
        gerenciador = new MappingManager(cassSession);
        mapeador = gerenciador.mapper(classe);
    }

    @Override
    public Object criar(T objeto) {
        mapeador.save(objeto);
        return true;
    }

    @Override
    public T recuperar(Object objeto) {
        return mapeador.get(objeto);
    }

    @Override
    public Object atualizar(T objeto) {
        return this.criar(objeto);
    }

    @Override
    public Object remover(Object objeto) {
        mapeador.delete(objeto);
        return true;
    }
}
