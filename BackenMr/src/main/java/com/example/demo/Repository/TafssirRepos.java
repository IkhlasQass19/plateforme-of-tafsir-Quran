package com.example.demo.Repository;

import com.example.demo.Model.Tafssir;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TafssirRepos extends JpaRepository<Tafssir, Integer> {
    // Ajoutez des méthodes personnalisées si nécessaire
}
