import axios from '@apis/index';
const login = function(data) {
  return axios.post('/login', data);
};
export default {
  login,
};
