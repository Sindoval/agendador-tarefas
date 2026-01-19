package com.javanauta.agendador_tarefas.infrastructure.repository;

import com.javanauta.agendador_tarefas.infrastructure.entity.TarefaEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefasRepository extends MongoRepository<TarefaEntity, String> {

    List<TarefaEntity> findByDataEventoBetween(LocalDateTime dataInicial, LocalDateTime dataFinal);

    List<TarefaEntity> findByEmailUsuario(String email);
}
