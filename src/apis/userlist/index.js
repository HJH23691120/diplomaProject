import axios from '@apis/index';
const getUser = function(data) {
  // 获取单个用户信息
  return axios.post('/getUser', data);
};
const updateUser = function(data) {
  // 更新用户信息
  return axios.post('/updateUser', data);
};
const getUserList = function(data) {
  // 获取用户信息
  return axios.post('/getUserList', data);
};
export default {
  getUser,
  updateUser,
  getUserList
};
