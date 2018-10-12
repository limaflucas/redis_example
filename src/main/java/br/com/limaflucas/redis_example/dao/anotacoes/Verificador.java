package br.com.limaflucas.redis_example.dao.anotacoes;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

public class Verificador {

    public static <T> Method analisarChaves(Class<T> classe) {
        System.out.println("analisar");
        Method metodoAnotado = null;

        try {
            for (PropertyDescriptor propertyDescriptor : Introspector.getBeanInfo(classe).getPropertyDescriptors()) {
                if(propertyDescriptor.getReadMethod().getAnnotation(ChaveRedis.class) != null)
                    metodoAnotado = propertyDescriptor.getReadMethod();
            }

        } catch (IntrospectionException e) {
            e.printStackTrace();
        }

        if (metodoAnotado == null)
            throw new IllegalArgumentException(String.format("Anotacao %s nao encontrada em %s", ChaveRedis.class.getSimpleName(), classe));

        return metodoAnotado;
    }
}
