package edu.rims.craft_verse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.rims.craft_verse.entity.Widget;

public interface WidgetRepository  extends JpaRepository<Widget, String>{
    
}
