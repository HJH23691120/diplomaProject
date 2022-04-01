import axios from '@apis/index';
const getUser = function(data) {
  return axios.post('/getUser', data);
};
const updateUser = function(data) {
  return axios.post('/updateUser', data);
};
export default {
  getUser,
  updateUser
};
