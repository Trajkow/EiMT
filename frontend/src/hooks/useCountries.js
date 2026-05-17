import { useState, useEffect, useCallback } from 'react';
import {
  getAllCountries,
  createCountry,
  updateCountry,
  deleteCountry,
} from '../api/countryRepository';

export const useCountries = () => {
  const [countries, setCountries] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const fetchAll = useCallback(async () => {
    setLoading(true);
    setError(null);
    try {
      const { data } = await getAllCountries();
      setCountries(data);
    } catch (err) {
      setError(err.response?.data?.message || 'Failed to load countries');
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => { fetchAll(); }, [fetchAll]);

  const create = useCallback(async (dto) => {
    await createCountry(dto);
    await fetchAll();
  }, [fetchAll]);

  const update = useCallback(async (id, dto) => {
    await updateCountry(id, dto);
    await fetchAll();
  }, [fetchAll]);

  const remove = useCallback(async (id) => {
    await deleteCountry(id);
    await fetchAll();
  }, [fetchAll]);

  return { countries, loading, error, refetch: fetchAll, create, update, remove };
};
