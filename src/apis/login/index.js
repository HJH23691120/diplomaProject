import axios from '@apis/index';
const login = function(data) {
  return axios.post('/login', data);
};
const updatePwd = function(data) {
  return axios.post('/updatePwd', data);
};
const getUserId = function(data) {
  return axios.post('/getUserId', data);
};
export default {
  login,
  updatePwd,
  getUserId
};
