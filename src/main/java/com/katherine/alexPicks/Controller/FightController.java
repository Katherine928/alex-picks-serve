package com.katherine.alexPicks.Controller;

import com.katherine.alexPicks.dao.FightDao;
import com.katherine.alexPicks.model.Fight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@CrossOrigin
public class FightController {

    @Autowired(required=true)
    private FightDao fightDao;

    @RequestMapping(path = "/fights", method = RequestMethod.GET)
    public List<Fight> getAllFights() {
        return fightDao.getAllFight();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public void addFight(@RequestBody Fight fight) {
       fightDao.addFight(fight);
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteFight(@PathVariable int id) {
        fightDao.deleteFight(id);
    }

    @RequestMapping(path = "/combos", method = RequestMethod.GET)
    public List<List<Fight>> getCombos() {
       return fightDao.getCombo();
    }

}
