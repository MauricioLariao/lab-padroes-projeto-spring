package one.digitalinnovation.gof.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//CrudRepository é um padrao Strategy

@Repository
public interface EnderecotRepository extends CrudRepository<Endereco, String>{

}
