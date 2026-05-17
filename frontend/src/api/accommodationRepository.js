import axiosInstance from './axiosInstance';

export const getAllAccommodations = () =>
  axiosInstance.get('/api/a');

export const getAccommodationById = (id) =>
  axiosInstance.get(`/api/a/${id}`);

export const createAccommodation = (data) =>
  axiosInstance.post('/api/a/add', data);

export const updateAccommodation = (id, data) =>
  axiosInstance.put(`/api/a/edit/${id}`, data);

export const deleteAccommodation = (id) =>
  axiosInstance.delete(`/api/a/delete/${id}`);
