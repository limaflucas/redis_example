package br.com.limaflucas.redis_example.dao.anotacoes;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

public class Verificador {

    public static <T> Method analisarChaves(Class<T> classe) {
        Method metodoAnotado = null;
        PropertyDescriptor[] propertyDescriptor;

        try {
            propertyDescriptor = Introspector.getBeanInfo(classe).getPropertyDescriptors();
//            Pulamos o primeiro elemento pois traz infos da classe
            for (int i = 1; i < propertyDescriptor.length; i++) {
                if (classe.getDeclaredField(propertyDescriptor[i].getName()).getAnnotation(ChaveRedis.class) != null)
                    metodoAnotado = propertyDescriptor[i].getReadMethod();
            }
        } catch (IntrospectionException|NoSuchFieldException e) {
            e.printStackTrace();
        }

        if (metodoAnotado == null)
            throw new IllegalArgumentException(String.format("Anotacao %s nao encontrada em %s", ChaveRedis.class.getSimpleName(), classe));

        System.out.println(metodoAnotado);
        return metodoAnotado;
    }
}
