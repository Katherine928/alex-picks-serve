package com.katherine.alexPicks.dao;

import com.katherine.alexPicks.model.Fight;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.*;

@Component
public class JdbcFightDao implements FightDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcFightDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Fight> getAllFight() {
        List<Fight> fights = new ArrayList<>();

        String sql = "SELECT id, fight_no, player_name, salary FROM fights;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            Fight fight = mapRowToFight(results);
            fights.add(fight);
        }

        return fights;
    }

    @Override
    public void addFight(Fight fight) {
        String sql = "INSERT INTO fights (fight_no, player_name, salary) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, fight.getFight_No(), fight.getPlayName(), fight.getSalary());
    }

    @Override
    public void deleteFight(int id) {
        String sql = "DELETE FROM fights WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<List<Fight>> checkCombo(int k) {
        List<Fight> fights = getAllFight();
        List<List<Fight>> ans = new LinkedList<>();
        backtrack(fights, fights.get(0), new LinkedList<Fight>(), fights.size(), k, ans);
        return ans;
    }

    @Override
    public List<List<Fight>> getCombo() {
        List<List<Fight>> combos = checkCombo(6);
        List<List<Fight>> results = new ArrayList<>();
        for(List<Fight> item: combos) {
            Map<String, Fight> fightMap = new HashMap<>();
            for(Fight fight: item) {
                fightMap.put(fight.getFight_No(), fight);
            }

            if(fightMap.size() == 6 && !results.contains(item) && getTotalSalary(item).compareTo(new BigDecimal(50000)) <= 0) {

                results.add(item);
            }
        }
//        results = results.subList(1,5);
        return results;
    }


    @Override
    public BigDecimal getTotalSalary(List<Fight> fights) {
        BigDecimal total = new BigDecimal(0);
        for(Fight fight: fights) {
            total = total.add(fight.getSalary());
        }
        return total;
    }

    public static void backtrack(List<Fight> fights, Fight fight, LinkedList<Fight> current,int n, int k, List<List<Fight>> ans) {
        if(current.size() == k) {
            ans.add(new LinkedList<>(current));
        }
        for(int i = fights.indexOf(fight); i < n && current.size() < k; i++) {
            current.add(fights.get(i));
            if(i == n-1) {
                backtrack(fights, fights.get(i), current, n , k, ans);
            } else {
                backtrack(fights, fights.get(i+1), current, n , k, ans);
            }

            current.removeLast();
        }
    }




    public Fight mapRowToFight(SqlRowSet result) {
        Fight fight = new Fight();
        fight.setId(result.getInt("id"));
        fight.setFight_No(result.getString("fight_no"));
        fight.setPlayName(result.getString("player_name"));
        fight.setSalary(result.getBigDecimal("salary"));

        return fight;
    }
}
