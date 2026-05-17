import axiosInstance from './axiosInstance';

export const getAllCountries = () =>
  axiosInstance.get('/api/countries');

export const getCountryById = (id) =>
  axiosInstance.get(`/api/countries/${id}`);

export const createCountry = (data) =>
  axiosInstance.post('/api/countries/add', data);

export const updateCountry = (id, data) =>
  axiosInstance.put(`/api/countries/edit/${id}`, data);

export const deleteCountry = (id) =>
  axiosInstance.delete(`/api/countries/delete/${id}`);
