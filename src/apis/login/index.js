import axios from '@apis/index';
const login = function(data) {
  return axios.post('/login', data);
};
const updatePwd = function(data) {
  return axios.post('/updatePwd', data);
};
const addUser = function(data) {
  return axios.post('/addUser', data);
};
const getUserId = function(data) {
  return axios.post('/getUserId', data);
};
export default {
  login,
  updatePwd,
  getUserId,
  addUser
};
