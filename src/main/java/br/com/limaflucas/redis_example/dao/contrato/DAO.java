package br.com.limaflucas.redis_example.dao.contrato;

public interface DAO<T> {

    public Object criar(T objeto);
    public T recuperar(Object objeto);
    public Object atualizar(T objeto);
    public Object remover(Object objeto);
}
