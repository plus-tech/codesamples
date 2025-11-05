import axios from 'axios';

import {
  ApiKeyHeader,
  ApiAccessKey,
  RestEndpoint,
  PathFindAllDpts,
  PathFindDptById,
  PathInsertDpt,
  PathUpdateDpt,
  PathDeleteDpt,
} from "../util/constant.ts";

export function ApiFindAllDpts() {
  console.log(">> entering ApiFindAllDpts.");

  const axiosInstance = getAxios();

  return axiosInstance.get(PathFindAllDpts);
}

export function ApiFindDptById(department_id) {
  console.log(">> entering ApiFindDptById.");

  const axiosInstance = getAxios();

  const apipath = PathFindDptById + '/' + department_id;

  return axiosInstance.get(apipath);
}

export function ApiInsertDpt(dpt) {
  console.log(">> entering ApiInsertDpt.");

  const axiosInstance = getAxios();

  console.log(">> axios instance: ", axiosInstance);

  return axiosInstance.post(PathInsertDpt, dpt);
}

export function ApiUpdateDpt(dpt) {
  console.log(">> entering ApiUpdateDpt.");

  const axiosInstance = getAxios();

  console.log(">> axios instance: ", axiosInstance);

  return axiosInstance.put(PathUpdateDpt, dpt);
}

export function ApiDeleteDpt(department_id) {
  console.log(">> entering ApiDeleteDpt.");

  const axiosInstance = getAxios();

  const apipath = PathDeleteDpt + '/' + department_id;

  return axiosInstance.delete(apipath);
}

/*
 * Create an Axios instance
 */
function getAxios(){
  return axios.create({
      baseURL: RestEndpoint,
      headers: {
          "Api-Key": ApiAccessKey,
      },
  });
}
