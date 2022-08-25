<template>
  <div class="home">
    <the-home />
  </div>
</template>

<script>
// @ is an alias to /src

import TheHome from "../components/Home.vue";

export default {
  name: "HomeView",
  components: {
    TheHome,
  },
};
</script>
