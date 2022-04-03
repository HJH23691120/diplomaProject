import axios from '@apis/index';
const updateWeek = function(data) {
  // 周记审核
  return axios.post('/updateWeek', data);
};
const getWeek = function(data) {
  // 获取周记
  return axios.post('/getWeek', data);
};
const getPracticeApply = function(data) {
  // 查询实习申请
  return axios.post('/getPracticeApply', data);
};
const applyPractice = function(data) {
  // 提交实习申请
  return axios.post('/applyPractice', data);
};
const getWeekNum = function(data) {
  // 获取周记条数
  return axios.post('/getWeekNum', data);
};
const addEvaluate = function(data) {
  // 提交实习评价
  return axios.post('/addEvaluate', data);
};
const updateEvaluate = function(data) {
  // 提交学生评价
  return axios.post('/updateEvaluate', data);
};
const getEvaluate = function(data) {
  // 获取实习评价
  return axios.post('/getEvaluate', data);
};
const addWeek = function(data) {
  // 添加周记
  return axios.post('/addWeek', data);
};
const updatePracticeApply = function(data) {
  // 添加周记
  return axios.post('/updatePracticeApply', data);
};
export default {
  updateWeek,
  getWeek,
  getPracticeApply,
  applyPractice,
  getWeekNum,
  addEvaluate,
  updateEvaluate,
  getEvaluate,
  addWeek,
  updatePracticeApply
};
