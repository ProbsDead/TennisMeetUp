<template>
  <div class="background">
    <div id="register" class="text-center">
      <form class="form-register" @submit.prevent="register">
        <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
        <div class="alert alert-danger" role="alert" v-if="registrationErrors">
          {{ registrationErrorMsg }}
        </div>
        <label for="firstname">First Name</label>
        <input
          type="text"
          id="firstname"
          class="form-control"
          placeholder="First Name"
          v-model="user.firstName"
          required
          autofocus
        />
        <label for="lastname">Last Name</label>
        <input
          type="text"
          id="lastname"
          class="form-control"
          placeholder="Last Name"
          v-model="user.lastName"
          required
          autofocus
        />
        <label for="username" class="sr-only">Username</label>
        <input
          type="text"
          id="username"
          class="form-control"
          placeholder="Username"
          v-model="user.username"
          required
          autofocus
        />
        <label for="password" class="sr-only">Password</label>
        <input
          type="password"
          id="password"
          class="form-control"
          placeholder="Password"
          v-model="user.password"
          required
        />
        <input
          type="password"
          id="confirmPassword"
          class="form-control"
          placeholder="Confirm Password"
          v-model="user.confirmPassword"
          required
        />

        <input
          type="text"
          id="email"
          class="form-control"
          placeholder="Email"
          v-model="user.email"
          required
        />

        <select 
          id="state"
          v-model="user.state"
          placeholder="State"
          required>
            <option value="" disabled>State</option>
            <option value="AL">AL</option>
            <option value="AK">AK</option>
            <option value="AR">AR</option>
            <option value="AZ">AZ</option>
            <option value="CA">CA</option>
            <option value="CO">CO</option>
            <option value="CT">CT</option>
            <option value="DC">DC</option>
            <option value="DE">DE</option>
            <option value="FL">FL</option>
            <option value="GA">GA</option>
            <option value="HI">HI</option>
            <option value="IA">IA</option>
            <option value="ID">ID</option>
            <option value="IL">IL</option>
            <option value="IN">IN</option>
            <option value="KS">KS</option>
            <option value="KY">KY</option>
            <option value="LA">LA</option>
            <option value="MA">MA</option>
            <option value="MD">MD</option>
            <option value="ME">ME</option>
            <option value="MI">MI</option>
            <option value="MN">MN</option>
            <option value="MO">MO</option>
            <option value="MS">MS</option>
            <option value="MT">MT</option>
            <option value="NC">NC</option>
            <option value="NE">NE</option>
            <option value="NH">NH</option>
            <option value="NJ">NJ</option>
            <option value="NM">NM</option>
            <option value="NV">NV</option>
            <option value="NY">NY</option>
            <option value="ND">ND</option>
            <option value="OH">OH</option>
            <option value="OK">OK</option>
            <option value="OR">OR</option>
            <option value="PA">PA</option>
            <option value="RI">RI</option>
            <option value="SC">SC</option>
            <option value="SD">SD</option>
            <option value="TN">TN</option>
            <option value="TX">TX</option>
            <option value="UT">UT</option>
            <option value="VT">VT</option>
            <option value="VA">VA</option>
            <option value="WA">WA</option>
            <option value="WI">WI</option>
            <option value="WV">WV</option>
            <option value="WY">WY</option>
        </select>

        <input
          type="text"
          id="city"
          class="form-control"
          placeholder="City"
          v-model="user.city"
          required
        />
        <router-link :to="{ name: 'login' }">Have an account?</router-link>
        <button class="btn btn-lg btn-primary btn-block" type="submit">
          Create Account
        </button>
      </form>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "register",
  data() {
    return {
      user: {
        firstName: "",
        lastName: "",
        username: "",
        password: "",
        confirmPassword: "",
        email: "",
        state: "",
        city: "",
        role: "user",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style scoped>
#register {
  margin-top: 100px;
  margin-bottom: 100px;
}

.form-register {
  display: grid;
  gap: 10px;
  max-width: 300px;
  margin: 0 auto;
  padding: 20px;
  border-radius: 5px;
  font-family: "Dosis", monospace, sans-serif;
  background-color: rgb(236, 245, 226);
  border: solid 4px;
  border-radius: 25px;
}

router-link {
  padding-left: 15px;
}

.alert {
  margin-bottom: 20px;
}

label {
  display: none;
}

input[type="text"],
input[type="password"],
#state {
  margin-bottom: 10px;
  border-radius: 5px;
  padding: 10px;
  background-color: #ccccff;
  color: #333;
  font-family: "Dosis", monospace, sans-serif;
}

router-link {
  display: block;
  margin-bottom: 10px;
}

button[type="submit"] {
  margin-top: 10px;
  background-color: #89c36f;
  color: #fff;
  padding: 15px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  font-family: "Dosis", monospace, sans-serif;
}

button[type="submit"]:hover {
  background-color: #2e7d32;
}

.background {
  font-family: "Dosis", monospace, sans-serif;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
  padding-bottom: 50px;
  opacity: 0.9;
  font-size: 1.1em;
  padding-left: 5%;
}
</style>
