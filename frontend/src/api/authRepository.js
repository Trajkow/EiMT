import axiosInstance from './axiosInstance';

export const login = (credentials) =>
  axiosInstance.post('/api/auth/login', credentials);

export const register = (data) =>
  axiosInstance.post('/api/auth/register', data);
