import axios from '@apis/index';
const updateWeek = function(data) {
  // 周记审核
  return axios.post('/updateWeek', JSON.stringify(data));
};
const getWeek = function(data) {
  // 获取周记
  return axios.post('/getWeek', JSON.stringify(data));
};
const getPracticeApply = function(data) {
  // 查询实习申请
  return axios.post('/getPracticeApply', JSON.stringify(data));
};
const applyPractice = function(data) {
  // 提交实习申请
  return axios.post('/applyPractice', JSON.stringify(data));
};
const getWeekNum = function(data) {
  // 获取周记条数
  return axios.post('/getWeekNum', JSON.stringify(data));
};
const addEvaluate = function(data) {
  // 提交实习评价
  return axios.post('/addEvaluate', JSON.stringify(data));
};
const updateEvaluate = function(data) {
  // 提交学生评价
  return axios.post('/updateEvaluate', JSON.stringify(data));
};
const getEvaluate = function(data) {
  // 获取实习评价
  return axios.post('/getEvaluate', JSON.stringify(data));
};
const addWeek = function(data) {
  // 添加周记
  return axios.post('/addWeek', JSON.stringify(data));
};
const updatePracticeApply = function(data) {
  // 添加周记
  return axios.post('/updatePracticeApply', JSON.stringify(data));
};
const getFirmTutro = function(data) {
  return axios.post('/getFirmTutro', JSON.stringify(data));
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
  updatePracticeApply,
  getFirmTutro
};
