// back end api

// header
export const HEADER = {
    'Content-Type': 'application/x-www-form-urlencoded'
}

// common prefix
const BACKEND = "http://localhost:8080/jobsite/";

// employer
export const GET_JOB = BACKEND + 'job/getjob/';
export const GET_JOB_FOR = BACKEND + 'job/employer/';
export const EDIT_JOB = BACKEND + 'job/edit';
export const ADD_JOB = BACKEND + 'job/add';


// candidate
export const GET_CANS_FOR = BACKEND + 'candidate/getapplicants/';
export const APPLY = BACKEND + 'candidate/apply';
export const GET_CAN = BACKEND + 'candidate/view/';
export const GET_CAN_SKILLS = BACKEND + 'candidate/skills/';
export const GET_APPLIED = BACKEND + 'job/candidate/';
export const GET_ALL = BACKEND + 'job/all';


//user
export const SIGNUP = BACKEND + 'authenticate/registerProcess';
export const LOGIN = BACKEND + 'authenticate/loginProcess';
export const EDIT_USER = BACKEND + 'user/edit';
