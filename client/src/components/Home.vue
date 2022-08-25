<template>
  <div>
    <div class="input-container">
      <form class="form" v-on:submit:prevent="add">
        <div class="input-field">
          <label for="name">Name:</label>
          <input type="text" id="name" v-model="fight.playName" />
        </div>
        <div class="input-field">
          <label for="number">Fight No. </label>
          <input type="number" id="number" v-model="fight.fight_No" />
        </div>
        <div class="input-field">
          <label for="salary">Salary:</label>
          <input type="text" id="salary" v-model="fight.salary" />
        </div>
        <button class="button-27" role="button" type="submit" v-on:click="add">
          Add
        </button>
      </form>
    </div>
    <div class="list-container">
      <table>
        <thead id="header">
          <tr>
            <th>Fight No.</th>
            <th>Name</th>
            <th>Salary</th>
            <th>Delete</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="fight in fights" v-bind:key="fight.playName">
            <td>{{ fight.fight_No }}</td>
            <td>{{ fight.playName }}</td>
            <td>{{ formatPrice(fight.salary) }}</td>
            <td>
              <font-awesome-icon
                icon="fa-solid fa-xmark"
                :style="{ color: 'red' }"
                class="delete-icon"
                v-on:click="deleted(fight.id)"
              />
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import FightService from "@/service/Fights";

export default {
  name: "the-home",
  data() {
    return {
      fights: [],
      fight: {},
    };
  },
  methods: {
    getAllFights() {
      FightService.getAllFights().then((response) => {
        this.fights = response.data;
      });
    },
    add() {
      FightService.add(this.fight).then((response) => {
        if (response.status === 201 || response.status === 200) {
          this.getAllFights();
          this.fight = {};
        } else {
          alert("Opps! Something went wrong!");
        }
      });
    },
    deleted(id) {
      FightService.delete(id).then(() => {
        this.getAllFights();
      });
    },
    formatPrice(value) {
      let val = (value / 1).toFixed(2);
      return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
  created() {
    this.getAllFights();
  },
};
</script>

<style scoped>
.form {
  padding: 100px;
  display: flex;
  flex-direction: row;
  column-gap: 20px;
  justify-content: center;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2), 0 6px 20px rgba(0, 0, 0, 0.19);
  width: 80%;
  margin: 20px auto;
  font-size: large;
  font-weight: 700;
  align-items: baseline;
}
.input-field {
  display: flex;
  column-gap: 5px;
}
.button-27 {
  appearance: none;
  background-color: #000000;
  border: 2px solid #1a1a1a;
  border-radius: 15px;
  box-sizing: border-box;
  color: #ffffff;
  cursor: pointer;
  display: inline-block;
  font-family: Roobert, -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica,
    Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol";
  font-size: 16px;
  font-weight: 600;
  line-height: normal;
  margin: 0;
  min-height: 30px;
  min-width: 0;
  outline: none;
  padding: 5px 10px;
  text-align: center;
  text-decoration: none;
  transition: all 300ms cubic-bezier(0.23, 1, 0.32, 1);
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;

  will-change: transform;
}

.button-27:disabled {
  pointer-events: none;
}

.button-27:hover {
  box-shadow: rgba(0, 0, 0, 0.25) 0 8px 15px;
  transform: translateY(-2px);
}

.button-27:active {
  box-shadow: none;
  transform: translateY(0);
}
table {
  padding: 1.5em;
  border-collapse: collapse;
  width: 90%;
  margin: 0 auto;
}
thead {
  color: white;
  border-bottom: 2px solid rgb(221, 217, 217);
  height: 100px;
  background-color: black;
}
th,
td {
  padding: 1em;
  font-size: 1.05em;
  font-weight: 700;
}
tbody > tr:nth-child(even) {
  background-color: #dddddd;
}
tbody > tr:nth-child(odd) {
  background-color: #edeef1;
}
.delete-icon {
  cursor: pointer;
}
</style>
