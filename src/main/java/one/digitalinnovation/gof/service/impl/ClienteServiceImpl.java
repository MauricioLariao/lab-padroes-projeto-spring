package one.digitalinnovation.gof.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.ClienteRepository;
import one.digitalinnovation.gof.model.Endereco;
import one.digitalinnovation.gof.model.EnderecotRepository;
import one.digitalinnovation.gof.service.ClienteService;
import one.digitalinnovation.gof.service.ViaCepService;

/** 
 * Implementação da <b>Strategy</b> {@link ClienteService} a qual pode ser 
 * injetada pleo Spring (via {@link Autowired}. Com isso, essa classe é um 
 * {@link Service}, ela será tratada como um <b> Singleton </b>.
 * multiplas implementações dessa mesma interface.  
 * 
 * @author mauriciolariao
 */

@Service
public class ClienteServiceImpl implements ClienteService{

	// Singleton: Injetar os componentes do Spring com @autowired
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecotRepository enderecoRepository;
	@Autowired
	private ViaCepService viaCepService;
	
	// Strategy: Impplementar os métodos definidos na interface
	// Facade: Abstrair integrações com subsistema, provendo uma interface simples.
	
	
	
	@Override
	public Iterable<Cliente> buscarTodos() {
		
		return clienteRepository.findAll();
	}

	@Override
	public Cliente buscarPorId(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.get();
	}

	@Override
	public void inserir(Cliente cliente) {
		
		salvarClienteComCep(cliente);
	}


	@Override
	public void atualizar(Long id, Cliente cliente) {
		Optional<Cliente> clienteBd = clienteRepository.findById(id);
		if (clienteBd.isPresent()) {
			salvarClienteComCep(cliente);
		}
		
	}

	@Override
	public void deletar(Long id) {
		clienteRepository.deleteById(id);
		
	}
	
	private void salvarClienteComCep(Cliente cliente) {
		String cep = cliente.getEndereco().getCep();
		Endereco endereco =  enderecoRepository.findById(cep).orElseGet(() -> {
			Endereco novoEndereco = viaCepService.consultarCep(cep);
			enderecoRepository.save(novoEndereco);
			return novoEndereco;
		});
		cliente.setEndereco(endereco);
		clienteRepository.save(cliente);
	}

	
}
