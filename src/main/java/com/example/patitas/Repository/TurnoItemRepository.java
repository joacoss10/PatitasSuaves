package com.example.patitas.Repository;

import com.example.patitas.Model.TurnoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoItemRepository extends JpaRepository<TurnoItem,Long> {
}
