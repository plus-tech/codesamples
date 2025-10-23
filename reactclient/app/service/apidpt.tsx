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
} from "../util/constant.tsx";

export function ApiFindAllDpts() {
  console.log(">> entering ApiFindAllDpts.");

  const axiosInstance = axios.create({
      baseURL: RestEndpoint,
      headers: {
          'Api-Key': ApiAccessKey,
      },
  });

  return axiosInstance.get(PathFindAllDpts);
}

export function ApiFindDptById(department_id) {
  console.log(">> entering ApiFindDptById.");

  const axiosInstance = axios.create({
      baseURL: RestEndpoint,
      headers: {
          'Api-Key': ApiAccessKey,
      },
  });

  const apipath = PathFindDptById + '/' + department_id;

  return axiosInstance.get(apipath);
}

export function ApiInsertDpt(dpt) {
  console.log(">> entering ApiInsertDpt.");

  const axiosInstance = axios.create({
      baseURL: RestEndpoint,
      headers: {
          'Api-Key': ApiAccessKey,
      },
  });

  console.log(">> axios instance: ", axiosInstance);

  return axiosInstance.post(PathInsertDpt, dpt);
}

export function ApiDeleteDpt(department_id) {
  console.log(">> entering ApiDeleteDpt.");

  const axiosInstance = axios.create({
      baseURL: RestEndpoint,
      headers: {
          'Api-Key': ApiAccessKey,
      },
  });

  const apipath = PathDeleteDpt + '/' + department_id;

  return axiosInstance.delete(apipath);
}

/*
import axios from 'axios';

const API_KEY = 'your_api_key_here'; // Replace with your actual key

const axiosInstance = axios.create({
    baseURL: 'http://localhost:8080/api', // Your Spring Boot API base URL
    headers: {
        'X-API-KEY': API_KEY,
    },
});

// Use axiosInstance for your API calls
axiosInstance.get('/protected-endpoint')
    .then(response => console.log(response.data))
    .catch(error => console.error('Error:', error));
*/
