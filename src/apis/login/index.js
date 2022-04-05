import axios from '@apis/index';
const baseUrl ='';
const login = function(data) {
  return axios.post(`${baseUrl}/login`, JSON.stringify(data));
};
const updatePwd = function(data) {
  return axios.post(`${baseUrl}/updatePwd`, JSON.stringify(data));
};
const addUser = function(data) {
  return axios.post(`${baseUrl}/addUser`, JSON.stringify(data));
};
const getUserId = function(data) {
  return axios.post(`${baseUrl}/getUserId`, JSON.stringify(data));
};
export default {
  login,
  updatePwd,
  getUserId,
  addUser
};
