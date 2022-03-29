import axios from '@apis/index';
const login = function(data) {
  return axios.post('user/login', data);
};
export default {
  login,
};
