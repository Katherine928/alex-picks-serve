import axios from "axios";
const http = axios.create({
  baseURL: "http://localhost:8080",
});

export default {
  getAllFights() {
    return http.get("/fights");
  },
  add(fight) {
    return http.post("/add", fight);
  },
  delete(id) {
    return http.delete("/delete/" + id);
  },
  getCombos() {
    return http.get("/combos");
  },
  getTotalSalary(fights) {
    return http.get("/total", fights);
  },
};
