package com.katherine.alexPicks.dao;

import com.katherine.alexPicks.model.Fight;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.math.BigDecimal;
import java.util.List;

@Component
@CrossOrigin
public interface FightDao {
    List<Fight> getAllFight();
    void addFight(Fight fight);
    void deleteFight(int id);
    List<List<Fight>> checkCombo(int k);
    List<List<Fight>> getCombo();

    BigDecimal getTotalSalary(List<Fight> fights);
}
