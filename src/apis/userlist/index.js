import axios from '@apis/index';
const getUser = function(data) {
  // 获取单个用户信息
  return axios.post('/getUser', JSON.stringify(data));
};
const updateUser = function(data) {
  // 更新用户信息
  return axios.post('/updateUser', JSON.stringify(data));
};
const getUserList = function(data) {
  // 获取用户信息
  return axios.post('/getUserList', JSON.stringify(data));
};
const addUser = function(data) {
  return axios.post('/addUser', JSON.stringify(data));
};
const delUser = function(data) {
  return axios.post('/delUser', JSON.stringify(data));
};
export default {
  getUser,
  updateUser,
  getUserList,
  addUser,
  delUser
};
