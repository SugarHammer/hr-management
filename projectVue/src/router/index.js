import Vue from 'vue'
import Router from 'vue-router'

import Index from '../views/Index';
import Resume from "../views/resume/Resume";
import Login from "../views/Login";
import Register from "../views/Register";
import ResumePage from "../views/resume/ResumePage";
import Data from "../views/company/Data";
import Us from "../views/company/Us";
import AdminIndex from "../views/admin/AdminIndex";
import User from "../views/admin/User";
import AddUser from "../views/admin/user/AddUser";
import RecruitList from "../views/admin/recruit/RecruitList";
import AddRecruit from "../views/admin/recruit/AddRecruit";
import RecruitUser from "../views/admin/recruit/RecruitUser";
import DeptList from "../views/admin/dept/DeptList";
import AddDept from "../views/admin/dept/AddDept";
import PositionList from "../views/admin/position/PositionList";
import AddPosition from "../views/admin/position/AddPosition";
import EmpList from "../views/admin/emp/EmpList";
import AddEmp from "../views/admin/emp/AddEmp";
import SalaryList from "../views/admin/salary/SalaryList";
import AddSalary from "../views/admin/salary/AddSalary";
import TrainList from "../views/admin/train/TrainList";
import AddTrain from "../views/admin/train/AddTrain";
import TrainUser from "../views/admin/train/TrainUser";
import AdminData from "../views/admin/AdminData";
import StaffIndex from "../views/staff/StaffIndex";
import ProfileInfo from "../views/staff/profile/ProfileInfo";
import ProfileSalary from "../views/staff/profile/ProfileSalary";
import ProfileTrain from "../views/staff/profile/ProfileTrain";
import EmpInfo from "../views/staff/EmpInfo";
import TrainInfo from "../views/staff/TrainInfo";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: '用户端主页',
      component: Index
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
    {
      path: '/resume',
      name: '简历',
      component: Resume
    },
    {
      path: '/resumePage',
      name: '招聘详情',
      component: ResumePage
    },
    {
      path: '/data',
      name: '数据可视化',
      component: Data
    },
    {
      path: '/us',
      name: '我的应聘',
      component: Us
    },
    {
      path: '/admin/index',
      name: '首页',
      component: AdminIndex,
      children: [
        {
          path: '/admin/user',
          name: '用户管理',
          component: User,
        },
        {
          path: '/admin/user/add',
          name: '添加用户',
          component: AddUser,
        },
        {
          path: '/admin/recruit/list',
          name: '招聘信息',
          component: RecruitList,
        },
        {
          path: '/admin/recruit/add',
          name: '添加招聘信息',
          component: AddRecruit,
        },
        {
          path: '/admin/recruit/user',
          name: '报名信息',
          component: RecruitUser,
        },
        {
          path: '/dept/list',
          name: '部门管理',
          component: DeptList,
        },
        {
          path: '/dept/add',
          name: '添加部门',
          component: AddDept,
        },
        {
          path: '/position/list',
          name: '职位管理',
          component: PositionList,
        },
        {
          path: '/position/add',
          name: '添加职位',
          component: AddPosition,
        },
        {
          path: '/emp/list',
          name: '员工管理',
          component: EmpList,
        },
        {
          path: '/emp/add',
          name: '添加员工',
          component: AddEmp,
        },
        {
          path: '/salary/list',
          name: '薪资管理',
          component: SalaryList,
        },
        {
          path: '/salary/add',
          name: '添加薪资',
          component: AddSalary,
        },
        {
          path: '/train/list',
          name: '培训活动',
          component: TrainList,
        },
        {
          path: '/train/add',
          name: '添加活动',
          component: AddTrain,
        },
        {
          path: '/train/user',
          name: '活动报名信息',
          component: TrainUser,
        },
        {
          path: '/admin/data',
          name: '数据可视化报表',
          component: AdminData,
        }
      ]
    },
    {
      path: '/staff/index',
      name: '员工端首页',
      component: StaffIndex,
      children: [
        {
          path: '/profile/info',
          name: '我的资料',
          component: ProfileInfo
        },
        {
          path: '/profile/salary',
          name: '我的工资',
          component: ProfileSalary
        },
        {
          path: '/profile/train',
          name: '我的报名',
          component: ProfileTrain
        },
        {
          path: '/staff/emp',
          name: '同事信息',
          component: EmpInfo
        },
        {
          path: '/staff/train',
          name: '培训信息',
          component: TrainInfo
        }
      ]
    }
  ]
})
