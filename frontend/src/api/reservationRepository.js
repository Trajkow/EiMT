import axiosInstance from './axiosInstance';

export const getAllReservations = () =>
  axiosInstance.get('/api/reservations');

export const createReservation = (accommodationId, data) =>
  axiosInstance.post(`/api/reservations/reserve/${accommodationId}`, data);
