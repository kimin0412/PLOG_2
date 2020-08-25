import axios from 'axios';

const API_URL = 'http://i3a502.p.ssafy.io:8080/api/auth/';
//const API_URL = 'http://localhost:8080/api';

class AuthService {
  login(user) {
    return axios
      .post(API_URL + 'signin', {
        username: user.username,
        //email : user.email,
        password: user.password
      })
      .then(response => {
        if (response.data.accessToken) {
          localStorage.setItem('user', JSON.stringify(response.data));
        }
        if(response.status == 401){
          //console.log();
        }
        else if(response.status == 400){
          //console.log();
        }
        return response.data;
      });
  }
  
  edit(user){
    return axios.post(API_URL + 'editprofile', {
        username : user.email,
        password : user.password,
        birthday : user.birthday,
        phone : user.phone
    });
  }

  logout() {
    localStorage.removeItem('user');
  }

  register(user) {
    return axios.post(API_URL + 'signup', {
      username: user.username,
      email: user.email,
      password: user.password
    }).then(error =>{
      if(error.response.status == 400) {
        //console.log(error.response.status);
      }
      
    });
  }
}

export default new AuthService();
