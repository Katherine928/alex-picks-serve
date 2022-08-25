<template>
  <div>
    <div class="input-container">
      <form action="#" class="form">
        <div>
          <h3>
            There are
            <p class="result">{{ combos.length }}</p>
            results in total.
          </h3>
        </div>
        <!-- <div>
          <div class="input-field">
            <label for="numOfCombo">How many combo do you want to check:</label>
            <input type="text" id="numOfCombo" />
          </div>
          <button class="button-27" role="button">Check Combo</button>
        </div> -->
      </form>
    </div>
    <div class="combo-container">
      <div class="card" v-for="combo in combos" v-bind:key="combo.id">
        <table>
          <thead id="header">
            <tr>
              <th>Fight No.</th>
              <th>Name</th>
              <th>Salary</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="fight in combo" v-bind:key="fight.id">
              <td>{{ fight.fight_No }}</td>
              <td>{{ fight.playName }}</td>
              <td>{{ formatPrice(fight.salary) }}</td>
            </tr>
          </tbody>
          <tfoot>
            <tr>
              <td colspan="3">
                Total Salary:
                <div class="total">
                  {{ formatPrice(getTotalSalary(combo)) }}
                </div>
              </td>
            </tr>
          </tfoot>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import FightService from "@/service/Fights";
export default {
  name: "the-combo",
  data() {
    return {
      combos: [],
    };
  },
  methods: {
    getAllCombos() {
      FightService.getCombos().then((response) => {
        this.combos = response.data;
      });
    },
    getTotalSalary(combo) {
      let sum = 0;
      combo.forEach((element) => {
        sum += element.salary;
      });
      return sum;
    },
    formatPrice(value) {
      let val = (value / 1).toFixed(2);
      return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
  created() {
    this.getAllCombos();
  },
};
</script>

<style scoped>
table {
  padding: 1.5em;
  border-collapse: collapse;
  width: 100%;
  margin: 0 auto;
  box-shadow: #1c3879 0px 0px 0px 3px;
}
thead {
  border-bottom: 2px solid hsla(200, 56%, 78%, 0.063);
  height: 80px;
  background-color: #1c3879;
  color: white;
}
th,
td {
  padding: 2.5em;
  font-size: 1.05em;
  font-weight: 700;
}
tbody > tr:nth-child(even) {
  background-color: #607eaa44;
}
tbody > tr:nth-child(odd) {
  background-color: #607eaa76;
}
.combo-container {
  display: flex;
  flex-direction: row;
  column-gap: 50px;
  row-gap: 20px;
  width: 80%;
  margin: 50px auto;
  justify-content: space-around;
  flex-wrap: wrap;
}
tfoot {
  text-align: right;
}
form {
  display: flex;
  flex-direction: column;
}
.form {
  padding: 50px;
  display: flex;
  flex-direction: row;
  column-gap: 20px;
  justify-content: center;
  box-shadow: rgba(3, 102, 214, 0.3) 0px 0px 0px 3px;
  width: 80%;
  margin: 20px auto;
  font-size: large;
  font-weight: 700;
  align-items: baseline;
}
.result {
  font-size: 50px !important;
  color: red;
  display: inline;
}
.card {
  width: 30%;
}
.total {
  color: red;
  display: inline;
}
</style>
