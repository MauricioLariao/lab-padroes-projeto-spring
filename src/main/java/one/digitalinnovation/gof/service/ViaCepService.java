package one.digitalinnovation.gof.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import one.digitalinnovation.gof.model.Endereco;


/** 
 * Client HTTP, criado via <b>OpenFeign</> para consumo da API do <b>ViaCEP<b/>
 * 
 * @see <a href="https://spring.io/projects/spring-cloud-openfeign">Spring Cloud OpenFeign</a>
 * @see <a href="https://viacep.com.br">ViaCEP</a>
 *   
 * @author mauriciolariao
 *
 */

@FeignClient(name="viacep", url = "https://viacep.com.br/ws")
public interface ViaCepService {

	//@RequestMapping(method = RequestMethod.GET, value = "/{cep}/json/")
	@GetMapping("/{cep}/json/")
	Endereco consultarCep(@PathVariable("cep") String cep);
}
