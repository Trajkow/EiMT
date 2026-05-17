import { useState, useEffect, useCallback } from 'react';
import {
  getAllAccommodations,
  createAccommodation,
  updateAccommodation,
  deleteAccommodation,
} from '../api/accommodationRepository';

export const useAccommodations = () => {
  const [accommodations, setAccommodations] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const fetchAll = useCallback(async () => {
    setLoading(true);
    setError(null);
    try {
      const { data } = await getAllAccommodations();
      setAccommodations(data);
    } catch (err) {
      setError(err.response?.data?.message || 'Failed to load accommodations');
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => { fetchAll(); }, [fetchAll]);

  const create = useCallback(async (dto) => {
    await createAccommodation(dto);
    await fetchAll();
  }, [fetchAll]);

  const update = useCallback(async (id, dto) => {
    await updateAccommodation(id, dto);
    await fetchAll();
  }, [fetchAll]);

  const remove = useCallback(async (id) => {
    await deleteAccommodation(id);
    await fetchAll();
  }, [fetchAll]);

  return { accommodations, loading, error, refetch: fetchAll, create, update, remove };
};
