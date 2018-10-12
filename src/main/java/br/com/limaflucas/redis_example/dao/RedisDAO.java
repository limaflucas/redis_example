package br.com.limaflucas.redis_example.dao;

import br.com.limaflucas.redis_example.dao.anotacoes.Verificador;
import br.com.limaflucas.redis_example.dao.contrato.DAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;


public abstract class RedisDAO<T> implements DAO<T> {

    private final String formatoFixoID = "id:%s";

    private Class<T> classe;
    private Method metodoID;

    private Jedis jedis;
    private ObjectMapper mapeador = new ObjectMapper();

    public RedisDAO(Class<T> classe) {
        this.metodoID = Verificador.analisarChaves(classe);
        this.classe = classe;
        this.jedis = new Jedis();
    }

    private String getID(T objeto) {
        String chaveID = null;

        try {
            chaveID = String.format(this.formatoFixoID, this.metodoID.invoke(objeto).toString());
            return chaveID;
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return chaveID;
    }

    @Override
    public Object criar(T objeto) {
        Map<String,String> conversao;

        conversao = mapeador.convertValue(objeto, Map.class);
        return jedis.hmset(this.getID(objeto), conversao);

    }

    @Override
    public T recuperar(Object objeto) {
        Map<String,String> resposta;

        if(objeto.getClass() == this.classe)
            resposta = jedis.hgetAll(this.getID(this.classe.cast(objeto)));
        else
            resposta = jedis.hgetAll(String.format(this.formatoFixoID, objeto.toString()));

        return this.mapParaClasse(resposta);
    }

    @Override
    public Object atualizar(T objeto) {
        return this.criar(objeto);
    }

    @Override
    public Object remover(Object objeto) {
        Long resposta;

        if(objeto.getClass() == this.classe)
            resposta = jedis.del(this.getID(this.classe.cast(objeto)));
        else
            resposta = jedis.del(String.format(this.formatoFixoID, objeto.toString()));

        return resposta;
    }

    private T mapParaClasse(Map<String,String> map) {
        return mapeador.convertValue(map, this.classe);
    }

}
