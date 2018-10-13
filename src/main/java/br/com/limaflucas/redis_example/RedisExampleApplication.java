package br.com.limaflucas.redis_example;

import br.com.limaflucas.redis_example.dao.anotacoes.Verificador;
import br.com.limaflucas.redis_example.dominio.Saldo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedisExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisExampleApplication.class, args);
	}
}

//public class RedisExampleApplication {
//    public static void main(String[] args) {
//        Verificador.analisarChaves(Saldo.class);
//    }
//}
