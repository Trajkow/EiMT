import axiosInstance from './axiosInstance';

export const getAllHosts = () =>
  axiosInstance.get('/api/hosts');

export const getHostById = (id) =>
  axiosInstance.get(`/api/hosts/${id}`);

export const createHost = (data) =>
  axiosInstance.post('/api/hosts/add', data);

export const updateHost = (id, data) =>
  axiosInstance.put(`/api/hosts/edit/${id}`, data);

export const deleteHost = (id) =>
  axiosInstance.delete(`/api/hosts/delete/${id}`);
