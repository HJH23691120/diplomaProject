const rules = {
  userRole: [{ required: true, message: '用户角色不能为空', trigger: 'change' }],
  useClass: [
    { required: true, message: '用户班级不能为空', trigger: 'change' }
  ],
  userGender: [
    { required: true, message: '用户性别不能为空', trigger: 'change' }
  ],
  userName: [{ required: true, message: '账号不能为空', trigger: 'change' }],
  userId: [{ required: true, message: '账号不能为空', trigger: 'change' }],
  userPwd: [{ required: true, message: '密码不能为空', trigger: 'change' }],
  newPwd: [{ required: true, message: '密码不能为空', trigger: 'change' }],
};
const userRoleList = [
  {
    label: '学生',
    key: '4'
  },
  {
    label: '校内导师',
    key: '3'
  },
  {
    label: '企业导师',
    key: '2'
  },
  {
    label: '管理员',
    key: '1'
  }
];
const userGenderList = ['男', '女'];
module.exports = {
  rules,
  userRoleList,
  userGenderList
};
