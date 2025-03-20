package edu.rims.craft_verse.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import edu.rims.craft_verse.constant.WidgetStatus;
import edu.rims.craft_verse.entity.Widget;

public interface WidgetRepository  extends JpaRepository<Widget, String>{

    List<Widget> findByWidgetStatus(WidgetStatus
    widgetStatus, Sort sort);
    
}
